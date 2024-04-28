package com.frogparser.flow.domain.element_property;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ElementPropertyAccessibleName.class, name = "ElementPropertyAccessibleName"),
        @JsonSubTypes.Type(value = ElementPropertyAriaRole.class, name = "ElementPropertyAriaRole"),
        @JsonSubTypes.Type(value = ElementPropertyAttribute.class, name = "ElementPropertyAttribute"),
        @JsonSubTypes.Type(value = ElementPropertyCssValue.class, name = "ElementPropertyCssValue"),
        @JsonSubTypes.Type(value = ElementPropertyDisplayed.class, name = "ElementPropertyDisplayed"),
        @JsonSubTypes.Type(value = ElementPropertyDomAttribute.class, name = "ElementPropertyDomAttribute"),
        @JsonSubTypes.Type(value = ElementPropertyDomProperty.class, name = "ElementPropertyDomProperty"),
        @JsonSubTypes.Type(value = ElementPropertyEnabled.class, name = "ElementPropertyEnabled"),
        @JsonSubTypes.Type(value = ElementPropertyLocation.class, name = "ElementPropertyLocation"),
        @JsonSubTypes.Type(value = ElementPropertyRectangle.class, name = "ElementPropertyRectangle"),
        @JsonSubTypes.Type(value = ElementPropertySelected.class, name = "ElementPropertySelected"),
        @JsonSubTypes.Type(value = ElementPropertyShadowRoot.class, name = "ElementPropertyShadowRoot"),
        @JsonSubTypes.Type(value = ElementPropertySize.class, name = "ElementPropertySize"),
        @JsonSubTypes.Type(value = ElementPropertyTagName.class, name = "ElementPropertyTagName"),
        @JsonSubTypes.Type(value = ElementPropertyText.class, name = "ElementPropertyText")
})
public abstract class AbstractElementProperty {

    public AbstractElementProperty() {
    }
}
