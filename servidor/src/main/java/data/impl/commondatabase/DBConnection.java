package data.impl.commondatabase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import config.Configuration;
import config.ConstantServer;
import errores.exceptions.BDDException;
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

        hikariConfig.setJdbcUrl(config.getProperty(ConstantServer.RESTAURANT_DATA_BASE));
        hikariConfig.setUsername(config.getProperty(ConstantServer.USER));
        hikariConfig.setPassword(config.getProperty(ConstantServer.PASSWORD));
        hikariConfig.setDriverClassName(config.getProperty(ConstantServer.DRIVER));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty(ConstantServer.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(ConstantServer.PREP_STMT_CACHE_SIZE, 250);
        hikariConfig.addDataSourceProperty(ConstantServer.PREP_STMT_CACHE_SQL_LIMIT, 2048);

        return new HikariDataSource(hikariConfig);
    }



    public Connection getConnection() {
        Connection con ;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }

        return con;
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }

}
