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
 * Bean for content elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Content extends ObjectBean {

    /**
     * Enumeration type for the 'mode'  property of Atom content elements.
     * <p>
     * @author Alejandro Abdelnur
     *
     */
    public static class Mode extends Enum {

        private Mode(String name) {
            super(name);
        }

    }

    public static final Mode XML = new Mode("xml");
    public static final Mode BASE64 = new Mode("base64");
    public static final Mode ESCAPED = new Mode("escaped");

    private String _type;
    private Mode _mode;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Content() {
    }

    /**
     * Returns the content type.
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
     * @param type the content type, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the content mode.
     * <p>
     * The mode indicates how the value was/will-be encoded in the XML feed.
     * <p>
     * @return the content mode, <b>null</b> if none.
     *
     */
    public Mode getMode() {
        return _mode;
    }

    /**
     * Sets the content mode.
     * <p>
     * The mode indicates how the value was/will-be encoded in the XML feed.
     * <p>
     * @param mode the content mode, <b>null</b> if none.
     *
     */
    public void setMode(Mode mode) {
        _mode = mode;
    }

    /**
     * Returns the content value.
     * <p>
     * The return value should be decoded.
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
     * The value being set should be decoded.
     * <p>
     * @param value the content value, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}


