/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.sun.syndication.io.impl;

import com.sun.syndication.feed.module.SyModule;
import com.sun.syndication.feed.module.ModuleI;
import com.sun.syndication.feed.module.SyModuleI;
import com.sun.syndication.io.ModuleGenerator;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.HashMap;
import java.util.Map;

/**
 * Feed Generator for SY Module
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class SyModuleGenerator implements ModuleGenerator {

    private static final String SY_URI  = "http://purl.org/rss/1.0/modules/syndication/";
    private static final Namespace SY_NS  = Namespace.getNamespace("sy", SY_URI);

    public String getNamespaceUri() {
        return SY_URI;
    }

    public void generate(ModuleI module, Element element) {

        SyModuleI syModule = (SyModuleI)module;

        Element updatePeriodElement = new Element("updatePeriod", SY_NS);
        updatePeriodElement.addContent(syModule.getUpdatePeriod().toString());
        element.addContent(updatePeriodElement);

        Element updateFrequencyElement = new Element("updateFrequency", SY_NS);
        updateFrequencyElement.addContent(String.valueOf(syModule.getUpdateFrequency()));
        element.addContent(updateFrequencyElement);

        Element updateBaseElement = new Element("updateBase", SY_NS);
        updateBaseElement.addContent(DateParser.parseW3CDateTime(syModule.getUpdateBase()));
        element.addContent(updateBaseElement);
    }
}
