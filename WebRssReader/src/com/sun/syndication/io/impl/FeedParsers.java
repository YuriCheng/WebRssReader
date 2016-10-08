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

import com.sun.syndication.io.WireFeedParser;
import org.jdom.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Parses an XML document (JDOM Document) into a Feed.
 * <p>
 * It accepts all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and
 * Atom 0.3 feeds.
 * <p>
 * The WireFeedParser is a liberal parser.
 * <p>
 * WireFeedParser instances are thread safe.
 * <p>
 * Parsers for a specific type must extend this class and register in the parser list.
 * (Right now registration is hardcoded in the WireFeedParser constructor).
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class FeedParsers {
    private List PARSERS;
    private List TYPES;

    private List loadParsers(String defaultFile,String fileProperty) {
        List list = new ArrayList();
        PlugableClasses pClasses = new PlugableClasses(defaultFile,fileProperty,Constants.FEED_PARSERS_KEY,
                                                       true,FeedParsers.class.getClassLoader());
        Object[] parsers;
        try {
            parsers = pClasses.createInstances();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        for (int i=0;i<parsers.length;i++) {
            WireFeedParser parser = (WireFeedParser) parsers[i];
            list.add(parser);
        }
        return list;
    }

    /**
     * Creates a parser instance.
     * <p>
     *
     */
    public FeedParsers() {
        this(Constants.DEFAULT_IMPLS_FILE,Constants.IMPLS_SYSTEM_PROPERTY);
    }

    public FeedParsers(String defaultFile,String fileProperty) {
        PARSERS = loadParsers(defaultFile,fileProperty);
        List types = new ArrayList();
        for (int i=0;i<PARSERS.size();i++) {
            types.add(((WireFeedParser)PARSERS.get(i)).getType());
        }
        TYPES = Collections.unmodifiableList(types);
    }

    public List getSupportedFeedTypes() {
        return TYPES;
    }

    /**
     * Finds the real parser type for the given document feed.
     * <p>
     * @param document document feed to find the parser for.
     * @return the parser for the given document or <b>null</b> if there is no parser for that document.
     *
     */
    public WireFeedParser getParserFor(Document document) {
        WireFeedParser parser = null;
        for (int i=0;parser==null && i<PARSERS.size();i++) {
            parser = (WireFeedParser) PARSERS.get(i);
            if (!parser.isMyType(document)) {
                parser = null;
            }
        }
        return parser;
    }

}
