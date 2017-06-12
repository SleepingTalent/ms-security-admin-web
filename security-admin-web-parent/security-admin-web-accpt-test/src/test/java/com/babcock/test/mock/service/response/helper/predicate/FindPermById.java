package com.babcock.test.mock.service.response.helper.predicate;

import com.babcock.test.mock.service.response.PermissionJsonMock;

import java.util.function.Predicate;

public class FindPermById implements Predicate<PermissionJsonMock> {

    private final String id;

    public FindPermById(String id) {
        this.id = id;
    }

    @Override
    public boolean test(PermissionJsonMock permissionJsonMock) {
        return id.equals(permissionJsonMock.getId());
    }
}
