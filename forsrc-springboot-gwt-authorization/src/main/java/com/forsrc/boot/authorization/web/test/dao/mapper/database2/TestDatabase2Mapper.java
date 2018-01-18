package com.forsrc.boot.authorization.web.test.dao.mapper.database2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.forsrc.boot.authorization.web.test.model.TestDatabase2;

@Mapper
public interface TestDatabase2Mapper {

    public TestDatabase2 findById(@Param("id") Long id);

    public int insert(@Param("bean") TestDatabase2 bean);

    public void createTable();
}
