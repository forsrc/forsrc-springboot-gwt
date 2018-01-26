package com.forsrc.boot.authorization.web.test.dao.database1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forsrc.boot.authorization.web.test.model.database1.TestDatabase1;

@Repository
public interface TestDatabase1Dao extends JpaRepository<TestDatabase1, Long> {

}
