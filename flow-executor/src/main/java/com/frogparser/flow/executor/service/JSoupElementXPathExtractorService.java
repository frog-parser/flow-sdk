package com.frogparser.flow.executor.service;

import com.frogparser.common.exception.ApplicationException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * A service class that extracts the XPath of a given JSoup Element.
 */
@Service
public class JSoupElementXPathExtractorService {

    /**
     * Generates an XPath for the given JSoup Element.
     *
     * @param element The JSoup Element for which to generate the XPath.
     * @return The generated XPath as a String.
     * @throws IllegalArgumentException if the input element is null.
     */
    public String generateXPath(final Element element) {
        if (element == null) {
            throw new ApplicationException("Element cannot be null.");
        }

        final String tagName = element.tagName().toLowerCase();
        final Element parent = element.parent();

        if (parent == null) {
            return "/" + tagName;
        }

        final Elements siblings = parent.children();
        int position = 1;

        for (Element sibling : siblings) {
            if (sibling.equals(element)) {
                break;
            }
            if (sibling.tagName().equalsIgnoreCase(tagName)) {
                position++;
            }
        }

        return generateXPath(parent) + "/" + tagName + "[" + position + "]";
    }
}