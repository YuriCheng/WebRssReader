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
import com.sun.syndication.io.impl.ModuleUtils;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndFeedI;
import com.sun.syndication.feed.synd.SyndContentI;
import com.sun.syndication.feed.synd.SyndEntryI;
import com.sun.syndication.feed.synd.SyndContent;

import java.util.List;
import java.util.ArrayList;

/**
 */
public class ConverterForRSS10 extends ConverterForRSS090 {

    public String getType() {
        return "rss_1.0";
    }

    public void copyInto(WireFeed feed,SyndFeedI syndFeed) {
        Channel channel = (Channel) feed;
        super.copyInto(channel,syndFeed);

        syndFeed.setModules(ModuleUtils.cloneModules(channel.getModules()));

    }

    protected SyndEntryI createSyndEntry(Item item) {
        SyndEntryI syndEntry = super.createSyndEntry(item);

        Description desc = item.getDescription();
        if (desc!=null) {
            SyndContentI content = new SyndContent();
            content.setType(desc.getType());
            content.setValue(desc.getValue());
            syndEntry.setDescription(content);

            // contents[0] and description then reference the same content
            //
            List contents = new ArrayList();
            contents.add(content);
            syndEntry.setContents(contents);

        }

        syndEntry.setModules(ModuleUtils.cloneModules(item.getModules()));


        return syndEntry;
    }

    protected WireFeed createRealFeed(String type,SyndFeedI syndFeed) {
        Channel channel = (Channel) super.createRealFeed(type,syndFeed);
        channel.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));
        return channel;
    }

    protected Item createRSSItem(SyndEntryI sEntry) {
        Item item = super.createRSSItem(sEntry);

        SyndContentI sContent = sEntry.getDescription();
        if (sContent!=null) {
            item.setDescription(createItemDescription(sContent));
        }

        item.setModules(ModuleUtils.cloneModules(sEntry.getModules()));
        return item;
    }

    protected Description createItemDescription(SyndContentI sContent) {
        Description desc = new Description();
        desc.setValue(sContent.getValue());
        desc.setType(sContent.getType());
        return desc;
    }

}
