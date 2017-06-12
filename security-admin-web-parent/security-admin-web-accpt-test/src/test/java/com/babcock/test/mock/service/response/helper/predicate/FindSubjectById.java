package com.babcock.test.mock.service.response.helper.predicate;

import com.babcock.test.mock.service.response.SubjectJsonMock;

import java.util.function.Predicate;

public class FindSubjectById implements Predicate<SubjectJsonMock> {

    private final String id;

    public FindSubjectById(String id) {
        this.id = id;
    }

    @Override
    public boolean test(SubjectJsonMock subjectJsonMock) {
        return id.equals(subjectJsonMock.getId());
    }
}
