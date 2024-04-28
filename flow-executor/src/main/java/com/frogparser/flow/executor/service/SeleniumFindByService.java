package com.frogparser.flow.executor.service;

import com.frogparser.flow.domain.find_by.*;
import com.frogparser.flow.exception.CommandExecutionException;
import org.openqa.selenium.By;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;

@Service
public class SeleniumFindByService {

    public By getSeleniumFindBy(AbstractFindBy findBy) throws CommandExecutionException {

        final By result;

        if (Objects.isNull(findBy)) {
            throw new CommandExecutionException("Search condition can not be empty.");
        }

        if (findBy instanceof AbstractFindByValue findByValue) {

            if (Objects.isNull(findByValue.getValue())) {
                throw new CommandExecutionException("Search condition value can not be empty.");
            }

            result = switch (findByValue) {
                case FindByAttributeName findByAttributeName -> By.name(findByAttributeName.getValue());
                case FindByClassName findByClassName -> By.className(findByClassName.getValue());
                case FindByCssSelector findByCssSelector -> By.cssSelector(findByCssSelector.getValue());
                case FindById findById -> By.id(findById.getValue());
                case FindByLinkText findByLinkText -> By.linkText(findByLinkText.getValue());
                case FindByPartialLinkText findByPartialLinkText -> By.partialLinkText(findByPartialLinkText.getValue());
                case FindByTagName findByTagName -> By.tagName(findByTagName.getValue());
                case FindByXPath findByXPath -> By.xpath(findByXPath.getValue());
                default -> throw new UnsupportedOperationException(
                        "Finder is not implemented for class '%s'".formatted(findBy.getClass().getSimpleName())
                );
            };

        } else {
            throw new UnsupportedOperationException(
                    "Finder is not implemented for class '%s'".formatted(findBy.getClass())
            );
        }

        return result;
    }

    public By getSeleniumFindBy(Supplier<AbstractFindBy> findBySupplier) throws CommandExecutionException {

        if (Objects.isNull(findBySupplier)) {
            throw new CommandExecutionException("Search condition supplier can not be empty.");
        }

        final var findBy = findBySupplier.get();

        return getSeleniumFindBy(findBy);
    }

}
