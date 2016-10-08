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
package com.sun.syndication.feed.atom;

import com.sun.syndication.feed.WireFeed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean for Atom feeds.
 * <p>
 * It handles Atom feeds version 0.3 without loosing any feed information.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Feed extends WireFeed {
    private String _language;
    private String _title;
    private List _alternateLinks;
    private List _otherLinks;
    private Person _author;
    private List _contributors;
    private Content _tagline;
    private String _id;
    private Generator _generator;
    private String _copyright;
    private Content _info;
    private Date _modified;
    private List _entries;
    private List _modules;

    /**
     * Default constructor, for bean cloning purposes only.
     *
     */
    public Feed() {
        super();
    }

    /**
     * Feed Constructor. All properties, except the type, are set to <b>null</b>.
     * <p>
     * @param type the type of the Atom feed.
     *
     */
    public Feed(String type) {
        super(type);
    }

    /**
     * Returns the feed language.
     * <p>
     * @return the feed language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return _language;
    }

    /**
     * Sets the feed language.
     * <p>
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        _language = language;
    }

    /**
     * Returns the feed title.
     * <p>
     * @return the feed title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the feed title.
     * <p>
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the feed alternate links.
     * <p>
     * @return a list of Link elements with the feed alternate links,
     *         an empty list if none.
     *
     */
    public List getAlternateLinks() {
        return (_alternateLinks==null) ? (_alternateLinks=new ArrayList()) : _alternateLinks;
    }

    /**
     * Sets the feed alternate links.
     * <p>
     * @param alternateLinks the list of Link elements with the feed alternate links to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setAlternateLinks(List alternateLinks) {
        _alternateLinks = alternateLinks;
    }

    /**
     * Returns the feed other links (non-alternate ones).
     * <p>
     * @return a list of Link elements with the feed other links (non-alternate ones),
     *         an empty list if none.
     *
     */
    public List getOtherLinks() {
        return (_otherLinks==null) ? (_otherLinks=new ArrayList()) : _otherLinks;
    }

    /**
     * Sets the feed other links (non-alternate ones).
     * <p>
     * @param otherLinks the list of Link elements with the feed other links (non-alternate ones) to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setOtherLinks(List otherLinks) {
        _otherLinks = otherLinks;
    }

    /**
     * Returns the feed author.
     * <p>
     * @return the feed author, <b>null</b> if none.
     *
     */
    public Person getAuthor() {
        return _author;
    }

    /**
     * Sets the feed author.
     * <p>
     * @param author the feed author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(Person author) {
        _author = author;
    }

    /**
     * Returns the feed contributors.
     * <p>
     * @return a list of Person elements with the feed contributors,
     *         an empty list if none.
     *
     */
    public List getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList()) : _contributors;
    }

    /**
     * Sets the feed contributors.
     * <p>
     * @param contributors the list of Person elements with the feed contributors to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContributors(List contributors) {
        _contributors = contributors;
    }

    /**
     * Returns the feed tag line.
     * <p>
     * @return the feed tag line, <b>null</b> if none.
     *
     */
    public Content getTagline() {
        return _tagline;
    }

    /**
     * Sets the feed tagline.
     * <p>
     * @param tagline the feed tagline to set, <b>null</b> if none.
     *
     */
    public void setTagline(Content tagline) {
        _tagline = tagline;
    }

    /**
     * Returns the feed ID.
     * <p>
     * @return the feed ID, <b>null</b> if none.
     *
     */
    public String getId() {
        return _id;
    }

    /**
     * Sets the feed ID.
     * <p>
     * @param id the feed ID to set, <b>null</b> if none.
     *
     */
    public void setId(String id) {
        _id = id;
    }

    /**
     * Returns the feed generator.
     * <p>
     * @return the feed generator, <b>null</b> if none.
     *
     */
    public Generator getGenerator() {
        return _generator;
    }

    /**
     * Sets the feed generator.
     * <p>
     * @param generator the feed generator to set, <b>null</b> if none.
     *
     */
    public void setGenerator(Generator generator) {
        _generator = generator;
    }

    /**
     * Returns the feed copyright.
     * <p>
     * @return the feed copyright, <b>null</b> if none.
     *
     */
    public String getCopyright() {
        return _copyright;
    }

    /**
     * Sets the feed copyright.
     * <p>
     * @param copyright the feed copyright to set, <b>null</b> if none.
     *
     */
    public void setCopyright(String copyright) {
        _copyright = copyright;
    }

    /**
     * Returns the feed info.
     * <p>
     * @return the feed info, <b>null</b> if none.
     *
     */
    public Content getInfo() {
        return _info;
    }

    /**
     * Sets the feed info.
     * <p>
     * @param info the feed info to set, <b>null</b> if none.
     *
     */
    public void setInfo(Content info) {
        _info = info;
    }

    /**
     * Returns the feed modified date.
     * <p>
     * @return the feed modified date, <b>null</b> if none.
     *
     */
    public Date getModified() {
        return _modified;
    }

    /**
     * Sets the feed modified date.
     * <p>
     * @param modified the feed modified date to set, <b>null</b> if none.
     *
     */
    public void setModified(Date modified) {
        _modified = modified;
    }

    /**
     * Returns the feed entries.
     * <p>
     * @return a list of Entry elements with the feed entries,
     *         an empty list if none.
     *
     */
    public List getEntries() {
        return (_entries==null) ? (_entries=new ArrayList()) : _entries;
    }

    /**
     * Sets the feed entries.
     * <p>
     * @param entries the list of Entry elements with the feed entries to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setEntries(List entries) {
        _entries = entries;
    }

    /**
     * Returns the feed modules.
     * <p>
     * @return a list of Module elements with the feed modules,
     *         an empty list if none.
     *
     */
    public List getModules() {
        return (_modules==null) ? (_modules=new ArrayList()) : _modules;
    }

    /**
     * Sets the feed moduless.
     * <p>
     * @param modules the list of Module elements with the feed moduless to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

}

