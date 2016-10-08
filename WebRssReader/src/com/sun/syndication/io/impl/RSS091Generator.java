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
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.TextInput;
import com.sun.syndication.feed.rss.Description;
import org.jdom.Attribute;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;

import java.util.List;

/**
 * Feed Generator for RSS 0.91
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class RSS091Generator implements WireFeedGenerator {

    private static final String VERSION = "0.91";
    private static final String RSS_NAME = "rss";

    private String _type;

    public RSS091Generator() {
        this("rss_0.91");
    }

    protected RSS091Generator(String feedType) {
        _type = feedType;
    }

    protected String getVersion() {
        return VERSION;
    }

    public String getType() {
        return _type;
    }

    public Document generate(WireFeed feed) throws FeedException {

        Channel channel = (Channel)feed;
        Element rootElement = generateRootElement(channel);
        Document doc = new Document(rootElement);
        //doc.setDocType(DOC_TYPE);

        return doc;
    }

    protected Element generateRootElement(Channel channel)
        throws FeedException {

        Element root = new Element("rss");
        Attribute version = new Attribute("version", getVersion());
        root.setAttribute(version);

        if (channel == null) {
            throw new FeedException("invalid RSS Channel - missing required channel element");
        }
        root.addContent(generateChannelElement(channel));
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

        Element channelElement = new Element("channel");
        channelElement.addContent(generateSimpleElement("title", title));
        channelElement.addContent(generateSimpleElement("link", link));
        channelElement.addContent(generateSimpleElement("description", description));
        channelElement.addContent(generateLanguageElement(channel.getLanguage()));

        if (channel.getRating() != null) {
            channelElement.addContent(generateSimpleElement("rating", channel.getRating()));
        }

        if (channel.getCopyright() != null) {
            channelElement.addContent(generateSimpleElement("copyright", channel.getCopyright()));
        }

        if (channel.getPubDate() != null) {
            channelElement.addContent(generateSimpleElement("pubDate", channel.getPubDate().toString()));
        }

        if (channel.getLastBuildDate() != null) {
            channelElement.addContent(generateSimpleElement("lastBuildDate", channel.getLastBuildDate().toString()));
        }

        if (channel.getDocs() != null) {
            channelElement.addContent(generateSimpleElement("docs", channel.getDocs()));
        }

        if (channel.getManagingEditor() != null) {
            channelElement.addContent(generateSimpleElement("managingEditor", channel.getManagingEditor()));
        }
        if (channel.getWebMaster() != null) {
            channelElement.addContent(generateSimpleElement("webMaster", channel.getWebMaster()));
        }

        if (channel.getImage() != null) {
            channelElement.addContent(generateImageElement(channel.getImage()));
        }

        if (channel.getTextInput() != null) {
            channelElement.addContent(generateTextInputElement(channel.getTextInput()));
        }

        if (channel.getSkipHours() != null) {
            channelElement.addContent(generateSkipHoursElement(channel.getSkipHours()));
        }
        if (channel.getSkipDays() != null && channel.getSkipDays().size() != 0) {
            channelElement.addContent(generateSkipDaysElement(channel.getSkipDays()));
        }

        List items = channel.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                channelElement.addContent(generateItemElement((Item)items.get(i)));
            }
        }

        return channelElement;
    }

    protected Element generateLanguageElement(String language)
        throws FeedException {

        if (language == null) {
            throw new FeedException("invalid RSS Channel - missing required language element");
        } else {
            return generateSimpleElement("language", language);
        }
    }

    protected Element generateSkipHoursElement(List hours)
        throws FeedException {

        Element skipHoursElement = new Element("skipHours");
        for (int i = 0; i < hours.size(); i++) {
            skipHoursElement.addContent(generateSimpleElement("hour", hours.get(i).toString()));
        }
        return skipHoursElement;
    }

    protected Element generateSkipDaysElement(List days)
        throws FeedException {

        Element skipDaysElement = new Element("skipDays");
        for (int i = 0; i < days.size(); i++) {
            skipDaysElement.addContent(generateSimpleElement("day", days.get(i).toString()));
        }
        return skipDaysElement;
    }


    protected Element generateImageElement(Image image)
        throws FeedException {

        String title = image.getTitle();
        String url = image.getUrl();

        if (title == null) {
            throw new FeedException("invalid RSS Image - missing required title element");
        }
        if (url == null) {
            throw new FeedException("invalid RSS Image - missing required url element");
        }

        Element imageElement = new Element("image");
        imageElement.addContent(generateSimpleElement("title", title));
        imageElement.addContent(generateSimpleElement("url", url));

        if (image.getLink() != null) {
            imageElement.addContent(generateSimpleElement("link", image.getLink()));
        }
        if (image.getWidth() != -1) {
            imageElement.addContent(generateSimpleElement("width", String.valueOf(image.getWidth())));
        }
        if (image.getHeight() != -1) {
            imageElement.addContent(generateSimpleElement("height", String.valueOf(image.getHeight())));
        }
        if (image.getDescription() != null) {
            imageElement.addContent(generateSimpleElement("description", image.getDescription()));
        }
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

        Element itemElement = new Element("item");
        itemElement.addContent(generateSimpleElement("title", title));
        itemElement.addContent(generateSimpleElement("link", link));

        if (item.getDescription() != null) {
            itemElement.addContent(generateDescriptionElement(item.getDescription()));
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

        Element textInputElement = new Element("textInput");
        textInputElement.addContent(generateSimpleElement("title", title));
        textInputElement.addContent(generateDescriptionElement(textInput.getDescription()));
        textInputElement.addContent(generateSimpleElement("name", name));
        textInputElement.addContent(generateSimpleElement("link", link));

        return textInputElement;
    }

    protected Element generateDescriptionElement(String description)
        throws FeedException {

        return generateSimpleElement("description", description);
    }

    protected Element generateDescriptionElement(Description description)
        throws FeedException {

        return generateDescriptionElement(description.getValue());
    }

    protected Element generateSimpleElement(String name, String value)
        throws FeedException  {

        Element element = new Element(name);
        element.addContent(value);

        return element;
    }

}
