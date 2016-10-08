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
package com.sun.syndication.feed;

import com.sun.syndication.common.ObjectBean;

/**
 * Parent class of the RSS (Channel) and Atom (Feed) feed beans.
 * <p>
 * NOTE: We don't like this class at this package level but the alternative would have
 * been a proliferation of packages (one more level to hold atom and rss package with
 * this class just in that package).
 * <p>
 * The format of the 'type' property must be [FEEDNAME]_[FEEDVERSION] with the FEEDNAME in lower case,
 * for example: rss_0.9, rss_0.93, atom_0.3
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public abstract class WireFeed extends ObjectBean  {
    private String _feedType;

    /**
     * Default constructor, for bean cloning purposes only.
     *
     */
    protected WireFeed() {
    }

    /**
     * Creates a feed for a given type.
     * <p>
     * @param type of the feed to create.
     *
     */
    protected WireFeed(String type) {
        _feedType = type;
    }

    /**
     * Sets the feedType of a the feed. <b>Do not use</b>, for bean cloning purposes only.
     * <p>
     * @param feedType the feedType of the feed.
     *
     */
    public void setFeedType(String feedType) {
        _feedType = feedType;
    }

    /**
     * Returns the type of the feed.
     *
     * @return the type of the feed.
     */
    public String getFeedType() {
        return _feedType;
    }

}
