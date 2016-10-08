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

import com.sun.syndication.common.ToString;

import java.util.Date;
import java.util.List;

/**
 * Bean interface for entries of SyndFeed feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface SyndEntryI extends ToString,Cloneable {
    /**
     * Returns the entry title.
     * <p>
     * @return the entry title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the entry title.
     * <p>
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the entry link.
     * <p>
     * @return the entry link, <b>null</b> if none.
     *
     */
    String getLink();

    /**
     * Sets the entry link.
     * <p>
     * @param link the entry link to set, <b>null</b> if none.
     *
     */
    void setLink(String link);

    /**
     * Returns the entry description.
     * <p>
     * @return the entry description, <b>null</b> if none.
     *
     */
    SyndContentI getDescription();

    /**
     * Sets the entry description.
     * <p>
     * @param description the entry description to set, <b>null</b> if none.
     *
     */
    void setDescription(SyndContentI description);

    /**
     * Returns the entry contents.
     * <p>
     * @return a list of SyndContent elements with the entry contents,
     *         an empty list if none.
     *
     */
    List getContents();

    /**
     * Sets the entry contents.
     * <p>
     * @param contents the list of SyndContent elements with the entry contents to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setContents(List contents);

    /**
     * Returns the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @return the entry published date, <b>null</b> if none.
     *
     */
    Date getPublishedDate();

    /**
     * Sets the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @param publishedDate the entry published date to set, <b>null</b> if none.
     *
     */
    void setPublishedDate(Date publishedDate);

    /**
     * Returns the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @return the entry author, <b>null</b> if none.
     *
     */
    String getAuthor();

    /**
     * Sets the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @param author the entry author to set, <b>null</b> if none.
     *
     */
    void setAuthor(String author);

    /**
     * Returns the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @return a list of SyndCategory elements with the entry categories,
     *         an empty list if none.
     *
     */
    List getCategories();

    /**
     * Sets the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @param categories the list of SyndCategory elements with the entry categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setCategories(List categories);

    /**
     * Returns the entry modules.
     * <p>
     * @return a list of Module elements with the entry modules,
     *         an empty list if none.
     *
     */
    List getModules();

    /**
     * Sets the entry modules.
     * <p>
     * @param modules the list of Module elements with the entry modules to set,
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
