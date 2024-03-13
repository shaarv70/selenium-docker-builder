package com.shaarv70.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();//this will convert that inputstream coming from resourceloader
    //class to java object

    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path))//feeding the path of the testdata file
        {
            //in this step json is converting into the java class object
            return mapper.readValue(stream, type); //passing the value of path & flightresrvationTestData class
        }
        catch (Exception e){
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }

/*Note:since at line 18 we are converting thw inputstream to java object of that record class so the arguments of
record class and keys coming from the json data should be matching with the same name or else it will throw exception
if we want to give out own name in record class different from what it is present in json data file, then we have to use
(go to  flightresrvationTestData class there you will know) */
}
