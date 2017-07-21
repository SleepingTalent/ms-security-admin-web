package com.babcock.securityweb.service;

import com.babcock.securityweb.service.proxy.PermissionProxy;
import com.babcock.securityweb.service.proxy.json.PermissionJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    protected Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private PermissionProxy permissionProxy;

    //@HystrixCommand
    public List<PermissionJson> getAllPermissions() {
        return permissionProxy.getAllPermissions();
    }

    //@HystrixCommand
    public String createPermission(PermissionJson permissionFormModel) {
       return permissionProxy.createPermission(permissionFormModel);
    }

    //@HystrixCommand
    public void updatePermission(PermissionJson permission) {
        permissionProxy.updatePermission(permission);
    }

    //@HystrixCommand
    public PermissionJson findById(String permissionId) {
        return permissionProxy.findByPermissionId(permissionId);
    }

}

