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
import com.sun.syndication.io.impl.FeedGenerators;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Generates an XML document (String, File, OutputStream, Writer, W3C DOM document or JDOM document)
 * out of an WireFeed (RSS/Atom).
 * <p>
 * It generates all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and
 * Atom 0.3 feeds. Generators are plugable (they must implement the ModuleParser interface).
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class WireFeedOutput {
    private final static FeedGenerators GENERATORS = new FeedGenerators();

    /**
     * Returns the list of supported output feed types.
     * <p>
     * @see WireFeed for details on the format of these strings.
     * <p>
     * @return a list of String elements with the supported output feed types.
     *
     */
    public static List getSupportedFeedTypes() {
        return GENERATORS.getSupportedFeedTypes();
    }

    /**
     * Creates a FeedOuput instance.
     * <p>
     *
     */
    public WireFeedOutput() {
    }

    /**
     * Creates a String with the XML representation for the given WireFeed.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return a String with the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(WireFeed feed) throws IllegalArgumentException,FeedException {
        Document doc = outputJDom(feed);
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        return outputter.outputString(doc);
    }

    /**
     * Creates a File containing with the XML representation for the given WireFeed.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,File file) throws IllegalArgumentException,IOException,FeedException {
        FileOutputStream os = new FileOutputStream(file);
        output(feed,os);
        os.close();
    }

    /**
     * Writes to an OutputStream the XML representation for the given WireFeed.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param os OutputStream to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the OutputStream.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,OutputStream os) throws IllegalArgumentException,IOException, FeedException {
        output(feed,new OutputStreamWriter(os));
    }

    /**
     * Writes to an Writer the XML representation for the given WireFeed.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,Writer writer) throws IllegalArgumentException,IOException, FeedException {
        Document doc = outputJDom(feed);
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(doc,writer);
    }

    /**
     * Creates a W3C DOM document for the given WireFeed.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create W3C DOM document from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return the W3C DOM document for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the W3C DOM document for the feed could not be created.
     *
     */
    public org.w3c.dom.Document outputW3CDom(WireFeed feed) throws IllegalArgumentException,FeedException {
        Document doc = outputJDom(feed);
        DOMOutputter outputter = new DOMOutputter();
        try {
            return outputter.output(doc);
        }
        catch (JDOMException jdomEx) {
            throw new FeedException("Could not create DOM",jdomEx);
        }
    }

    /**
     * Creates a JDOM document for the given WireFeed.
     * <p>
     * NOTE: All other output methods delegate to this method.
     * <p>
     * @param feed Abstract feed to create JDOM document from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return the JDOM document for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the JDOM document for the feed could not be created.
     *
     */
    public Document outputJDom(WireFeed feed) throws IllegalArgumentException,FeedException {
        String type = feed.getFeedType();
        WireFeedGenerator generator = GENERATORS.getGenerator(type);
        if (generator==null) {
            throw new IllegalArgumentException("Invalid feed type ["+type+"]");
        }

        if (!generator.getType().equals(type)) {
            throw new IllegalArgumentException("WireFeedOutput type["+type+"] and WireFeed type ["+
                                               type+"] don't match");
        }
        return generator.generate(feed);
    }

}
