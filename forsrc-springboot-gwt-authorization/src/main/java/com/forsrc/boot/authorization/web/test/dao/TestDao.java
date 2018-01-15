package com.forsrc.boot.authorization.web.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forsrc.boot.authorization.web.test.model.TestDatabase1;


public interface TestDao extends JpaRepository<TestDatabase1, Long> {

}
