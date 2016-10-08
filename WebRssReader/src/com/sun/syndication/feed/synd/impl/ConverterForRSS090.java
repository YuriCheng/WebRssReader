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
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndFeedI;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndEntryI;
import com.sun.syndication.feed.synd.SyndImageI;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ConverterForRSS090 implements Converter {

    public String getType() {
        return "rss_0.9";
    }


    public void copyInto(WireFeed feed,SyndFeedI syndFeed) {
        Channel channel = (Channel) feed;
        syndFeed.setTitle(channel.getTitle());
        syndFeed.setLink(channel.getLink());
        syndFeed.setDescription(channel.getDescription());
        syndFeed.setPublishedDate(channel.getPubDate());

        Image image = channel.getImage();
        if (image!=null) {
            syndFeed.setImage(createSyndImage(image));
        }

        List items = channel.getItems();
        if (items!=null) {
            syndFeed.setEntries(createSyndEntries(items));
        }
    }

    protected SyndImageI createSyndImage(Image rssImage) {
        SyndImageI syndImage = new SyndImage();
        syndImage.setTitle(rssImage.getTitle());
        syndImage.setUrl(rssImage.getUrl());
        syndImage.setLink(rssImage.getLink());
        return syndImage;
    }

    protected List createSyndEntries(List rssItems) {
        List syndEntries = new ArrayList();
        for (int i=0;i<rssItems.size();i++) {
            syndEntries.add(createSyndEntry((Item) rssItems.get(i)));
        }
        return syndEntries;
    }

    protected SyndEntryI createSyndEntry(Item item) {
        SyndEntryI syndEntry = new SyndEntry();
        syndEntry.setTitle(item.getTitle());
        syndEntry.setLink(item.getLink());
        return syndEntry;
    }

    public WireFeed createRealFeed(SyndFeedI syndFeed) {
        return createRealFeed(getType(),syndFeed);
    }

    protected WireFeed createRealFeed(String type,SyndFeedI syndFeed) {
        Channel channel = new Channel(type);

        channel.setTitle(syndFeed.getTitle());
        channel.setLink(syndFeed.getLink());
        channel.setDescription(syndFeed.getDescription());

        SyndImageI sImage = syndFeed.getImage();
        if (sImage!=null) {
            channel.setImage(createRSSImage(sImage));
        }

        List sEntries = syndFeed.getEntries();
        if (sEntries!=null) {
            channel.setItems(createRSSItems(sEntries));
        }
        return channel;
    }

    protected Image createRSSImage(SyndImageI sImage) {
        Image image = new Image();
        image.setTitle(sImage.getTitle());
        image.setUrl(sImage.getUrl());
        image.setLink(sImage.getLink());
        return image;
    }

    protected List createRSSItems(List sEntries) {
        List list = new ArrayList();
        for (int i=0;i<sEntries.size();i++) {
            list.add(createRSSItem((SyndEntryI)sEntries.get(i)));
        }
        return list;
    }

    protected Item createRSSItem(SyndEntryI sEntry) {
        Item item = new Item();
        item.setTitle(sEntry.getTitle());
        item.setLink(sEntry.getLink());
        return item;
    }

}
