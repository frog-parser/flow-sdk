package com.frogparser.flow.executor.service.os;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class ZombieProcessKillerService {

    private static final Logger log = LoggerFactory.getLogger(ZombieProcessKillerService.class);

    final static String PROC_DIRECTORY_PATH = "/proc";

    @Async("zombieProcessKillerDelayedExecutor")
    public void killZombieProcesses() {
        if (Files.exists(Paths.get(PROC_DIRECTORY_PATH))) {
            ProcessHandle.allProcesses()
                    .filter(ZombieProcessKillerService::isZombieProcess)
                    .peek(processHandle -> log.error("Zombie process found with PID %d, user: '%s', commandLine: '%s'".formatted(processHandle.pid(),
                            processHandle.info().user(),
                            processHandle.info().commandLine())))
                    .forEach(ZombieProcessKillerService::killProcess);
        } else {
            final String osName = System.getProperty("os.name");
            log.error(String.format("Can not list zombie processes. '%s' directory does not exist. Operation system: '%s'", PROC_DIRECTORY_PATH, osName));
        }

    }

    private static boolean isZombieProcess(ProcessHandle processHandle) {
        final Path statusFilePath = Path.of(PROC_DIRECTORY_PATH, String.valueOf(processHandle.pid()), "status");

        try (Stream<String> stringStream = Files.lines(statusFilePath)) {
            return stringStream
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .anyMatch(line -> line.startsWith("state:") && line.contains("zombie"));
        } catch (Throwable t) {
            log.debug("Error reading process status file, process with PID %d : '%s'".formatted(processHandle.pid(), t.getMessage()), t);
        }

        return false;
    }

    private static void killProcess(ProcessHandle processHandle) {
        try {
            final boolean success = processHandle.destroyForcibly();

            if (success) {
                log.debug("Zombie process with PID %d killed.".formatted(processHandle.pid()));
            } else {
                log.debug("Zombie process with PID %d kill request has been rejected by operation system.".formatted(processHandle.pid()));
            }

        } catch (Throwable t) {
            log.debug("Error occurred while killing process with PID %d : '%s'".formatted(processHandle.pid(), t.getMessage()), t);
        }

    }

}