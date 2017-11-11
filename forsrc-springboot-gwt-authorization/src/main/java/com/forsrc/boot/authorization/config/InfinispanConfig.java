package com.forsrc.boot.authorization.config;

import java.lang.invoke.MethodHandles;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.spring.provider.SpringEmbeddedCacheManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import infinispan.autoconfigure.embedded.InfinispanCacheConfigurer;
import infinispan.autoconfigure.embedded.InfinispanGlobalConfigurer;



@org.springframework.context.annotation.Configuration
@EnableCaching
public class InfinispanConfig {

    public static final String CACHE_NAME = "forsrc-cache";

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Bean
    public InfinispanGlobalConfigurer globalConfiguration() {
        logger.info("Defining Global Configuration");
        return () -> GlobalConfigurationBuilder
              .defaultClusteredBuilder()
              .globalJmxStatistics().allowDuplicateDomains(true)
              .build();
    }


    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        logger.info("Defining {} configuration", CACHE_NAME);
        return manager -> {
            Configuration ispnConfig = new ConfigurationBuilder()
                  .clustering().cacheMode(CacheMode.DIST_SYNC)
                  .build();

            manager.defineConfiguration(CACHE_NAME, ispnConfig);
        };
    }

    @Bean
    public SpringEmbeddedCacheManagerFactoryBean springCache() {
        return new SpringEmbeddedCacheManagerFactoryBean();
    }
}
