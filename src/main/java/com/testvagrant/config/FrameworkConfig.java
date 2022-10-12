package com.testvagrant.config;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.Serializable;
import java.util.Properties;

@Slf4j
public class FrameworkConfig implements Cloneable, Serializable {
    private static volatile FrameworkConfig instance = new FrameworkConfig();
    private Properties frameworkProperties;

    private FrameworkConfig() {
        frameworkProperties = new PropertyFileReader(new File(String
                                                                      .format("%s/src/main/resources/frameworkConfig.properties", System.getProperty("user.dir"))))
                                      .getPropertyFile();
    }

    public static FrameworkConfig getInstance() {
        return instance;
    }

    public Properties getConfigProperties() {
        return frameworkProperties;
    }

    public Object readResolve() {
        return instance;
    }

   
}
