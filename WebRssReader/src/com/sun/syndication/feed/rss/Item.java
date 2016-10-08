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
package com.sun.syndication.feed.rss;

import com.sun.syndication.common.ObjectBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean for items of RSS feeds.
 * <p>
 * It handles all RSS versions without loosing information.
 * <p>
 * For RSS1.0 it supports Dublin Core and Syndication modules. Note that
 * those modules currently support simple syntax format only.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Item extends ObjectBean {
    private String _title;
    private String _link;
    private Description _description;
    private Source _source;
    private List _enclosures;
    private List _categories;
    private Guid _guid;
    private String _comments;
    private String _author;
    private Date _pubDate;
    private Date _expirationDate;
    private List _modules;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Item() {
    }

    /**
     * Returns the item title.
     * <p>
     * @return the item title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the item title.
     * <p>
     * @param title the item title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the item link.
     * <p>
     * @return the item link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the item link.
     * <p>
     * @param link the item link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the item description.
     * <p>
     * @return the item description, <b>null</b> if none.
     *
     */
    public Description getDescription() {
        return _description;
    }

    /**
     * Sets the item description.
     * <p>
     * @param description the item description to set, <b>null</b> if none.
     *
     */
    public void setDescription(Description description) {
        _description = description;
    }

    /**
     * Returns the item source.
     * <p>
     * @return the item source, <b>null</b> if none.
     *
     */
    public Source getSource() {
        return _source;
    }

    /**
     * Sets the item source.
     * <p>
     * @param source the item source to set, <b>null</b> if none.
     *
     */
    public void setSource(Source source) {
        _source = source;
    }

    /**
     * Returns the item enclosures.
     * <p>
     * @return a list of Enclosure elements with the item enclosures,
     *         an empty list if none.
     *
     */
    public List getEnclosures() {
        return (_enclosures==null) ? (_enclosures=new ArrayList()) : _enclosures;
    }

    /**
     * Sets the item enclosures.
     * <p>
     * @param enclosures the list of Enclosure elements with the item enclosures to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setEnclosures(List enclosures) {
        _enclosures = enclosures;
    }

    /**
     * Returns the item categories.
     * <p>
     * @return a list of Category elements with the item categories,
     *         an empty list if none.
     *
     */
    public List getCategories() {
        return (_categories==null) ? (_categories=new ArrayList()) : _categories;
    }

    /**
     * Sets the item categories.
     * <p>
     * @param categories the list of Categories elements with the item categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setCategories(List categories) {
        _categories = categories;
    }

    /**
     * Returns the item GUID.
     * <p>
     * @return the item GUID, <b>null</b> if none.
     *
     */
    public Guid getGuid() {
        return _guid;
    }

    /**
     * Sets the item GUID.
     * <p>
     * @param guid the item GUID to set, <b>null</b> if none.
     *
     */
    public void setGuid(Guid guid) {
        _guid = guid;
    }

    /**
     * Returns the item comments.
     * <p>
     * @return the item comments, <b>null</b> if none.
     *
     */
    public String getComments() {
        return _comments;
    }

    /**
     * Sets the item comments.
     * <p>
     * @param comments the item comments to set, <b>null</b> if none.
     *
     */
    public void setComments(String comments) {
        _comments = comments;
    }

    /**
     * Returns the item author.
     * <p>
     * @return the item author, <b>null</b> if none.
     *
     */
    public String getAuthor() {
        return _author;
    }

    /**
     * Sets the item author.
     * <p>
     * @param author the item author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(String author) {
        _author = author;
    }

    /**
     * Returns the item modules.
     * <p>
     * @return a list of Module elements with the item modules,
     *         an empty list if none.
     *
     */
    public List getModules() {
        return (_modules==null) ? (_modules=new ArrayList()) : _modules;
    }

    /**
     * Sets the item modules.
     * <p>
     * @param modules the list of Module elements with the item modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

    /**
     * Returns the item publishing date.
     * <p>
     * @return the item publishing date, <b>null</b> if none.
     *
     */
    public Date getPubDate() {
        return _pubDate;
    }

    /**
     * Sets the item publishing date.
     * <p>
     * @param pubDate the item publishing date to set, <b>null</b> if none.
     *
     */
    public void setPubDate(Date pubDate) {
        _pubDate = pubDate;
    }

    /**
     * Returns the item expiration date.
     * <p>
     * @return the item expiration date, <b>null</b> if none.
     *
     */
    public Date getExpirationDate() {
        return _expirationDate;
    }

    /**
     * Sets the item expiration date.
     * <p>
     * @param expirationDate the item expiration date to set, <b>null</b> if none.
     *
     */
    public void setExpirationDate(Date expirationDate) {
        _expirationDate = expirationDate;
    }

}
