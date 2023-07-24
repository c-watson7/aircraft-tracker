package tracker.repository;

import com.zaxxer.hikari.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.util.config.ConfigLoader;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseManager.class);

    private static final HikariConfig hikariConfig = new HikariConfig();
    private static final HikariDataSource dataSource;

    private static final ConfigLoader config = new ConfigLoader();
    private static final String jdbcUrl = config.getProperty("jdbcUrl");
    private static final String username = config.getProperty("username");
    private static final String pw = config.getProperty("pw");

    static {
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(pw);
        hikariConfig.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(hikariConfig);
    }

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch(SQLException e) {
            LOG.error("Error while getting a DB connection", e);
            throw e;
        }
    }
}
