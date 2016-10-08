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
 * Convenience class providing clone(), toString(), equals() and hashCode() functionality for Java Beans.
 * <p>
 * It works on all read/write properties, recursively.
 * <p>
 * It uses the CloneableBean, EqualsBean and ToStringBean classes in a delegation pattern.
 * <p>
 * <h3>ObjectBean programming conventions</h3>
 * <P>
 * All ObjectBean subclasses having properties that return collections they should never
 * return null if the property has been set to <b>null</b> or if a collection has not been set.
 * They should create and return an empty collection, this empty collection instance should
 * also be set to the corresponding property.
 * <P>
 * All ObjectBean subclasses properties should be live references.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class ObjectBean implements Serializable, Cloneable, ToString {
    private EqualsBean _equalsBean;
    private ToStringBean _toStringBean;
    private CloneableBean _cloneableBean;

    /**
     * Default constructor.
     *
     */
    protected ObjectBean() {
        _equalsBean = new EqualsBean(this);
        _toStringBean = new ToStringBean(this);
        _cloneableBean = new CloneableBean(this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _cloneableBean.beanClone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        return _equalsBean.beanEquals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _equalsBean.beanHashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _toStringBean.toString();
    }

    /**
     * Returns the String representation for the bean using a prefix.
     * <p>
     * This method is used by ToString implementations.
     * <p>
     * @param prefix prefix to use in the String representation.
     * @return String representation for the bean using the given prefix.
     *
     */
    public String toString(String prefix) {
        return _toStringBean.toString(prefix);
    }

}

