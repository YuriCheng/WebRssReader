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

import com.sun.syndication.io.FeedException;
import com.sun.syndication.feed.rss.Item;
import org.jdom.Element;


/**
 * Feed Generator for RSS 0.93
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class RSS093Generator extends RSS092Generator {

    private static final String VERSION = "0.93";

    public RSS093Generator() {
        super("rss_0.93");
    }

    protected RSS093Generator(String feedType) {
        super(feedType);
    }

    protected String getVersion() {
        return VERSION;
    }

    protected Element generateItemElement(Item item)
        throws FeedException {

        Element itemElement = super.generateItemElement(item);

        if (item.getPubDate() != null) {
            itemElement.addContent(generateSimpleElement("pubDate", item.getPubDate().toString()));
        }
        if (item.getExpirationDate() != null) {
            itemElement.addContent(
                generateSimpleElement("expirationDate", DateParser.parseRFC822(item.getExpirationDate())));
        }

        return itemElement;
    }

}
