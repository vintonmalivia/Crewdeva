package com.crewdeva.Crewdeva.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private static abstract class DatabaseParams {
        private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
        private static final String URL = "jdbc:postgresql://localhost:5432/crewdeva";
        private static final String POSTGRES_USERNAME = "postgres";
        private static final String POSTGRES_PASSWORD = "postgres";
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
}