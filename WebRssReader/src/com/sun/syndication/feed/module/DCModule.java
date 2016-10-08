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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dublin Core Module, default implementation.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 */
public class DCModule extends Module implements DCModuleI {
    private String _title;
    private String _creator;
    private List _subjects;
    private String _description;
    private String _publisher;
    private List _contributors;
    private Date _date;
    private String type;
    private String _format;
    private String _identifier;
    private String _source;
    private String _language;
    private String _relation;
    private String _coverage;
    private String _rights;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public DCModule() {
        super(URI);
    }

    /**
     * Returns the DublinCore module title.
     * <p>
     * @return the DublinCore module title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the DublinCore module title.
     * <p>
     * @param title the DublinCore module title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the DublinCore module creator.
     * <p>
     * @return the DublinCore module creator, <b>null</b> if none.
     *
     */
    public String getCreator() {
        return _creator;
    }

    /**
     * Sets the DublinCore module creator.
     * <p>
     * @param creator the DublinCore module creator to set, <b>null</b> if none.
     *
     */
    public void setCreator(String creator) {
        _creator = creator;
    }

    /**
     * Returns the DublinCore module subjects.
     * <p>
     * @return a list of DCSubjectI elements with the DublinCore module subjects,
     *         an empty list if none.
     *
     */
    public List getSubjects() {
        return (_subjects==null) ? (_subjects=new ArrayList()) : _subjects;
    }

    /**
     * Sets the DublinCore module subjects.
     * <p>
     * @param subjects the list of DCSubjectI elements with the DublinCore module subjects to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setSubjects(List subjects) {
        _subjects = subjects;
    }

    /**
     * Returns the DublinCore module description.
     * <p>
     * @return the DublinCore module description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the DublinCore module description.
     * <p>
     * @param description the DublinCore module description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * Returns the DublinCore module publisher.
     * <p>
     * @return the DublinCore module publisher, <b>null</b> if none.
     *
     */
    public String getPublisher() {
        return _publisher;
    }

    /**
     * Sets the DublinCore module publisher.
     * <p>
     * @param publisher the DublinCore module publisher to set, <b>null</b> if none.
     *
     */
    public void setPublisher(String publisher) {
        _publisher = publisher;
    }

    /**
     * Returns the DublinCore module contributors.
     * <p>
     * @return a list of String elements with the DublinCore module contributors,
     *         an empty list if none.
     *
     */
    public List getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList()) : _contributors;
    }

    /**
     * Sets the DublinCore module contributors.
     * <p>
     * @param contributors the list of String elements with the DublinCore module contributors to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContributors(List contributors) {
        _contributors = contributors;
    }

    /**
     * Returns the DublinCore module date.
     * <p>
     * @return the DublinCore module date, <b>null</b> if none.
     *
     */
    public Date getDate() {
        return _date;
    }

    /**
     * Sets the DublinCore module date.
     * <p>
     * @param date the DublinCore module date to set, <b>null</b> if none.
     *
     */
    public void setDate(Date date) {
        _date = date;
    }

    /**
     * Returns the DublinCore module type.
     * <p>
     * @return the DublinCore module type, <b>null</b> if none.
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the DublinCore module type.
     * <p>
     * @param type the DublinCore module type to set, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the DublinCore module format.
     * <p>
     * @return the DublinCore module format, <b>null</b> if none.
     *
     */
    public String getFormat() {
        return _format;
    }

    /**
     * Sets the DublinCore module format.
     * <p>
     * @param format the DublinCore module format to set, <b>null</b> if none.
     *
     */
    public void setFormat(String format) {
        _format = format;
    }

    /**
     * Returns the DublinCore module identifier.
     * <p>
     * @return the DublinCore module identifier, <b>null</b> if none.
     *
     */
    public String getIdentifier() {
        return _identifier;
    }

    /**
     * Sets the DublinCore module identifier.
     * <p>
     * @param identifier the DublinCore module identifier to set, <b>null</b> if none.
     *
     */
    public void setIdentifier(String identifier) {
        _identifier = identifier;
    }

    /**
     * Returns the DublinCore module source.
     * <p>
     * @return the DublinCore module source, <b>null</b> if none.
     *
     */
    public String getSource() {
        return _source;
    }

    /**
     * Sets the DublinCore module source.
     * <p>
     * @param source the DublinCore module source to set, <b>null</b> if none.
     *
     */
    public void setSource(String source) {
        _source = source;
    }

    /**
     * Returns the DublinCore module language.
     * <p>
     * @return the DublinCore module language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return _language;
    }

    /**
     * Sets the DublinCore module language.
     * <p>
     * @param language the DublinCore module language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        _language = language;
    }

    /**
     * Returns the DublinCore module relation.
     * <p>
     * @return the DublinCore module relation, <b>null</b> if none.
     *
     */
    public String getRelation() {
        return _relation;
    }

    /**
     * Sets the DublinCore module relation.
     * <p>
     * @param relation the DublinCore module relation to set, <b>null</b> if none.
     *
     */
    public void setRelation(String relation) {
        _relation = relation;
    }

    /**
     * Returns the DublinCore module coverage.
     * <p>
     * @return the DublinCore module coverage, <b>null</b> if none.
     *
     */
    public String getCoverage() {
        return _coverage;
    }

    /**
     * Sets the DublinCore module coverage.
     * <p>
     * @param coverage the DublinCore module coverage to set, <b>null</b> if none.
     *
     */
    public void setCoverage(String coverage) {
        _coverage = coverage;
    }

    /**
     * Returns the DublinCore module rights.
     * <p>
     * @return the DublinCore module rights, <b>null</b> if none.
     *
     */
    public String getRights() {
        return _rights;
    }

    /**
     * Sets the DublinCore module rights.
     * <p>
     * @param rights the DublinCore module rights to set, <b>null</b> if none.
     *
     */
    public void setRights(String rights) {
        _rights = rights;
    }

}
