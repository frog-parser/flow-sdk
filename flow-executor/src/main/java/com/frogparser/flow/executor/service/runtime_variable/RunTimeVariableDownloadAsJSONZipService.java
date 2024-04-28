package com.frogparser.flow.executor.service.runtime_variable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frogparser.flow.executor.domain.RunTimeVariable;
import com.frogparser.common.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class RunTimeVariableDownloadAsJSONZipService {

    public byte[] getContent(RunTimeVariable runTimeVariable) {

        Objects.requireNonNull(runTimeVariable);

        try (
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)
        ) {

            final ZipEntry mappingZipEntry = new ZipEntry("content.json");
            zipOutputStream.putNextEntry(mappingZipEntry);

            final ObjectMapper objectMapper = new ObjectMapper();

            final byte[] bytes = objectMapper.writeValueAsBytes(runTimeVariable);

            zipOutputStream.write(bytes);

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
