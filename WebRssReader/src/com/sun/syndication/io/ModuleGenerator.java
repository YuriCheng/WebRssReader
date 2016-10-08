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
package com.sun.syndication.io;

import com.sun.syndication.feed.module.ModuleI;
import org.jdom.Element;

/**
 * Injects module metadata into a XML node (JDOM element).
 * <p>
 * ModuleGenerator instances must thread safe.
 * <p>
 * TODO: explain how developers can plugin their own implementations.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface ModuleGenerator {

    /**
     * Returns the namespace URI this generator handles.
     * <p>
     * @return the namespace URI.
     *
     */
    public String getNamespaceUri();

    /**
     * Generates and injectts module metadata in a XML node (JDOM element).
     * <p>
     * @param module the module to inject into the XML node (JDOM element).
     * @param element the XML node to inject the module metadata to.
     *
     */
    public void generate(ModuleI module,Element element);

}
