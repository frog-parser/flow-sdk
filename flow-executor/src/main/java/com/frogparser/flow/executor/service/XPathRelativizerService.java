package com.frogparser.flow.executor.service;

import com.frogparser.flow.exception.CommandExecutionException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class XPathRelativizerService {

    public String getRelativeXPath(final String parentXPath, final String xpath) throws CommandExecutionException {

        if (Objects.isNull(parentXPath) || parentXPath.isBlank()) {
            throw new CommandExecutionException("Parent XPath can not be null or blank.");
        }

        final List<String> parentSteps = splitXPath(parentXPath);
        final List<String> childSteps = splitXPath(xpath);

        if (isChildPath(parentSteps, childSteps)) {
            return "." + IntStream.range(parentSteps.size(), childSteps.size())
                    .mapToObj(i -> "/" + childSteps.get(i))
                    .collect(Collectors.joining());
        } else {
            throw new CommandExecutionException("The given xpath is not a child of the parentXPath.");
        }
    }

    private List<String> splitXPath(final String xpath) throws CommandExecutionException {

        if (Objects.isNull(xpath) || xpath.isBlank()) {
            throw new CommandExecutionException("XPath cannot be null or blank.");
        }

        return Arrays.stream(xpath.split("/"))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
    }

    private boolean isChildPath(final List<String> parentSteps, final List<String> childSteps) {
        return parentSteps.size() < childSteps.size() &&
                IntStream.range(0, parentSteps.size())
                        .allMatch(i -> Objects.equals(parentSteps.get(i), childSteps.get(i)));
    }

}
