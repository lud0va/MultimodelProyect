package data.impl.commondatabase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import config.Configuration;
import config.Constants;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class DBConnection {
    private final Configuration config;
    private final DataSource hikariDataSource;

    @Inject
    public DBConnection(Configuration config) {
        this.config = config;
        hikariDataSource = getHikariPool();
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(config.getProperty(Constants.RESTAURANT_DATA_BASE));
        hikariConfig.setUsername(config.getProperty(Constants.USER));
        hikariConfig.setPassword(config.getProperty(Constants.PASSWORD));
        hikariConfig.setDriverClassName(config.getProperty(Constants.DRIVER));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty(Constants.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(Constants.PREP_STMT_CACHE_SIZE, 250);
        hikariConfig.addDataSourceProperty(Constants.PREP_STMT_CACHE_SQL_LIMIT, 2048);

        return new HikariDataSource(hikariConfig);
    }



    public Connection getConnection() {
        Connection con ;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }

}
