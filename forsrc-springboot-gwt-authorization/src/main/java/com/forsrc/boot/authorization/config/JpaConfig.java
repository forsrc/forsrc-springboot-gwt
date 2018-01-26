package com.forsrc.boot.authorization.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private Environment environment;

    @Configuration
    @EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1",
        basePackages = { "com.forsrc..dao.database1", "com.forsrc.boot.authorization.web.test.dao.database1" })
    class Jap1 {

        @Autowired
        @Qualifier("dataSource1")
        private DataSource dataSource1;

        @Bean(name = "entityManagerFactory1")
        @Qualifier("entityManagerFactory1")
        @Primary
        public LocalContainerEntityManagerFactoryBean entityManagerFactory1(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(dataSource1)
                    .properties(getVendorProperties(dataSource1))
                    //.packages("com.forsrc.boot.authorization.web.test.model.database1")
                    .packages(environment.getProperty("db.db1.jpa.packages").split("\\s*,\\s*"))
                    .persistenceUnit("persistenceUnit-database-1")
                    .build();
        }

        @Bean(name = "entityManager1")
        @Qualifier("entityManager1")
        @Primary
        public EntityManager entityManager1(EntityManagerFactoryBuilder builder) {
            return entityManagerFactory1(builder).getObject().createEntityManager();
        }

        @Bean(name = "transactionManager1")
        @Qualifier("transactionManager1")
        @Primary
        PlatformTransactionManager transactionManager1(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(entityManagerFactory1(builder).getObject());
        }
    }

    @Configuration
    @EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2",
        basePackages = { "com.forsrc..dao.database2", "com.forsrc.boot.authorization.web.test.dao.database2" })
    class Jap2 {

        @Autowired
        @Qualifier("dataSource2")
        private DataSource dataSource2;

        @Bean(name = "entityManager2")
        @Qualifier("entityManager2")
        public EntityManager entityManager2(EntityManagerFactoryBuilder builder) {
            return entityManagerFactory2(builder).getObject().createEntityManager();
        }

        @Bean(name = "entityManagerFactory2")
        @Qualifier("entityManagerFactory2")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(dataSource2)
                    .properties(getVendorProperties(dataSource2))
                    //.packages("com.forsrc.boot.authorization.web.test.model.database2")
                    .packages(environment.getProperty("db.db2.jpa.packages").split("\\s*,\\s*"))
                    .persistenceUnit("persistenceUnit-database-2")
                    .build();
        }

        @Bean(name = "transactionManager2")
        @Qualifier("transactionManager2")
        PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(entityManagerFactory2(builder).getObject());
        }
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}
