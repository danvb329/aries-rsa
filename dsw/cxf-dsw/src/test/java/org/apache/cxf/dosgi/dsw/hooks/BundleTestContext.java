/** 
  * Licensed to the Apache Software Foundation (ASF) under one 
  * or more contributor license agreements. See the NOTICE file 
  * distributed with this work for additional information 
  * regarding copyright ownership. The ASF licenses this file 
  * to you under the Apache License, Version 2.0 (the 
  * "License"); you may not use this file except in compliance 
  * with the License. You may obtain a copy of the License at 
  * 
  * http://www.apache.org/licenses/LICENSE-2.0 
  * 
  * Unless required by applicable law or agreed to in writing, 
  * software distributed under the License is distributed on an 
  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY 
  * KIND, either express or implied. See the License for the 
  * specific language governing permissions and limitations 
  * under the License. 
  */
package org.apache.cxf.dosgi.dsw.hooks;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class BundleTestContext implements BundleContext {

    private Bundle bundle;
    private Object serviceObject;
    private Map<String, ServiceReference> testReferences = new
        HashMap<String, ServiceReference>();
    private Map<String, ServiceRegistration> testRegistrations = new
        HashMap<String, ServiceRegistration>();
    private Map<String, ServiceReference> registeredReferences = new
        HashMap<String, ServiceReference>();
    private Map<String, ServiceRegistration> registeredRegistrations = new
        HashMap<String, ServiceRegistration>();
    private Map<String, List<Dictionary>> registeredProperties = new
        HashMap<String, List<Dictionary>>();
        
    public BundleTestContext(Bundle b) {
        bundle = b;
    }
    
    public void addBundleListener(BundleListener arg0) {
    }

    public void addFrameworkListener(FrameworkListener arg0) {
    }

    public void addServiceListener(ServiceListener arg0) {
    }

    public void addServiceListener(ServiceListener arg0, String arg1) throws InvalidSyntaxException {
    }

    public Filter createFilter(String arg0) throws InvalidSyntaxException {
        return null;
    }

    public ServiceReference[] getAllServiceReferences(String arg0, String arg1) throws InvalidSyntaxException {
        return null;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public Bundle getBundle(long arg0) {
        return null;
    }

    public Bundle[] getBundles() {
        return null;
    }

    public File getDataFile(String arg0) {
        return null;
    }

    public String getProperty(String arg0) {
        return null;
    }

    public Object getService(ServiceReference sref) {
        return serviceObject;
    }

    public ServiceReference getServiceReference(String name) {
        return registeredReferences.get(name);
    }

    public ServiceReference[] getServiceReferences(String arg0, String arg1) throws InvalidSyntaxException {
        // TODO Auto-generated method stub
        return null;
    }

    public Bundle installBundle(String arg0) throws BundleException {
        // TODO Auto-generated method stub
        return null;
    }

    public Bundle installBundle(String arg0, InputStream arg1) throws BundleException {
        // TODO Auto-generated method stub
        return null;
    }

    public ServiceRegistration registerService(String[] names, Object service, Dictionary props) {
        for (String s : names) {
            registeredReferences.put(s, testReferences.get(s));
        }
        serviceObject = service;
        return null;
    }

    public ServiceRegistration registerService(String clz, Object obj, Dictionary props) {
        registeredRegistrations.put(clz, testRegistrations.get(clz));
        cacheProperties(clz, props);
        return testRegistrations.get(clz);
    }

    public void removeBundleListener(BundleListener arg0) {
    }

    public void removeFrameworkListener(FrameworkListener arg0) {
    }

    public void removeServiceListener(ServiceListener arg0) {
    }

    public boolean ungetService(ServiceReference arg0) {
        return false;
    }

    // test methods
    public void addServiceReference(String name, ServiceReference sref) {
        testReferences.put(name, sref);
    }

    public void addServiceRegistration(String name, ServiceRegistration reg) {
        testRegistrations.put(name, reg);
    }
    
    public Map<String, ServiceReference> getRegisteredReferences() {
        return registeredReferences;
    }

    public Map<String, ServiceRegistration> getRegisteredRegistrations() {
        return registeredRegistrations;
    }

    public Map<String, List<Dictionary>> getRegisteredProperties() {
        return registeredProperties;
    }

    private void cacheProperties(String clz, Dictionary props) {
        List<Dictionary> propsList = registeredProperties.get(clz);
        if (propsList == null) {
            propsList = new ArrayList<Dictionary>();
            registeredProperties.put(clz, propsList);
        }
        propsList.add(props);
    }
}
