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
 * Bean for item descriptions of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Description extends ObjectBean {
    private String _type;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Description() {
    }

    /**
     * Returns the description type.
     * <p>
     * @return the description type, <b>null</b> if none.
     *
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the description type.
     * <p>
     * @param type the description type to set, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the description value.
     * <p>
     * @return the description value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the description value.
     * <p>
     * @param value the description value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}
