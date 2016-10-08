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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.common.ToString;

import java.util.Date;
import java.util.List;

/**
 * Bean interface for all types of feeds.
 * <p>
 * It handles all RSS versions and Atom 0.3, it normalizes all info, it may lose information.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface SyndFeedI extends ToString,Cloneable {

    /**
     * Returns the real feed types the SyndFeed supports when converting from and to.
     * <p>
     * @return the real feed type supported.
     */
    List getSupportedFeedTypes();

    /**
     * Creates a rea feed containing the information of the SyndFeed.
     * <p>
     * The feed type of the created WireFeed is taken from the SyndFeed feedType property.
     * <p>
     * @return the real feed.
     *
     */
    WireFeed createWireFeed();

    /**
     * Creates a real feed containing the information of the SyndFeed.
     * <p>
     * @param feedType the feed type for the WireFeed to be created.
     * @return the real feed.
     *
     */
    WireFeed createWireFeed(String feedType);


    /**
     * Returns the wire feed type the feed had/will-have when coverted from/to a WireFeed.
     * <p>
     * @return the feed type, <b>null</b> if none.
     *
     */
    String getFeedType();

    /**
     * Sets the wire feed type the feed will-have when coverted to a WireFeed.
     * <p>
     * @param feedType the feed type to set, <b>null</b> if none.
     *
     */
    void setFeedType(String feedType);


    /**
     * Returns the feed title.
     * <p>
     * @return the feed title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the feed title.
     * <p>
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the feed link.
     * <p>
     * @return the feed link, <b>null</b> if none.
     *
     */
    String getLink();

    /**
     * Sets the feed link.
     * <p>
     * @param link the feed link to set, <b>null</b> if none.
     *
     */
    void setLink(String link);

    /**
     * Returns the feed description.
     * <p>
     * @return the feed description, <b>null</b> if none.
     *
     */
    String getDescription();

    /**
     * Sets the feed description.
     * <p>
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    void setDescription(String description);

    /**
     * Returns the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @return the feed published date, <b>null</b> if none.
     *
     */
    Date getPublishedDate();

    /**
     * Sets the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @param publishedDate the feed published date to set, <b>null</b> if none.
     *
     */
    void setPublishedDate(Date publishedDate);

    /**
     * Returns the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @return the feed author, <b>null</b> if none.
     *
     */
    String getAuthor();

    /**
     * Sets the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @param author the feed author to set, <b>null</b> if none.
     *
     */
    void setAuthor(String author);

    /**
     * Returns the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     * @return the feed copyright, <b>null</b> if none.
     *
     */
    String getCopyright();

    /**
     * Sets the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     * @param copyright the feed copyright to set, <b>null</b> if none.
     *
     */
    void setCopyright(String copyright);

    /**
     * Returns the feed image.
     * <p>
     * @return the feed image, <b>null</b> if none.
     *
     */
    SyndImageI getImage();

    /**
     * Sets the feed image.
     * <p>
     * @param image the feed image to set, <b>null</b> if none.
     *
     */
    void setImage(SyndImageI image);

    /**
     * Returns the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @return a list of SyndCategory elements with the feed categories,
     *         an empty list if none.
     *
     */
    List getCategories();

    /**
     * Sets the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @param categories the list of SyndCategory elements with the feed categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setCategories(List categories);

    /**
     * Returns the feed entries.
     * <p>
     * @return a list of SyndEntry elements with the feed entries,
     *         an empty list if none.
     *
     */
    List getEntries();

    /**
     * Sets the feed entries.
     * <p>
     * @param entries the list of SyndEntry elements with the feed entries to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setEntries(List entries);

    /**
     * Returns the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     * @return the feed language, <b>null</b> if none.
     *
     */
    String getLanguage();

    /**
     * Sets the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    void setLanguage(String language);

    /**
     * Returns the feed modules.
     * <p>
     * @return a list of Module elements with the feed modules,
     *         an empty list if none.
     *
     */
    List getModules();

    /**
     * Sets the feed modules.
     * <p>
     * @param modules the list of Module elements with the feed modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setModules(List modules);

    /**
     * Creates a deep clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
