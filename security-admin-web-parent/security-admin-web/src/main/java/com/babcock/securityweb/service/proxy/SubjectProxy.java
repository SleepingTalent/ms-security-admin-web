package com.babcock.securityweb.service.proxy;

import com.babcock.securityweb.service.proxy.json.SubjectJson;
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
public class SubjectProxy {

    protected Logger logger = LoggerFactory.getLogger(SubjectProxy.class);

    @Value("${security-admin-service-url:http://localhost/security-admin-service}")
    private String securityAdminServiceUrl;

    public static final String GET_ALL_SUBJECTS_URL = "/securityadmin/subjects";
    public static final String FIND_SUBJECT_BY_ID_URL = "/securityadmin/subjects/{id}";

    public static final String CREATE_SUBJECT_URL = "/securityadmin/subjects";
    public static final String UPDATE_SUBJECT_URL = "/securityadmin/subjects";

    @Autowired
    RestTemplate restTemplate;

    private String getUrl(String restUrl) {
        if(securityAdminServiceUrl.contains("http://")) {
            return securityAdminServiceUrl + restUrl;
        }else {
            return "http://" + securityAdminServiceUrl + restUrl;
        }
    }

    public List<SubjectJson> getAllSubjects() {
        try {
            String url = getUrl(GET_ALL_SUBJECTS_URL);

            logger.info("getAllSubjects: Calling Url {}", url);

            ResponseEntity<SubjectJson[]> response = restTemplate.getForEntity(url, SubjectJson[].class);

            logger.info("getAllSubjects: Response Body {}", response.getBody());

            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException hce) {
            logger.error("getAllSubjects: Error Response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public String createSubject(SubjectJson subject) {
        try {
            String url = getUrl(CREATE_SUBJECT_URL);

            logger.info("createSubject: calling crl {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(subject, getHeaders());

            ResponseEntity<SubjectJson> response = restTemplate.postForEntity(url, httpEntity, SubjectJson.class);

            logger.info("createSubject: response body {}", response.getBody());

            return response.getBody().getId();
        } catch (HttpClientErrorException hce) {
            logger.error("createSubject: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public void updateSubject(SubjectJson subject) {
        try {
            String url = getUrl(UPDATE_SUBJECT_URL);

            logger.info("updateSubject: calling url {}", url);

            HttpEntity<?> httpEntity = new HttpEntity<>(subject, getHeaders());

            restTemplate.put(url, httpEntity);

            logger.info("updateSubject; role {} updated", subject.getId());
        } catch (HttpClientErrorException hce) {
            logger.error("updateSubject: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    public SubjectJson findBySubjectId(String id) {
        try {
            String url = getUrl(FIND_SUBJECT_BY_ID_URL);

            url = StringUtils.replace(url, "{id}", id);

            logger.info("findBySubjectId: calling url {}", url);

            ResponseEntity<SubjectJson> response = restTemplate.getForEntity(url, SubjectJson.class);

            logger.info("findBySubjectId: response body {}", response.getBody());

            return response.getBody();
        } catch (HttpClientErrorException hce) {
            logger.error("findBySubjectId: error response : {}: {}", hce.getRawStatusCode(), hce.getResponseBodyAsString());
            throw hce;
        }
    }

    private MultiValueMap<String, String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
