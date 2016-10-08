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

import com.sun.syndication.feed.module.DCSubjectI;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * List implementation for SyndCategory elements. To be directly used by the SyndFeed
 * and SyndEntry classes only.
 * <p>
 * It acts as a facade on top of the DCSubject elements of the underlying list
 * and remains in synch with it. It is possible to work on either list, the categories
 * one or the subjects one and they remain in synch.
 * <p>
 * This is necessary because the SyndFeed categories are just a convenience to access
 * the DublinCore subjects.
 * <P>
 * All this mess to avoid making DCSubject implement SyndCategoryI (which it would be odd).
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndCategoryListFacade extends AbstractList {
    private List _subjects;

    /**
     * Default constructor. Creates and empty list.
     */
    public SyndCategoryListFacade() {
        this(new ArrayList());
    }

    /**
     * Creates a facade list of categories on top the given subject list.
     * <P>
     * @param subjects the list of subjects to create the facade.
     *
     */
    public SyndCategoryListFacade(List subjects) {
        _subjects = subjects;
    }

    /**
     * Gets the category by index.
     * <p>
     * @param index the index position to retrieve the category.
     * @return the SyndCategory in position index, <b>null</b> if none.
     *
     */
    public Object get(int index) {
        return new SyndCategory((DCSubjectI) _subjects.get(index));
    }

    /**
     * Returns the size of the list.
     * <p>
     * @return the size of the list.
     *
     */
    public int size() {
        return _subjects.size();
    }

    /**
     * Sets a category in an existing position in the list.
     * <p>
     * @param index position to set the category.
     * @param obj the SyndCategory object to set.
     * @return the SyndCategory object that is being replaced, <b>null</b> if none.
     *
     */
    public Object set(int index,Object obj) {
        SyndCategory sCat = (SyndCategory) obj;
        DCSubjectI subject = (sCat!=null) ? sCat.getSubject() : null;
        subject = (DCSubjectI) _subjects.set(index,subject);
        return (subject!=null) ? new SyndCategory(subject) : null;
    }

    /**
    * Adds a category to the list.
    * <p>
    * @param index position to add the category.
    * @param obj the SyndCategory object to add.
     *
     */
    public void add(int index,Object obj) {
        SyndCategory sCat = (SyndCategory) obj;
        DCSubjectI subject = (sCat!=null) ? sCat.getSubject() : null;
        _subjects.add(index,subject);
    }

    /**
     * Removes a category element from a specific position.
     * <p>
     * @param index position to remove the category from.
     * @return the SyndCategory being removed from position index, <b>null</b> if none.
     *
     */
    public Object remove(int index) {
        DCSubjectI subject = (DCSubjectI) _subjects.remove(index);
        return (subject!=null) ? new SyndCategory(subject) : null;
    }

    /**
     * Returns a list with the DCSubjectI elements of the SyndCategory list facade.
     * To be used by the SyndFeed class only.
     * <p>
     * @param cList the list with SyndCategory elements to convert to subject list.
     * @return a list with DCSubjectI elements corresponding to the categories in the given list.
     *
     */
    public static List convertElementsSyndCategoryToSubject(List cList) {
        List sList = null;
        if (cList!=null) {
            sList = new ArrayList();
            for (int i=0;i<cList.size();i++) {
                SyndCategory sCat = (SyndCategory) cList.get(i);
                DCSubjectI subject = null;
                if (sCat!=null) {
                    subject = sCat.getSubject();
                }
                sList.add(subject);
            }
        }
        return sList;
    }

}
