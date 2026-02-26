package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.example")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        var cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:postgresql://localhost:5432/user_directory");
        cfg.setUsername("postgres");
        cfg.setPassword("qwerty");
        return new HikariDataSource(cfg);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
