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
package com.sun.syndication.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Loads and instantiates classes indicated in property files.
 * <p>
 * The PlugableClasses is useful for handling collection of classes that are not known or fixed
 * at development time.
 * <p>
 * It reads the class names from a default properties file and from an additional properties
 * file indicated through a System property. The additional properties file does not override
 * the default properties file, the classes are aggregated.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class PlugableClasses {
    private String _defaultFile;
    private String _systemPropertyFile;
    private String _propertyKey;
    private ClassLoader _classLoader;
    private boolean _hardFailure = true;
    private Class[] _classes;

    /**
     * Creates a PlugableClasses instance that will load the classes indicated in properties files.
     * <p>
     * It uses the same ClassLoader of the PlugableClasses and it is set for hard failure
     * (a RuntimeException will be thrown if one or more of the classes cannot be loaded or instanciated).
     * <p>
     * @param defaultFile properties file that defines the classes to load
     * @param systemPropertyFile system property that if present it should be indicate an additional
     *        properties file to look for classes to load. The additional properties file does not
     *        override the default properties file, the classes are aggregated.
     * @param propertyKey property name that contains the list of classes to load. The class names
     *        must be separated with white spaces.
     *
     */
    public PlugableClasses(String defaultFile,String systemPropertyFile,String propertyKey) {
        this(defaultFile,systemPropertyFile,propertyKey,true,PlugableClasses.class.getClassLoader());
    }

    /**
     * Creates a PlugableClasses instance that will load the classes indicated in properties files.
     * <p>
     * @param defaultFile properties file that defines the classes to load
     * @param systemPropertyFile system property that if present it should be indicate an additional
     *        properties file to look for classes to load. The additional properties file does not
     *        override the default properties file, the classes are aggregated.
     * @param propertyKey property name that contains the list of classes to load. The class names
     *        must be separated with white spaces.
     * @param hardFailure indicates if a RuntimeException will be thrown if one or more of the classes
     *        cannot be loaded or instanciated. Otherwise it will ignore the failure and continue with
     *        the other classes.
     * @param classLoader ClassLoader to use for loading the properties files and classes.
     *
     */
    public PlugableClasses(String defaultFile,String systemPropertyFile,String propertyKey,
                           boolean hardFailure,ClassLoader classLoader) {
        _defaultFile = defaultFile;
        _systemPropertyFile = systemPropertyFile;
        _propertyKey = propertyKey;
        _hardFailure = hardFailure;
        _classLoader = classLoader;
    }

    /**
     * Sets the hard failure mode for the PlugableClasses instance.
     * <p>
     * If a hard failure mode is OFF, a ClassNotFoundException will be thrown if one of the classes cannot
     * be loaded or instanciated. Otherwise it will ignore the failure and continue with the other
     * classes.
     * <p>
     * @param hardFailure <b>true</b> to set hard failure to ON, <b>false</b> to set it to OFF.
     *
     */
    public void setHardFailure(boolean hardFailure) {
        _hardFailure = hardFailure;
    }

    /**
     * Returns the hard failure mode for the PlugableClasses instance.
     * <p>
     * If a hard failure mode is OFF, a RuntimeException will be thrown if one of the classes cannot
     * be loaded or instanciated. Otherwise it will ignore the failure and continue with the other
     * classes.
     * <p>
     * @return <b>true</b> to set hard failure to ON, <b>false</b> to set it to OFF.
     *
     */
    public boolean isHardFailure() {
        return _hardFailure;
    }

    /**
     * Loads and returns the classes defined in the properties files.
     * <p>
     * @return array containing the classes defined in the properties files.
     * @throws java.io.IOException thrown if the properties files cannot be found or read, and hard failure is ON.
     * @throws java.lang.ClassNotFoundException thrown if one of the classes defined in the properties file cannot be loaded
     *         and hard failure is ON.
     *
     */
    public Class[] getClasses() throws IOException,ClassNotFoundException {
        synchronized (this) {
            if (_classes==null) {
                _classes = loadClasses();
            }
        }
        return _classes;
    }

    /**
     * Loads, instanciates and returns the classes defined in the properties files.
     * <p>
     * @return array containing the classes defined in the properties files.
     * @throws java.io.IOException thrown if the properties files cannot be found or read, and hard failure is ON.
     * @throws java.lang.ClassNotFoundException thrown if one of the classes defined in the properties files cannot be loaded
     *         and hard failure is ON.
     * @throws java.lang.InstantiationException thrown if one of the classes defined in the properties files cannot be
     *         instantiated and hard failure is ON.
     *
     */
    public Object[] createInstances() throws IOException,ClassNotFoundException,InstantiationException {
        List objects = new ArrayList();
        Class[] classes = getClasses();
        for (int i=0;i<classes.length;i++) {
            try {
                objects.add(classes[i].newInstance());
            }
            catch (Exception ex) {
                if (isHardFailure()) {
                    if (ex instanceof InstantiationException) {
                        throw (InstantiationException)ex;
                    }
                    throw new InstantiationException("Could not create instance for ["+classes[i].getName()+"]");
                }
            }
        }
        return objects.toArray();
    }

    /**
     * Loads classes defined in the default and System-properties-indicated properties files.
     *
     */
    private Class[] loadClasses() throws IOException,ClassNotFoundException {
        List classes = new ArrayList();
        if (_defaultFile!=null) {
            classes.addAll(loadClasses(_defaultFile));
        }
        if (_systemPropertyFile!=null) {
            String extraFile = System.getProperty(_systemPropertyFile);
            if (extraFile!=null) {
                classes.addAll(loadClasses(extraFile));
            }
        }
        Class[] array = new Class[classes.size()];
        classes.toArray(array);
        return array;
    }

    /**
     * Loads classes defined in a properties file.
     *
     */
    private List loadClasses(String fileName) throws IOException,ClassNotFoundException {
        List classes = new ArrayList();
        Properties props = new Properties();
        InputStream is = _classLoader.getResourceAsStream(fileName);
        if (is!=null) {
            try {
                props.load(is);
            }
            catch (IOException ioEx) {
                if (isHardFailure()) {
                    throw ioEx;
                }
            }
            String classNames = props.getProperty(_propertyKey);
            if (classNames!=null) {
                classes.addAll(parseAndLoadClasses(classNames));
            }
        }
        else {
            if (isHardFailure()) {
                throw new IOException("Could not find properties file ["+fileName+"]");
            }
        }
        return classes;

    }

    private List parseAndLoadClasses(String classNames) throws ClassNotFoundException {
        List classes = new ArrayList();
        StringTokenizer st = new StringTokenizer(classNames," ");
        while (st.hasMoreTokens()) {
            String className = st.nextToken();
            Class mClass = null;
            try {
                mClass = _classLoader.loadClass(className);
                classes.add(mClass);
            }
            catch (ClassNotFoundException ex) {
                if (isHardFailure()) {
                    throw ex;
                }
            }
        }
        return classes;
    }

}
