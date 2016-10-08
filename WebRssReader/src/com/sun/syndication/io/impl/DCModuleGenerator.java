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

import com.sun.syndication.feed.module.ModuleI;
import com.sun.syndication.feed.module.DCModuleI;
import com.sun.syndication.feed.module.DCSubjectI;
import com.sun.syndication.io.ModuleGenerator;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.List;

/**
 * Feed Generator for DC Module
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class DCModuleGenerator implements ModuleGenerator {

    private static final String DC_URI  = "http://purl.org/dc/elements/1.1/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";
    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private static final Namespace DC_NS  = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);

    public String getNamespaceUri() {
        return DC_URI;
    }

    public void generate(ModuleI module, Element element) {

        DCModuleI dcModule = (DCModuleI)module;

        if (dcModule.getTitle() != null) {
            element.addContent(generateSimpleElement("title", dcModule.getTitle()));
        }
        if (dcModule.getCreator() != null) {
            element.addContent(generateSimpleElement("creator", dcModule.getCreator()));
        }
        List subjects = dcModule.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            element.addContent(generateSubjectElement((DCSubjectI) subjects.get(i)));
        }
        if (dcModule.getDescription() != null) {
            element.addContent(generateSimpleElement("description", dcModule.getDescription()));
        }
        if (dcModule.getPublisher() != null) {
            element.addContent(generateSimpleElement("publisher", dcModule.getPublisher()));
        }
        List contributors = dcModule.getContributors();
        if (contributors != null) {
            for (int i = 0; i < contributors.size(); i++) {
                String contributor = (String)contributors.get(i);
                element.addContent(generateSimpleElement("contributor", contributor));
            }
        }
        if (dcModule.getDate() != null) {
            element.addContent(
                generateSimpleElement("date", DateParser.parseW3CDateTime(dcModule.getDate())));
        }
        if (dcModule.getType() != null) {
            element.addContent(generateSimpleElement("type", dcModule.getType()));
        }
        if (dcModule.getFormat() != null) {
            element.addContent(generateSimpleElement("format", dcModule.getFormat()));
        }
        if (dcModule.getIdentifier() != null) {
            element.addContent(generateSimpleElement("identifier", dcModule.getIdentifier()));
        }
        if (dcModule.getSource() != null) {
            element.addContent(generateSimpleElement("source", dcModule.getSource()));
        }
        if (dcModule.getLanguage() != null) {
            element.addContent(generateSimpleElement("language", dcModule.getLanguage()));
        }
        if (dcModule.getRelation() != null) {
            element.addContent(generateSimpleElement("relation", dcModule.getRelation()));
        }
        if (dcModule.getCoverage() != null) {
            element.addContent(generateSimpleElement("coverage", dcModule.getCoverage()));
        }
        if (dcModule.getRights() != null) {
            element.addContent(generateSimpleElement("rights", dcModule.getRights()));
        }
    }

    protected Element generateSubjectElement(DCSubjectI subject) {

        Element subjectElement = new Element("subject", DC_NS);

        if (subject.getTaxonomyUri() != null) {
            Element descriptionElement = new Element("Description", RDF_NS);
            Element topicElement = new Element("topic", TAXO_NS);
            Attribute resourceAttribute = new Attribute("resource", subject.getTaxonomyUri(), RDF_NS);
            topicElement.setAttribute(resourceAttribute);
            descriptionElement.addContent(topicElement);

            if (subject.getValue() != null) {
                Element valueElement = new Element("value", RDF_NS);
                valueElement.addContent(subject.getValue());
                descriptionElement.addContent(valueElement);
            }
            subjectElement.addContent(descriptionElement);
        } else {
            subjectElement.addContent(subject.getValue());
        }
        return subjectElement;
    }


    protected Element generateSimpleElement(String name, String value)  {

        Element element = new Element(name, DC_NS);
        element.addContent(value);

        return element;
    }

}
