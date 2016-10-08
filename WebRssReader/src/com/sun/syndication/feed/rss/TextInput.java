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
 * Bean for text input of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class TextInput extends ObjectBean {
    private String _title;
    private String _description;
    private String _name;
    private String _link;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public TextInput() {
    }

    /**
     * Returns the text input title.
     * <p>
     * @return the text input title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the text input title.
     * <p>
     * @param title the text input title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the text input description.
     * <p>
     * @return the text input description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the text input description.
     * <p>
     * @param description the text input description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * Returns the text input name.
     * <p>
     * @return the text input name, <b>null</b> if none.
     *
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the text input name.
     * <p>
     * @param name the text input name to set, <b>null</b> if none.
     *
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Returns the text input link.
     * <p>
     * @return the text input link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the text input link.
     * <p>
     * @param link the text input link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

}
