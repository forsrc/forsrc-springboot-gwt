package com.forsrc.boot.authorization.web.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_test_database2")
public class TestDatabase2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long testTd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getTestTd() {
        return testTd;
    }

    public void setTestTd(Long testTd) {
        this.testTd = testTd;
    }

}
