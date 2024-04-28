package com.frogparser.flow.executor.service.command.dataset;

import com.frogparser.flow.domain.command.dataset.CreateDatasetCommand;
import com.frogparser.flow.domain.dataset.Dataset;
import com.frogparser.flow.domain.runtime_variable.DatasetRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreateDatasetCommandExecutorService extends AbstractCommandExecutorService<CreateDatasetCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, CreateDatasetCommand command) throws CommandExecutionException {

        final String variableName = getNotBlankVariableName(command.getVariable());

        getContextVariables(flowContext).put(
                variableName,
                new DatasetRunTimeVariable(
                        new Dataset()
                                .setVariableName(variableName)
                                .setMetadata(command.getMetadata())
                                .setRows(new ArrayList<>())
                )
        );

    }
}
