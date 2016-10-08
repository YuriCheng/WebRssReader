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
import com.sun.syndication.feed.rss.Item;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.List;

/**
 */
public class RSS10Parser extends RSS090Parser {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String RSS_URI = "http://purl.org/rss/1.0/";

    private ModulesParser _modulesParser;

    public RSS10Parser() {
        this("rss_1.0");
    }

    protected RSS10Parser(String type) {
        super(type);
        _modulesParser = new ModulesParser();
    }

    /**
     * Indicates if a JDom document is an RSS instance that can be parsed with the parser.
     * <p/>
     * It checks for RDF ("http://www.w3.org/1999/02/22-rdf-syntax-ns#") and
     * RSS ("http://purl.org/rss/1.0/") namespaces being defined in the root element.
     *
     * @param document document to check if it can be parsed with this parser implementation.
     * @return <b>true</b> if the document is RSS1., <b>false</b> otherwise.
     */
    public boolean isMyType(Document document) {
        boolean ok = false;
        Namespace rdfNS = Namespace.getNamespace(RDF_URI);
        Namespace rssNS = Namespace.getNamespace(RSS_URI);

        Element rssRoot = document.getRootElement();
        Namespace defaultNS = rssRoot.getNamespace();
        List additionalNSs = rssRoot.getAdditionalNamespaces();

        ok = defaultNS!=null && defaultNS.equals(rdfNS);
        if (ok) {
            if (additionalNSs==null) {
                ok = false;
            }
            else {
                ok = false;
                for (int i=0;!ok && i<additionalNSs.size();i++) {
                    ok = rssNS.equals(additionalNSs.get(i));
                }
            }
        }
        return ok;
    }

    /**
     * Returns the namespace used by RSS elements in document of the RSS 1.0
     * <P>
     *
     * @return returns "http://purl.org/rss/1.0/".
     */
    protected Namespace getRSSNamespace() {
        return Namespace.getNamespace(RSS_URI);
    }

    protected WireFeed parseChannel(Element rssRoot) {
        Channel channel = (Channel) super.parseChannel(rssRoot);

        List modules = _modulesParser.parseFeedModules(rssRoot.getChild("channel",getRSSNamespace()));
        if (modules!=null) {
            channel.setModules(modules);
        }

        return channel;
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
        List modules = _modulesParser.parseItemModules(eItem);
        if (modules!=null) {
            item.setModules(modules);
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
