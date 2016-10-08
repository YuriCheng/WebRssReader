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
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.Description;
import org.jdom.Element;


/**
 * Feed Generator for RSS 2.0
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS20Generator extends RSS094Generator {

    private static final String VERSION = "2.0";

    public RSS20Generator() {
        super("rss_2.0");
    }

    protected RSS20Generator(String feedType) {
        super(feedType);
    }

    protected String getVersion() {
        return VERSION;
    }

    protected Element generateChannelElement(Channel channel)
        throws FeedException {

        Element channelElement = super.generateChannelElement(channel);

        if (channel.getRating() != null) {
            channelElement.addContent(generateSimpleElement("rating", channel.getRating()));
        }

        if (channel.getGenerator() != null) {
            channelElement.addContent(generateSimpleElement("generator", channel.getGenerator()));
        }
        if (channel.getTtl() != 0) {
            channelElement.addContent(generateSimpleElement("ttl", String.valueOf(channel.getTtl())));
        }

        /* TODO: need to handle categories with domain as attribute
        if (channel.getCategories() != null) {
            channelElement.addContent(generateCategoryElement(channel.getCategory()));
        }
        */

        return channelElement;
    }

    protected Element generateItemElement(Item item)
        throws FeedException {

        Element itemElement = super.generateItemElement(item);

        if (item.getAuthor() != null) {
            itemElement.addContent(generateSimpleElement("author", item.getAuthor()));
        }
        if (item.getComments() != null) {
            itemElement.addContent(generateSimpleElement("comments", item.getComments()));
        }
        if (item.getGuid() != null) {
            itemElement.addContent(generateSimpleElement("guid", item.getGuid().toString()));
        }

        return itemElement;
    }

    protected Description parseItemDescription(Element rssRoot,Element eDesc) {
        Description desc = new Description();
        desc.setType("text/html");
        desc.setValue(eDesc.getText());
        return desc;
    }

}
