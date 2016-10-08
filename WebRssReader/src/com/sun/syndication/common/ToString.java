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

/**
 * To provide support for contextual toString functionality.
 * <p>
 * Classes implementing this interface can collaborate with the caller of the toString method by
 * prepending a prefix to their toString output.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface ToString {

    /**
     * Returns the String representation of the object with a prefix.
     * <p>
     * @param prefix prefix for the String representation of the object.
     * @return String representation of the object prepended by the given prefix.
     *
     */
    public String toString(String prefix);

}
