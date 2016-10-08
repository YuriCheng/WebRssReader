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
import com.sun.syndication.io.ModuleGenerator;
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
public class ModulesGenerator {
    private Map _feedModuleGenerators = new HashMap();
    private Map _itemModuleGenerators = new HashMap();

    private void loadGenerators(String defaultFile,String fileProperty,String key,Map map) {
        PlugableClasses pClasses = new PlugableClasses(defaultFile,fileProperty,
                                                        key,true,ModulesGenerator.class.getClassLoader());
        Object[] generators;
        try {
            generators = pClasses.createInstances();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        for (int i=0;i<generators.length;i++) {
            ModuleGenerator generator = (ModuleGenerator) generators[i];
            map.put(generator.getNamespaceUri(),generator);
        }
    }

    public ModulesGenerator() {
        this(Constants.DEFAULT_IMPLS_FILE,Constants.IMPLS_SYSTEM_PROPERTY);
    }

    public ModulesGenerator(String defaultFile,String fileProperty) {
        loadGenerators(defaultFile,fileProperty,Constants.FEED_MODULE_GENERATORS_KEY,_feedModuleGenerators);
        loadGenerators(defaultFile,fileProperty,Constants.ITEM_MODULE_GENERATORS_KEY,_itemModuleGenerators);
    }

    public List getFeedModuleNamespaces() {
        return Collections.unmodifiableList(new ArrayList(_feedModuleGenerators.keySet()));
    }

    public List getItemModuleNamespaces() {
        return Collections.unmodifiableList(new ArrayList(_itemModuleGenerators.keySet()));
    }

    public void generateFeedModules(List modules, Element element) {
        generateModules(_feedModuleGenerators, modules, element);
    }

    public void generateItemModules(List modules, Element element) {
        generateModules(_itemModuleGenerators, modules, element);
    }

    private void generateModules(Map generators, List modules, Element element) {

        List moduleElements = new ArrayList(modules.size());

        for (int i = 0; i < modules.size(); i++) {
            ModuleI module = (ModuleI) modules.get(i);
            String namespaceURI = module.getUri();
            ModuleGenerator generator = (ModuleGenerator)generators.get(namespaceURI);
            if (generator != null) {
                generator.generate(module, element);
            }
        }
    }

}
