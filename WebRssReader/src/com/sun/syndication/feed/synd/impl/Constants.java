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
package com.sun.syndication.feed.synd.impl;

/**
 * Created by IntelliJ IDEA.
 * User: tucu
 * Date: May 21, 2004
 * Time: 7:04:49 PM
 * To change this template use Options | File Templates.
 */
public interface Constants {

    public static final String DEFAULT_IMPLS_FILE = "com/sun/syndication/feed/synd/impl/implementations.properties";
    public static final String IMPLS_FILE_PROPERTY = "com.sun.syndication.feed.synd.impl.extraImplsFile";

    /**
     * Converter.classes=  [className] ...
     *
     */
    public static final String CONVERTERS_KEY = "Converter.classes";

}
