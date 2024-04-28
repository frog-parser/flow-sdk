package com.frogparser.flow.executor.service.validator.xpath;

import com.frogparser.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class XPathValidator {

    public void validate(String xpathAsString) {

        if (Optional.ofNullable(xpathAsString).filter(Predicate.not(String::isBlank)).isEmpty()) {
            throw new ValidationException("XPath expression can not be empty");
        }

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        try {
            XPathExpression expr = xpath.compile(xpathAsString);
        } catch (XPathExpressionException e) {
            throw new ValidationException("Invalid XPath expression: '%s'".formatted(e.getMessage()), e);
        }

    }

    public boolean isValid(String xpathAsString) {

        boolean result;

        if (Optional.ofNullable(xpathAsString).filter(Predicate.not(String::isBlank)).isEmpty()) {
            result = false;
        } else {

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            try {
                XPathExpression expr = xpath.compile(xpathAsString);
                result = true;
            } catch (XPathExpressionException e) {
                result = false;
            }

        }

        return result;

    }


}
