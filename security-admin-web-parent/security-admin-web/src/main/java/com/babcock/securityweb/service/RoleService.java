package com.babcock.securityweb.service;

import com.babcock.securityweb.service.proxy.RoleProxy;
import com.babcock.securityweb.service.proxy.json.RoleJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleProxy roleProxy;

    protected Logger logger = LoggerFactory.getLogger(RoleService.class);

    //@HystrixCommand
    public List<RoleJson> getAllRoles() {
        return roleProxy.getAllRoles();
    }

    //@HystrixCommand
    public String createRole(RoleJson role) {
        return roleProxy.createRole(role);
    }

    //@HystrixCommand
    public void updateRole(RoleJson role) {
        roleProxy.updateRole(role);
    }

    //@HystrixCommand
    public RoleJson findById(String id) {
       return roleProxy.findByRoleId(id);
    }
}
