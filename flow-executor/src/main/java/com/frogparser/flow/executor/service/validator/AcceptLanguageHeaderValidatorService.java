package com.frogparser.flow.executor.service.validator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class AcceptLanguageHeaderValidatorService {

    private final static String LANGUAGE_TAG_RE = "(?:[a-zA-Z]{2}(?:-[a-zA-Z]{2,3})?|\\*)";
    private final static String QUALITY_FACTOR_RE = "(?:;q=(?:0(?:\\.\\d{0,3})?|1(?:\\.0{0,3})?))?";
    private final static String LANGUAGE_TAG_WITH_QUALITY_FACTOR_RE = LANGUAGE_TAG_RE + QUALITY_FACTOR_RE;
    private final static String ACCEPT_LANGUAGE_RE = "^" + LANGUAGE_TAG_WITH_QUALITY_FACTOR_RE + "(?:,\\s?" + LANGUAGE_TAG_WITH_QUALITY_FACTOR_RE + ")*$";

    private static final Pattern ACCEPT_LANGUAGE_PATTERN = Pattern.compile(ACCEPT_LANGUAGE_RE);

    public boolean isValidAcceptLanguageHeaderValue(String acceptLanguageHeaderValue) {
        return Objects.nonNull(acceptLanguageHeaderValue)
                && !acceptLanguageHeaderValue.isBlank()
                && isLengthValid(acceptLanguageHeaderValue)
                && ACCEPT_LANGUAGE_PATTERN.matcher(acceptLanguageHeaderValue).matches()
                && Arrays.stream(acceptLanguageHeaderValue.split(",\\s?"))
                .allMatch(this::validateLanguageTag);
    }

    private Boolean validateLanguageTag(String languageTag) {
        final String[] parts = languageTag.split(";")[0].split("-");
        final String language = parts[0];
        final boolean validLanguage = "*".equals(language) || isValidLanguageCode(language);
        return validLanguage && isValidCountry(parts);
    }

    private boolean isValidCountry(String[] parts) {
        return parts.length <= 1 || isValidCountryCode(parts[1]);
    }

    private boolean isValidLanguageCode(String language) {
        return language.matches("[a-zA-Z]{2}");
    }

    private boolean isValidCountryCode(String country) {
        return country.matches("[a-zA-Z]{2,3}");
    }

    private boolean isLengthValid(String acceptLanguageHeaderValue) {
        final int length = Objects.nonNull(acceptLanguageHeaderValue) ? acceptLanguageHeaderValue.length() : 0;
        return length >= 1 && length <= 60;
    }

}
