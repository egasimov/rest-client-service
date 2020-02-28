package org.qasimovey.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1521:XE");
        dataSourceBuilder.username("back");
        dataSourceBuilder.password("123");
        return dataSourceBuilder.build();
    }
}
