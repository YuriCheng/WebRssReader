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
import com.sun.syndication.io.ModuleParser;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class SyModuleParser implements ModuleParser {

    public String getNamespaceUri() {
        return SyModuleI.URI;
    }

    private Namespace getDCNamespace() {
        return Namespace.getNamespace(SyModuleI.URI);
    }

    private static final Map PERIODS = new HashMap();

    static {
        PERIODS.put(SyModuleI.HOURLY.toString(),SyModuleI.HOURLY);
        PERIODS.put(SyModuleI.DAILY.toString(),SyModuleI.DAILY);
        PERIODS.put(SyModuleI.WEEKLY.toString(),SyModuleI.WEEKLY);
        PERIODS.put(SyModuleI.MONTHLY.toString(),SyModuleI.MONTHLY);
        PERIODS.put(SyModuleI.YEARLY.toString(),SyModuleI.YEARLY);
    }

    public ModuleI parse(Element syndRoot) {
        boolean foundSomething = false;
        SyModuleI sm = new SyModule();

        Element e = syndRoot.getChild("updatePeriod",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            sm.setUpdatePeriod((SyModuleI.Period)PERIODS.get(e.getText()));
        }
        e = syndRoot.getChild("updateFrequency",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            sm.setUpdateFrequency(Integer.parseInt(e.getText()));
        }
        e = syndRoot.getChild("updateBase",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            sm.setUpdateBase(DateParser.parseW3CDateTime(e.getText()));
        }
        return (foundSomething) ? sm : null;
    }

}
