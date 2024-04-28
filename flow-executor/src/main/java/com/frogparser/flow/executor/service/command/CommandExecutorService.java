package com.frogparser.flow.executor.service.command;

import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class CommandExecutorService {

    private final Map<String, AbstractCommandExecutorService<? extends AbstractCommand>> commandExecutorServiceMap;

    public CommandExecutorService(Map<String, AbstractCommandExecutorService<? extends AbstractCommand>> commandExecutorServiceMap) {
        this.commandExecutorServiceMap = commandExecutorServiceMap;
    }

    private static final Logger log = LoggerFactory.getLogger(CommandExecutorService.class);

    public void execute(FlowExecutionSettings flowExecutionSettings,
                        FlowContext flowContext,
                        AbstractCommand command) throws CommandExecutionException {

        requireNonNull(command);

        final Long flowExecutionTimeLimit = Optional.of(flowExecutionSettings)
                .map(FlowExecutionSettings::getFlowExecutionTimeLimit)
                .orElseThrow();

        final Long recursionDepthLimit = Optional.of(flowExecutionSettings)
                .map(FlowExecutionSettings::getMaximumRecursionDepthLimit)
                .orElseThrow();

        validateMaximumRecursionDepth(flowContext, recursionDepthLimit);

        final Long maximumCommandExecutionsInFlowLimit = Optional.of(flowExecutionSettings)
                .map(FlowExecutionSettings::getMaximumCommandExecutionsInFlowLimit)
                .orElseThrow();

        int attemptNumber = 0;

        while (true) {

            flowContext.setCommandExecutionsInFlow(flowContext.getCommandExecutionsInFlow() + 1);
            validateMaximumCommandExecutionsInFlow(flowContext, maximumCommandExecutionsInFlowLimit);
            validateFlowExecutionTimeLimit(flowContext, flowExecutionTimeLimit);

            try {

                final String postfix = "ExecutorService";
                final String simpleName = command.getClass().getSimpleName();
                final String unCapitalizedSimpleName = simpleName.substring(0, 1).toLowerCase().concat(simpleName.substring(1));
                final String serviceName = "%s%s".formatted(unCapitalizedSimpleName, postfix);

                final AbstractCommandExecutorService<? extends AbstractCommand> executorService = commandExecutorServiceMap.get(serviceName);

                if (Objects.nonNull(executorService)) {
                    executorService.executeAbstract(flowExecutionSettings, flowContext, command);
                } else {
                    throw new UnsupportedOperationException(
                            "Executor service is not implemented for class '%s'".formatted(command.getClass().getName())
                    );
                }

                break;

            } catch (TimeoutException | ScriptTimeoutException e) {

                log.debug(("Can not execute command due to '%s' exception, attempt number: %d," +
                                "name: '%s', message: (%s)")
                                .formatted(
                                        getWebDriverExceptionCode(e),
                                        attemptNumber,
                                        command.getName(),
                                        e.getMessage()
                                ),
                        e
                );

                final int retryCount = Optional.of(command).map(AbstractCommand::getRetryCount).orElse(1);

                if (attemptNumber >= retryCount) {

                    throw new CommandExecutionException(
                            ("Can not execute command due to '%s' exception, attempt number: %d," +
                                    "name: '%s'")
                                    .formatted(
                                            getWebDriverExceptionCode(e),
                                            attemptNumber,
                                            command.getName()
                                    ),
                            e
                    );

                } else {

                    attemptNumber++;

                }

            } catch (StaleElementReferenceException e) {

                log.debug(
                        ("Can not execute command due to '%s' exception (element no longer exists on the page)," +
                                " attempt number: %d, name: '%s', message: (%s)")
                                .formatted(
                                        getWebDriverExceptionCode(e),
                                        attemptNumber,
                                        command.getName(),
                                        e.getMessage()
                                ),
                        e
                );

                throw new CommandExecutionException(
                        "Can not execute command due to '%s' exception, attempt number: %d, name: '%s'"
                                .formatted(
                                        getWebDriverExceptionCode(e),
                                        attemptNumber,
                                        command.getName()
                                ),
                        e
                );

            } catch (WebDriverException e) {

                log.debug(
                        ("Can not execute command due to '%s' exception, attempt number: %d," +
                                " name: '%s', message: (%s)")
                                .formatted(
                                        getWebDriverExceptionCode(e),
                                        attemptNumber,
                                        command.getName(),
                                        e.getMessage()
                                ),
                        e
                );

                throw new CommandExecutionException(
                        "Can not execute command due to '%s' exception, attempt number: %d, name: '%s'"
                                .formatted(
                                        getWebDriverExceptionCode(e),
                                        attemptNumber,
                                        command.getName()
                                ),
                        e
                );

            } catch (Exception e) {

                log.info("Can not execute command, attempt number: %d, name: '%s', message: (%s)"
                                .formatted(attemptNumber, command.getName(), e.getMessage()),
                        e);

                throw new CommandExecutionException(
                        "Can not execute command, attempt number: %d, name: '%s'"
                                .formatted(attemptNumber, command.getName()),
                        e
                );

            }

        }

    }

    private void validateMaximumCommandExecutionsInFlow(FlowContext flowContext, Long maximumCommandExecutionsInFlowLimit) throws CommandExecutionException {
        final Long commandExecutionsInFlow = requireNonNull(flowContext.getCommandExecutionsInFlow());
        if (commandExecutionsInFlow > maximumCommandExecutionsInFlowLimit) {
            throw new CommandExecutionException(
                    "Command executions in flow exceeded, command executions limit: %d"
                            .formatted(maximumCommandExecutionsInFlowLimit)
            );
        }
    }

    private void validateMaximumRecursionDepth(FlowContext flowContext, Long recursionDepthLimit) throws CommandExecutionException {
        final Long recursionDepth = requireNonNull(requireNonNull(flowContext).getRecursionDepth());
        if (recursionDepth > recursionDepthLimit) {
            throw new CommandExecutionException(
                    "Command execution recursion depth in flow exceeded, recursion depth limit: %d"
                            .formatted(recursionDepthLimit)
            );
        }

    }

    private void validateFlowExecutionTimeLimit(FlowContext flowContext, Long flowExecutionTimeLimit) throws CommandExecutionException {
        Long timeLimit = requireNonNull(flowExecutionTimeLimit);

        final LocalDateTime startTime = flowContext.getCommandsExecutionStartTime();

        if (ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()) > timeLimit) {
            throw new CommandExecutionException(
                    "Commands execution time in flow exceeded, time limit: %d milliseconds".formatted(timeLimit)
            );
        }

    }

    private String getWebDriverExceptionCode(WebDriverException webDriverException) {
        return webDriverException.getClass().getSimpleName();
    }

}
