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

/**
 */
public interface Constants {
    public static final String DEFAULT_IMPLS_FILE = "com/sun/syndication/io/impl/implementations.properties";
    public static final String IMPLS_SYSTEM_PROPERTY = "com.sun.syndication.io.impl.extraImplsFile";

    /**
     * WireFeedParser.classes=  [className] ...
     *
     */
    public static final String FEED_PARSERS_KEY        = "WireFeedParser.classes";

    /**
     * feed.ModuleParser.classes=  [className] ...
     *
     */
    public static final String FEED_MODULE_PARSERS_KEY = "feed.ModuleParser.classes";

    /**
     * item.ModuleParser.classes= [className] ...
     *
     */
    public static final String ITEM_MODULE_PARSERS_KEY = "item.ModuleParser.classes";

    /**
     * WireFeedGenerator.classes=  [className] ...
     *
     */
    public static final String FEED_GENERATORS_KEY = "WireFeedGenerator.classes";

    /**
     * feed.ModuleGenerator.classes=  [className] ...
     *
     */
    public static final String FEED_MODULE_GENERATORS_KEY = "feed.ModuleGenerator.classes";

    /**
     * item.ModuleGenerator.classes= [className] ...
     *
     */
    public static final String ITEM_MODULE_GENERATORS_KEY = "item.ModuleGenerator.classes";


}
