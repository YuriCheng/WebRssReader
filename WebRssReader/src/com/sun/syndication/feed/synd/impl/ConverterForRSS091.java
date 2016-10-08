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
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndFeedI;
import com.sun.syndication.feed.synd.SyndContentI;
import com.sun.syndication.feed.synd.SyndEntryI;
import com.sun.syndication.feed.synd.SyndImageI;
import com.sun.syndication.feed.synd.SyndContent;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ConverterForRSS091 extends ConverterForRSS090 {

    public String getType() {
        return "rss_0.91";
    }

    public void copyInto(WireFeed feed,SyndFeedI syndFeed) {
        Channel channel = (Channel) feed;
        super.copyInto(channel,syndFeed);
        syndFeed.setLanguage(channel.getLanguage());
        syndFeed.setCopyright(channel.getCopyright());
        syndFeed.setPublishedDate(channel.getPubDate());
        syndFeed.setAuthor(channel.getManagingEditor());
    }

    protected SyndImageI createSyndImage(Image rssImage) {
        SyndImageI syndImage = super.createSyndImage(rssImage);
        syndImage.setDescription(rssImage.getDescription());
        return syndImage;
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
        return syndEntry;
    }

    protected WireFeed createRealFeed(String type,SyndFeedI syndFeed) {
        Channel channel = (Channel) super.createRealFeed(type,syndFeed);
        channel.setLanguage(syndFeed.getLanguage());
        channel.setCopyright(syndFeed.getCopyright());
        channel.setPubDate(syndFeed.getPublishedDate());
        channel.setManagingEditor(syndFeed.getAuthor());
        return channel;
    }

    protected Image createRSSImage(SyndImageI sImage) {
        Image image = super.createRSSImage(sImage);
        image.setDescription(sImage.getDescription());
        return image;
    }

    protected Item createRSSItem(SyndEntryI sEntry) {
        Item item = super.createRSSItem(sEntry);

        SyndContentI sContent = sEntry.getDescription();
        if (sContent!=null) {
            item.setDescription(createItemDescription(sContent));
        }
        return item;
    }

    protected Description createItemDescription(SyndContentI sContent) {
        Description desc = new Description();
        desc.setValue(sContent.getValue());
        desc.setType(sContent.getType());
        return desc;
    }


}
