package com.babcock.securityweb.view.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PageModelHelper {

    protected Logger logger = LoggerFactory.getLogger(PageModelHelper.class);

    public ModelAndView getModel(NavigationHelper navigationHelper) {
        return getModel(navigationHelper,false);
    }

    public ModelAndView getModel(NavigationHelper navigationHelper, boolean redirect) {

        if(redirect) {
            logger.debug("setting view name {}",navigationHelper.getGetPageWithRedirect());
            return new ModelAndView(navigationHelper.getGetPageWithRedirect());
        } else {
            logger.debug("setting view name {}",navigationHelper.getGetPage());
            return new ModelAndView(navigationHelper.getGetPage());
        }
    }
}
