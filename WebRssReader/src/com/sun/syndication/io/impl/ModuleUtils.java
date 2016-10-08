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

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ModuleUtils {

    public static List cloneModules(List modules) {
        List cModules = null;
        if (modules!=null) {
            cModules = new ArrayList();
            for (int i=0;i<modules.size();i++) {
                ModuleI module = (ModuleI) modules.get(i);
                try {
                    cModules.add(module.clone());
                }
                catch (Exception ex) {
                    throw new RuntimeException("Cloning modules",ex);
                }
            }
        }
        return cModules;
    }

}
