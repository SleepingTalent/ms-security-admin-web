package com.babcock.securityweb.service.proxy;

import com.babcock.securityweb.service.proxy.json.RoleJson;
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
public class RoleProxy {

    protected Logger logger = LoggerFactory.getLogger(RoleProxy.class);

    @Value("${security-admin-service-url:http://localhost/security-admin-service}")
    private String securityAdminServiceUrl;

    public static final String GET_ALL_ROLES_URL = "/securityadmin/roles";
    public static final String FIND_ROLE_BY_ID_URL = "/securityadmin/roles/{id}";

    public static final String CREATE_ROLE_URL = "/securityadmin/roles";
    public static final String UPDATE_ROLE_URL = "/securityadmin/roles";

    @Autowired
    RestTemplate restTemplate;

    private String getUrl(String restUrl) {
        if(securityAdminServiceUrl.contains("http://")) {
            return securityAdminServiceUrl + restUrl;
        }else {
            return "http://" + securityAdminServiceUrl + restUrl;
        }
    }

    public List<RoleJson> getAllRoles() {
        try {
            String url = getUrl(GET_ALL_ROLES_URL);

            logger.info("getAllRoles: calling url {}", url);

            ResponseEntity<RoleJson[]> response = restTemplate.getForEntity(url, RoleJson[].class);

            List<RoleJson> resultList = Arrays.asList(response.getBody());
            logger.info("getAllRoles: response body {}", resultList);

            return resultList;
        } catch (HttpClientErrorException hce) {
            logger.error("getAllRoles: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public String createRole(RoleJson role) {
        try {
            String url = getUrl(CREATE_ROLE_URL);

            logger.info("createRole: calling crl {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(role, getHeaders());

            ResponseEntity<RoleJson> response = restTemplate.postForEntity(url, httpEntity, RoleJson.class);

            logger.info("createRole: response body {}", response.getBody());

            return response.getBody().getId();
        } catch (HttpClientErrorException hce) {
            logger.error("createRole: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    private MultiValueMap<String, String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public void updateRole(RoleJson role) {
        try {
            String url = getUrl(UPDATE_ROLE_URL);

            logger.info("updateRole: calling url {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(role, getHeaders());

            restTemplate.put(url, httpEntity);

            logger.info("updateRole; role {} updated", role.getId());
        } catch (HttpClientErrorException hce) {
            logger.error("updateRole: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public RoleJson findByRoleId(String id) {
        try {
            String url = getUrl(FIND_ROLE_BY_ID_URL);

            url = StringUtils.replace(url, "{id}", id);

            logger.info("findByRoleId: calling url {}", url);

            ResponseEntity<RoleJson> response = restTemplate.getForEntity(url, RoleJson.class);

            logger.info("findByRoleId: response body {}", response.getBody());

            return response.getBody();
        } catch (HttpClientErrorException hce) {
            logger.error("findByRoleId: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }
}
