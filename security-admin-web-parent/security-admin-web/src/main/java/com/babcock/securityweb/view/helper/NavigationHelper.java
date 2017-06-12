package com.babcock.securityweb.view.helper;



public enum NavigationHelper {

    USERS_PAGE("user/users"),
    SUBJECTS_PAGE("subject/subjects"),
    ROLES_PAGE("role/roles"),
    PERMISSIONS_PAGE("permission/permissions"),

    CREATE_USER_PAGE("user/create-user"),
    CREATE_SUBJECT_PAGE("subject/create-subject"),
    CREATE_ROLE_PAGE("role/create-role"),
    CREATE_PERMISSION_PAGE("permission/create-permission"),

    ASSIGN_SUBJECT_ROLES_PAGE("subject/assign-subject-roles"),
    ASSIGN_ROLE_PERMISSIONS_PAGE("role/assign-role-permissions"),

    EDIT_PERMISSION_PAGE("permission/edit-permission"),
    EDIT_USER_PAGE("user/edit-user"),
    EDIT_ROLE_PAGE("role/edit-role"),
    EDIT_SUBJECT_PAGE("subject/edit-subject");

    private String getPage;

    NavigationHelper(String getPage) {
        this.getPage = getPage;
    }

    public String getGetPage() {
        return getPage;
    }

    public String getGetPageWithRedirect() {
        return "redirect:/"+getGetPage();
    }
}
