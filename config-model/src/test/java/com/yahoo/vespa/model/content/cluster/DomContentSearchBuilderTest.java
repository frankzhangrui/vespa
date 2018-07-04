// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.model.content.cluster;

import com.yahoo.vespa.model.content.ContentSearch;
import com.yahoo.vespa.model.builder.xml.dom.ModelElement;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.junit.Test;

import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Simon Thoresen Hult
 */
public class DomContentSearchBuilderTest {

    @Test
    public void requireThatDefaultsAreNull() throws Exception {
        ContentSearch search = newContentSearch(
                "<content/>");
        assertNull(search.getVisibilityDelay());
        assertNull(search.getQueryTimeout());
    }

    @Test
    public void requireThatEmptySearchIsSafe() throws Exception {
        ContentSearch search = newContentSearch(
                "<content>" +
                "  <search/>" +
                "</content>");
        assertNull(search.getVisibilityDelay());
        assertNull(search.getQueryTimeout());
    }

    @Test
    public void requireThatContentSearchCanBeBuilt() throws Exception {
        ContentSearch search = newContentSearch(
                "<content>" +
                "  <search>" +
                "    <query-timeout>1.1</query-timeout>" +
                "    <visibility-delay>2.3</visibility-delay>" +
                "  </search>" +
                "</content>");
        assertEquals(1.1, search.getQueryTimeout(), 1E-6);
        assertEquals(2.3, search.getVisibilityDelay(), 1E-6);
    }

    private static ContentSearch newContentSearch(String xml) throws Exception {
        return DomContentSearchBuilder.build(
                new ModelElement(DocumentBuilderFactory.newInstance()
                                                       .newDocumentBuilder()
                                                       .parse(new CharSequenceInputStream(xml, StandardCharsets.UTF_8))
                                                       .getDocumentElement()));
    }
}
