package com.frogparser.flow.executor.service;

import com.frogparser.flow.exception.CommandExecutionException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MultipleNodesXPathModifierService {

    public String modifyXpathForMultipleElements(final String xpathExpression) throws CommandExecutionException {

        if (Objects.isNull(xpathExpression) || xpathExpression.isBlank()) {
            throw new CommandExecutionException("Input XPath expression cannot be null or blank");
        }

        // Remove specific indices
        final Pattern indexPattern = Pattern.compile("\\[\\d+]$");
        final Matcher indexMatcher = indexPattern.matcher(xpathExpression);
        return indexMatcher.replaceAll("");

    }

}
