package com.dermai.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * application.yml config
 *
 * @author Shaobo
 */
@Configuration
@ConfigurationProperties(prefix = "dermai")
public class DermAIConfiguration {
    /**
     * upload path
     */
    private static String profile;

    /**
     * file max size
     */
    private static Integer maxFileSize;

    public static String getUploadPath() {
        return (getProfile() + "/upload");
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        DermAIConfiguration.profile = profile;
    }

    public static Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        DermAIConfiguration.maxFileSize = maxFileSize;
    }
}
