package com.forsrc.boot.authorization.web.test.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forsrc.boot.authorization.web.test.dao.database1.TestDatabase1Dao;
import com.forsrc.boot.authorization.web.test.dao.database2.TestDatabase2Dao;
import com.forsrc.boot.authorization.web.test.dao.mapper.database1.TestDatabase1Mapper;
import com.forsrc.boot.authorization.web.test.dao.mapper.database2.TestDatabase2Mapper;
import com.forsrc.boot.authorization.web.test.model.database1.TestDatabase1;
import com.forsrc.boot.authorization.web.test.model.database2.TestDatabase2;
import com.forsrc.boot.authorization.web.test.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {

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

    // public TestServiceImpl(TestDatabase1Dao testDatabase1Dao, TestDatabase2Dao
    // testDatabase2Dao) {
    // this.testDatabase1Dao = testDatabase1Dao;
    // this.testDatabase2Dao = testDatabase2Dao;
    // }

    @SuppressWarnings("unchecked")
    @Override
    public void initDb() {

        Map<String, Object> map = new HashMap<>();
        map.put("id", "0");
        this.jmsTemplate.convertAndSend("jms/queues/test", map);
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
        map.put("id", id);
        // this.jmsTemplate.convertAndSend("jms/queues/test", map);
        this.jmsTemplate.convertAndSend("jms/queues/test", test1);
        this.jmsTemplate.convertAndSend("jms/queues/test", test2);
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
        map.put("id", id);
        // this.jmsTemplate.convertAndSend("jms/queues/test", map);
        this.jmsTemplate.convertAndSend("jms/queues/test", test1);
        this.jmsTemplate.convertAndSend("jms/queues/test", test2);

        map.put("test", "OK");
        final ObjectMapper objectMapper = new ObjectMapper();
        jmsTemplate.execute(new SessionCallback() {
            @Override
            public Object doInJms(Session session) throws JMSException {
 
                Destination destination = jmsTemplate.getDestinationResolver()
                        .resolveDestinationName(session, "jms/queues/test", false);
                MessageProducer producer = session.createProducer(destination);
                TextMessage textMessage = session.createTextMessage();
                try {
                    System.out.println("=========");
                    textMessage.setText(objectMapper.writeValueAsString(map));
                } catch (JsonProcessingException e) {
                    textMessage.setText(e.getMessage());
                }
                producer.send(textMessage);
                return textMessage;
            }
        });
    }

}
