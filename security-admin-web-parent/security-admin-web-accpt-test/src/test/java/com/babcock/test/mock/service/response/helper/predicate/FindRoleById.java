package com.babcock.test.mock.service.response.helper.predicate;

import com.babcock.test.mock.service.response.RoleJsonMock;

import java.util.function.Predicate;

public class FindRoleById implements Predicate<RoleJsonMock> {

    private final String id;

    public FindRoleById(String id) {
        this.id = id;
    }

    @Override
    public boolean test(RoleJsonMock roleJsonMock) {
        return id.equals(roleJsonMock.getId());
    }
}
