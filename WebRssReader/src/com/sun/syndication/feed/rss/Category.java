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

/**
 * Bean for categories of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Category extends ObjectBean {
    private String _domain;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Category() {
    }

    /**
     * Returns the category domain.
     * <p>
     * @return the category domain, <b>null</b> if none.
     *
     */
    public String getDomain() {
        return _domain;
    }

    /**
     * Sets the category domain.
     * <p>
     * @param domain the category domain to set, <b>null</b> if none.
     *
     */
    public void setDomain(String domain) {
        _domain = domain;
    }

    /**
     * Returns the category value.
     * <p>
     * @return the category value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the category value.
     * <p>
     * @param value the category value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}
