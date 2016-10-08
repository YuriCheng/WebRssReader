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
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedI;
import org.jdom.Document;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;

/**
 * Parses an XML document (File, InputStream, Reader, W3C SAX InputSource, W3C DOM Document or JDom DOcument)
 * into an SyndFeed.
 * <p>
 * It delegates to a WireFeedInput to handle all feed types.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndFeedInput {
    private WireFeedInput _feedInput;

    /**
     * Creates a SyndFeedInput instance with input validation turned off.
     * <p>
     *
     */
    public SyndFeedInput() {
        this(false);
    }

    /**
     * Creates a SyndFeedInput instance.
     * <p>
     * @param validate indicates if the input should be validated. NOT IMPLEMENTED YET (validation does not happen)
     *
     */
    public SyndFeedInput(boolean validate) {
        _feedInput = new WireFeedInput(validate);
    }

    /**
     * Builds SyndFeed from a file.
     * <p>
     * @param file file to read to create the SyndFeed.
     * @return the SyndFeed read from the file.
     * @throws FileNotFoundException thrown if the file could not be found.
     * @throws IOException thrown if there is problem reading the file.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeedI build(File file) throws FileNotFoundException,IOException,IllegalArgumentException,FeedException {
        return new SyndFeed(_feedInput.build(file));
    }

    /**
     * Builds SyndFeed from an InputStream.
     * <p>
     * @param is InputStream to read to create the SyndFeed.
     * @return the SyndFeed read from the InputStream.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeedI build(InputStream is) throws IllegalArgumentException,FeedException {
        return new SyndFeed(_feedInput.build(is));
    }

    /**
     * Builds SyndFeed from an Reader.
     * <p>
     * @param reader Reader to read to create the SyndFeed.
     * @return the SyndFeed read from the Reader.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeedI build(Reader reader) throws IllegalArgumentException,FeedException {
        return new SyndFeed(_feedInput.build(reader));
    }

    /**
     * Builds SyndFeed from an W3C SAX InputSource.
     * <p>
     * @param is W3C SAX InputSource to read to create the SyndFeed.
     * @return the SyndFeed read from the W3C SAX InputSource.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeedI build(InputSource is) throws IllegalArgumentException,FeedException {
        return new SyndFeed(_feedInput.build(is));
    }

    /**
     * Builds SyndFeed from an W3C DOM document.
     * <p>
     * @param document W3C DOM document to read to create the SyndFeed.
     * @return the SyndFeed read from the W3C DOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeedI build(org.w3c.dom.Document document) throws IllegalArgumentException,FeedException {
        return new SyndFeed(_feedInput.build(document));
    }

    /**
     * Builds SyndFeed from an JDOM document.
     * <p>
     * @param document JDOM document to read to create the SyndFeed.
     * @return the SyndFeed read from the JDOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeedI build(Document document) throws IllegalArgumentException,FeedException {
        return new SyndFeed(_feedInput.build(document));
    }

}
