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

/**
 * Bean for content of SyndFeed entries.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndContent extends ObjectBean implements SyndContentI {
    private String _type;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndContent() {
    }

    /**
     * Returns the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     * @return the content type, <b>null</b> if none.
     *
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     * @param type the content type to set, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the content value.
     * <p>
     * @return the content value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the content value.
     * <p>
     * @param value the content value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}
