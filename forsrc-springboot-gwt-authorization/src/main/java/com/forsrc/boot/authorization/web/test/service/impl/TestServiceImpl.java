package com.forsrc.boot.authorization.web.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forsrc.boot.authorization.web.test.dao.mapper.database1.TestDatabase1Mapper;
import com.forsrc.boot.authorization.web.test.dao.mapper.database2.TestDatabase2Mapper;
import com.forsrc.boot.authorization.web.test.model.TestDatabase1;
import com.forsrc.boot.authorization.web.test.model.TestDatabase2;
import com.forsrc.boot.authorization.web.test.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDatabase1Mapper testDatabase1;
    
    @Autowired
    private TestDatabase2Mapper testDatabase2;

    @Override
    public void initDb() {

        testDatabase1.createTable();
        testDatabase2.createTable();
        TestDatabase1 test1 = new TestDatabase1();
        test1.setName("test-" + System.currentTimeMillis());
        long id = testDatabase1.insert(test1);
        

        TestDatabase2 test2 = new TestDatabase2();
        test2.setTestId(id);
        id = testDatabase2.insert(test2);
        System.out.println("id: " + id);
        System.out.println(testDatabase2.findById(id));
    }

}
