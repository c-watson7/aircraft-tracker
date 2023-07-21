package tracker.repository;

import com.zaxxer.hikari.*;
import tracker.util.config.ConfigLoader;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final HikariConfig hikariConfig = new HikariConfig();
    private static final HikariDataSource dataSource;

    private static final ConfigLoader config = new ConfigLoader();
    private static final String jdbcUrl = config.getProperty("jdbcUrl");
    private static final String username = config.getProperty("username");
    private static final String pw = config.getProperty("pw");

    static {
        hikariConfig.setJdbcUrl(jdbcUrl); // replace with your database URL
        hikariConfig.setUsername(username); // replace with your username
        hikariConfig.setPassword(pw); // replace with your password
        hikariConfig.setMaximumPoolSize(10); // set the maximum pool size

        dataSource = new HikariDataSource(hikariConfig);
    }

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
