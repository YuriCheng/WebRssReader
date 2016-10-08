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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Cloud;
import com.sun.syndication.feed.rss.Enclosure;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.Source;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import java.util.List;


/**
 * Feed Generator for RSS 0.92
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS092Generator extends RSS091Generator {

    private static final String VERSION = "0.92";

    public RSS092Generator() {
        super("rss_0.92");
    }

    protected RSS092Generator(String feedType) {
        super(feedType);
    }

    protected String getVersion() {
        return VERSION;
    }

    public Document generate(WireFeed feed) throws FeedException {

        Channel channel = (Channel)feed;
        Element rootElement = generateRootElement(channel);
        Document doc = new Document(rootElement);

        return doc;
    }

    protected Element generateChannelElement(Channel channel)
        throws FeedException {

        Element channelElement = super.generateChannelElement(channel);

        if (channel.getCloud() != null) {
            channelElement.addContent(generateCloudElement(channel.getCloud()));
        }

        return channelElement;
    }

    protected Element generateLanguageElement(String language)
        throws FeedException {

        if (language == null) {
            return generateSimpleElement("language", "");
        } else {
            return generateSimpleElement("language", language);
        }
    }

    protected Element generateCloudElement(Cloud cloud)
        throws FeedException {

        Element cloudElement = new Element("cloud");

        if (cloud.getDomain() != null) {
            cloudElement.setAttribute(new Attribute("domain", cloud.getDomain()));
        }

        if (cloud.getPort() != 0) {
            cloudElement.setAttribute(new Attribute("port", String.valueOf(cloud.getPort())));
        }

        if (cloud.getRegisterProcedure() != null) {
            cloudElement.setAttribute(new Attribute("registerProcedure", cloud.getRegisterProcedure()));
        }

        if (cloud.getProtocol() != null) {
            cloudElement.setAttribute(new Attribute("protocol", cloud.getProtocol()));
        }

        return cloudElement;
    }

    protected Element generateItemElement(Item item)
        throws FeedException {

        Element itemElement = new Element("item");
        if (item.getTitle() != null) {
            itemElement.addContent(generateSimpleElement("title", item.getTitle()));
        }
        if (item.getLink() != null) {
            itemElement.addContent(generateSimpleElement("link", item.getLink()));
        }
        if (item.getDescription() != null) {
            itemElement.addContent(generateDescriptionElement(item.getDescription()));
        }

        if (item.getSource() != null) {
            itemElement.addContent(generateSourceElement(item.getSource()));
        }

        List enclosures = item.getEnclosures();
        for(int i = 0; i < enclosures.size(); i++) {
            itemElement.addContent(generateEnclosure((Enclosure)enclosures.get(i)));
        }

        List categories = item.getCategories();
        for(int i = 0; i < categories.size(); i++) {
            itemElement.addContent(generateCategoryElement((Category)categories.get(i)));
        }

        return itemElement;
    }

    protected Element generateSourceElement(Source source)
        throws FeedException {

        Element sourceElement = new Element("source");
        if (source.getUrl() != null) {
            sourceElement.setAttribute(new Attribute("url", source.getUrl()));
        }
        sourceElement.addContent(source.getValue());

        return sourceElement;
    }

    protected Element generateEnclosure(Enclosure enclosure)
        throws FeedException {

        Element enclosureElement = new Element("enclosure");
        if (enclosure.getUrl() != null) {
            enclosureElement.setAttribute("url", enclosure.getUrl());
        }
        if (enclosure.getLength() != 0) {
            enclosureElement.setAttribute("length", String.valueOf(enclosure.getLength()));
        }
        if (enclosure.getType() != null) {
            enclosureElement.setAttribute("type", enclosure.getType());
        }
        return enclosureElement;
    }

    protected Element generateCategoryElement(Category category)
        throws FeedException {

        Element categoryElement = new Element("category");
        if (category.getDomain() != null) {
            categoryElement.setAttribute("domain", category.getDomain());
        }
        categoryElement.addContent(category.getValue());

        return categoryElement;
    }


}
