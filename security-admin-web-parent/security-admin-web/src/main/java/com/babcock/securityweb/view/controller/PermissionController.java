package com.babcock.securityweb.view.controller;

import com.babcock.securityweb.service.proxy.json.PermissionJson;
import com.babcock.securityweb.view.model.PermissionFormModel;
import com.babcock.securityweb.view.helper.NavigationHelper;
import com.babcock.securityweb.view.helper.PageModelHelper;
import com.babcock.securityweb.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PermissionController {

    protected Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    PermissionService permissionService;

    @Autowired
    PageModelHelper pageModelHelper;

    @ModelAttribute("permission")
    public PermissionFormModel getSecurityPermission() {
        return new PermissionFormModel();
    }

    @RequestMapping(value = "/create-permission", method = RequestMethod.POST)
    public ModelAndView createPermission(@Valid @ModelAttribute("permission") PermissionFormModel permissionFormModel, BindingResult bindingResult) {
        logger.info("creating permission {}",permissionFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for permission {}",permissionFormModel);
            return pageModelHelper.getModel(NavigationHelper.CREATE_PERMISSION_PAGE);
        }

        PermissionJson permissionJson = new PermissionJson();
        permissionJson.setName(permissionFormModel.getName());
        permissionJson.setDescription(permissionFormModel.getDescription());

        String createdId = permissionService.createPermission(permissionJson);
        permissionFormModel.setId(createdId);

        return navigateToPermissions();

    }

    @RequestMapping(value = "/edit-permission", method = RequestMethod.POST)
    public ModelAndView editPermission(@Valid @ModelAttribute("permission") PermissionFormModel permissionFormModel, BindingResult bindingResult) {
        logger.info("updating permission {}",permissionFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for permission {}",permissionFormModel);
            return pageModelHelper.getModel(NavigationHelper.EDIT_PERMISSION_PAGE);
        }

        PermissionJson permissionJson = new PermissionJson();
        permissionJson.setName(permissionFormModel.getName());
        permissionJson.setDescription(permissionFormModel.getDescription());

        permissionService.updatePermission(permissionJson);

        return navigateToPermissions();

    }

    @RequestMapping(value = "/navigate-to-permissions", method = RequestMethod.GET)
    public ModelAndView navigateToPermissions() {
        logger.info("navigating to permissions page");
        return pageModelHelper.getModel(NavigationHelper.PERMISSIONS_PAGE)
                .addObject("recentPermissions",permissionService.getAllPermissions());
    }

    @RequestMapping(value = "/navigate-to-create-permission", method = RequestMethod.GET)
    public ModelAndView navigateToCreatePermission() {
        logger.info("navigating to create permission page");
        return pageModelHelper.getModel(NavigationHelper.CREATE_PERMISSION_PAGE);
    }

    @RequestMapping(value = "/navigate-to-edit-permission", method = RequestMethod.GET)
    public ModelAndView navigateToEditPermission(@RequestParam("permissionId") String permissionId) {
        logger.info("navigating to edit permission page");

            PermissionJson permission = permissionService.findById(permissionId);

            PermissionFormModel permissionFormModel = new PermissionFormModel();
            permissionFormModel.setId(permission.getId());
            permissionFormModel.setName(permission.getName());
            permissionFormModel.setDescription(permission.getDescription());

            return pageModelHelper.getModel(NavigationHelper.EDIT_PERMISSION_PAGE).addObject("permission", permissionFormModel);
    }
}
