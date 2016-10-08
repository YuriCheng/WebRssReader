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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.impl.FeedParsers;
import org.jdom.Document;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.util.List;

/**
 * Parses an XML document (File, InputStream, Reader, W3C SAX InputSource, W3C DOM Document or JDom DOcument)
 * into an WireFeed (RSS/Atom).
 * <p>
 * It accepts all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and
 * Atom 0.3 feeds. Parsers are plugable (they must implement the WireFeedParser interface).
 * <p>
 * The WireFeedInput useds liberal parsers.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class WireFeedInput {
    private static FeedParsers FEED_PARSERS = new FeedParsers();

    private boolean _validate;

    /**
     * Returns the list of supported input feed types.
     * <p>
     * @see WireFeed for details on the format of these strings.
     * <p>
     * @return a list of String elements with the supported input feed types.
     *
     */
    public static List getSupportedFeedTypes() {
        return FEED_PARSERS.getSupportedFeedTypes();
    }

    /**
     * Creates a WireFeedInput instance with input validation turned off.
     * <p>
     *
     */
    public WireFeedInput() {
        this (false);
    }

    /**
     * Creates a WireFeedInput instance.
     * <p>
     * @param validate indicates if the input should be validated. NOT IMPLEMENTED YET (validation does not happen)
     *
     */
    public WireFeedInput(boolean validate) {
        _validate = false; // TODO FIX THIS THINGY
    }

    /**
     * Builds an WireFeed (RSS or Atom) from a file.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param file file to read to create the WireFeed.
     * @return the WireFeed read from the file.
     * @throws FileNotFoundException thrown if the file could not be found.
     * @throws IOException thrown if there is problem reading the file.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(File file) throws FileNotFoundException,IOException,IllegalArgumentException,FeedException {
        WireFeed feed;
        Reader reader = new FileReader(file);
        feed = build(reader);
        reader.close();
        return feed;
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an InputStream.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param is InputStream to read to create the WireFeed.
     * @return the WireFeed read from the InputStream.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(InputStream is) throws IllegalArgumentException,FeedException {
        return build(new InputStreamReader(is));
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an Reader.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param reader Reader to read to create the WireFeed.
     * @return the WireFeed read from the Reader.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(Reader reader) throws IllegalArgumentException,FeedException {
        SAXBuilder saxBuilder = new SAXBuilder(_validate);
        try {
            Document document = saxBuilder.build(reader);
            return build(document);
        }
        catch (Exception ex) {
            throw new FeedException("Invalid XML",ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an W3C SAX InputSource.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param is W3C SAX InputSource to read to create the WireFeed.
     * @return the WireFeed read from the W3C SAX InputSource.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(InputSource is) throws IllegalArgumentException,FeedException {
        SAXBuilder saxBuilder = new SAXBuilder(_validate);
        try {
            Document document = saxBuilder.build(is);
            return build(document);
        }
        catch (Exception ex) {
            throw new FeedException("Invalid XML",ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an W3C DOM document.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param document W3C DOM document to read to create the WireFeed.
     * @return the WireFeed read from the W3C DOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(org.w3c.dom.Document document) throws IllegalArgumentException,FeedException {
        DOMBuilder domBuilder = new DOMBuilder();
        try {
            Document jdomDoc = domBuilder.build(document);
            return build(jdomDoc);
        }
        catch (Exception ex) {
            throw new FeedException("Invalid XML",ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an JDOM document.
     * <p>
     * NOTE: All other build methods delegate to this method.
     * <p>
     * @param document JDOM document to read to create the WireFeed.
     * @return the WireFeed read from the JDOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(Document document) throws IllegalArgumentException,FeedException {
        WireFeedParser parser = FEED_PARSERS.getParserFor(document);
        if (parser==null) {
            throw new IllegalArgumentException("Invalid document");
        }
        return parser.parse(document, _validate);
    }

}
