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

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.io.Serializable;

/**
 * Provides deep <b>Bean</b> toString support.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings, Collections,
 * ToString objects and multi-dimensional arrays of any of them.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class ToStringBean implements Serializable, ToString {

    private static final Object[] NO_PARAMS = new Object[0];

    private Object _obj;

    /**
     * Default constructor.
     * <p>
     * To be used by classes extending ToStringBean only.
     * <p>
     *
     */
    protected ToStringBean() {
        _obj = this;
    }

    /**
     * Creates a ToStringBean to be used in a delegation pattern.
     * <p>
     * For example:
     * <p>
     * <code>
     *   public class Foo implements ToString {
     *
     *       public String toString(String prefix) {
     *           ToStringBean tsb = new ToStringBean(this);
     *           return tsb.toString(prefix);
     *       }
     *
     *       public String toString() {
     *           return toString("Foo");
     *       }
     *
     *   }
     * </code>
     * <p>
     * @param obj object bean to create String representation.
     *
     */
    public ToStringBean(Object obj) {
        _obj = obj;
    }

    /**
     * Returns the String representation of the bean given in the constructor.
     * <p>
     * It uses the Class name as the prefix.
     * <p>
     * @return bean object String representation.
     *
     */
    public String toString() {
        String className = _obj.getClass().getName();
        className = className.substring(className.lastIndexOf(".")+1);
        return toString(className);
    }

    /**
     * Returns the String representation of the bean given in the constructor.
     * <p>
     * @param prefix to use for bean properties.
     * @return bean object String representation.
     *
     */
    public String toString(String prefix) {
        StringBuffer sb = new StringBuffer(128);
        try {
            BeanInfo bi = Introspector.getBeanInfo(_obj.getClass());
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            if (pds!=null) {
                for (int i=0;i<pds.length;i++) {
                    String pName = pds[i].getName();
                    Method pReadMethod = pds[i].getReadMethod();
                    if (pReadMethod!=null &&                             // ensure it has a getter method
                        pReadMethod.getDeclaringClass()!=Object.class && // filter Object.class getter methods
                        pReadMethod.getParameterTypes().length==0) {     // filter getter methods that take parameters
                        Object value = pReadMethod.invoke(_obj,NO_PARAMS);
                        printProperty(sb,prefix+"."+pName,value);
                    }
                }
            }
        }
        catch (Exception ex) {
            sb.append("\n\nEXCEPTION: Could not complete ").append(_obj.getClass()).append(".toString()\n");
        }
        return sb.toString();
    }

    private void printProperty(StringBuffer sb,String prefix,Object value) {
        if (value==null) {
            sb.append(prefix).append("=null\n");
        }
        else
        if (value.getClass().isArray()) {
            printArrayProperty(sb,prefix,value);
        }
        else
        if (value instanceof ToString) {
            ToString ts = (ToString) value;
            sb.append(ts.toString(prefix));
        }
        else
        if (value instanceof Map) {
            Map map = (Map) value;
            Iterator i = map.entrySet().iterator();
            if (i.hasNext()) {
                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();
                    String ePrefix = prefix+"["+me.getKey()+"]";
                    Object eValue = me.getValue();
                    if (eValue instanceof ToString) {
                        ToString ts = (ToString) eValue;
                        sb.append(ts.toString(ePrefix));
                    }
                    else {
                        sb.append(ePrefix).append("=").append(eValue).append("\n");
                    }
                }
            }
            else {
                sb.append(prefix).append("=[]\n");
            }
        }
        else
        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            Iterator i = collection.iterator();
            if (i.hasNext()) {
                int c = 0;
                while (i.hasNext()) {
                    String cPrefix = prefix+"["+(c++)+"]";
                    Object cValue = i.next();
                    if (cValue instanceof ToString) {
                        ToString ts = (ToString) cValue;
                        sb.append(ts.toString(cPrefix));
                    }
                    else {
                        sb.append(cPrefix).append("=").append(cValue).append("\n");
                    }
                }
            }
            else {
                sb.append(prefix).append("=[]\n");
            }
        }
        else {
            sb.append(prefix).append("=").append(value).append("\n");
        }
    }

    private void printArrayProperty(StringBuffer sb, String prefix,Object array) {
        int length = Array.getLength(array);
        for (int i=0;i<length;i++) {
            Object obj = Array.get(array,i);
            printProperty(sb,prefix+"["+i+"]",obj);
        }
    }

}

