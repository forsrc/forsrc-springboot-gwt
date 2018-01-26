package com.forsrc.boot.authorization.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
// @MapperScan("com.forsrc.**.dao.mapper")
public class MyBatisConfig {

    @Configuration
    @MapperScan(basePackages = {"com.forsrc.**.mapper.databsae1"},
        sqlSessionFactoryRef ="sqlSessionFactory1",
        sqlSessionTemplateRef = "sqlSessionTemplate1")
    public static class DataSource1 {

        @Autowired
        @Qualifier("dataSource1")
        private DataSource dataSource1;

        @Bean("sqlSessionFactory1")
        @Qualifier("sqlSessionFactory1")
        @Primary
        public SqlSessionFactory sqlSessionFactory1() throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource1);

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            sqlSessionFactoryBean.setMapperLocations(
                    resolver.getResources("classpath*:com/forsrc/**/mapper/database1/*Mapper.xml"));
            // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
            return sqlSessionFactoryBean.getObject();
        }

        @Bean("sqlSessionTemplate1")
        @Qualifier("sqlSessionTemplate1")
        @Primary
        public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory1) {
            return new SqlSessionTemplate(sqlSessionFactory1);
        }
    }

    @Configuration
    @MapperScan(basePackages = {"com.forsrc.**.mapper.databsae2"},
        sqlSessionFactoryRef ="sqlSessionFactory2",
        sqlSessionTemplateRef = "sqlSessionTemplate2")
    public static class DataSource2 {
        @Autowired
        @Qualifier("dataSource2")
        private DataSource dataSource2;

        @Bean("sqlSessionFactory2")
        @Qualifier("sqlSessionFactory2")
        public SqlSessionFactory sqlSessionFactory2() throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource2);

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            sqlSessionFactoryBean.setMapperLocations(
                    resolver.getResources("classpath*:com/forsrc/**/mapper/database2/*Mapper.xml"));
            // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
            return sqlSessionFactoryBean.getObject();
        }

        @Bean("sqlSessionTemplate2")
        @Qualifier("sqlSessionTemplate2")
        public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory2) {
            return new SqlSessionTemplate(sqlSessionFactory2);
        }
    }
}