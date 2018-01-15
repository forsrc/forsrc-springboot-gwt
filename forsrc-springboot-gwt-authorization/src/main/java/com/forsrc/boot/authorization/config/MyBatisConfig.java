package com.forsrc.boot.authorization.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
// @MapperScan("com.forsrc.**.dao.mapper")
public class MyBatisConfig {

    @Configuration
    @MapperScan("com.forsrc.**.dao.mapper.databsae1")
    public static class DataSource1 {

        @Autowired
        @Qualifier("dataSource1")
        private DataSource dataSource1;

        @Bean
        @Qualifier("sqlSessionFactory1")
        public SqlSessionFactory sqlSessionFactory1() throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource1);

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            sqlSessionFactoryBean.setMapperLocations(
                    resolver.getResources("classpath*:com/forsrc/**/dao/mapper/database1/*Mapper.xml"));
            // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
            return sqlSessionFactoryBean.getObject();
        }

    }

    @Configuration
    @MapperScan("com.forsrc.**.dao.mapper.databsae2")
    public static class DataSource2 {
        @Autowired
        @Qualifier("dataSource2")
        private DataSource dataSource2;

        @Bean
        @Qualifier("sqlSessionFactory2")
        public SqlSessionFactory sqlSessionFactory2() throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource2);

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            sqlSessionFactoryBean.setMapperLocations(
                    resolver.getResources("classpath*:com/forsrc/**/dao/mapper/database2/*Mapper.xml"));
            // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
            return sqlSessionFactoryBean.getObject();
        }
    }
}