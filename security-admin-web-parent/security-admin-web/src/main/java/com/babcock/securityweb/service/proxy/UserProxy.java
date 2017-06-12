package com.babcock.securityweb.service.proxy;

import com.babcock.securityweb.service.proxy.json.UserJson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class UserProxy {

    protected Logger logger = LoggerFactory.getLogger(UserProxy.class);

    @Value("${security-admin-service-url:http://localhost/security-admin-service}")
    private String securityAdminServiceUrl;

    public static final String GET_ALL_USERS_URL = "/securityadmin/users";
    public static final String FIND_USER_BY_ID_URL = "/securityadmin/users/{id}";

    public static final String CREATE_USER_URL = "/securityadmin/users";
    public static final String UPDATE_USER_URL = "/securityadmin/users";

    @Autowired
    RestTemplate restTemplate;

    private String getUrl(String restUrl) {
        if(securityAdminServiceUrl.contains("http://")) {
            return securityAdminServiceUrl + restUrl;
        }else {
            return "http://" + securityAdminServiceUrl + restUrl;
        }
    }

    public List<UserJson> getAllUsers() {
        try {
            String url = getUrl(GET_ALL_USERS_URL);

            logger.info("getAllUsers: Calling Url {}", url);

            ResponseEntity<UserJson[]> response = restTemplate.getForEntity(url, UserJson[].class);

            List<UserJson> responseList = Arrays.asList(response.getBody());
            logger.info("getAllUsers: Response Body {}", responseList);

            return responseList;
        } catch (HttpClientErrorException hce) {
            logger.error("getAllUsers: Error Response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public String createUser(UserJson user) {
        try {
            String url = getUrl(CREATE_USER_URL);

            logger.info("createUser: Calling Url {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(user,getHeaders());

            ResponseEntity<UserJson> response = restTemplate.postForEntity(url, httpEntity, UserJson.class);

            logger.info("createUser: Response Body {}", response.getBody());

            return response.getBody().getId();
        } catch (HttpClientErrorException hce) {
            logger.error("createUser: Error Response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public void updateUser(UserJson user) {
        try {
            String url = getUrl(UPDATE_USER_URL);

            logger.info("updateUser: calling url {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(user, getHeaders());

            restTemplate.put(url, httpEntity);

            logger.info("updateUser; user {} updated", user.getId());
        } catch (HttpClientErrorException hce) {
            logger.error("updateUser: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public UserJson findByUserId(String id) {
        try {
            String url = getUrl(FIND_USER_BY_ID_URL);

            url = StringUtils.replace(url, "{id}", id);

            logger.info("findByUserId: calling url {}", url);

            ResponseEntity<UserJson> response = restTemplate.getForEntity(url, UserJson.class);

            logger.info("findByUserId: response body {}", response.getBody());

            return response.getBody();
        } catch (HttpClientErrorException hce) {
            logger.error("findByUserId: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    private MultiValueMap<String, String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
