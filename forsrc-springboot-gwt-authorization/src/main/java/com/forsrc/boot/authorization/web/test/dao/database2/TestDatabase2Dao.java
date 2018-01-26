package com.forsrc.boot.authorization.web.test.dao.database2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forsrc.boot.authorization.web.test.model.database2.TestDatabase2;

@Repository
public interface TestDatabase2Dao extends JpaRepository<TestDatabase2, Long> {

}
