package com.babcock.test.mock.wiremock;


import com.babcock.test.mock.service.response.helper.JsonResponseHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.fail;

@Component
public abstract class WireMockBase {

    @Autowired
    JsonResponseHelper jsonResponseHelper;

    protected abstract Logger getLogger();

    protected void verifyGetUrl(String url) {
        verify(postRequestedFor(urlEqualTo(url))
                .withHeader("Content-Type", equalTo(MediaType.APPLICATION_JSON_VALUE)));
    }

    protected void verifyPostUrl(String url) {
        verify(postRequestedFor(urlEqualTo(url))
                .withHeader("Content-Type", equalTo(MediaType.APPLICATION_JSON_VALUE)));
    }

    protected <T> void verifyPostUrlWithBody(String url, T json) {
        try{
        verify(postRequestedFor(urlEqualTo(url))
                .withHeader("Content-Type", equalTo(MediaType.APPLICATION_JSON_VALUE))
                .withRequestBody(equalTo(jsonResponseHelper.getJsonString(json))));
    } catch (JsonProcessingException e) {
        fail("Error paring Json: "+e.getMessage());
    }
    }

    protected <T> void verifyPutUrlWithBody(String url, T json) {
        try{
        verify(putRequestedFor(urlEqualTo(url))
                .withHeader("Content-Type", equalTo(MediaType.APPLICATION_JSON_VALUE))
                .withRequestBody(equalTo(jsonResponseHelper.getJsonString(json))));
    } catch (JsonProcessingException e) {
        fail("Error paring Json: "+e.getMessage());
    }
    }

    protected void verifyPutUrl(String url) {
        verify(putRequestedFor(urlEqualTo(url))
                .withHeader("Content-Type", equalTo(MediaType.APPLICATION_JSON_VALUE)));
    }

    protected <T> void mockGetUrlToReturn(String url, List<T> jsonList, UUID id) {
        String mockUrl = url;

        getLogger().debug("Mocking GET : {}",mockUrl);

        try {
            WireMock.stubFor(get(urlEqualTo(mockUrl)).withId(id)
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            .withBody(jsonResponseHelper.getJsonStringPrettyPrint(jsonList))));
        } catch (JsonProcessingException e) {
            fail("Error paring Json: "+e.getMessage());
        }
    }

    protected <T> void updateMockGetUrlToReturn(String url, List<T> jsonList, UUID id) {
        String mockUrl = url;

        getLogger().debug("Mocking GET : {}",mockUrl);

        try {
            WireMock.editStub(get(urlEqualTo(mockUrl)).withId(id)
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            .withBody(jsonResponseHelper.getJsonStringPrettyPrint(jsonList))));
        } catch (JsonProcessingException e) {
            fail("Error paring Json: "+e.getMessage());
        }
    }

    protected <T> void mockGetUrlToReturn(String url, T json, UUID id) {
        String mockUrl = url;

        getLogger().debug("Mocking GET : {}",mockUrl);

        try {
            WireMock.stubFor(get(urlEqualTo(mockUrl)).withId(id)
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            .withBody(jsonResponseHelper.getJsonStringPrettyPrint(json))));
        } catch (JsonProcessingException e) {
            fail("Error paring Json: "+e.getMessage());
        }
    }

    protected <T> void mockPostUrlToReturn(String url, T json, UUID id) {
        String mockUrl = url;

        getLogger().debug("Mocking POST : {}",mockUrl);

        try {
            WireMock.stubFor(post(urlEqualTo(mockUrl)).withId(id)
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            .withBody(jsonResponseHelper.getJsonStringPrettyPrint(json))));
        } catch (JsonProcessingException e) {
            fail("Error paring Json: "+e.getMessage());
        }
    }

    protected void mockPutUrl(String url, UUID id) {
        String mockUrl = url;

        getLogger().debug("Mocking PUT : {}",mockUrl);

        WireMock.stubFor(put(urlEqualTo(mockUrl)).withId(id)
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));
    }
}
