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
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.module.DCSubjectI;

/**
 * Bean for categories of SyndFeed feeds and entries.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndCategory extends ObjectBean implements SyndCategoryI {
    private DCSubjectI _subject;
    private String _name;
    private String _taxonomyUri;

    /**
     * Package private constructor, used by SyndCategoryListFacade.
     * <p>
     * @param subject the DC subject to wrap.
     *
     */
    SyndCategory(DCSubjectI subject) {
        _subject = subject;
    }

    /**
     * Package private constructor, used by SyndCategoryListFacade.
     * <p>
     * @return the DC subject being wrapped.
     *
     */
    DCSubjectI getSubject() {
        return _subject;
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndCategory() {
        this(new DCSubject());
    }

    /**
     * Returns the category name.
     * <p>
     * @return the category name, <b>null</b> if none.
     *
     */
    public String getName() {
        return _subject.getValue();
    }

    /**
     * Sets the category name.
     * <p>
     * @param name the category name to set, <b>null</b> if none.
     *
     */
    public void setName(String name) {
        _subject.setValue(name);
    }

    /**
     * Returns the category taxonomy URI.
     * <p>
     * @return the category taxonomy URI, <b>null</b> if none.
     *
     */
    public String getTaxonomyUri() {
        return _subject.getTaxonomyUri();
    }

    /**
     * Sets the category taxonomy URI.
     * <p>
     * @param taxonomyUri the category taxonomy URI to set, <b>null</b> if none.
     *
     */
    public void setTaxonomyUri(String taxonomyUri) {
        _subject.setTaxonomyUri(taxonomyUri);
    }

}
