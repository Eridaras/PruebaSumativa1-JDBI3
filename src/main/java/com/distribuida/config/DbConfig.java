package com.distribuida.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.sql.DataSource;

@ApplicationScoped
public class DbConfig {

    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;

    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;

    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;

    @Inject
    @ConfigProperty(name="db.drive")
    String dbDrive;

    @ApplicationScoped
    @Produces
    public DataSource dataSource(){

        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(dbDrive);
        ds.setJdbcUrl(dbUrl);
        ds.setUsername(dbUser);
        ds.setPassword(dbPassword);

        return ds;
    }
}
