package com.frogparser.flow.executor.service.command.read_property;

import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.domain.command.read_property.ReadPropertyCommand;
import com.frogparser.flow.domain.element_property.*;
import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ReadPropertyCommandExecutorService extends AbstractCommandExecutorService<ReadPropertyCommand> {

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, ReadPropertyCommand command) throws CommandExecutionException {

        final var variables = getContextVariables(flowContext);

        String newVariableName = getOptionalVariableName(command::getNewVariable)
                .orElseThrow(() -> new CommandExecutionException("New variable name can not be empty."));

        final WebElement webElement = getContextVariableValue(flowContext, command.getVariable(), WebElement.class);

        var property = Optional.of(command).map(ReadPropertyCommand::getProperty)
                .orElseThrow(() -> new CommandExecutionException("Property cannot be empty."));

        switch (property) {
            case ElementPropertyAccessibleName _ -> variables.put(newVariableName, new StringRunTimeVariable(webElement.getAccessibleName()));
            case ElementPropertyAriaRole _ -> variables.put(newVariableName, new StringRunTimeVariable(webElement.getAriaRole()));
            case ElementPropertyAttribute attribute -> {

                final String attributeName = attribute.getName();
                if (Objects.nonNull(attributeName) && !attributeName.isBlank()) {
                    variables.put(newVariableName, new StringRunTimeVariable(webElement.getAttribute(attributeName)));
                } else {
                    throw new CommandExecutionException("Attribute name can not be empty.");
                }
            }
            case ElementPropertyCssValue cssValue -> {

                final String cssPropertyName = cssValue.getPropertyName();

                if (Objects.nonNull(cssPropertyName) && !cssPropertyName.isBlank()) {
                    variables.put(newVariableName, new StringRunTimeVariable(webElement.getCssValue(cssPropertyName)));
                } else {
                    throw new CommandExecutionException("CSS property name can not be empty.");
                }
            }
            case ElementPropertyDisplayed _ -> variables.put(newVariableName, new BooleanRunTimeVariable(webElement.isDisplayed()));
            case ElementPropertyDomAttribute domAttribute -> {

                final String domAttributeName = domAttribute.getName();

                if (Objects.nonNull(domAttributeName) && !domAttributeName.isBlank()) {
                    variables.put(newVariableName, new StringRunTimeVariable(webElement.getDomAttribute(domAttributeName)));
                } else {
                    throw new CommandExecutionException("DOM attribute name can not be empty.");
                }
            }
            case ElementPropertyDomProperty domProperty -> {

                final String domPropertyName = domProperty.getName();

                if (Objects.nonNull(domPropertyName) && !domPropertyName.isBlank()) {
                    variables.put(newVariableName, new StringRunTimeVariable(webElement.getDomProperty(domPropertyName)));
                } else {
                    throw new CommandExecutionException("DOM property name can not be empty.");
                }
            }
            case ElementPropertyEnabled _ -> variables.put(newVariableName, new BooleanRunTimeVariable(webElement.isEnabled()));
            case ElementPropertyLocation _ -> variables.put(newVariableName, new LocationRunTimeVariable(webElement.getLocation()));
            case ElementPropertyRectangle _ -> variables.put(newVariableName, new RectangleRunTimeVariable(webElement.getRect()));
            case ElementPropertyShadowRoot _ -> variables.put(newVariableName, new SearchContextRunTimeVariable(webElement.getShadowRoot()));
            case ElementPropertySelected _ -> variables.put(newVariableName, new BooleanRunTimeVariable(webElement.isSelected()));
            case ElementPropertySize _ -> variables.put(newVariableName, new DimensionRunTimeVariable(webElement.getSize()));
            case ElementPropertyTagName _ -> variables.put(newVariableName, new StringRunTimeVariable(webElement.getTagName()));
            case ElementPropertyText _ -> variables.put(newVariableName, new StringRunTimeVariable(webElement.getText()));
            case null, default -> throw new UnsupportedOperationException("Property reader is not implemented for class '%s'"
                    .formatted(Optional.ofNullable(property).map(Object::getClass).map(Class::getName).orElse("Unknown")));
        }

    }

}
