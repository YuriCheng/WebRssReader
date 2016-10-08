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

import com.sun.syndication.io.impl.PlugableClasses;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.Converter;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: tucu
 * Date: May 21, 2004
 * Time: 5:26:04 PM
 * To change this template use Options | File Templates.
 */
public class Converters {
    private Map _converters;
    private List _types;

    private Map loadConverters(String defaultFile,String fileProperty) {
        Map map = new HashMap();
        PlugableClasses pClasses = new PlugableClasses(defaultFile,fileProperty,
                                                       Constants.CONVERTERS_KEY,true,SyndFeed.class.getClassLoader());
        Object[] converters;
        try {
            converters = pClasses.createInstances();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        for (int i=0;i<converters.length;i++) {
            Converter converter  = (Converter) converters[i];
            map.put(converter.getType(),converter);
        }
        return map;
    }

    public Converters() {
        this(Constants.DEFAULT_IMPLS_FILE,Constants.IMPLS_FILE_PROPERTY);
    }

    public Converters(String defaultFile,String fileProperty) {
        _converters = loadConverters(defaultFile,fileProperty);
        _types = Collections.unmodifiableList(new ArrayList(_converters.keySet()));
    }

    public List getSupportedFeedTypes() {
        return _types;
    }

    public Converter getConverter(String type) {
        return (Converter) _converters.get(type);
    }

}
