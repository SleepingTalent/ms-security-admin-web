package com.babcock.test.helper.rest;

import com.babcock.test.runtime.RuntimeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class RestHelper {

    @Autowired
    RuntimeState runtimeState;

}
