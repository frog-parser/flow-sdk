package com.frogparser.flow.executor.service.validator.content;

import com.frogparser.flow.domain.FlowContent;
import com.frogparser.common.exception.ValidationException;
import com.frogparser.flow.executor.service.validator.AcceptLanguageHeaderValidatorService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FlowContentValidator {

    private final AcceptLanguageHeaderValidatorService acceptLanguageHeaderValidatorService;

    public FlowContentValidator(AcceptLanguageHeaderValidatorService acceptLanguageHeaderValidatorService) {
        this.acceptLanguageHeaderValidatorService = acceptLanguageHeaderValidatorService;
    }

    public void validate(FlowContent flowContent) {
        if (Objects.isNull(flowContent)) {
            throw new ValidationException("Flow content can not be empty");
        }

        final String acceptLanguage = flowContent.getAcceptLanguage();

        if (Objects.nonNull(acceptLanguage)
                && !acceptLanguage.isEmpty()
                && !acceptLanguageHeaderValidatorService.isValidAcceptLanguageHeaderValue(acceptLanguage)) {
            throw new ValidationException("Invalid flow content language (locale)");
        }

    }

}
