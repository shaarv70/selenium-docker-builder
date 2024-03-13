package com.shaarv70.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;


public class Config {

    private static final Logger log= LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES="config/default.properties";
    private static Properties properties; //This is an object of property class
    //which extends hashtable, which means it will store the data in the form of key-vaue pair

    public static void initialize()
    {
        //load default property
        properties=loadProperties();

        //check for any override
       for(String key: properties.stringPropertyNames())//thi will contain all the keys present in properties file
       {
          if(System.getProperties().containsKey(key))//this will check for the key for which we are entering the value in cmd has the
                                                          // key present in default property file or not
          {
              properties.setProperty(key,System.getProperty(key)); //if the key is present then update that key with the value passing through cmd
          }                                                      //its like(overriding the default value with the cmd value)
       }
          //print
        log.info("Test properties");
       log.info("------------------------------------------");
       for (String key:properties.stringPropertyNames())
       {
           log.info("{}={}",key,properties.getProperty(key));
       }

    }
    public static String get(String key)
    {
     return properties.getProperty(key);
    }

    private static Properties loadProperties(){

       Properties properties= new Properties();
       try(InputStream stream=ResourceLoader.getResource(DEFAULT_PROPERTIES)){
           properties.load(stream);
       }
      catch (Exception e)
      {
          log.error("unable to read the property file {}",DEFAULT_PROPERTIES,e);
      }
     return properties;
    }

}
