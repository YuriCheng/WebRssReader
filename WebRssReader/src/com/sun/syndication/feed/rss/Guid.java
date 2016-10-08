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
 * Bean for item GUIDs of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Guid extends ObjectBean {
    private boolean _permaLink;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Guid() {
    }

    /**
     * Returns the guid perma link.
     * <p>
     * @return the guid perma link, <b>null</b> if none.
     *
     */
    public boolean isPermaLink() {
        return _permaLink;
    }

    /**
     * Sets the guid perma link.
     * <p>
     * @param permaLink the guid perma link to set, <b>null</b> if none.
     *
     */
    public void setPermaLink(boolean permaLink) {
        _permaLink = permaLink;
    }

    /**
     * Returns the guid value.
     * <p>
     * @return the guid value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the guid value.
     * <p>
     * @param value the guid value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}
