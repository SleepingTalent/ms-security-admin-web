package com.babcock.test.helper.rest;

import com.babcock.test.runtime.RuntimeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class RestHelper {

    @Autowired
    RuntimeState runtimeState;

    //@Autowired
    //protected TestRestTemplate restTemplate;

    private String getCustomerUrl() {
        return "http://" + runtimeState.getHost() + "/customer";
    }

    private String buildFindByIdUrl(Long id) {
        return getCustomerUrl() + "/id/" + id;
    }

    private String buildFindByLastNameUrl(String lastName) {
        return getCustomerUrl() + "/lastname/" + lastName;
    }

    private String buildFindAll() {
        return getCustomerUrl() + "/findAll";
    }

//    public Customer findById(Long id) {
//        HttpEntity<?> httpEntity = new HttpEntity<>(createAuthHeader());
//
//        ResponseEntity<Customer> response = restTemplate.exchange(buildFindByIdUrl(id), HttpMethod.GET, httpEntity, Customer.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        return response.getBody();
//    }
//
//    private HttpHeaders createAuthHeader() {
//        HttpHeaders requestHeaders = new HttpHeaders();
//        String auth = "guest:guest123";
//
//        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
//        requestHeaders.add("Authorization","Basic " + new String(encodedAuth));
//        return requestHeaders;
//    }
//
//    public List<Customer> findByLastName(String lastName) {
//        HttpEntity<?> httpEntity = new HttpEntity<>(createAuthHeader());
//
//        ResponseEntity<Customer[]> response = restTemplate.exchange(buildFindByLastNameUrl(lastName), HttpMethod.GET, httpEntity, Customer[].class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        return Arrays.asList(response.getBody());
//    }
//
//    public List<Customer> findAll() {
//        HttpEntity<?> httpEntity = new HttpEntity<>(createAuthHeader());
//
//        ResponseEntity<Customer[]> response = restTemplate.exchange(buildFindAll(), HttpMethod.GET, httpEntity, Customer[].class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        return Arrays.asList(response.getBody());
//    }

}
