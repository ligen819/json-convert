package com.lig.convert.config;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @className: com.lig.jsonconvert.config-> GlobalRequestBodyAdvice
 * @description:
 * @author: lig
 * @createDate: 2022-06-16 20:13
 * @version: 1.0
 * @todo:
 */
@Slf4j
@ControllerAdvice(basePackages = "com.lig.convert.controller")
@RequiredArgsConstructor
public class GlobalRequestBodyAdvice extends RequestBodyAdviceAdapter {




    private final JsonConvertConfig jsonConvertConfig;


    @Autowired
    private HttpServletRequest request;



    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {


        JsonConvertConfig.Convert convert = jsonConvertConfig.getConvertMap().get(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1));
        String body = IoUtil.read(inputMessage.getBody(), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String data = JSONUtil.toJsonStr(jsonObject.get(convert.getData()));
        if(data != null) {
            return new HttpInputMessage() {
                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }

                @Override
                public InputStream getBody() {
                    String body = data;
                    return new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
                }
            };
        }
        return null;
    }
}
