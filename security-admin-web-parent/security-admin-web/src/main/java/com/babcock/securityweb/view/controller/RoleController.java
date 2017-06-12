package com.babcock.securityweb.view.controller;

import com.babcock.securityweb.view.helper.BindErrorHelper;
import com.babcock.securityweb.view.helper.NavigationHelper;
import com.babcock.securityweb.view.helper.PageModelHelper;
import com.babcock.securityweb.view.model.RoleFormModel;
import com.babcock.securityweb.service.PermissionService;
import com.babcock.securityweb.service.RoleService;
import com.babcock.securityweb.service.proxy.json.RoleJson;
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
public class RoleController {

    protected Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    PageModelHelper pageModelHelper;

    @Autowired
    BindErrorHelper bindErrorHelper;

    @ModelAttribute("role")
    public RoleFormModel getSecurityRole() {
        return new RoleFormModel();
    }

    @RequestMapping(value = "/create-role", method = RequestMethod.POST)
    public ModelAndView createRole(@Valid @ModelAttribute("role") RoleFormModel roleFormModel, BindingResult bindingResult) {
        boolean assignPermissions = roleFormModel.isAssignPermissions();

        logger.info("creating role {}",roleFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for role {}",roleFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.CREATE_ROLE_PAGE);
        }


        RoleJson roleJson = new RoleJson();
        roleJson.setName(roleFormModel.getName());
        roleJson.setDescription(roleFormModel.getDescription());

        String createdRoleId = roleService.createRole(roleJson);
        roleFormModel.setId(createdRoleId);

        if(assignPermissions) {
            logger.info("redirecting to assign role permissions");
            return pageModelHelper.getModel(NavigationHelper.ASSIGN_ROLE_PERMISSIONS_PAGE, false)
                    .addObject("permissions", permissionService.getAllPermissions())
                    .addObject("role", roleFormModel);
        }else {
            return navigateToRoles();
        }
    }

    @RequestMapping(value = "/edit-role", method = RequestMethod.POST)
    public ModelAndView editRole(@Valid @ModelAttribute("role") RoleFormModel roleFormModel, BindingResult bindingResult) {
        logger.info("updating role {}",roleFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for role {}",roleFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.EDIT_ROLE_PAGE);
        }

        RoleJson roleJson = new RoleJson();
        roleJson.setId(roleFormModel.getId());
        roleJson.setName(roleFormModel.getName());
        roleJson.setDescription(roleFormModel.getDescription());
        roleJson.setPermissions(roleFormModel.getSecurityPermissions());

        roleService.updateRole(roleJson);

        return navigateToRoles();
    }

    @RequestMapping(value = "/update-role-permissions", method = RequestMethod.POST)
    public ModelAndView updateRolePermissions(@Valid @ModelAttribute("role") RoleFormModel roleFormModel, BindingResult bindingResult) {
        logger.info("updating role {}",roleFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for role {}",roleFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.ASSIGN_ROLE_PERMISSIONS_PAGE);
        }

        RoleJson roleJson = new RoleJson();
        roleJson.setId(roleFormModel.getId());
        roleJson.setName(roleFormModel.getName());
        roleJson.setDescription(roleFormModel.getDescription());
        roleJson.setPermissions(roleFormModel.getSecurityPermissions());

        roleService.updateRole(roleJson);

        return navigateToRoles();
    }

    @RequestMapping(value = "/navigate-to-roles", method = RequestMethod.GET)
    public ModelAndView navigateToRoles() {
        logger.info("navigating to roles page");
        return pageModelHelper.getModel(NavigationHelper.ROLES_PAGE)
                .addObject("recentRoles",roleService.getAllRoles());
    }

    @RequestMapping(value = "/navigate-to-create-role", method = RequestMethod.GET)
    public ModelAndView navigateToCreateRole() {
        return pageModelHelper.getModel(NavigationHelper.CREATE_ROLE_PAGE);
    }

    @RequestMapping(value = "/navigate-to-edit-role", method = RequestMethod.GET)
    public ModelAndView navigateToEditRole(@RequestParam("roleId") String roleId) {
        logger.info("finding role By Id {}",roleId);

        RoleJson roleJson = roleService.findById(roleId);

        logger.info("found role {}",roleJson);

        RoleFormModel roleFormModel = new RoleFormModel();
        roleFormModel.setId(roleJson.getId());
        roleFormModel.setName(roleJson.getName());
        roleFormModel.setDescription(roleJson.getDescription());
        roleFormModel.setSecurityPermissions(roleJson.getPermissions());

        return pageModelHelper.getModel(NavigationHelper.EDIT_ROLE_PAGE)
                .addObject("permissions", permissionService.getAllPermissions())
                .addObject("role", roleFormModel);
    }
}
