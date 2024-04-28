package com.frogparser.flow.executor.service.runtime_variable;

import com.frogparser.flow.executor.domain.RunTimeVariable;
import com.frogparser.common.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class RunTimeVariableDownloadAsCSVZipService {

    private final RunTimeVariableConvertToCSVService runTimeVariableConvertToCSVService;

    public RunTimeVariableDownloadAsCSVZipService(RunTimeVariableConvertToCSVService runTimeVariableConvertToCSVService) {
        this.runTimeVariableConvertToCSVService = runTimeVariableConvertToCSVService;
    }

    public byte[] getContent(RunTimeVariable runTimeVariable) {

        Objects.requireNonNull(runTimeVariable);
        final byte[] content = runTimeVariableConvertToCSVService.convert(runTimeVariable.getBody());

        try (
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)
        ) {

            final ZipEntry mappingZipEntry = new ZipEntry("content.csv");
            zipOutputStream.putNextEntry(mappingZipEntry);
            zipOutputStream.write(content);

            zipOutputStream.finish();

            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new ApplicationException(
                    String.format(
                            "Cannot export variable in CSV format: %s", e.getMessage()),
                    e
            );
        }

    }

}
