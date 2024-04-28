package com.frogparser.flow.domain.find_by;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FindByAttributeName.class, name = "FindByAttributeName"),
        @JsonSubTypes.Type(value = FindByClassName.class, name = "FindByClassName"),
        @JsonSubTypes.Type(value = FindByCssSelector.class, name = "FindByCssSelector"),
        @JsonSubTypes.Type(value = FindById.class, name = "FindById"),
        @JsonSubTypes.Type(value = FindByLinkText.class, name = "FindByLinkText"),
        @JsonSubTypes.Type(value = FindByPartialLinkText.class, name = "FindByPartialLinkText"),
        @JsonSubTypes.Type(value = FindByTagName.class, name = "FindByTagName"),
        @JsonSubTypes.Type(value = FindByXPath.class, name = "FindByXPath")
})
public abstract class AbstractFindBy {

    public AbstractFindBy() {

    }

}
