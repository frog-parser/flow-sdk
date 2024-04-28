package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.domain.command.list.AddListItemCommand;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class AddListItemCommandExecutorService extends AbstractCommandExecutorService<AddListItemCommand> {

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, AddListItemCommand command) throws CommandExecutionException {

        final List<Object> list = getList(flowContext, command);

        final Class<?> genericParameterClass = GenericTypeResolver.resolveTypeArgument(list.getClass(), List.class);

        final Object item = getRunTimeVariable(flowContext, command.getItemVariable()).getValue();

        if (genericParameterClass == item.getClass()) {

            final Long limit = requireNonNull(requireNonNull(flowExecutionSettings)
                    .getMaximumRowsInListLimit());

            if (list.size() >= limit) {
                throw new CommandExecutionException("The limit of items count in the list '%s' exceeded, limit: %d"
                        .formatted(command.getItemVariable().getName(), limit));
            }

            list.add(item);
        } else {
            throw new CommandExecutionException("Item and list item type are not compatible");
        }

    }

    @SuppressWarnings("unchecked")
    private List<Object> getList(FlowContext flowContext, AddListItemCommand command) throws CommandExecutionException {
        return getContextVariableValue(flowContext, command.getVariable(), List.class);
    }
}
