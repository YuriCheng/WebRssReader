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
package com.sun.syndication.feed.synd;

import com.sun.syndication.common.ObjectBean;
import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.impl.Converters;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.ModuleI;
import com.sun.syndication.feed.module.DCModuleI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean for all types of feeds.
 * <p>
 * It handles all RSS versions and Atom 0.3, it normalizes all info, it may lose information.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndFeed extends ObjectBean implements SyndFeedI {
    private String _title;
    private String _feedType;
    private String _link;
    private String _description;
    private SyndImageI _image;
    private List _entries;
    private List _modules;

    private static final Converters CONVERTERS = new Converters();

    /**
     * Returns the real feed types the SyndFeed supports when converting from and to.
     * <p>
     * @return the real feed type supported.
     */
    public List getSupportedFeedTypes() {
        return CONVERTERS.getSupportedFeedTypes();
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndFeed() {
        this(null);
    }

    /**
     * Creates a SyndFeed an populates all its properties out of the
     * given RSS Channel or Atom Feed properties.
     * <p>
     * @param feed the RSS Channel or the Atom Feed to populate the properties from.
     *
     */
    public SyndFeed(WireFeed feed) {
        if (feed!=null) {
            _feedType = feed.getFeedType();
            Converter converter = (Converter) CONVERTERS.getConverter(_feedType);
            if (converter==null) {
                throw new IllegalArgumentException("Invalid feed type ["+_feedType+"]");
            }
            converter.copyInto(feed,this);
        }
    }

    /**
     * Creates a real feed containing the information of the SyndFeed.
     * <p>
     * The feed type of the created WireFeed is taken from the SyndFeed feedType property.
     * <p>
     * @return the real feed.
     *
     */
    public WireFeed createWireFeed() {
        return createWireFeed(_feedType);
    }

    /**
     * Creates a real feed containing the information of the SyndFeed.
     * <p>
     * @param feedType the feed type for the WireFeed to be created.
     * @return the real feed.
     *
     */
    public WireFeed createWireFeed(String feedType) {
        if (feedType==null) {
            throw new IllegalArgumentException("Feed type cannot be null");
        }
        Converter converter = (Converter) CONVERTERS.getConverter(feedType);
        if (converter==null) {
            throw new IllegalArgumentException("Invalid feed type ["+feedType+"]");
        }
        return converter.createRealFeed(this);
    }

    /**
     * Returns the wire feed type the feed had/will-have when coverted from/to a WireFeed.
     * <p>
     * @return the feed type, <b>null</b> if none.
     *
     */
    public String getFeedType() {
        return _feedType;
    }

    /**
     * Sets the wire feed type the feed will-have when coverted to a WireFeed.
     * <p>
     * @param feedType the feed type to set, <b>null</b> if none.
     *
     */
    public void setFeedType(String feedType) {
        _feedType = feedType;
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
     * Returns the feed link.
     * <p>
     * @return the feed link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the feed link.
     * <p>
     * @param link the feed link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the feed description.
     * <p>
     * @return the feed description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the feed description.
     * <p>
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * Returns the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @return the feed published date, <b>null</b> if none.
     *
     */
    public Date getPublishedDate() {
        return getDCModule().getDate();
    }

    /**
     * Sets the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @param publishedDate the feed published date to set, <b>null</b> if none.
     *
     */
    public void setPublishedDate(Date publishedDate) {
        getDCModule().setDate(publishedDate);
    }

    /**
     * Returns the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @return the feed author, <b>null</b> if none.
     *
     */
    public String getAuthor() {
        return getDCModule().getCreator();
    }

    /**
     * Sets the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @param author the feed author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(String author) {
        getDCModule().setCreator(author);
    }

    /**
     * Returns the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     * @return the feed copyright, <b>null</b> if none.
     *
     */
    public String getCopyright() {
        return getDCModule().getRights();
    }

    /**
     * Sets the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     * @param copyright the feed copyright to set, <b>null</b> if none.
     *
     */
    public void setCopyright(String copyright) {
        getDCModule().setRights(copyright);
    }

    /**
     * Returns the feed image.
     * <p>
     * @return the feed image, <b>null</b> if none.
     *
     */
    public SyndImageI getImage() {
        return _image;
    }

    /**
     * Sets the feed image.
     * <p>
     * @param image the feed image to set, <b>null</b> if none.
     *
     */
    public void setImage(SyndImageI image) {
        _image = image;
    }

    /**
     * Returns the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @return a list of SyndCategory elements with the feed categories,
     *         an empty list if none.
     *
     */
    public List getCategories() {
        return new SyndCategoryListFacade(getDCModule().getSubjects());
    }

    /**
     * Sets the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @param categories the list of SyndCategory elements with the feed categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setCategories(List categories) {
        getDCModule().setSubjects(SyndCategoryListFacade.convertElementsSyndCategoryToSubject(categories));
    }

    /**
     * Returns the feed entries.
     * <p>
     * @return a list of SyndEntry elements with the feed entries,
     *         an empty list if none.
     *
     */
    public List getEntries() {
        return (_entries==null) ? (_entries=new ArrayList()) : _entries;
    }

    /**
     * Sets the feed entries.
     * <p>
     * @param entries the list of SyndEntry elements with the feed entries to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setEntries(List entries) {
        _entries = entries;
    }

    /**
     * Returns the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     * @return the feed language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return getDCModule().getLanguage();
    }

    /**
     * Sets the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        getDCModule().setLanguage(language);
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
     * Sets the feed modules.
     * <p>
     * @param modules the list of Module elements with the feed modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

    /**
     * Returns the Dublin Core module of the feed.
     * @return the DC module, it's never <b>null</b>
     *
     */
    private DCModuleI getDCModule() {
        DCModuleI dcModule = null;
        List modules = getModules();
        for (int i=0;dcModule==null && i<modules.size();i++) {
            ModuleI module = (ModuleI) modules.get(i);
            if (module.getUri().equals(DCModuleI.URI)) {
                dcModule = (DCModuleI) module;
            }
        }
        if (dcModule==null) {
            dcModule = new DCModule();
            modules.add(dcModule);
        }
        return dcModule;
    }

}
