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
package com.sun.syndication.feed.module;

import com.sun.syndication.common.ObjectBean;

/**
 * Base class for modules describing Metadata of feeds, default implementations.
 * Examples of such modules are the Dublin Core and Syndication modules.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Module extends ObjectBean implements ModuleI {
    private String _uri;

    /**
     * Constructor.
     * <p>
     * @param uri URI of the module.
     *
     */
    protected Module(String uri) {
        _uri = uri;
    }

    /**
     * Returns the URI of the module.
     * <p>
     * @return URI of the module.
     *
     */
    public String getUri() {
        return _uri;
    }

}
