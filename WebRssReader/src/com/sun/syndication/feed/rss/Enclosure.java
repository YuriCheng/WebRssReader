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
 * Bean for item enclosures of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Enclosure extends ObjectBean {
    private String _url;
    private int _length;
    private String _type;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Enclosure() {
    }

    /**
     * Returns the enclosure URL.
     * <p>
     * @return the enclosure URL, <b>null</b> if none.
     *
     */
    public String getUrl() {
        return _url;
    }

    /**
     * Sets the enclosure URL.
     * <p>
     * @param url the enclosure URL to set, <b>null</b> if none.
     *
     */
    public void setUrl(String url) {
        _url = url;
    }

    /**
     * Returns the enclosure length.
     * <p>
     * @return the enclosure length, <b>null</b> if none.
     *
     */
    public int getLength() {
        return _length;
    }

    /**
     * Sets the enclosure length.
     * <p>
     * @param length the enclosure length to set, <b>null</b> if none.
     *
     */
    public void setLength(int length) {
        _length = length;
    }

    /**
     * Returns the enclosure type.
     * <p>
     * @return the enclosure type, <b>null</b> if none.
     *
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the enclosure type.
     * <p>
     * @param type the enclosure type to set, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

}
