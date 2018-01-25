package com.forsrc.boot.authorization.web.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.forsrc.boot.authorization.web.test.dao.database1.TestDatabase1Dao;
import com.forsrc.boot.authorization.web.test.dao.database2.TestDatabase2Dao;
import com.forsrc.boot.authorization.web.test.dao.mapper.database1.TestDatabase1Mapper;
import com.forsrc.boot.authorization.web.test.dao.mapper.database2.TestDatabase2Mapper;
import com.forsrc.boot.authorization.web.test.model.database1.TestDatabase1;
import com.forsrc.boot.authorization.web.test.model.database2.TestDatabase2;
import com.forsrc.boot.authorization.web.test.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDatabase1Mapper testDatabase1;
    
    @Autowired
    private TestDatabase2Mapper testDatabase2;

    @Autowired
    private TestDatabase1Dao testDatabase1Dao;

    @Autowired
    private TestDatabase2Dao testDatabase2Dao;

    @Autowired
    private JmsTemplate jmsTemplate;

//    public TestServiceImpl(TestDatabase1Dao testDatabase1Dao, TestDatabase2Dao testDatabase2Dao) {
//        this.testDatabase1Dao = testDatabase1Dao;
//        this.testDatabase2Dao = testDatabase2Dao;
//    }

    @Override
    public void initDb() {

    	this.jmsTemplate.convertAndSend("jms/queues/test", "test ...");
        testDatabase1.createTable();
        testDatabase2.createTable();
        TestDatabase1 test1 = new TestDatabase1();
        test1.setName("test-" + System.currentTimeMillis());
        int count = testDatabase1.insert(test1);
        Long id = test1.getId();
        System.out.println(testDatabase1.findById(id));

        TestDatabase2 test2 = new TestDatabase2();
        test2.setTestId(id);
        count = testDatabase2.insert(test2);
        id = test2.getId();
        System.out.println("id: " + id);
        System.out.println(testDatabase2.findById(id));
        System.out.println("------------");

        test1 = new TestDatabase1();
        test1.setName("test-" + System.currentTimeMillis());
        testDatabase1Dao.save(test1);
        id = test1.getId();
        System.out.println(testDatabase1Dao.getOne(id));

        test2 = new TestDatabase2();
        test2.setTestId(id);
        testDatabase2Dao.save(test2);
        id = test2.getId();
        System.out.println(testDatabase2Dao.getOne(id));
        this.jmsTemplate.convertAndSend("jms/queues/test", "test OK");
    }

}
