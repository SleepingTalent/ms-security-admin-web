package com.babcock.securityweb.service.proxy;

import com.babcock.securityweb.service.proxy.json.PermissionJson;
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
public class PermissionProxy {

    protected Logger logger = LoggerFactory.getLogger(PermissionProxy.class);

    @Value("${security-admin-service-url:http://localhost/security-admin-service}")
    private String securityAdminServiceUrl;

    public static final String GET_ALL_PERMISSIONS_URL = "/securityadmin/permissions";
    public static final String FIND_PERMISSION_BY_ID_URL = "/securityadmin/permissions/{id}";

    public static final String CREATE_PERMISSIONS_URL = "/securityadmin/permissions";
    public static final String UPDATE_PERMISSIONS_URL = "/securityadmin/permissions";

    @Autowired
    RestTemplate restTemplate;

    private String getUrl(String restUrl) {
        if(securityAdminServiceUrl.contains("http://")) {
            return securityAdminServiceUrl + restUrl;
        }else {
            return "http://" + securityAdminServiceUrl + restUrl;
        }
    }

    public List<PermissionJson> getAllPermissions() {
        try {
            String url = getUrl(GET_ALL_PERMISSIONS_URL);

            logger.info("getAllPermissions: Calling Url {}", url);

            ResponseEntity<PermissionJson[]> response = restTemplate.getForEntity(url, PermissionJson[].class);

            List<PermissionJson> responseList = Arrays.asList(response.getBody());
            logger.info("getAllPermissions: Response Body {}", responseList);

            return responseList;
        } catch (HttpClientErrorException hce) {
            logger.error("getAllPermissions: Error Response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public String createPermission(PermissionJson permissionJson) {
        try {
            String url = getUrl(CREATE_PERMISSIONS_URL);

            logger.info("createPermision: Calling Url {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(permissionJson,getHeaders());

            ResponseEntity<PermissionJson> response = restTemplate.postForEntity(url, httpEntity, PermissionJson.class);

            logger.info("createPermision: Response Body {}", response.getBody());

            return response.getBody().getId();
        } catch (HttpClientErrorException hce) {
            logger.error("createPermision: Error Response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public void updatePermission(PermissionJson permission) {
        try {
            String url = getUrl(UPDATE_PERMISSIONS_URL);

            logger.info("updatePermission: calling url {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(permission, getHeaders());

            restTemplate.put(url, httpEntity);

            logger.info("updatePermission; permission {} updated", permission.getId());
        } catch (HttpClientErrorException hce) {
            logger.error("updatePermission: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public PermissionJson findByPermissionId(String permissionId) {
        try {
            String url = getUrl(FIND_PERMISSION_BY_ID_URL);

            url = StringUtils.replace(url, "{id}", permissionId);

            logger.info("findByRoleId: calling url {}", url);

            ResponseEntity<PermissionJson> response = restTemplate.getForEntity(url, PermissionJson.class);

            logger.info("findByRoleId: response body {}", response.getBody());

            return response.getBody();
        } catch (HttpClientErrorException hce) {
            logger.error("findByRoleId: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    private MultiValueMap<String, String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
