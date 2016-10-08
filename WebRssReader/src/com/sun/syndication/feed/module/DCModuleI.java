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

import java.util.List;
import java.util.Date;

/**
 * Dublin Core Module.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 */
public interface DCModuleI extends ModuleI {

    /**
     * URI of the Dublin Core Module (http://purl.org/dc/elements/1.1/).
     *
     */
    String URI = "http://purl.org/dc/elements/1.1/";

    /**
     * Returns the DublinCore module title.
     * <p>
     * @return the DublinCore module title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the DublinCore module title.
     * <p>
     * @param title the DublinCore module title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the DublinCore module creator.
     * <p>
     * @return the DublinCore module creator, <b>null</b> if none.
     *
     */
    String getCreator();

    /**
     * Sets the DublinCore module creator.
     * <p>
     * @param creator the DublinCore module creator to set, <b>null</b> if none.
     *
     */
    void setCreator(String creator);

    /**
     * Returns the DublinCore module subjects.
     * <p>
     * @return a list of DCSubjectI elements with the DublinCore module subjects,
     *         an empty list if none.
     *
     */
    List getSubjects();

    /**
     * Sets the DublinCore module subjects.
     * <p>
     * @param subjects the list of DCSubjectI elements with the DublinCore module subjects to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setSubjects(List subjects);

    /**
     * Returns the DublinCore module description.
     * <p>
     * @return the DublinCore module description, <b>null</b> if none.
     *
     */
    String getDescription();

    /**
     * Sets the DublinCore module description.
     * <p>
     * @param description the DublinCore module description to set, <b>null</b> if none.
     *
     */
    void setDescription(String description);

    /**
     * Returns the DublinCore module publisher.
     * <p>
     * @return the DublinCore module publisher, <b>null</b> if none.
     *
     */
    String getPublisher();

    /**
     * Sets the DublinCore module publisher.
     * <p>
     * @param publisher the DublinCore module publisher to set, <b>null</b> if none.
     *
     */
    void setPublisher(String publisher);

    /**
     * Returns the DublinCore module contributors.
     * <p>
     * @return a list of String elements with the DublinCore module contributors,
     *         an empty list if none.
     *
     */
    List getContributors();

    /**
     * Sets the DublinCore module contributors.
     * <p>
     * @param contributors the list of String elements with the DublinCore module contributors to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setContributors(List contributors);

    /**
     * Returns the DublinCore module date.
     * <p>
     * @return the DublinCore module date, <b>null</b> if none.
     *
     */
    Date getDate();

    /**
     * Sets the DublinCore module date.
     * <p>
     * @param date the DublinCore module date to set, <b>null</b> if none.
     *
     */
    void setDate(Date date);

    /**
     * Returns the DublinCore module type.
     * <p>
     * @return the DublinCore module type, <b>null</b> if none.
     *
     */
    String getType();

    /**
     * Sets the DublinCore module type.
     * <p>
     * @param type the DublinCore module type to set, <b>null</b> if none.
     *
     */
    void setType(String type);

    /**
     * Returns the DublinCore module format.
     * <p>
     * @return the DublinCore module format, <b>null</b> if none.
     *
     */
    String getFormat();

    /**
     * Sets the DublinCore module format.
     * <p>
     * @param format the DublinCore module format to set, <b>null</b> if none.
     *
     */
    void setFormat(String format);

    /**
     * Returns the DublinCore module identifier.
     * <p>
     * @return the DublinCore module identifier, <b>null</b> if none.
     *
     */
    String getIdentifier();

    /**
     * Sets the DublinCore module identifier.
     * <p>
     * @param identifier the DublinCore module identifier to set, <b>null</b> if none.
     *
     */
    void setIdentifier(String identifier);

    /**
     * Returns the DublinCore module source.
     * <p>
     * @return the DublinCore module source, <b>null</b> if none.
     *
     */
    String getSource();

    /**
     * Sets the DublinCore module source.
     * <p>
     * @param source the DublinCore module source to set, <b>null</b> if none.
     *
     */
    void setSource(String source);

    /**
     * Returns the DublinCore module language.
     * <p>
     * @return the DublinCore module language, <b>null</b> if none.
     *
     */
    String getLanguage();

    /**
     * Sets the DublinCore module language.
     * <p>
     * @param language the DublinCore module language to set, <b>null</b> if none.
     *
     */
    void setLanguage(String language);

    /**
     * Returns the DublinCore module relation.
     * <p>
     * @return the DublinCore module relation, <b>null</b> if none.
     *
     */
    String getRelation();

    /**
     * Sets the DublinCore module relation.
     * <p>
     * @param relation the DublinCore module relation to set, <b>null</b> if none.
     *
     */
    void setRelation(String relation);

    /**
     * Returns the DublinCore module coverage.
     * <p>
     * @return the DublinCore module coverage, <b>null</b> if none.
     *
     */
    String getCoverage();

    /**
     * Sets the DublinCore module coverage.
     * <p>
     * @param coverage the DublinCore module coverage to set, <b>null</b> if none.
     *
     */
    void setCoverage(String coverage);

    /**
     * Returns the DublinCore module rights.
     * <p>
     * @return the DublinCore module rights, <b>null</b> if none.
     *
     */
    String getRights();

    /**
     * Sets the DublinCore module rights.
     * <p>
     * @param rights the DublinCore module rights to set, <b>null</b> if none.
     *
     */
    void setRights(String rights);

}
