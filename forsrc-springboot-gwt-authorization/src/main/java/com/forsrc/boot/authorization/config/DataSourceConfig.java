package com.forsrc.boot.authorization.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class DataSourceConfig {



    @Bean(name = "dataSource1")
    @Qualifier("dataSource1")
    @Primary
    @ConfigurationProperties(prefix = "db.db1.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource2")
    @Qualifier("dataSource2")
    @ConfigurationProperties(prefix = "db.db2.datasource")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
}
