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
package com.sun.syndication.feed.atom;

import com.sun.syndication.common.ObjectBean;
import com.sun.syndication.common.Enum;

/**
 * Bean for link elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Link extends ObjectBean {

    /**
     * Enumeration type for the 'rel' property of Atom Link elements.
     * <p>
     * @author Alejandro Abdelnur
     *
     */
    public static class Rel extends Enum {

        private Rel(String name) {
            super(name);
        }

    }

    public static final Rel ALTERNATE = new Rel("alternate");
    public static final Rel START = new Rel("start");
    public static final Rel NEXT = new Rel("next");
    public static final Rel PREV = new Rel("prev");
    public static final Rel SERVICE_EDIT = new Rel("service.edit");
    public static final Rel SERVICE_POST = new Rel("service.post");
    public static final Rel SERVICE_FEED = new Rel("service.feed");

    private Rel _rel;
    private String _type;
    private String _href;
    private String _title;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Link() {
    }

    /**
     * Returns the link rel.
     * <p>
     * @return the link rel, <b>null</b> if none.
     *
     */
    public Rel getRel() {
        return _rel;
    }

    /**
     * Sets the link rel.
     * <p>
     * @param rel the link rel,, <b>null</b> if none.
     *
     */
    public void setRel(Rel rel) {
        _rel = rel;
    }

    /**
     * Returns the link type.
     * <p>
     * @return the link type, <b>null</b> if none.
     *
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the link type.
     * <p>
     * @param type the link type, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the link href.
     * <p>
     * @return the link href, <b>null</b> if none.
     *
     */
    public String getHref() {
        return _href;
    }

    /**
     * Sets the link href.
     * <p>
     * @param href the link href, <b>null</b> if none.
     *
     */
    public void setHref(String href) {
        _href = href;
    }

    /**
     * Returns the link title.
     * <p>
     * @return the link title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the link title.
     * <p>
     * @param title the link title, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

}
