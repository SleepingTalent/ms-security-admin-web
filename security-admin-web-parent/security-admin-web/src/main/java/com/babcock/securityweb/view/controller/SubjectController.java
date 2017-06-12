package com.babcock.securityweb.view.controller;


import com.babcock.securityweb.service.proxy.json.SubjectJson;
import com.babcock.securityweb.view.model.SubjectFormModel;
import com.babcock.securityweb.view.helper.BindErrorHelper;
import com.babcock.securityweb.view.helper.NavigationHelper;
import com.babcock.securityweb.view.helper.PageModelHelper;
import com.babcock.securityweb.service.RoleService;
import com.babcock.securityweb.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SubjectController {

    protected Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    SubjectService subjectService;

    @Autowired
    RoleService roleService;

    @Autowired
    PageModelHelper pageModelHelper;

    @Autowired
    BindErrorHelper bindErrorHelper;

    @ModelAttribute("subject")
    public SubjectFormModel getSecurityRole() {
        return new SubjectFormModel();
    }

    @RequestMapping(value = "/create-subject", method = RequestMethod.POST)
    public ModelAndView createRole(@Valid @ModelAttribute("subject") SubjectFormModel subjectFormModel, BindingResult bindingResult) {
        logger.info("creating subject {}",subjectFormModel);
        boolean assignRoles = subjectFormModel.isAssignRoles();

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for subject {}",subjectFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.CREATE_SUBJECT_PAGE);
        }

        SubjectJson subjectJson = new SubjectJson();
        subjectJson.setUsername(subjectFormModel.getUsername());
        subjectJson.setPassword(subjectFormModel.getPassword());

        String createdSubjectId = subjectService.createSubject(subjectJson);

        subjectFormModel.setId(createdSubjectId);

        if(assignRoles) {
            logger.info("redirecting to assign subject roles");
            return pageModelHelper.getModel(NavigationHelper.ASSIGN_SUBJECT_ROLES_PAGE, false)
                    .addObject("roles", roleService.getAllRoles())
                    .addObject("subject", subjectFormModel);
        }else {
            return navigateToSubjects();
        }
    }

    @RequestMapping(value = "/update-subject-roles", method = RequestMethod.POST)
    public ModelAndView updateRolePermissions(@Valid @ModelAttribute("subject") SubjectFormModel subjectFormModel, BindingResult bindingResult) {
        logger.info("updating subject {}",subjectFormModel);

        if(bindingResult.hasErrors()) {
            logger.info("validation errors for subject {}",subjectFormModel);
            bindErrorHelper.outputErrors(bindingResult,logger);
            return pageModelHelper.getModel(NavigationHelper.ASSIGN_ROLE_PERMISSIONS_PAGE);
        }

        SubjectJson subjectJson = new SubjectJson();
        subjectJson.setId(subjectFormModel.getId());
        subjectJson.setUsername(subjectFormModel.getUsername());
        subjectJson.setPassword(subjectFormModel.getPassword());
        subjectJson.setRoles(subjectFormModel.getSecurityRoles());

        subjectService.updateSubject(subjectJson);
        return navigateToSubjects();
    }

    @RequestMapping(value = "/edit-subject", method = RequestMethod.POST)
    public ModelAndView editSubject(@Valid @ModelAttribute("subject") SubjectFormModel subjectFormModel, BindingResult bindingResult) {
        logger.info("updating subject {}",subjectFormModel);

        if(bindingResult.hasErrors()) {

            //TODO: Hacky Need to Refactor Model to use view objects.
            if(bindingResult.getAllErrors().size()==1) {
                FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);
                logger.warn("validation error {}:{}",fieldError.getField(),fieldError.getCode());
                if(!fieldError.getField().equals("password")) {
                    logger.info("validation errors for subject {}", subjectFormModel);
                    bindErrorHelper.outputErrors(bindingResult, logger);
                    return pageModelHelper.getModel(NavigationHelper.EDIT_SUBJECT_PAGE);
                }

            }else {
                logger.info("validation errors for subject {}", subjectFormModel);
                bindErrorHelper.outputErrors(bindingResult, logger);
                return pageModelHelper.getModel(NavigationHelper.EDIT_SUBJECT_PAGE);
            }
        }

        SubjectJson subjectJson = new SubjectJson();
        subjectJson.setId(subjectFormModel.getId());
        subjectJson.setUsername(subjectFormModel.getUsername());
        subjectJson.setPassword(subjectFormModel.getPassword());
        subjectJson.setRoles(subjectFormModel.getSecurityRoles());

        subjectService.updateSubject(subjectJson);
        return navigateToSubjects();
    }

    @RequestMapping(value = "/navigate-to-subjects", method = RequestMethod.GET)
    public ModelAndView navigateToSubjects() {
        logger.info("navigating to subjects page");
        return pageModelHelper.getModel(NavigationHelper.SUBJECTS_PAGE)
                .addObject("recentSubjects",subjectService.getAllSubjects());
    }

    @RequestMapping(value = "/navigate-to-create-subject", method = RequestMethod.GET)
    public ModelAndView navigateToCreateSubjects() {
        return pageModelHelper.getModel(NavigationHelper.CREATE_SUBJECT_PAGE);
    }

    @RequestMapping(value = "/navigate-to-edit-subject", method = RequestMethod.GET)
    public ModelAndView navigateToEditSubjects(@RequestParam("subjectId") String subjectId) {
        SubjectJson subject = subjectService.findById(subjectId);

        SubjectFormModel subjectFormModel = new SubjectFormModel();
        subjectFormModel.setId(subject.getId());
        subjectFormModel.setUsername(subject.getUsername());
        subjectFormModel.setSecurityRoles(subject.getRoles());

        return pageModelHelper.getModel(NavigationHelper.EDIT_SUBJECT_PAGE)
                .addObject("roles", roleService.getAllRoles())
                .addObject("subject", subjectFormModel);
    }

}
