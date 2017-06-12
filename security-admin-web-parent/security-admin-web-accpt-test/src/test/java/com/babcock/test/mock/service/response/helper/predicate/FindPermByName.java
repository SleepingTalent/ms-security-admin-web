package com.babcock.test.mock.service.response.helper.predicate;

import com.babcock.test.mock.service.response.PermissionJsonMock;

import java.util.function.Predicate;

public class FindPermByName implements Predicate<PermissionJsonMock> {

    private final String displayName;

    public FindPermByName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean test(PermissionJsonMock permissionJsonMock) {
        return displayName.equals(permissionJsonMock.getName()+" ("+permissionJsonMock.getDescription()+")");
    }
}
