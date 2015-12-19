/*
 * Copyright (c) 2002-2015 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript.host.html;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

/**
 * Unit tests for {@link HTMLUListElement}.
 *
 * @author Daniel Gredler
 * @author Marc Guillemot
 * @author Ronald Brill
 * @author Frank Danek
 */
@RunWith(BrowserRunner.class)
public class HTMLUListElementTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("[object HTMLUListElement]")
    public void simpleScriptable() throws Exception {
        final String html = "<html><head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    alert(document.getElementById('myId'));\n"
            + "  }\n"
            + "</script>\n"
            + "</head><body onload='test()'>\n"
            + "  <ul id='myId'/>\n"
            + "</body></html>";

        final WebDriver driver = loadPageWithAlerts2(html);
        if (driver instanceof HtmlUnitDriver) {
            final WebElement element = driver.findElement(By.id("myId"));
            assertTrue(toHtmlElement(element) instanceof HtmlUnorderedList);
        }
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Alerts({ "false", "true", "true", "true", "null", "", "blah", "2",
                   "true", "false", "true", "false", "", "null", "", "null" })
    public void compact() throws Exception {
        final String html =
            "<html>\n"
            + "  <head>\n"
            + "    <script>\n"
            + "      function test() {\n"
            + "        alert(document.getElementById('u1').compact);\n"
            + "        alert(document.getElementById('u2').compact);\n"
            + "        alert(document.getElementById('u3').compact);\n"
            + "        alert(document.getElementById('u4').compact);\n"
            + "        alert(document.getElementById('u1').getAttribute('compact'));\n"
            + "        alert(document.getElementById('u2').getAttribute('compact'));\n"
            + "        alert(document.getElementById('u3').getAttribute('compact'));\n"
            + "        alert(document.getElementById('u4').getAttribute('compact'));\n"

            + "        document.getElementById('u1').compact = true;\n"
            + "        document.getElementById('u2').compact = false;\n"
            + "        document.getElementById('u3').compact = 'xyz';\n"
            + "        document.getElementById('u4').compact = null;\n"
            + "        alert(document.getElementById('u1').compact);\n"
            + "        alert(document.getElementById('u2').compact);\n"
            + "        alert(document.getElementById('u3').compact);\n"
            + "        alert(document.getElementById('u4').compact);\n"
            + "        alert(document.getElementById('u1').getAttribute('compact'));\n"
            + "        alert(document.getElementById('u2').getAttribute('compact'));\n"
            + "        alert(document.getElementById('u3').getAttribute('compact'));\n"
            + "        alert(document.getElementById('u4').getAttribute('compact'));\n"
            + "      }\n"
            + "    </script>\n"
            + "  </head>\n"
            + "  <body onload='test()'>\n"
            + "    <ul id='u1'><li>a</li><li>b</li></ul>\n"
            + "    <ul compact='' id='u2'><li>a</li><li>b</li></ul>\n"
            + "    <ul compact='blah' id='u3'><li>a</li><li>b</li></ul>\n"
            + "    <ul compact='2' id='u4'><li>a</li><li>b</li></ul>\n"
            + "  </body>\n"
            + "</html>";
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Alerts(DEFAULT = { "", "", "blah", "A", "null", "", "blah", "A", "1", "a", "A", "i", "I", "u" },
            IE11 = { "", "", "", "A", "null", "", "blah", "A", "1", "a", "A", "i", "I", "exception", "I" })
    public void type() throws Exception {
        final String html =
                "<html>\n"
                + "  <head>\n"
                + "    <script>\n"
                + "      function test() {\n"
                + "        alert(document.getElementById('u1').type);\n"
                + "        alert(document.getElementById('u2').type);\n"
                + "        alert(document.getElementById('u3').type);\n"
                + "        alert(document.getElementById('u4').type);\n"
                + "        alert(document.getElementById('u1').getAttribute('type'));\n"
                + "        alert(document.getElementById('u2').getAttribute('type'));\n"
                + "        alert(document.getElementById('u3').getAttribute('type'));\n"
                + "        alert(document.getElementById('u4').getAttribute('type'));\n"

                + "        document.getElementById('u1').type = '1';\n"
                + "        alert(document.getElementById('u1').type);\n"

                + "        document.getElementById('u1').type = 'a';\n"
                + "        alert(document.getElementById('u1').type);\n"

                + "        document.getElementById('u1').type = 'A';\n"
                + "        alert(document.getElementById('u1').type);\n"

                + "        document.getElementById('u1').type = 'i';\n"
                + "        alert(document.getElementById('u1').type);\n"

                + "        document.getElementById('u1').type = 'I';\n"
                + "        alert(document.getElementById('u1').type);\n"

                + "        try { document.getElementById('u1').type = 'u' } catch(e) {alert('exception');};\n"
                + "        alert(document.getElementById('u1').type);\n"
                + "      }\n"
                + "    </script>\n"
                + "  </head>\n"
                + "  <body onload='test()'>\n"
                + "    <ul id='u1'><li>a</li><li>b</li></ul>\n"
                + "    <ul type='' id='u2'><li>a</li><li>b</li></ul>\n"
                + "    <ul type='blah' id='u3'><li>a</li><li>b</li></ul>\n"
                + "    <ul type='A' id='u4'><li>a</li><li>b</li></ul>\n"
                + "  </body>\n"
                + "</html>";
        loadPageWithAlerts2(html);
    }
}
