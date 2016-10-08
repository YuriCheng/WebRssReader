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
package com.sun.syndication.io.impl;

import com.sun.syndication.io.FeedException;
import com.sun.syndication.feed.rss.Description;
import org.jdom.Attribute;
import org.jdom.Element;

/**
 * Feed Generator for RSS 0.94
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS094Generator extends RSS093Generator {

    private static final String VERSION = "0.94";

    public RSS094Generator() {
        super("rss_0.94");
    }

    protected RSS094Generator(String feedType) {
        super(feedType);
    }

    protected String getVersion() {
        return VERSION;
    }

    protected Element generateDescriptionElement(Description description)
        throws FeedException {

        Element descriptionElement = new Element("description");
        if (description.getType() != null) {
            descriptionElement.setAttribute(new Attribute("type",description.getType()));
        }
        descriptionElement.addContent(description.getValue());

        return descriptionElement;
    }

}
