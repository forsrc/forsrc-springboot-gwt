package com.forsrc.boot.authorization.web.test.dao.mapper.database1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.forsrc.boot.authorization.web.test.model.TestDatabase1;

@Mapper
public interface TestDatabase1Mapper {

    public TestDatabase1 findByUserId(@Param("id") Long id);

    public TestDatabase1 insert(@Param("bean") TestDatabase1 bean);
}
