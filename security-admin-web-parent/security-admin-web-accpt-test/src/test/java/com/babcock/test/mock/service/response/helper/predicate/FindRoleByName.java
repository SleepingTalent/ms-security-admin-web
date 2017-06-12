package com.babcock.test.mock.service.response.helper.predicate;

import com.babcock.test.mock.service.response.RoleJsonMock;

import java.util.function.Predicate;

public class FindRoleByName implements Predicate<RoleJsonMock> {

    private final String displayName;

    public FindRoleByName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean test(RoleJsonMock roleJsonMock) {
        return displayName.equals(roleJsonMock.getName()+" ("+roleJsonMock.getDescription()+")");
    }
}
