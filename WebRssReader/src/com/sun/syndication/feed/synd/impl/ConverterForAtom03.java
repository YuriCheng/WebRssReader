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
package com.sun.syndication.feed.synd.impl;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.io.impl.ModuleUtils;
import com.sun.syndication.feed.synd.SyndFeedI;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndEntryI;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndContentI;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 */
public class ConverterForAtom03 implements Converter {

    public String getType() {
        return "atom_0.3";
    }

    public void copyInto(WireFeed feed,SyndFeedI syndFeed) {
        Feed aFeed = (Feed) feed;
        syndFeed.setTitle(aFeed.getTitle());

        Link link = (Link) aFeed.getAlternateLinks().get(0);
        syndFeed.setLink(link.getHref());

        Content info = aFeed.getInfo();
        if (info!=null) {
            syndFeed.setDescription(info.getValue());
        }

        syndFeed.setModules(ModuleUtils.cloneModules(aFeed.getModules()));

        List aEntries = aFeed.getEntries();
        if (aEntries!=null) {
            syndFeed.setEntries(createSyndEntries(aEntries));
        }

        // Core Atom language/author/copyright/modified elements have precedence
        // over DC equivalent info.

        String language = aFeed.getLanguage();
        if (language!=null) {
            syndFeed.setLanguage(language);
        }

        Person author = aFeed.getAuthor();
        if (author!=null && author.getName()!=null) {
            syndFeed.setAuthor(author.getName());
        }

        String copyright = aFeed.getCopyright();
        if (copyright!=null) {
            syndFeed.setCopyright(copyright);
        }

        Date date = aFeed.getModified();
        if (date!=null) {
            syndFeed.setPublishedDate(date);
        }

    }

    protected List createSyndEntries(List atomEntries) {
        List syndEntries = new ArrayList();
        for (int i=0;i<atomEntries.size();i++) {
            syndEntries.add(createSyndEntry((Entry) atomEntries.get(i)));
        }
        return syndEntries;
    }

    protected SyndEntryI createSyndEntry(Entry entry) {
        SyndEntryI syndEntry = new SyndEntry();

        syndEntry.setTitle(entry.getTitle());

        Link link = (Link) entry.getAlternateLinks().get(0);
        syndEntry.setLink(link.getHref());

        Content content = entry.getSummary();
        if (content==null) {
            List contents = entry.getContents();
            if (contents!=null && contents.size()>0) {
                content = (Content) contents.get(0);
            }
        }

        List contents = entry.getContents();
        if (contents.size()>0) {
            List sContents = new ArrayList();
            for (int i=0;i<contents.size();i++) {
                content = (Content) contents.get(i);
                SyndContentI sContent = new SyndContent();
                sContent.setType(content.getType());
                sContent.setValue(content.getValue());
                sContents.add(sContent);
            }
            syndEntry.setContents(sContents);
        }

        syndEntry.setModules(ModuleUtils.cloneModules(entry.getModules()));

        // Core Atom author/modified elements have precedence
        // over DC equivalent info.

        Person author = entry.getAuthor();
        if (author!=null && author.getName()!=null) {
            syndEntry.setAuthor(author.getName());
        }

        Date date = entry.getModified();
        if (date!=null) {
            syndEntry.setPublishedDate(date);
        }


        return syndEntry;
    }

    public WireFeed createRealFeed(SyndFeedI syndFeed) {
        Feed aFeed = new Feed(getType());

        aFeed.setTitle(syndFeed.getTitle());

        String sLink = syndFeed.getLink();
        if (sLink!=null) {
            Link link = new Link();
            link.setRel(Link.ALTERNATE);
            link.setHref(sLink);
            List list = new ArrayList();
            list.add(link);
            aFeed.setAlternateLinks(list);
        }

        String sDesc = syndFeed.getDescription();
        if (sDesc!=null) {
            Content info = new Content();
            info.setValue(sDesc);
            aFeed.setInfo(info);
        }

        aFeed.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));

        aFeed.setLanguage(syndFeed.getLanguage());

        String sAuthor = syndFeed.getAuthor();
        if (sAuthor!=null) {
            Person person = new Person();
            person.setName(sAuthor);
            aFeed.setAuthor(person);
        }

        aFeed.setCopyright(syndFeed.getCopyright());

        aFeed.setModified(syndFeed.getPublishedDate());

        List sEntries = syndFeed.getEntries();
        if (sEntries!=null) {
            aFeed.setEntries(createAtomEntries(sEntries));
        }

        return aFeed;
    }


    protected List createAtomEntries(List syndEntries) {
        List atomEntries = new ArrayList();
        for (int i=0;i<syndEntries.size();i++) {
            atomEntries.add(createAtomEntry((SyndEntryI)syndEntries.get(i)));
        }
        return atomEntries;
    }

    protected Entry createAtomEntry(SyndEntryI sEntry) {
        Entry aEntry = new Entry();

        aEntry.setTitle(sEntry.getTitle());

        String sLink = sEntry.getLink();
        if (sLink!=null) {
            Link link = new Link();
            link.setRel(Link.ALTERNATE);
            link.setHref(sLink);
            List list = new ArrayList();
            list.add(link);
            aEntry.setAlternateLinks(list);
        }

        SyndContentI sContent = sEntry.getDescription();
        if (sContent!=null) {
            Content content = new Content();
            content.setType(sContent.getType());
            content.setValue(sContent.getValue());
            content.setMode(Content.ESCAPED);
            aEntry.setSummary(content);
        }

        List contents = sEntry.getContents();
        if (contents.size()>0) {
            List aContents = new ArrayList();
            for (int i=0;i<contents.size();i++) {
                sContent = (SyndContent) contents.get(i);
                Content content = new Content();
                content.setType(sContent.getType());
                content.setValue(sContent.getValue());
                content.setMode(Content.ESCAPED);
                aContents.add(content);

            }
            aEntry.setContents(aContents);
        }

        aEntry.setModules(ModuleUtils.cloneModules(sEntry.getModules()));

        String sAuthor = sEntry.getAuthor();
        if (sAuthor!=null) {
            Person person = new Person();
            person.setName(sAuthor);
            aEntry.setAuthor(person);
        }

        aEntry.setModified(sEntry.getPublishedDate());

        return aEntry;
    }

}
