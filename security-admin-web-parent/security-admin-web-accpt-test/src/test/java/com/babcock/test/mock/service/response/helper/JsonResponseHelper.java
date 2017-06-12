package com.babcock.test.mock.service.response.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class JsonResponseHelper {

    ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new ObjectMapper();
    }

    public <T> String getJsonString(T jsonMockResponse) throws JsonProcessingException {
        return mapper.writeValueAsString(jsonMockResponse);
    }

    public <T> String getJsonStringPrettyPrint(T jsonMockResponse) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMockResponse);
    }

    public <T> String getJsonString(List<T> jsonMockResponseList) throws JsonProcessingException {
      return mapper.writeValueAsString(jsonMockResponseList);

    }

    public <T> String getJsonStringPrettyPrint(List<T> jsonMockResponseList) throws JsonProcessingException {
       return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMockResponseList);
    }
}
