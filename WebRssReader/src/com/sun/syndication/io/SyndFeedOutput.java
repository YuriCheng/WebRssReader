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
package com.sun.syndication.io;

import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.feed.synd.SyndFeedI;
import org.jdom.Document;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.File;

/**
 * Generates an XML document (String, File, OutputStream, Writer, W3C DOM document or JDOM document)
 * out of an SyndFeed..
 * <p>
 * It delegates to a WireFeedOutput to generate all feed types.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndFeedOutput {
    private WireFeedOutput _feedOutput;

    /**
     * Creates a SyndFeedOutput instance.
     * <p>
     *
     */
    public SyndFeedOutput() {
        _feedOutput = new WireFeedOutput();
    }

    /**
     * Creates a String with the XML representation for the given SyndFeed.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return a String with the XML representation for the given SyndFeed.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(SyndFeedI feed) throws FeedException {
        return _feedOutput.outputString(feed.createWireFeed());
    }

    /**
     * Creates a File containing with the XML representation for the given SyndFeed.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given SyndFeed.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeedI feed,File file) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),file);
    }

    /**
     * Writes to an OutputStream the XML representation for the given SyndFeed.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param os OutputStream to write the XML representation for the given SyndFeed.
     * @throws IOException thrown if there was some problem writing to the OutputStream.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeedI feed,OutputStream os) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),os);
    }

    /**
     * Writes to an Writer the XML representation for the given SyndFeed.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given SyndFeed.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeedI feed,Writer writer) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),writer);
    }

    /**
     * Creates a W3C DOM document for the given SyndFeed.
     * <p>
     * @param feed Abstract feed to create W3C DOM document from. The type of the SyndFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return the W3C DOM document for the given SyndFeed.
     * @throws FeedException thrown if the W3C DOM document for the feed could not be created.
     *
     */
    public org.w3c.dom.Document outputW3CDom(SyndFeedI feed) throws FeedException {
        return _feedOutput.outputW3CDom(feed.createWireFeed());
    }

    /**
     * Creates a JDOM document for the given SyndFeed.
     * <p>
     * @param feed Abstract feed to create JDOM document from. The type of the SyndFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return the JDOM document for the given SyndFeed.
     * @throws FeedException thrown if the JDOM document for the feed could not be created.
     *
     */
    public Document outputJDom(SyndFeedI feed) throws FeedException {
        return _feedOutput.outputJDom(feed.createWireFeed());
    }

}
