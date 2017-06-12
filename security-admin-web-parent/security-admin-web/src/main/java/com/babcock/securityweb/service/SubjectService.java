package com.babcock.securityweb.service;

import com.babcock.securityweb.service.proxy.json.SubjectJson;
import com.babcock.securityweb.view.model.SubjectFormModel;
import com.babcock.securityweb.service.proxy.SubjectProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    protected Logger logger = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    SubjectProxy subjectProxy;

    @HystrixCommand
    public List<SubjectJson> getAllSubjects() {
        return subjectProxy.getAllSubjects();
    }

    @HystrixCommand
    public String createSubject(SubjectJson subject) {
        return subjectProxy.createSubject(subject);
    }

    @HystrixCommand
    public void updateSubject(SubjectJson subject) {
        subjectProxy.updateSubject(subject);
    }

    @HystrixCommand
    public SubjectJson findById(String id) {
        return subjectProxy.findBySubjectId(id);
    }
}
