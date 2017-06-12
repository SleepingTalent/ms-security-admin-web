package com.babcock.securityweb.view.controller;


import com.babcock.securityweb.service.proxy.json.UserJson;
import com.babcock.securityweb.view.model.UserFormModel;
import com.babcock.securityweb.view.helper.BindErrorHelper;
import com.babcock.securityweb.view.helper.NavigationHelper;
import com.babcock.securityweb.view.helper.PageModelHelper;
import com.babcock.securityweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    PageModelHelper pageModelHelper;

    @Autowired
    BindErrorHelper bindErrorHelper;

    @ModelAttribute("user")
    public UserFormModel getUser() {
        return new UserFormModel();
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ModelAndView createUser(@Valid @ModelAttribute("user") UserFormModel userFormModel, BindingResult bindingResult) {
        logger.info("creating userFormModel {}", userFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for userFormModel {}", userFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.CREATE_USER_PAGE);
        }

        UserJson userJson = new UserJson();
        userJson.setPayNumber(userFormModel.getPayNumber());
        userJson.setName(userFormModel.getName());
        userJson.setEmail(userFormModel.getEmail());
        userJson.setJobTile(userFormModel.getJobTitle());
        userJson.setDepartment(userFormModel.getDepartment());
        userJson.setLocation(userFormModel.getLocation());


        userService.createUser(userJson);

        return navigateToUsers();
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("user") UserFormModel userFormModel, BindingResult bindingResult) {
        logger.info("updating userFormModel {}", userFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for userFormModel {}", userFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.EDIT_USER_PAGE);
        }

        UserJson userJson = new UserJson();
        userJson.setId(userFormModel.getId());
        userJson.setPayNumber(userFormModel.getPayNumber());
        userJson.setName(userFormModel.getName());
        userJson.setEmail(userFormModel.getEmail());
        userJson.setJobTile(userFormModel.getJobTitle());
        userJson.setDepartment(userFormModel.getDepartment());
        userJson.setLocation(userFormModel.getLocation());

        userService.updateUser(userJson);

        return navigateToUsers();
    }

    @RequestMapping(value = "/navigate-to-users", method = RequestMethod.GET)
    public ModelAndView navigateToUsers() {
        logger.info("navigating to users page");
        return pageModelHelper.getModel(NavigationHelper.USERS_PAGE)
                .addObject("recentUsers",userService.getAllUsers());
    }

    @RequestMapping(value = "/navigate-to-create-user", method = RequestMethod.GET)
    public ModelAndView navigateToCreateUser() {
        return pageModelHelper.getModel(NavigationHelper.CREATE_USER_PAGE);
    }

    @RequestMapping(value = "/navigate-to-edit-user", method = RequestMethod.GET)
    public ModelAndView navigateToEditUser(@RequestParam("userId") String userId) {

        UserJson userJson = userService.findById(userId);

        UserFormModel userFormModel = new UserFormModel();
        userFormModel.setId(userJson.getId());
        userFormModel.setPayNumber(userJson.getPayNumber());
        userFormModel.setName(userJson.getName());
        userFormModel.setEmail(userJson.getEmail());
        userFormModel.setJobTitle(userJson.getJobTile());
        userFormModel.setDepartment(userJson.getDepartment());
        userFormModel.setLocation(userJson.getLocation());

        return pageModelHelper.getModel(NavigationHelper.EDIT_USER_PAGE).addObject("user", userFormModel);
    }

}
