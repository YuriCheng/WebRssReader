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
import com.sun.syndication.io.WireFeedGenerator;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.TextInput;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.List;


/**
 * Feed Generator for RSS 0.90
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class RSS090Generator implements WireFeedGenerator {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String RSS_URI = "http://my.netscape.com/rdf/simple/0.9/";

    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);

    private String _type;

    public RSS090Generator() {
        this("rss_0.9");
    }

    protected RSS090Generator(String feedType) {
        _type = feedType;
    }

    public String getType() {
        return _type;
    }

    public Document generate(WireFeed feed) throws FeedException {

        Channel channel = (Channel)feed;
        Element rootElement = generateRootElement(channel);
        Document doc = new Document(rootElement);

        return doc;
    }

    protected Element generateRootElement(Channel channel)
        throws FeedException  {

        Element root = new Element("RDF");

        root.setNamespace(RDF_NS);
        root.addNamespaceDeclaration(RDF_NS);
        root.addNamespaceDeclaration(RSS_NS);

        if (channel == null) {
            throw new FeedException("invalid RSS Channel - missing required channel element");
        }
        root.addContent(generateChannelElement(channel));

        if (channel.getImage() != null) {
            root.addContent(generateImageElement(channel.getImage()));
        }

        List items = channel.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                root.addContent(generateItemElement((Item)items.get(i)));
            }
        }

        if (channel.getTextInput() != null) {
            root.addContent(generateTextInputElement(channel.getTextInput()));
        }

        return root;
    }


    protected Element generateChannelElement(Channel channel)
        throws FeedException  {

        String title = channel.getTitle();
        String link = channel.getLink();
        String description = channel.getDescription();

        if (title == null) {
            throw new FeedException("invalid RSS Channel - missing required title element");
        }
        if (link == null) {
            throw new FeedException("invalid RSS Channel - missing required link element");
        }
        if (description == null) {
            throw new FeedException("invalid RSS Channel - missing required description element");
        }

        Element channelElement = new Element("channel", RSS_NS);
        channelElement.addContent(generateSimpleElement("title", title, RSS_NS));
        channelElement.addContent(generateSimpleElement("link", link, RSS_NS));
        channelElement.addContent(generateSimpleElement("description", description, RSS_NS));

        return channelElement;
    }


    protected Element generateImageElement(Image image)
        throws FeedException  {

        String title = image.getTitle();
        String url = image.getUrl();
        String link = image.getLink();

        if (title == null) {
            throw new FeedException("invalid RSS Image - missing required title element");
        }
        if (url == null) {
            throw new FeedException("invalid RSS Image - missing required url element");
        }
        if (link == null) {
            throw new FeedException("invalid RSS Image - missing required link element");
        }

        Element imageElement = new Element("image", RSS_NS);
        imageElement.addContent(generateSimpleElement("title", title, RSS_NS));
        imageElement.addContent(generateSimpleElement("url", url, RSS_NS));
        imageElement.addContent(generateSimpleElement("link", link, RSS_NS));

        return imageElement;
    }


    protected Element generateItemElement(Item item)
        throws FeedException  {

        String title = item.getTitle();
        String link = item.getLink();

        if (title == null) {
            throw new FeedException("invalid RSS Item - missing required title element");
        }
        if (link == null) {
            throw new FeedException("invalid RSS Item - missing required link element");
        }

        Element itemElement = new Element("item", RSS_NS);
        itemElement.addContent(generateSimpleElement("title", title, RSS_NS));
        itemElement.addContent(generateSimpleElement("link", link, RSS_NS));

        return itemElement;

    }

    protected Element generateTextInputElement(TextInput textInput)
        throws FeedException  {

        String title = textInput.getTitle();
        String description = textInput.getDescription();
        String name = textInput.getName();
        String link = textInput.getLink();

        if (title == null) {
            throw new FeedException("invalid RSS TextInput - missing required title element");
        }
        if (description == null) {
            throw new FeedException("invalid RSS TextInput - missing required description element");
        }
        if (name == null) {
            throw new FeedException("invalid RSS TextInput - missing required name element");
        }
        if (link == null) {
            throw new FeedException("invalid RSS TextInput - missing required link element");
        }

        Element textInputElement = new Element("textInput", RSS_NS);
        textInputElement.addContent(generateSimpleElement("title", title, RSS_NS));
        textInputElement.addContent(generateDescriptionElement(textInput.getDescription(), RSS_NS));
        textInputElement.addContent(generateSimpleElement("name", name, RSS_NS));
        textInputElement.addContent(generateSimpleElement("link", link, RSS_NS));

        return textInputElement;
    }


    protected Element generateDescriptionElement(Description description, Namespace namespace)
        throws FeedException {

        return generateDescriptionElement(description.getValue(), namespace);
    }


    protected Element generateDescriptionElement(String description, Namespace namespace)
        throws FeedException {

        return generateSimpleElement("description", description, namespace);
    }

    protected Element generateSimpleElement(String name, String value, Namespace namespace)
        throws FeedException {

        Element element = new Element(name, namespace);
        element.addContent(value);

        return element;
    }

}
