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

import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.module.ModuleI;
import com.sun.syndication.feed.module.DCModuleI;
import com.sun.syndication.feed.module.DCSubjectI;
import com.sun.syndication.io.ModuleParser;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.List;

public class DCModuleParser implements ModuleParser {

    public String getNamespaceUri() {
        return DCModuleI.URI;
    }

    private Namespace getDCNamespace() {
        return Namespace.getNamespace(DCModuleI.URI);
    }

    public ModuleI parse(Element dcRoot) {
        boolean foundSomething = false;
        DCModuleI dcm = new DCModule();

        Element e = dcRoot.getChild("title",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setTitle(e.getText());
        }
        e = dcRoot.getChild("creator",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setCreator(e.getText());
        }
        List eList = dcRoot.getChildren("subject",getDCNamespace());
        if (eList.size()>0) {
            foundSomething = true;
            dcm.setSubjects(parseSubjects(eList));
        }
        e = dcRoot.getChild("description",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setDescription(e.getText());
        }
        e = dcRoot.getChild("publisher",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setPublisher(e.getText());
        }
        eList = dcRoot.getChildren("contributor",getDCNamespace());
        if (eList.size()>0) {
            foundSomething = true;
            dcm.setContributors(parseContributors(eList));
        }
        e = dcRoot.getChild("date",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setDate(DateParser.parseW3CDateTime(e.getText()));
        }
        e = dcRoot.getChild("type",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setType(e.getText());
        }
        e = dcRoot.getChild("format",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setFormat(e.getText());
        }
        e = dcRoot.getChild("identifier",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setIdentifier(e.getText());
        }
        e = dcRoot.getChild("source",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setSource(e.getText());
        }
        e = dcRoot.getChild("language",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setLanguage(e.getText());
        }
        e = dcRoot.getChild("relation",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setRelation(e.getText());
        }
        e = dcRoot.getChild("coverage",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setCoverage(e.getText());
        }
        e = dcRoot.getChild("rights",getDCNamespace());
        if (e!=null) {
            foundSomething = true;
            dcm.setRights(e.getText());
        }

        return (foundSomething) ? dcm : null;
    }

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";

    private String getTaxonomy(Element desc) {
        String d = null;
        Element taxo = desc.getChild("topic",Namespace.getNamespace(TAXO_URI));
        if (taxo!=null) {
            Attribute a = taxo.getAttribute("resource",Namespace.getNamespace((RDF_URI)));
            if (a!=null) {
                d = a.getValue();
            }
        }
        return d;
    }



    private List parseSubjects(List eList) {
        List subjects = new ArrayList();
        for (int i=0;i<eList.size();i++) {
            Element eSubject = (Element) eList.get(i);
            Element eDesc = eSubject.getChild("Description",Namespace.getNamespace(RDF_URI));
            if (eDesc!=null) {
                String taxonomy = getTaxonomy(eDesc);
                List eValues = eDesc.getChildren("value",Namespace.getNamespace(RDF_URI));
                for (int j=0;j<eValues.size();j++) {
                    Element eValue = (Element) eValues.get(j);
                    DCSubjectI subject = new DCSubject();
                    subject.setTaxonomyUri(taxonomy);;
                    subject.setValue(eValue.getText());
                    subjects.add(subject);
                }
            }
            else {
                DCSubjectI subject = new DCSubject();
                subject.setValue(eSubject.getText());
                subjects.add(subject);
            }
        }
        return subjects;
    }

    private List parseContributors(List eList) {
        List contributors = new ArrayList();
        for (int i=0;i<eList.size();i++) {
            Element e = (Element) eList.get(i);
            contributors.add(e.getText());
        }
        return contributors;

    }

}
