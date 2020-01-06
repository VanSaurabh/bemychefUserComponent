package com.bemychef.bemychefusercomponent.fileReader;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties()
@PropertySource({"classpath:userErrorMessage.properties"})
public class ErrorPropertiesFileReader {

    private static final Map<String, String> errorMap = new HashMap<>();

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public static String getDataByKey(String key) {
        return errorMap.get(key);
    }


}
