package com.frogparser.flow.executor.service.command.dataset;

import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.domain.command.dataset.CreateDatasetRowColumn;
import com.frogparser.flow.domain.command.dataset.CreateDatasetRowCommand;
import com.frogparser.flow.domain.dataset.Dataset;
import com.frogparser.flow.domain.dataset.Row;
import com.frogparser.flow.domain.dataset_metadata.DatasetMetadata;
import com.frogparser.flow.domain.dataset_metadata.DatasetMetadataColumn;
import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class CreateDatasetRowCommandExecutorService extends AbstractCommandExecutorService<CreateDatasetRowCommand> {

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, CreateDatasetRowCommand command) throws CommandExecutionException {

        final Dataset dataset = getContextVariableValue(flowContext, command.getVariable(), Dataset.class);

        final List<Row> rows = Optional.of(dataset)
                .map(Dataset::getRows)
                .orElseThrow(() -> new CommandExecutionException("Rows are not set in dataset"));

        final Long limit = requireNonNull(requireNonNull(flowExecutionSettings)
                .getMaximumRowsInDatasetLimit());

        if (rows.size() >= limit) {
            throw new CommandExecutionException("The limit of rows count in the dataset '%s' exceeded, limit: %d"
                    .formatted(command.getVariable().getName(), limit));
        }

        final Row row = new Row();

        final Map<String, AbstractRunTimeVariable<?>> rowColumns = new LinkedHashMap<>();

        row.setColumns(rowColumns);

        final List<CreateDatasetRowColumn> commandColumns = Optional.of(command)
                .map(CreateDatasetRowCommand::getColumns)
                .orElseThrow(() -> new CommandExecutionException("Columns are not set in command"));

        final Map<String, FlowVariable> commandColumnsMap = commandColumns
                .stream()
                .collect(Collectors.toMap(CreateDatasetRowColumn::getName, CreateDatasetRowColumn::getColumnVariable));

        final var datasetMetadataColumns = Optional.of(dataset)
                .map(Dataset::getMetadata)
                .map(DatasetMetadata::getColumns)
                .orElseThrow(() -> new CommandExecutionException("Columns are not set in dataset metadata"));

        for (final DatasetMetadataColumn column : datasetMetadataColumns) {

            if (Objects.isNull(column)) {
                throw new CommandExecutionException("Column is not set  in dataset metadata");
            }

            final var type = switch (column.getType()) {
                case BIG_DECIMAL -> BigDecimalRunTimeVariable.class;
                case BOOLEAN -> BooleanRunTimeVariable.class;
                case INTEGER -> IntegerRunTimeVariable.class;
                case LOCAL_DATE -> LocalDateRunTimeVariable.class;
                case LOCAL_DATE_TIME -> LocalDateTimeRunTimeVariable.class;
                case LONG -> LocalDateTimeRunTimeVariable.class;
                case STRING -> StringRunTimeVariable.class;
                case URL -> UrlRunTimeVariable.class;
            };

            final FlowVariable flowVariable = Optional.ofNullable(commandColumnsMap.get(column.getName()))
                    .orElseThrow(() -> new CommandExecutionException("Column with name '%s' is not defined in command".formatted(column.getName())));

            rowColumns.put(column.getName(), getRunTimeVariable(flowContext, flowVariable, type));

        }

        rows.add(row);

    }

}
