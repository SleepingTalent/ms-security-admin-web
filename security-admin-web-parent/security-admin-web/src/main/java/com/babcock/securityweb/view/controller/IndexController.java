package com.babcock.securityweb.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String navigateToContextRoot() {
        return "index";
    }

    @RequestMapping("/navigate-to-index")
    public String navigateToIndex() {
        return navigateToContextRoot();
    }

}
