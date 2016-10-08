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

import com.sun.syndication.feed.module.ModuleI;
import com.sun.syndication.io.ModuleParser;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 */
public class ModulesParser {
    private Map _feedModuleParsers = new HashMap();
    private Map _itemModuleParsers = new HashMap();

    private void loadParsers(String defaultFile,String fileProperty,String key,Map map) {
        PlugableClasses pClasses = new PlugableClasses(defaultFile,fileProperty,
                                                        key,true,ModulesParser.class.getClassLoader());
        Object[] parsers;
        try {
            parsers = pClasses.createInstances();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        for (int i=0;i<parsers.length;i++) {
            ModuleParser parser = (ModuleParser) parsers[i];
            map.put(parser.getNamespaceUri(),parser);
        }
    }

    public ModulesParser() {
        this(Constants.DEFAULT_IMPLS_FILE,Constants.IMPLS_SYSTEM_PROPERTY);
    }

    public ModulesParser(String defaultFile,String fileProperty) {
        loadParsers(defaultFile,fileProperty,Constants.FEED_MODULE_PARSERS_KEY,_feedModuleParsers);
        loadParsers(defaultFile,fileProperty,Constants.ITEM_MODULE_PARSERS_KEY,_itemModuleParsers);
    }

    public List getFeedModuleNamespaces() {
        return Collections.unmodifiableList(new ArrayList(_feedModuleParsers.keySet()));
    }

    public List getItemModuleNamespaces() {
        return Collections.unmodifiableList(new ArrayList(_itemModuleParsers.keySet()));
    }

    public List parseFeedModules(Element root) {
        return parseModules(_feedModuleParsers,root);
    }

    public List parseItemModules(Element root) {
        return parseModules(_itemModuleParsers,root);
    }


    private boolean hasElementsFrom(Element root,Namespace namespace) {
        boolean itHas = false;
        List children = root.getChildren();
        for (int i=0;!itHas && i<children.size();i++) {
            Element child = (Element) children.get(i);
            itHas = namespace.equals(child.getNamespace());
        }
       return itHas;
    }

    private List parseModules(Map parsers,Element root) {
        List modules = null;
        Iterator i = parsers.keySet().iterator();
        while (i.hasNext()) {
            String namespaceURI = (String) i.next();
            Namespace namespace = Namespace.getNamespace(namespaceURI);
            if (hasElementsFrom(root,namespace)) {
                ModuleParser parser = (ModuleParser) parsers.get(namespaceURI);
                if (parser!=null) {
                    ModuleI module = parser.parse(root);
                    if (module!=null) {
                        if (modules==null) {
                            modules = new ArrayList();
                        }
                        modules.add(module);
                    }
                }
            }
        }
        return modules;
    }

}
