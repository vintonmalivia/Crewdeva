package com.crewdeva.Crewdeva.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {

    private static abstract class DatabaseParams {
        private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
        private static final String URL = "jdbc:postgresql://localhost:5432/keycloak_db";
        private static final String POSTGRES_USERNAME = "postgres";
        private static final String POSTGRES_PASSWORD = "postgres";
    }

    private static abstract class HibernateProperties {
        private static final String AUTO_DDL_EXECUTION = "hibernate.hbm2ddl.auto";
        private static final String SHOW_DDL_LOG = "hibernate.show_sql";
        private static final String DIALECT = "hibernate.dialect";
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(DatabaseParams.DRIVER_CLASS_NAME);
        dataSourceBuilder.url(DatabaseParams.URL);
        dataSourceBuilder.username(DatabaseParams.POSTGRES_USERNAME);
        dataSourceBuilder.password(DatabaseParams.POSTGRES_PASSWORD);
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("com.crewdeva.Crewdeva.models");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.put(HibernateProperties.AUTO_DDL_EXECUTION, "update");
        jpaProperties.put(HibernateProperties.SHOW_DDL_LOG, "true");
        jpaProperties.put(HibernateProperties.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
        em.setJpaProperties(jpaProperties);

        return em;
    }
}