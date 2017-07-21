package com.babcock.securityweb.service;

import com.babcock.securityweb.service.proxy.UserProxy;
import com.babcock.securityweb.service.proxy.json.UserJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    protected Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserProxy userProxy;

    //@HystrixCommand
    public List<UserJson> getAllUsers() {
        return userProxy.getAllUsers();
    }

    //@HystrixCommand
    public String createUser(UserJson user) {
        return userProxy.createUser(user);
    }

    //@HystrixCommand
    public void updateUser(UserJson user) {
        userProxy.createUser(user);
    }

    //@HystrixCommand
    public UserJson findById(String id) {
        return userProxy.findByUserId(id);
    }
}
