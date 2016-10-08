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

/**
 * Bean for person elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Person extends ObjectBean  {
    private String _name;
    private String _url;
    private String _email;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Person() {
    }

    /**
      * Returns the person name.
      * <p>
      * @return the person name, <b>null</b> if none.
      *
      */
    public String getName() {
        return _name;
    }

    /**
      * Sets the personname.
      * <p>
      * @param name the person name, <b>null</b> if none.
      *
      */
    public void setName(String name) {
        _name = name;
    }

    /**
      * Returns the person URL.
      * <p>
      * @return the person URL, <b>null</b> if none.
      *
      */
    public String getUrl() {
        return _url;
    }

    /**
      * Sets the person URL.
      * <p>
      * @param url the person URL, <b>null</b> if none.
      *
      */
    public void setUrl(String url) {
        _url = url;
    }

    /**
      * Returns the person email.
      * <p>
      * @return the person email, <b>null</b> if none.
      *
      */
    public String getEmail() {
        return _email;
    }

    /**
      * Sets the person email.
      * <p>
      * @param email the person email, <b>null</b> if none.
      *
      */
    public void setEmail(String email) {
        _email = email;
    }

}
