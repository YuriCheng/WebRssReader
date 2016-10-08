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
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.TextInput;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Feed Generator for RSS 1.0
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS10Generator extends RSS090Generator {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String RSS_URI = "http://purl.org/rss/1.0/";
    private static final String DC_URI  = "http://purl.org/dc/elements/1.1/";
    private static final String SY_URI  = "http://purl.org/rss/1.0/modules/syndication/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";


    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);
    private static final Namespace DC_NS  = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace SY_NS  = Namespace.getNamespace("sy", SY_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);


    public RSS10Generator() {
        super("rss_1.0");
    }

    protected RSS10Generator(String feedType) {
        super(feedType);
    }

    protected Element generateRootElement(Channel channel)
        throws FeedException {

        Element root = new Element("RDF");

        root.setNamespace(RDF_NS);
        root.addNamespaceDeclaration(RDF_NS);
        root.addNamespaceDeclaration(DC_NS);
        root.addNamespaceDeclaration(SY_NS);
        root.addNamespaceDeclaration(TAXO_NS);
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
        throws FeedException {

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
        channelElement.addContent(generateDescriptionElement(description, RSS_NS));

        List modules = channel.getModules();
        if (modules != null) {
            ModulesGenerator modulesGenerator = new ModulesGenerator();
            modulesGenerator.generateFeedModules(modules, channelElement);
        }

        return channelElement;
    }


    protected Element generateImageElement(Image image)
        throws FeedException {

        // TODO: can there be more than one image?

        // TODO: need parsing to store about
        //Attribute about = new Attribute("about", image.getAbout(), RD_NS);
        //imageElement.setAttribute(about);

        Element imageElement = new Element("image", RSS_NS);

        if (image.getTitle() != null) {
            imageElement.addContent(generateSimpleElement("title", image.getTitle(), RSS_NS));
        }
        if (image.getUrl() != null) {
            imageElement.addContent(generateSimpleElement("url", image.getUrl(), RSS_NS));
        }
        if (image.getLink() != null) {
            imageElement.addContent(generateSimpleElement("link", image.getLink(), RSS_NS));
        }
        return imageElement;
    }


    protected Element generateItemElement(Item item)
        throws FeedException {

        Element itemElement = new Element("item", RSS_NS);
        // TODO: need to have about parsed
        //Attribute about = new Attribute("about", item.getAbout(), RD_NS);
        //itemElement.setAttribute(about);

        if (item.getTitle() != null) {
            itemElement.addContent(generateSimpleElement("title", item.getTitle(), RSS_NS));
        }
        if (item.getLink() != null) {
            itemElement.addContent(generateSimpleElement("link", item.getLink(), RSS_NS));
        }
        if (item.getDescription() != null) {
            itemElement.addContent(generateDescriptionElement(item.getDescription(), RSS_NS));
        }
        List modules = item.getModules();
        if (modules != null) {
            ModulesGenerator modulesGenerator = new ModulesGenerator();
            modulesGenerator.generateItemModules(modules, itemElement);
        }
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
}

