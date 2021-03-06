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
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Generator;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedParser;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class Atom03Parser implements WireFeedParser {
    private static final String ATOM_URI = "http://purl.org/atom/ns#";

    private ModulesParser _modulesParser;

    public Atom03Parser() {
        _modulesParser = new ModulesParser();
    }

    public String getType() {
        return "atom_0.3";
    }

    protected Namespace getAtomNamespace() {
        return Namespace.getNamespace(ATOM_URI);
    }

    public boolean isMyType(Document document) {
        Element rssRoot = document.getRootElement();
        Namespace defaultNS = rssRoot.getNamespace();
        return (defaultNS!=null) && defaultNS.equals(getAtomNamespace());
    }

    public WireFeed parse(Document document, boolean validate) throws IllegalArgumentException,FeedException {
        if (validate) {
            validateFeed(document);
        }
        Element rssRoot = document.getRootElement();
        return parseFeed(rssRoot);
    }

    protected void validateFeed(Document document) throws FeedException {
        // TBD
        // here we have to validate the Feed against a schema or whatever
        // not sure how to do it
        // one posibility would be to produce an ouput and attempt to parse it again
        // with validation turned on.
        // otherwise will have to check the document elements by hand.
    }

    protected WireFeed parseFeed(Element eFeed) {

        com.sun.syndication.feed.atom.Feed feed = new com.sun.syndication.feed.atom.Feed(getType());

        Element e = eFeed.getChild("title",getAtomNamespace());
        if (e!=null) {
            feed.setTitle(e.getText());
        }

        List eList = eFeed.getChildren("link",getAtomNamespace());
        feed.setAlternateLinks(parseAlternateLinks(eList));
        feed.setOtherLinks(parseOtherLinks(eList));

        e = eFeed.getChild("author",getAtomNamespace());
        if (e!=null) {
            feed.setAuthor(parsePerson(e));
        }

        eList = eFeed.getChildren("contributor",getAtomNamespace());
        if (eList.size()>0) {
            feed.setContributors(parsePersons(eList));
        }

        e = eFeed.getChild("tagline",getAtomNamespace());
        if (e!=null) {
            feed.setTagline(parseContent(e));
        }

        e = eFeed.getChild("id",getAtomNamespace());
        if (e!=null) {
            feed.setId(e.getText());
        }

        e = eFeed.getChild("generator",getAtomNamespace());
        if (e!=null) {
            Generator gen = new Generator();
            gen.setValue(e.getText());
            String att = e.getAttributeValue("url");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                gen.setUrl(att);
            }
            att = e.getAttributeValue("version");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                gen.setVersion(att);
            }
            feed.setGenerator(gen);
        }

        e = eFeed.getChild("copyright",getAtomNamespace());
        if (e!=null) {
            feed.setCopyright(e.getText());
        }

        e = eFeed.getChild("info",getAtomNamespace());
        if (e!=null) {
            feed.setInfo(parseContent(e));
        }

        e = eFeed.getChild("modified",getAtomNamespace());
        if (e!=null) {
            feed.setModified(DateParser.parseW3CDateTime(e.getText()));
        }

        eList = eFeed.getChildren("entry",getAtomNamespace());
        if (eList.size()>0) {
            feed.setEntries(parseEntries(eList));
        }

        List modules = _modulesParser.parseFeedModules(eFeed);
        if (modules!=null) {
            feed.setModules(modules);
        }

        return feed;
    }

    private static final Map RELS = new HashMap();

    static {
        RELS.put(Link.ALTERNATE.toString(),Link.ALTERNATE);
        RELS.put(Link.START.toString(),Link.START);
        RELS.put(Link.NEXT.toString(),Link.NEXT);
        RELS.put(Link.PREV.toString(),Link.PREV);
        RELS.put(Link.SERVICE_EDIT.toString(),Link.SERVICE_EDIT);
        RELS.put(Link.SERVICE_POST.toString(),Link.SERVICE_POST);
        RELS.put(Link.SERVICE_FEED.toString(),Link.SERVICE_FEED);
    }

    private Link parseLink(Element eLink) {
        Link link = new Link();
        String att = eLink.getAttributeValue("rel");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
        if (att!=null) {
            link.setRel((Link.Rel) RELS.get(att));
        }
        att = eLink.getAttributeValue("type");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
        if (att!=null) {
            link.setType(att);
        }
        att = eLink.getAttributeValue("href");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
        if (att!=null) {
            link.setHref(att);
        }
        return link;
    }

    // List(Elements) -> List(Link)
    private List parseLinks(List eLinks,boolean alternate) {
        List links = new ArrayList();
        for (int i=0;i<eLinks.size();i++) {
            Element eLink = (Element) eLinks.get(i);
            //Namespace ns = getAtomNamespace();
            String rel = eLink.getAttributeValue("rel");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
            if (alternate) {
                if ("alternate".equals(rel)) {
                    links.add(parseLink(eLink));
                }
            }
            else {
                if (!("alternate".equals(rel))) {
                    links.add(parseLink(eLink));
                }
            }
        }
        return (links.size()>0) ? links : null;
    }

    // List(Elements) -> List(Link)
    private List parseAlternateLinks(List eLinks) {
        return parseLinks(eLinks,true);
    }

    // List(Elements) -> List(Link)
    private List parseOtherLinks(List eLinks) {
        return parseLinks(eLinks,false);
    }

    private Person parsePerson(Element ePerson) {
        Person person = new Person();
        Element e = ePerson.getChild("name",getAtomNamespace());
        if (e!=null) {
            person.setName(e.getText());
        }
        e = ePerson.getChild("url",getAtomNamespace());
        if (e!=null) {
            person.setUrl(e.getText());
        }
        e = ePerson.getChild("email",getAtomNamespace());
        if (e!=null) {
            person.setEmail(e.getText());
        }
        return person;
    }

    // List(Elements) -> List(Persons)
    private List parsePersons(List ePersons) {
        List persons = new ArrayList();
        for (int i=0;i<ePersons.size();i++) {
            persons.add(parsePerson((Element)ePersons.get(i)));
        }
        return (persons.size()>0) ? persons : null;
    }

    private static final Map MODES = new HashMap();

    static {
        MODES.put(Content.XML.toString(),Content.XML);
        MODES.put(Content.ESCAPED.toString(),Content.ESCAPED);
        MODES.put(Content.BASE64.toString(),Content.BASE64);
        MODES.put(null,Content.XML);
    }

    private Content parseContent(Element e) {
        String value = null;
        String type = e.getAttributeValue("type");//getAtomNamespace()); DONT KNOW WHY DOESN'T WORK
        type = (type!=null) ? type : "text/plain";
        Content.Mode mode = (Content.Mode)MODES.get(e.getAttributeValue("mode"));//getAtomNamespace())); DONT KNOW WHY DOESN'T WORK
        if (mode.equals(Content.ESCAPED)) {
            // do nothing XML Parser took care of this
            value = e.getText();
        }
        else
        if (mode.equals(Content.BASE64)) {
                value = Base64.decode(e.getText());
        }
        else
        if (mode.equals(Content.XML)) {
            StringBuffer sb = new StringBuffer();
            XMLOutputter outputter = new XMLOutputter();
            List children = e.getChildren();
            for (int i=0;i<children.size();i++) {
                sb.append(outputter.outputString((Element)children.get(i))).append("\n");
            }
            value = sb.toString();
        }

        Content content = new Content();
        content.setType(type);
        content.setMode(mode);
        content.setValue(value);
        return content;
    }

    // List(Elements) -> List(Entries)
    private List parseEntries(List eEntries) {
        List entries = new ArrayList();
        for (int i=0;i<eEntries.size();i++) {
            entries.add(parseEntry((Element)eEntries.get(i)));
        }
        return (entries.size()>0) ? entries : null;
    }

    private Entry parseEntry(Element eEntry) {
        Entry entry = new Entry();

        Element e = eEntry.getChild("title",getAtomNamespace());
        if (e!=null) {
            entry.setTitle(e.getText());
        }

        List eList = eEntry.getChildren("link",getAtomNamespace());
        entry.setAlternateLinks(parseAlternateLinks(eList));
        entry.setOtherLinks(parseOtherLinks(eList));

        e = eEntry.getChild("author",getAtomNamespace());
        if (e!=null) {
            entry.setAuthor(parsePerson(e));
        }

        eList = eEntry.getChildren("contributor",getAtomNamespace());
        if (eList.size()>0) {
            entry.setContributors(parsePersons(eList));
        }

        e = eEntry.getChild("id",getAtomNamespace());
        if (e!=null) {
            entry.setId(e.getText());
        }

        e = eEntry.getChild("modified",getAtomNamespace());
        if (e!=null) {
            entry.setModified(DateParser.parseW3CDateTime(e.getText()));
        }

        e = eEntry.getChild("issued",getAtomNamespace());
        if (e!=null) {
            entry.setIssued(DateParser.parseW3CDateTime(e.getText()));
        }

        e = eEntry.getChild("summary",getAtomNamespace());
        if (e!=null) {
            entry.setSummary(parseContent(e));
        }

        eList = eEntry.getChildren("content",getAtomNamespace());
        if (eList.size()>0) {
            List content = new ArrayList();
            for (int i=0;i<eList.size();i++) {
                content.add(parseContent((Element)eList.get(i)));
            }
            entry.setContents(content);
        }

        List modules = _modulesParser.parseItemModules(eEntry);
        if (modules!=null) {
            entry.setModules(modules);
        }

        return entry;
    }


}
