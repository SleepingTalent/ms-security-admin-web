package com.babcock.test.mock.service.response.helper.predicate;

import com.babcock.test.mock.service.response.UserJsonMock;

import java.util.function.Predicate;

public class FindUserById implements Predicate<UserJsonMock> {

    private final String id;

    public FindUserById(String id) {
        this.id = id;
    }

    @Override
    public boolean test(UserJsonMock userJsonMock) {
        return id.equals(userJsonMock.getId());
    }
}
