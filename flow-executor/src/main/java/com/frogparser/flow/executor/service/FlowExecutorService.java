package com.frogparser.flow.executor.service;

import com.frogparser.flow.domain.Flow;
import com.frogparser.flow.domain.FlowContent;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.domain.FlowExecutionId;
import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.exception.FlowExecutionException;
import com.frogparser.flow.executor.domain.web_driver_settings.WebDriverSettings;
import com.frogparser.flow.executor.domain.web_driver_settings.WebDriverTypeEnum;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.CommandExecutorService;
import com.frogparser.flow.executor.service.os.ZombieProcessKillerService;
import com.frogparser.flow.executor.service.validator.AcceptLanguageHeaderValidatorService;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class FlowExecutorService {

    private static final Logger log = LoggerFactory.getLogger(FlowExecutorService.class);

    private static final boolean IS_LINUX_OS = System.getProperty("os.name").toLowerCase().contains("linux");

    private final AcceptLanguageHeaderValidatorService languageHeaderValidatorService;
    private final CommandExecutorService commandExecutorService;
    private final WebDriverSettings webDriverSettings;
    private final ZombieProcessKillerService zombieProcessKillerService;
    private final Boolean zombieProcessKillerActive;

    public FlowExecutorService(
            AcceptLanguageHeaderValidatorService languageHeaderValidatorService,
            CommandExecutorService commandExecutorService,
            WebDriverSettings webDriverSettings,
            ZombieProcessKillerService zombieProcessKillerService,
            @Value("${frog.zombie-process-killer.active:false}")
            Boolean zombieProcessKillerActive) {
        this.languageHeaderValidatorService = languageHeaderValidatorService;
        this.commandExecutorService = commandExecutorService;
        this.webDriverSettings = webDriverSettings;
        this.zombieProcessKillerService = zombieProcessKillerService;
        this.zombieProcessKillerActive = zombieProcessKillerActive;
    }

    public void execute(FlowExecutionSettings flowExecutionSettings,
                        FlowExecutionId flowExecutionId,
                        Supplier<Boolean> flowExecutionRunning,
                        Flow flow) throws FlowExecutionException {

        Objects.requireNonNull(flow);

        final FlowContext flowContext = createFlowContext(flowExecutionId);

        flowContext.setExecutionStartTime(LocalDateTime.now());

        flowContext.setCommandExecutionsInFlow(0L);
        flowContext.setRecursionDepth(0L);

        WebDriver webDriver = null;

        try {

            webDriver = getWebDriver(flow);

            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(webDriverSettings.getTimeouts().pageLoadTimeout()));
            webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(webDriverSettings.getTimeouts().scriptTimeout()));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(webDriverSettings.getTimeouts().implicitlyWait()));
            webDriver.manage().window().setSize(new Dimension(webDriverSettings.getWindow().size().width(), webDriverSettings.getWindow().size().height()));

            flowContext.setWebDriver(webDriver);

            execute(flowExecutionSettings, flowContext, flow, flowExecutionRunning);

        } finally {

            if (Objects.nonNull(webDriver)) {

                try {
                    webDriver.close();
                } catch (Throwable t) {
                    log.info("Can not close web driver windows: '%s'".formatted(t.getMessage()), t);
                }

                try {
                    webDriver.quit();
                } catch (Throwable t) {
                    log.info("Can not quit web driver: '%s'".formatted(t.getMessage()), t);
                }

                killZombieProcesses();

            }

            flowContext.setExecutionFinishTime(LocalDateTime.now());

        }

    }

    private void killZombieProcesses() {
        if (zombieProcessKillerActive) {
            if (WebDriverTypeEnum.LOCAL_CHROME.equals(webDriverSettings.getType())) {
                if (IS_LINUX_OS) {
                    zombieProcessKillerService.killZombieProcesses();
                }
            }
        }
    }

    private void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, Flow flow, Supplier<Boolean> flowExecutionRunning) throws FlowExecutionException {

        if (Objects.isNull(flow)) {
            throw new FlowExecutionException("Flow is not defined");
        }

        Objects.requireNonNull(flowContext);

        final List<AbstractCommand> commands = Optional.of(flow)
                .map(Flow::getContent)
                .map(FlowContent::getCommands)
                .orElse(List.of())
                .stream()
                .toList();

        flowContext.setCommandsExecutionStartTime(LocalDateTime.now());

        try {

            int commandIndex = 1;

            for (AbstractCommand command : commands) {

                commandIndex++;

                try {

                    if (Objects.isNull(command)) {
                        throw new FlowExecutionException("Command with index %d is not defined".formatted(commandIndex));
                    }

                    final boolean enabled = Objects.isNull(command.getEnabled()) || command.getEnabled();

                    if (enabled) {

                        if (flowExecutionRunning.get()) {

                            try {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                                commandExecutorService.execute(flowExecutionSettings, flowContext, command);
                            } finally {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                            }

                        }

                    }

                } catch (Exception t) {

                    final String errorMessage = "Can not execute command(index: %d, name: '%s'), flow(id: %d, name: '%s'), message: '%s'"
                            .formatted(commandIndex, command.getName(), flow.getId(), flow.getName(), t.getMessage());

                    log.debug(errorMessage);

                    throw new FlowExecutionException(errorMessage, t);
                }

            }

        } finally {
            flowContext.setCommandsExecutionFinishTime(LocalDateTime.now());
        }

    }

    private FlowContext createFlowContext(FlowExecutionId flowExecutionId) {
        final FlowContext flowContext = new FlowContext();
        flowContext.setVariables(new LinkedHashMap<>());
        flowContext.setFlowExecutionId(flowExecutionId);
        return flowContext;
    }

    private WebDriver getWebDriver(Flow flow) throws FlowExecutionException {
        return switch (webDriverSettings.getType()) {
            case LOCAL_CHROME -> getLocalChromeWebDriver(flow);
            case REMOTE_CHROME -> getRemoteChromeWebDriver(flow);
            case null -> throw new UnsupportedOperationException("Unknown web driver type");
        };
    }

    private WebDriver getLocalChromeWebDriver(Flow flow) throws FlowExecutionException {
        final ChromeDriverService service = new ChromeDriverService
                .Builder()
                .usingDriverExecutable(webDriverSettings.getLocalChrome().getDriverExecutablePath().toFile())
                .build();
        final ChromeOptions chromeOptions = getCapabilities(flow);
        return new ChromeDriver(service, chromeOptions);
    }

    private WebDriver getRemoteChromeWebDriver(Flow flow) throws FlowExecutionException {
        return new RemoteWebDriver(webDriverSettings.getRemoteChrome().getUrl(), getCapabilities(flow));
    }

    private ChromeOptions getCapabilities(Flow flow) throws FlowExecutionException {
        final ChromeOptions chromeOptions = new ChromeOptions();

        final Optional<String> acceptLanguageHeaderValue = Optional.ofNullable(flow).map(Flow::getContent).map(FlowContent::getAcceptLanguage).map(String::trim).filter(Predicate.not(String::isBlank));

        if (acceptLanguageHeaderValue.isPresent()) {

            if (!languageHeaderValidatorService.isValidAcceptLanguageHeaderValue(acceptLanguageHeaderValue.get())) {
                throw new FlowExecutionException("Invalid Accept-Language header value.");
            }

            chromeOptions.addArguments("--lang=%s".formatted(acceptLanguageHeaderValue.get()));
        }

        if (WebDriverTypeEnum.LOCAL_CHROME.equals(webDriverSettings.getType())) {
            chromeOptions.setBinary(webDriverSettings.getLocalChrome().getExecutablePath().toFile());
        }

        switch (webDriverSettings.getType()) {
            case LOCAL_CHROME -> webDriverSettings.getLocalChrome().getArguments().forEach(chromeOptions::addArguments);
            case REMOTE_CHROME -> webDriverSettings.getRemoteChrome().getArguments().forEach(chromeOptions::addArguments);
            default -> throw new UnsupportedOperationException("Web driver arguments logic is not implemented for '%s'".formatted(webDriverSettings.getType()));
        }

        return chromeOptions;
    }

}
