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
package com.sun.syndication.feed.rss;

import com.sun.syndication.common.ObjectBean;

/**
 * Bean for clouds of RSS feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Cloud extends ObjectBean {
    private String _domain;
    private int _port;
    private String _path;
    private String _registerProcedure;
    private String _protocol;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Cloud() {
    }

    /**
     * Returns the cloud domain.
     * <p>
     * @return the cloud domain, <b>null</b> if none.
     *
     */
    public String getDomain() {
        return _domain;
    }

    /**
     * Sets the cloud domain.
     * <p>
     * @param domain the cloud domain to set, <b>null</b> if none.
     *
     */
    public void setDomain(String domain) {
        _domain = domain;
    }

    /**
     * Returns the cloud port.
     * <p>
     * @return the cloud port, <b>null</b> if none.
     *
     */
    public int getPort() {
        return _port;
    }

    /**
     * Sets the cloud port.
     * <p>
     * @param port the cloud port to set, <b>null</b> if none.
     *
     */
    public void setPort(int port) {
        _port = port;
    }

    /**
     * Returns the cloud path.
     * <p>
     * @return the cloud path, <b>null</b> if none.
     *
     */
    public String getPath() {
        return _path;
    }

    /**
     * Sets the cloud path.
     * <p>
     * @param path the cloud path to set, <b>null</b> if none.
     *
     */
    public void setPath(String path) {
        _path = path;
    }

    /**
     * Returns the cloud register procedure.
     * <p>
     * @return the cloud register procedure, <b>null</b> if none.
     *
     */
    public String getRegisterProcedure() {
        return _registerProcedure;
    }

    /**
     * Sets the cloud register procedure.
     * <p>
     * @param registerProcedure the cloud register procedure to set, <b>null</b> if none.
     *
     */
    public void setRegisterProcedure(String registerProcedure) {
        _registerProcedure = registerProcedure;
    }

    /**
     * Returns the cloud protocol.
     * <p>
     * @return the cloud protocol, <b>null</b> if none.
     *
     */
    public String getProtocol() {
        return _protocol;
    }

    /**
     * Sets the cloud protocol.
     * <p>
     * @param protocol the cloud protocol to set, <b>null</b> if none.
     *
     */
    public void setProtocol(String protocol) {
        _protocol = protocol;
    }

}
