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
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.ModuleI;
import com.sun.syndication.feed.module.DCModuleI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean for entries of SyndFeed feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndEntry extends ObjectBean implements SyndEntryI {
    private String _title;
    private String _link;
    private SyndContentI _description;
    private List _contents;
    private List _modules;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndEntry() {
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
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the entry link.
     * <p>
     * @return the entry link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the entry link.
     * <p>
     * @param link the entry link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the entry description.
     * <p>
     * @return the entry description, <b>null</b> if none.
     *
     */
    public SyndContentI getDescription() {
        return _description;
    }

    /**
     * Sets the entry description.
     * <p>
     * @param description the entry description to set, <b>null</b> if none.
     *
     */
    public void setDescription(SyndContentI description) {
        _description = description;
    }

    /**
     * Returns the entry contents.
     * <p>
     * @return a list of SyndContent elements with the entry contents,
     *         an empty list if none.
     *
     */
    public List getContents() {
        return (_contents==null) ? (_contents=new ArrayList()) : _contents;
    }

    /**
     * Sets the entry contents.
     * <p>
     * @param contents the list of SyndContent elements with the entry contents to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContents(List contents) {
        _contents = contents;
    }


    /**
     * Returns the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @return the entry published date, <b>null</b> if none.
     *
     */
    public Date getPublishedDate() {
        return getDCModule().getDate();
    }

    /**
     * Sets the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @param publishedDate the entry published date to set, <b>null</b> if none.
     *
     */
    public void setPublishedDate(Date publishedDate) {
        getDCModule().setDate(publishedDate);
    }

    /**
     * Returns the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @return the entry author, <b>null</b> if none.
     *
     */
    public String getAuthor() {
        return getDCModule().getCreator();
    }

    /**
     * Sets the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @param author the entry author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(String author) {
        getDCModule().setCreator(author);
    }

    /**
     * Returns the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @return a list of SyndCategory elements with the entry categories,
     *         an empty list if none.
     *
     */
    public List getCategories() {
       return new SyndCategoryListFacade(getDCModule().getSubjects());
    }

    /**
     * Sets the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @param categories the list of SyndCategory elements with the entry categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setCategories(List categories) {
        getDCModule().setSubjects(SyndCategoryListFacade.convertElementsSyndCategoryToSubject(categories));
    }

    /**
     * Returns the entry modules.
     * <p>
     * @return a list of Module elements with the entry modules,
     *         an empty list if none.
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

    /**
     * Returns the Dublin Core module of the entry.
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
