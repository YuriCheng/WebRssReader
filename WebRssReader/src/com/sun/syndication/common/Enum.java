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
package com.sun.syndication.common;

import java.io.Serializable;

/**
 * Base class for enumerations (to bad is too early for Java 1.5).
 * <p>
 * Enum objects are inmutable.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Enum implements Serializable,Cloneable {
    String _name;

    /**
     * Creates an Enum object with the given name.
     * <p>
     * @param name of the Enum.
     *
     */
    protected Enum(String name) {
        _name = name;
    }

    /**
     * Returns the String representation of the Enum.
     * <p>
     * @return the Enum String representation.
     *
     */
    public String toString() {
        return _name;
    }

    /**
     * Clones the Enum.
     * <p>
     * @return a clone of the Enum object, as Enum objects are inmutable, it returns the same object.
     *
     */
    public Object clone() {
        return this;
    }

    /**
     * Indicates whether some other Enum is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param obj the object to compare with.
     * @return <b>true</b> if 'this' object is equal to the 'obj' object.
     *
     */
    public boolean equals(Object obj) {
        boolean eq = false;
        if (this.getClass().isInstance(obj)) {
            Enum e = (Enum) obj;
            eq = (_name == e._name);
        }
        return eq;
    }

    /**
     * Returns a hashcode value for the Enum.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the Enum object.
     *
     */
    public int hashCode() {
        return _name.hashCode();
    }

}
