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
package com.sun.syndication.feed.module;

import com.sun.syndication.common.ObjectBean;

/**
 * Subject of the Dublin Core Module, default implementation.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 */
public class DCSubject extends ObjectBean implements DCSubjectI {
    private String _taxonomyUri;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public DCSubject() {
    }

    /**
     * Returns the DublinCore subject taxonomy URI.
     * <p>
     * @return the DublinCore subject taxonomy URI, <b>null</b> if none.
     *
     */
    public String getTaxonomyUri() {
        return _taxonomyUri;
    }

    /**
     * Sets the DublinCore subject taxonomy URI.
     * <p>
     * @param taxonomyUri the DublinCore subject taxonomy URI to set, <b>null</b> if none.
     *
     */
    public void setTaxonomyUri(String taxonomyUri) {
        _taxonomyUri = taxonomyUri;
    }

    /**
     * Returns the DublinCore subject value.
     * <p>
     * @return the DublinCore subject value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the DublinCore subject value.
     * <p>
     * @param value the DublinCore subject value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}
