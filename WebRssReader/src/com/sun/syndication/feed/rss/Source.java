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
 * Bean for item sources of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Source extends ObjectBean {
    private String _url;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Source() {
    }

    /**
     * Returns the source URL.
     * <p>
     * @return the source URL, <b>null</b> if none.
     *
     */
    public String getUrl() {
        return _url;
    }

    /**
     * Sets the source URL.
     * <p>
     * @param url the source URL to set, <b>null</b> if none.
     *
     */
    public void setUrl(String url) {
        _url = url;
    }

    /**
     * Returns the source value.
     * <p>
     * @return the source value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the source value.
     * <p>
     * @param value the source value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }
}
