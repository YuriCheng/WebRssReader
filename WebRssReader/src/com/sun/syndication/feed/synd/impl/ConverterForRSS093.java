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

import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntryI;

/**
 */
public class ConverterForRSS093 extends ConverterForRSS092 {

    public String getType() {
        return "rss_0.93";
    }

    protected SyndEntryI createSyndEntry(Item item) {
        SyndEntryI syndEntry = super.createSyndEntry(item);
        syndEntry.setPublishedDate(item.getPubDate());
        return syndEntry;
    }

    protected Item createRSSItem(SyndEntryI sEntry) {
        Item item = super.createRSSItem(sEntry);
        item.setPubDate(sEntry.getPublishedDate());
        return item;
    }

}
