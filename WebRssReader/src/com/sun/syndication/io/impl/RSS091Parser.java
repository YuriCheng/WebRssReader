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
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import org.jdom.Attribute;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class RSS091Parser extends RSS090Parser {

    public RSS091Parser() {
        this("rss_0.91");
    }

    protected RSS091Parser(String type) {
        super(type);
    }

    public boolean isMyType(Document document) {
        boolean ok = false;
        Element rssRoot = document.getRootElement();
        ok = rssRoot.getName().equals("rss");
        if (ok) {
            ok = false;
            Attribute version = rssRoot.getAttribute("version");
            if (version!=null) {
                ok = version.getValue().equals(getRSSVersion());
            }
        }
        return ok;
    }

    protected String getRSSVersion() {
            return "0.91";
    }

    private static final String USERLAND = "Userland";
    private static final String NETSCAPE = "Netscape";

    private static final String ELEMENT_NAME = "rss";
    private static final String PUBLIC_ID = "-//Netscape Communications//DTD RSS 0.91//EN";
    private static final String SYSTEM_ID = "http://my.netscape.com/publish/formats/rss-0.91.dtd";

    private String getVariant(Document document) {
        String variant = USERLAND;
        DocType docType = document.getDocType();

        if (docType!=null) {
            boolean ok = ELEMENT_NAME.equals(docType.getElementName());
            ok = ok && PUBLIC_ID.equals(docType.getPublicID());
            ok = ok && SYSTEM_ID.equals(docType.getSystemID());
            if (ok) {
                variant = NETSCAPE;
            }
        }
        return variant;
    }

    protected Namespace getRSSNamespace() {
        return Namespace.getNamespace("");
    }

    protected boolean isHourFormat24(Element rssRoot) {
        String variant = getVariant((Document)rssRoot.getParent());
        return variant.equals(USERLAND);
    }

    /**
     * Parses the root element of an RSS document into a Channel bean.
     * <p/>
     * It first invokes super.parseChannel and then parses and injects the following
     * properties if present: language, pubDate, rating and copyright.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse.
     * @return the parsed Channel bean.
     */
    protected WireFeed parseChannel(Element rssRoot)  {
        Channel channel = (Channel) super.parseChannel(rssRoot);

        Element eChannel = rssRoot.getChild("channel",getRSSNamespace());

        Element e = eChannel.getChild("language",getRSSNamespace());
        if (e!=null) {
            channel.setLanguage(e.getText());
        }
        e = eChannel.getChild("rating",getRSSNamespace());
        if (e!=null) {
            channel.setRating(e.getText());
        }
        e = eChannel.getChild("copyright",getRSSNamespace());
        if (e!=null) {
            channel.setCopyright(e.getText());
        }
        e = eChannel.getChild("pubDate",getRSSNamespace());
        if (e!=null) {
            channel.setPubDate(DateParser.parseRFC822(e.getText()));
        }
        e = eChannel.getChild("lastBuildDate",getRSSNamespace());
        if (e!=null) {
            channel.setLastBuildDate(DateParser.parseRFC822(e.getText()));
        }
        e = eChannel.getChild("docs",getRSSNamespace());
        if (e!=null) {
            channel.setDocs(e.getText());
        }
        e = eChannel.getChild("docs",getRSSNamespace());
        if (e!=null) {
            channel.setDocs(e.getText());
        }
        e = eChannel.getChild("managingEditor",getRSSNamespace());
        if (e!=null) {
            channel.setManagingEditor(e.getText());
        }
        e = eChannel.getChild("webMaster",getRSSNamespace());
        if (e!=null) {
            channel.setWebMaster(e.getText());
        }
        e = eChannel.getChild("skipHours");
        if (e!=null) {
            List skipHours = new ArrayList();
            List eHours = e.getChildren("hour",getRSSNamespace());
            for (int i=0;i<eHours.size();i++) {
                Element eHour = (Element) eHours.get(i);
                skipHours.add(new Integer(eHour.getText()));
            }
            channel.setSkipHours(skipHours);
        }

        e = eChannel.getChild("skipDays");
        if (e!=null) {
            List skipDays = new ArrayList();
            List eDays = e.getChildren("day",getRSSNamespace());
            for (int i=0;i<eDays.size();i++) {
                Element eDay = (Element) eDays.get(i);
                skipDays.add(DAYS.get(eDay.getText()));
            }
            channel.setSkipDays(skipDays);
        }
        return channel;
    }

    private static final Map DAYS = new HashMap();

    static {
        DAYS.put(Channel.SUNDAY.toString(),Channel.SUNDAY);
        DAYS.put(Channel.MONDAY.toString(),Channel.MONDAY);
        DAYS.put(Channel.TUESDAY.toString(),Channel.TUESDAY);
        DAYS.put(Channel.WEDNESDAY.toString(),Channel.WEDNESDAY);
        DAYS.put(Channel.THURSDAY.toString(),Channel.THURSDAY);
        DAYS.put(Channel.FRIDAY.toString(),Channel.FRIDAY);
        DAYS.put(Channel.SATURDAY.toString(),Channel.SATURDAY);
    }

    /**
     * Parses the root element of an RSS document looking for  image information.
     * <p/>
     * It first invokes super.parseImage and then parses and injects the following
     * properties if present: url, link, width, height and description.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse for image information.
     * @return the parsed RSSImage bean.
     */
    protected Image parseImage(Element rssRoot) {
        Image image = super.parseImage(rssRoot);
        if (image!=null) {
            Element eImage = getImage(rssRoot);
            Element e = eImage.getChild("width",getRSSNamespace());
            if (e!=null) {
                image.setWidth(Integer.parseInt(e.getText()));
            }
            e = eImage.getChild("height",getRSSNamespace());
            if (e!=null) {
                image.setHeight(Integer.parseInt(e.getText()));
            }
            e = eImage.getChild("description",getRSSNamespace());
            if (e!=null) {
                image.setLink(e.getText());
            }
        }
        return image;
    }


    /**
     * It looks for the 'item' elements under the 'channel' elemment.
     */
    protected List getItems(Element rssRoot) {
        Element eChannel = rssRoot.getChild("channel",getRSSNamespace());
        return (eChannel!=null) ? eChannel.getChildren("item",getRSSNamespace()) : Collections.EMPTY_LIST;
    }

    /**
     * It looks for the 'image' elements under the 'channel' elemment.
     */
    protected Element getImage(Element rssRoot) {
        Element eChannel = rssRoot.getChild("channel",getRSSNamespace());
        return (eChannel!=null) ? eChannel.getChild("image",getRSSNamespace()) : null;
    }

    /**
     * It looks for the 'textinput' elements under the 'channel' elemment.
     */
    protected Element getTextInput(Element rssRoot) {
        String variant = getVariant((Document)rssRoot.getParent());
        String elementName = (variant.equals(USERLAND)) ? "textInput" : "textinput";
        Element eChannel = rssRoot.getChild("channel",getRSSNamespace());
        return (eChannel!=null) ? eChannel.getChild(elementName,getRSSNamespace()) : null;
    }

    /**
     * Parses an item element of an RSS document looking for item information.
     * <p/>
     * It first invokes super.parseItem and then parses and injects the description property if present.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document in case it's needed for context.
     * @param eItem the item element to parse.
     * @return the parsed RSSItem bean.
     */
    protected Item parseItem(Element rssRoot,Element eItem) {
        Item item = super.parseItem(rssRoot,eItem);
        Element e = eItem.getChild("description",getRSSNamespace());
        if (e!=null) {
            item.setDescription(parseItemDescription(rssRoot,e));
        }
        return item;
    }

    protected Description parseItemDescription(Element rssRoot,Element eDesc) {
        Description desc = new Description();
        desc.setType("text/plain");
        desc.setValue(eDesc.getText());
        return desc;
    }

}
