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

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedGenerator;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import com.sun.syndication.feed.atom.*;
import com.sun.syndication.feed.module.ModuleI;

/**
 * Feed Generator for Atom
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class Atom03Generator implements WireFeedGenerator {

    private String _type;

    private static final String VERSION = "0.3";
    private static final String ATOM_URI = "http://purl.org/atom/ns#";
    private static final Namespace ATOM_NS = Namespace.getNamespace(ATOM_URI);

    private static final String DC_URI  = "http://purl.org/dc/elements/1.1/";
    private static final String SY_URI  = "http://purl.org/rss/1.0/modules/syndication/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";
    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private static final Namespace DC_NS  = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace SY_NS  = Namespace.getNamespace("sy", SY_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);

    public Atom03Generator() {
        _type = "atom_0.3";
    }

    public String getType() {
        return _type;
    }

    public Document generate(WireFeed feed) throws FeedException {
        Element rootElement = generateRootElement((Feed)feed);
        Document doc = new Document(rootElement);
        return doc;
    }

    protected Element generateRootElement(Feed feed)
        throws FeedException  {

        Element root = new Element("feed");
        root.setNamespace(ATOM_NS);
        root.addNamespaceDeclaration(ATOM_NS);

        // TODO: should only be set if DC module is used
        root.addNamespaceDeclaration(DC_NS);
        root.addNamespaceDeclaration(TAXO_NS);
        root.addNamespaceDeclaration(RDF_NS);
        root.addNamespaceDeclaration(SY_NS);

        Attribute version = new Attribute("version", VERSION);
        root.setAttribute(version);

        if (feed == null) {
            throw new FeedException("invalid Atom Feed - missing required feed element");
        }

        if (feed.getTitle() != null) {
            root.addContent(generateSimpleElement("title", feed.getTitle()));
        }

        List links = feed.getAlternateLinks();
        for (int i = 0; i < links.size(); i++) {
            root.addContent(generateLinkElement((Link)links.get(i)));
        }

        links = feed.getOtherLinks();
        for (int i = 0; i < links.size(); i++) {
            root.addContent(generateLinkElement((Link)links.get(i)));
        }

        if (feed.getAuthor() != null) {
            Element authorElement = new Element("author", ATOM_NS);
            fillPersonElement(authorElement, feed.getAuthor());
            root.addContent(authorElement);
        }

        List contributors = feed.getContributors();
        for (int i = 0; i < contributors.size(); i++) {
            Element contributorElement = new Element("contributor", ATOM_NS);
            fillPersonElement(contributorElement, (Person)contributors.get(i));
            root.addContent(contributorElement);
        }

        if (feed.getTagline() != null) {
            Element taglineElement = new Element("tagline", ATOM_NS);
            fillContentElement(taglineElement, feed.getTagline());
            root.addContent(taglineElement);
        }

        if (feed.getId() != null) {
            root.addContent(generateSimpleElement("id", feed.getId()));
        }

        if (feed.getGenerator() != null) {
            root.addContent(generateGeneratorElement(feed.getGenerator()));
        }

        if (feed.getCopyright() != null) {
            root.addContent(generateSimpleElement("copyright", feed.getCopyright()));
        }

        if (feed.getInfo() != null) {
            Element infoElement = new Element("info", ATOM_NS);
            fillContentElement(infoElement, feed.getInfo());
            root.addContent(infoElement);
        }

        if (feed.getModified() != null) {
            Element modifiedElement = new Element("modified", ATOM_NS);
            modifiedElement.addContent(DateParser.parseW3CDateTime(feed.getModified()));
            root.addContent(modifiedElement);
        }

        List entries = feed.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            root.addContent(generateEntryElement((Entry)entries.get(i)));
        }

        List modules = feed.getModules();
        if (modules!=null) {
            ModulesGenerator modulesGenerator = new ModulesGenerator();
            modulesGenerator.generateFeedModules(modules, root);
        }

        return root;
    }

    protected Element generateLinkElement(Link link)
        throws FeedException  {

        Element linkElement = new Element("link", ATOM_NS);

        if (link.getRel() != null) {
            Attribute relAttribute = new Attribute("rel", link.getRel().toString());
            linkElement.setAttribute(relAttribute);
        }

        if (link.getType() != null) {
            Attribute typeAttribute = new Attribute("type", link.getType());
            linkElement.setAttribute(typeAttribute);
        }

        if (link.getHref() != null) {
            Attribute hrefAttribute = new Attribute("href", link.getHref());
            linkElement.setAttribute(hrefAttribute);
        }
        return linkElement;
    }


    protected void fillPersonElement(Element element, Person person)
        throws FeedException  {

        if (person.getName() != null) {
            element.addContent(generateSimpleElement("name", person.getName()));
        }
        if (person.getUrl() != null) {
            element.addContent(generateSimpleElement("url", person.getUrl()));
        }

        if (person.getEmail() != null) {
            element.addContent(generateSimpleElement("email", person.getEmail()));
        }
    }


    protected Element generateTagLineElement(Content tagline)
        throws FeedException  {

        Element taglineElement = new Element("tagline", ATOM_NS);

        if (tagline.getType() != null) {
            Attribute typeAttribute = new Attribute("type", tagline.getType());
            taglineElement.setAttribute(typeAttribute);
        }

        if (tagline.getValue() != null) {
            taglineElement.addContent(tagline.getValue());
        }

        return taglineElement;

    }

    protected void fillContentElement(Element contentElement, Content content)
        throws FeedException  {

        if (content.getType() != null) {
            Attribute typeAttribute = new Attribute("type", content.getType());
            contentElement.setAttribute(typeAttribute);
        }

        Content.Mode mode = content.getMode();
        if (mode != null) {
            Attribute modeAttribute = new Attribute("mode", content.getMode().toString());
            contentElement.setAttribute(modeAttribute);
        }

        if (content.getValue() != null) {

            if (mode == null || mode.equals(Content.ESCAPED)) {
                contentElement.addContent(content.getValue());
            } else if (mode.equals(Content.BASE64)) {
                contentElement.addContent(Base64.encode(content.getValue()));
            } else if (mode.equals(Content.XML)) {

                StringBuffer tmpDocString = new StringBuffer("<tmpdoc>");
                tmpDocString.append(content.getValue());
                tmpDocString.append("</tmpdoc>");
                StringReader tmpDocReader = new StringReader(tmpDocString.toString());
                Document tmpDoc = null;

                try {
                    SAXBuilder saxBuilder = new SAXBuilder();
                    tmpDoc = saxBuilder.build(tmpDocReader);
                }
                catch (Exception ex) {
                    throw new FeedException("Invalid XML",ex);
                }

                List children = tmpDoc.getRootElement().removeContent();
                contentElement.addContent(children);
            }
        }
    }

    protected Element generateGeneratorElement(Generator generator)
        throws FeedException  {

        Element generatorElement = new Element("generator", ATOM_NS);

        if (generator.getUrl() != null) {
            Attribute urlAttribute = new Attribute("url", generator.getUrl());
            generatorElement.setAttribute(urlAttribute);
        }

        if (generator.getVersion() != null) {
            Attribute versionAttribute = new Attribute("version", generator.getVersion());
            generatorElement.setAttribute(versionAttribute);
        }

        if (generator.getValue() != null) {
            generatorElement.addContent(generator.getValue());
        }

        return generatorElement;

    }

    protected Element generateEntryElement(Entry entry)
        throws FeedException  {

        Element entryElement = new Element("entry", ATOM_NS);

        if (entry.getTitle() != null) {
            entryElement.addContent(generateSimpleElement("title", entry.getTitle()));
        }
        List links = entry.getAlternateLinks();
        for (int i = 0; i < links.size(); i++) {
            entryElement.addContent(generateLinkElement((Link)links.get(i)));
        }

        links = entry.getOtherLinks();
        for (int i = 0; i < links.size(); i++) {
            entryElement.addContent(generateLinkElement((Link)links.get(i)));
        }

        if (entry.getAuthor() != null) {
            Element authorElement = new Element("author", ATOM_NS);
            fillPersonElement(authorElement, entry.getAuthor());
            entryElement.addContent(authorElement);
        }

        List contributors = entry.getContributors();
        for (int i = 0; i < contributors.size(); i++) {
            Element contributorElement = new Element("contributor", ATOM_NS);
            fillPersonElement(contributorElement, (Person)contributors.get(i));
            entryElement.addContent(contributorElement);
        }
        if (entry.getId() != null) {
            entryElement.addContent(generateSimpleElement("id", entry.getId()));
        }

        if (entry.getModified() != null) {
            Element modifiedElement = new Element("modified", ATOM_NS);
            modifiedElement.addContent(DateParser.parseW3CDateTime(entry.getModified()));
            entryElement.addContent(modifiedElement);
        }

        if (entry.getIssued() != null) {
            Element issuedElement = new Element("issued", ATOM_NS);
            issuedElement.addContent(DateParser.parseW3CDateTime(entry.getIssued()));
            entryElement.addContent(issuedElement);
        }

        if (entry.getCreated() != null) {
            Element createdElement = new Element("created", ATOM_NS);
            createdElement.addContent(DateParser.parseW3CDateTime(entry.getCreated()));
            entryElement.addContent(createdElement);
        }

        if (entry.getSummary() != null) {
            Element summaryElement = new Element("summary", ATOM_NS);
            fillContentElement(summaryElement, entry.getSummary());
            entryElement.addContent(summaryElement);
        }

        List contents = entry.getContents();
        for (int i = 0; i < contents.size(); i++) {
            Element contentElement = new Element("content", ATOM_NS);
            fillContentElement(contentElement, (Content)contents.get(i));
            entryElement.addContent(contentElement);
        }

        List modules = entry.getModules();
        if (modules!=null) {
            ModulesGenerator modulesGenerator = new ModulesGenerator();
            modulesGenerator.generateFeedModules(modules, entryElement);
        }

        return entryElement;

    }


    protected Element generateSimpleElement(String name, String value)
        throws FeedException  {

        Element element = new Element(name, ATOM_NS);
        element.addContent(value);

        return element;
    }
}
