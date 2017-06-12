package com.babcock.securityweb.view.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @InjectMocks
    IndexController indexController;

    @Test
    public void navigateToContextRoot_returnsExpectedTemplate() throws Exception {
        assertEquals("index", indexController.navigateToContextRoot());
    }

    @Test
    public void navigateToIndex_returnsExpectedTemplate() throws Exception {
        assertEquals("index", indexController.navigateToIndex());
    }
}