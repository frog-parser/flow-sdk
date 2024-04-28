package com.frogparser.flow.executor.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class WebElementXpathExtractorService {

    private final JSoupElementXPathExtractorService jsoupElementXPathExtractorService;

    public WebElementXpathExtractorService(final JSoupElementXPathExtractorService jsoupElementXPathExtractorService) {
        this.jsoupElementXPathExtractorService = jsoupElementXPathExtractorService;
    }

    public String generateXPathByWebElement(final WebElement webElement) {
        String elementHtml = webElement.getAttribute("outerHTML");
        Document document = Jsoup.parseBodyFragment(elementHtml);
        Element element = document.body().child(0);
        return jsoupElementXPathExtractorService.generateXPath(element);
    }

}
