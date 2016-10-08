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

import com.sun.syndication.common.ObjectBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean for entry elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Entry extends ObjectBean {
    private String _title;
    private List _alternateLinks;
    private List _otherLinks;
    private Person _author;
    private List _contributors;
    private String _id;
    private Date _modified;
    private Date _issued;
    private Date _created;
    private Content _summary;
    private List _contents;
    private List _modules;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Entry() {
    }

    /**
     * Returns the entry title.
     * <p>
     * @return the entry title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the entry title.
     * <p>
     * @param title the entry title, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the entry alternate links.
     * <p>
     * @return a list of Link elements with the entry alternate links, an empty list if none.
     *
     */
    public List getAlternateLinks() {
        return (_alternateLinks==null) ? (_alternateLinks=new ArrayList()) : _alternateLinks;
    }

    /**
     * Sets the entry alternate links.
     * <p>
     * @param alternateLinks the list of Link elements with the entry alternate links to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setAlternateLinks(List alternateLinks) {
        _alternateLinks = alternateLinks;
    }

    /**
     * Returns the entry non-alternate links.
     * <p>
     * @return the list of Link elements with the entry non-alternate links to set,
     *         an empty list if none.
     *
     */
    public List getOtherLinks() {
        return (_otherLinks==null) ? (_otherLinks=new ArrayList()) : _otherLinks;
    }

    /**
     * Sets the entry non-alternate links.
     * <p>
     * @param otherLinks the list Link elements with the entry non-alternate links to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setOtherLinks(List otherLinks) {
        _otherLinks = otherLinks;
    }

    /**
     * Returns the entry author.
     * <p>
     * @return the entry author, <b>null</b> if none.
     *
     */
    public Person getAuthor() {
        return _author;
    }

    /**
     * Sets the author of the entry.
     * <p>
     * @param author the author of the entry, <b>null</b> if none.
     *
     */
    public void setAuthor(Person author) {
        _author = author;
    }

    /**
     * Returns the entry contributors.
     * <p>
     * @return a list of Person elements with the entry contributors,
     *         an empty list if none.
     *
     */
    public List getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList()) : _contributors;
    }

    /**
     * Sets the entry contributors.
     * <p>
     * @param contributors the list of Person elements with the entry contributors to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContributors(List contributors) {
        _contributors = contributors;
    }

    /**
     * Returns the entry ID.
     * <p>
     * @return the entry ID, <b>null</b> if none.
     *
     */
    public String getId() {
        return _id;
    }

    /**
     * Sets the entry ID.
     * <p>
     * @param id the entry ID, <b>null</b> if none.
     *
     */
    public void setId(String id) {
        _id = id;
    }

    /**
     * Returns the entry modified date.
     * <p>
     * @return the entry modified date, <b>null</b> if none.
     *
     */
    public Date getModified() {
        return _modified;
    }

    /**
     * Sets the entry modified date.
     * <p>
     * @param modified the entry modified date, <b>null</b> if none.
     *
     */
    public void setModified(Date modified) {
        _modified = modified;
    }

    /**
     * Returns the entry issued date.
     * <p>
     * @return the entry issued date, <b>null</b> if none.
     *
     */
    public Date getIssued() {
        return _issued;
    }

    /**
     * Sets the entry issued date.
     * <p>
     * @param issued the entry issued date, <b>null</b> if none.
     *
     */
    public void setIssued(Date issued) {
        _issued = issued;
    }

    /**
     * Returns the entry created date.
     * <p>
     * @return the entry created date, <b>null</b> if none.
     *
     */
    public Date getCreated() {
        return _created;
    }

    /**
     * Sets the entry created date.
     * <p>
     * @param created the entry created date, <b>null</b> if none.
     *
     */
    public void setCreated(Date created) {
        _created = created;
    }

    /**
     * Returns the entry summary.
     * <p>
     * @return  the entry summary, <b>null</b> if none.
     *
     */
    public Content getSummary() {
        return _summary;
    }

    /**
     * Sets the entry summary.
     * <p>
     * @param summary the entry summary, <b>null</b> if none.
     *
     */
    public void setSummary(Content summary) {
        _summary = summary;
    }

    /**
     * Returns the entry contents.
     * <p>
     * @return a list of Content elements with the entry contents,
     *         an empty list if none.
     *
     */
    public List getContents() {
        return (_contents==null) ? (_contents=new ArrayList()) : _contents;
    }

    /**
     * Sets the entry contents.
     * <p>
     * @param contents the list of Content elements with the entry contents to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContents(List contents) {
        _contents = contents;
    }

    /**
     * Returns the entry modules.
     * <p>
     * @return a list of Module elements with the entry modules,
     *         an emtpy list if none.
     *
     */
    public List getModules() {
        return (_modules==null) ? (_modules=new ArrayList()) : _modules;
    }

    /**
     * Sets the entry modules.
     * <p>
     * @param modules the list of Module elements with the entry modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

}
