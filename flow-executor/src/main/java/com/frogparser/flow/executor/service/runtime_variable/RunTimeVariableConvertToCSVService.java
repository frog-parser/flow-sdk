package com.frogparser.flow.executor.service.runtime_variable;

import com.frogparser.flow.domain.dataset.Dataset;
import com.frogparser.flow.domain.dataset.Row;
import com.frogparser.flow.domain.dataset_metadata.DatasetMetadata;
import com.frogparser.flow.domain.dataset_metadata.DatasetMetadataColumn;
import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.domain.runtime_variable.list.*;
import com.frogparser.common.exception.ApplicationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

@Service
public class RunTimeVariableConvertToCSVService {

    private final static String[] HEADER_VALUE = {"value"};

    public byte[] convert(final AbstractRunTimeVariable<?> runTimeVariable) {
        Objects.requireNonNull(runTimeVariable);

        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream);
                CSVPrinter printer = new CSVPrinter(
                        printStream,
                        CSVFormat.Builder.create().setHeader(getHeaders(runTimeVariable)).build()
                )
        ) {

            printRecords((Object[] objects) -> {
                try {
                    printer.printRecord(objects);
                } catch (IOException e) {
                    throw new ApplicationException(String.format("Cannot export variable in CSV format: %s", e.getMessage()), e);
                }
            }, runTimeVariable);

            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new ApplicationException(String.format("Cannot export variable in CSV format: %s", e.getMessage()), e);
        }

    }

    private String[] getHeaders(final AbstractRunTimeVariable<?> runTimeVariable) {

        return switch (runTimeVariable) {
            case BigDecimalListRunTimeVariable _ -> HEADER_VALUE;
            case BigDecimalRunTimeVariable _ -> HEADER_VALUE;
            case BooleanListRunTimeVariable _ -> HEADER_VALUE;
            case BooleanRunTimeVariable _ -> HEADER_VALUE;
            case CustomWebElementListRunTimeVariable _ -> HEADER_VALUE;
            case CustomWebElementRunTimeVariable _ -> HEADER_VALUE;
            case DatasetRunTimeVariable datasetRunTimeVariable -> datasetRunTimeVariable.getValue().getMetadata().getColumns().stream().map(DatasetMetadataColumn::getName).toArray(String[]::new);
            case DimensionListRunTimeVariable _ -> new String[]{"width", "height"};
            case DimensionRunTimeVariable _ -> new String[]{"width", "height"};
            case IntegerListRunTimeVariable _ -> HEADER_VALUE;
            case IntegerRunTimeVariable _ -> HEADER_VALUE;
            case LocalDateListRunTimeVariable _ -> HEADER_VALUE;
            case LocalDateRunTimeVariable _ -> HEADER_VALUE;
            case LocalDateTimeListRunTimeVariable _ -> HEADER_VALUE;
            case LocalDateTimeRunTimeVariable _ -> HEADER_VALUE;
            case LocationListRunTimeVariable _ -> new String[]{"x", "y"};
            case LocationRunTimeVariable _ -> new String[]{"x", "y"};
            case LongListRunTimeVariable _ -> HEADER_VALUE;
            case LongRunTimeVariable _ -> HEADER_VALUE;
            case RectangleListRunTimeVariable _ -> new String[]{"x", "y", "height", "width"};
            case RectangleRunTimeVariable _ -> new String[]{"x", "y", "height", "width"};
            case StringListRunTimeVariable _ -> HEADER_VALUE;
            case StringRunTimeVariable _ -> HEADER_VALUE;
            case UrlListRunTimeVariable _ -> HEADER_VALUE;
            case UrlRunTimeVariable _ -> HEADER_VALUE;
            case null, default -> throw new ApplicationException("Unsupported variable type: '%s'".formatted(runTimeVariable.getClass().getSimpleName()));
        };
    }

    private void printRecords(final Consumer<Object[]> printer, final AbstractRunTimeVariable<?> runTimeVariable) {
        switch (runTimeVariable) {
            case BigDecimalListRunTimeVariable bigDecimalListRunTimeVariable -> bigDecimalListRunTimeVariable.getValue().forEach(value -> printer.accept(new BigDecimal[]{value}));
            case BigDecimalRunTimeVariable bigDecimalRunTimeVariable -> printer.accept(new BigDecimal[]{bigDecimalRunTimeVariable.getValue()});
            case BooleanListRunTimeVariable booleanListRunTimeVariable -> booleanListRunTimeVariable.getValue().forEach(value -> printer.accept(new Boolean[]{value}));
            case BooleanRunTimeVariable booleanRunTimeVariable -> printer.accept(new Boolean[]{booleanRunTimeVariable.getValue()});
            case CustomWebElementListRunTimeVariable _ -> willBeImplementedInFuture();
            case CustomWebElementRunTimeVariable _ -> willBeImplementedInFuture();
            case DatasetRunTimeVariable datasetRunTimeVariable -> {

                final Dataset value = datasetRunTimeVariable.getValue();

                final List<DatasetMetadataColumn> columns = Optional.ofNullable(value).map(Dataset::getMetadata).map(DatasetMetadata::getColumns).orElse(List.of());
                final List<Row> rows = Optional.ofNullable(value).map(Dataset::getRows).orElse(List.of());

                columns.forEach(datasetMetadataColumn -> {
                    if (Objects.isNull(datasetMetadataColumn)) {
                        throw new ApplicationException("Dataset column can not be empty");
                    }

                    final String columnName = datasetMetadataColumn.getName();

                    if (Objects.isNull(columnName) || columnName.isBlank()) {
                        throw new ApplicationException("Dataset column can not be blank");
                    }
                });

                rows.forEach(row -> {

                    Map<String, AbstractRunTimeVariable<?>> rowColumn = row.getColumns();

                    List<Object> objects = new ArrayList<>();

                    columns.forEach(column -> {

                        final AbstractRunTimeVariable<?> rtv = rowColumn.get(column.getName());

                        if (Objects.isNull(rtv)) {
                            throw new ApplicationException("Dataset cell value can not be empty, column name: '%s'".formatted(column));
                        }

                        switch (rtv) {
                            case BigDecimalRunTimeVariable bigDecimalRunTimeVariable -> objects.add(bigDecimalRunTimeVariable.getValue());
                            case BooleanRunTimeVariable booleanRunTimeVariable -> objects.add(booleanRunTimeVariable.getValue());
                            case IntegerRunTimeVariable integerRunTimeVariable -> objects.add(integerRunTimeVariable.getValue());
                            case LocalDateRunTimeVariable localDateRunTimeVariable -> {

                                final LocalDate localDate = localDateRunTimeVariable.getValue();
                                objects.add(Objects.nonNull(localDate) ? localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "");
                            }
                            case LocalDateTimeRunTimeVariable localDateTimeRunTimeVariable -> {

                                final LocalDateTime localDateTime = localDateTimeRunTimeVariable.getValue();
                                objects.add(Objects.nonNull(localDateTime) ? localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")) : "");
                            }
                            case LongRunTimeVariable longRunTimeVariable -> objects.add(longRunTimeVariable.getValue());
                            case StringRunTimeVariable stringRunTimeVariable -> objects.add(stringRunTimeVariable.getValue());
                            case UrlRunTimeVariable urlRunTimeVariable -> {

                                final URL url = urlRunTimeVariable.getValue();
                                objects.add(Objects.nonNull(url) ? url.toString() : "");
                            }
                            default -> throw new ApplicationException("Unsupported variable type: '%s'".formatted(rtv.getClass().getSimpleName()));
                        }

                    });

                    printer.accept(objects.toArray());

                });

            }
            case DimensionListRunTimeVariable dimensionListRunTimeVariable -> dimensionListRunTimeVariable.getValue().forEach(value -> printer.accept(new Integer[]{value.width, value.height}));
            case DimensionRunTimeVariable dimensionRunTimeVariable -> {
                final Dimension dimension = dimensionRunTimeVariable.getValue();
                printer.accept(Objects.nonNull(dimension) ? new Integer[]{dimension.width, dimension.height} : new Integer[]{null, null});
            }
            case IntegerListRunTimeVariable integerListRunTimeVariable -> integerListRunTimeVariable.getValue().forEach(value -> printer.accept(new Integer[]{value}));
            case IntegerRunTimeVariable integerRunTimeVariable -> printer.accept(new Integer[]{integerRunTimeVariable.getValue()});
            case LocalDateListRunTimeVariable localDateListRunTimeVariable -> localDateListRunTimeVariable.getValue().forEach(value -> printer.accept(new String[]{Objects.nonNull(value) ? value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}));
            case LocalDateRunTimeVariable localDateRunTimeVariable -> {
                final LocalDate value = localDateRunTimeVariable.getValue();
                printer.accept(new String[]{Objects.nonNull(value) ? value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""});
            }
            case LocalDateTimeListRunTimeVariable localDateTimeListRunTimeVariable -> localDateTimeListRunTimeVariable.getValue().forEach(value -> printer.accept(new String[]{Objects.nonNull(value) ? value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")) : ""}));
            case LocalDateTimeRunTimeVariable localDateTimeRunTimeVariable -> {
                final LocalDateTime value = localDateTimeRunTimeVariable.getValue();
                printer.accept(new String[]{Objects.nonNull(value) ? value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")) : ""});
            }
            case LocationListRunTimeVariable locationListRunTimeVariable -> locationListRunTimeVariable.getValue().forEach(value -> printer.accept(Objects.nonNull(value) ? new Integer[]{value.x, value.y} : new Integer[]{null, null}));
            case LocationRunTimeVariable locationRunTimeVariable -> {
                final Point value = locationRunTimeVariable.getValue();
                printer.accept(Objects.nonNull(value) ? new Integer[]{value.x, value.y} : new Integer[]{null, null});
            }
            case LongListRunTimeVariable longListRunTimeVariable -> longListRunTimeVariable.getValue().forEach(value -> printer.accept(new Long[]{value}));
            case LongRunTimeVariable longRunTimeVariable -> printer.accept(new Long[]{longRunTimeVariable.getValue()});
            case RectangleListRunTimeVariable rectangleListRunTimeVariable -> rectangleListRunTimeVariable.getValue().forEach(value -> printer.accept(new Integer[]{value.x, value.y, value.height, value.width}));
            case RectangleRunTimeVariable rectangleRunTimeVariable -> {
                final Rectangle value = rectangleRunTimeVariable.getValue();
                printer.accept(new Integer[]{value.x, value.y, value.height, value.width});
            }
            case StringListRunTimeVariable stringListRunTimeVariable -> stringListRunTimeVariable.getValue().forEach(value -> printer.accept(new String[]{value}));
            case StringRunTimeVariable stringRunTimeVariable -> printer.accept(new String[]{stringRunTimeVariable.getValue()});
            case UrlListRunTimeVariable urlListRunTimeVariable -> urlListRunTimeVariable.getValue().forEach(value -> printer.accept(new String[]{Objects.nonNull(value) ? value.toString() : ""}));
            case UrlRunTimeVariable urlRunTimeVariable -> {
                final URL value = urlRunTimeVariable.getValue();
                printer.accept(new String[]{Objects.nonNull(value) ? value.toString() : ""});
            }
            case null, default -> throw new ApplicationException("Unsupported variable type: '%s'".formatted(runTimeVariable.getClass().getSimpleName()));
        }
    }

    private void willBeImplementedInFuture() {

    }

}
