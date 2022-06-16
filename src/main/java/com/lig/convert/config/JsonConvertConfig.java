package com.lig.convert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @className: com.lig.convert.config-> JsonConverConfig
 * @description:
 * @author: lig
 * @createDate: 2022-06-16 20:57
 * @version: 1.0
 * @todo:
 */
@Component
@ConfigurationProperties("json")
@Data
public class JsonConvertConfig {

    private List<Convert> convert;

    private Map<String, Convert> convertMap;


    public Map<String, Convert> getConvertMap() {
        if(convertMap == null) {
            synchronized (JsonConvertConfig.class) {
                if(convertMap == null) {
                    convertMap = convert.stream().collect(Collectors.toMap(Convert::getUri, Function.identity()));
                }
            }
        }
        return convertMap;
    }

    @Data
    public static class Convert{

        private String uri;

        private String data;


    }


}
