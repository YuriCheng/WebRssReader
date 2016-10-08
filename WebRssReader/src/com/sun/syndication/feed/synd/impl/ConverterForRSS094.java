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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndFeedI;
import com.sun.syndication.feed.synd.SyndEntryI;

import java.util.List;

/**
 */
public class ConverterForRSS094 extends ConverterForRSS093 {

    public String getType() {
        return "rss_0.94";
    }

    public void copyInto(WireFeed feed,SyndFeedI syndFeed) {
        Channel channel = (Channel) feed;
        super.copyInto(channel,syndFeed);
        List cats = channel.getCategories();
        if (cats!=null) {
            syndFeed.setCategories(this.createSyndCategories(cats));
        }
    }

    protected SyndEntryI createSyndEntry(Item item) {
        SyndEntryI syndEntry = super.createSyndEntry(item);
        syndEntry.setAuthor(item.getAuthor());
        return syndEntry;
    }


    protected WireFeed createRealFeed(String type,SyndFeedI syndFeed) {
        Channel channel = (Channel) super.createRealFeed(type,syndFeed);
        List cats = syndFeed.getCategories();
        if (cats!=null) {
            channel.setCategories(createRSSCategories(cats));
        }
        return channel;
    }

    protected Item createRSSItem(SyndEntryI sEntry) {
        Item item = super.createRSSItem(sEntry);
        item.setAuthor(sEntry.getAuthor());
        return item;
    }

}
