package tracker.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.api.AircraftRetriever;
import tracker.model.Aircraft;

import java.sql.*;

public class AircraftDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AircraftRetriever.class);

    private static final String INSERT_AIRCRAFT = """
            INSERT INTO aircraft_data(icao, reg, cou, type, lat, lon, firstSeen, lastSeen, totalSeen)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String GET_AIRCRAFT = """
            SELECT * FROM aircraft_data
            WHERE icao = ?
            """;
    private static final String UPDATE_AIRCRAFT = """
            UPDATE aircraft_data
            set lat = ?,
            lon = ?,
            lastSeen = ?,
            totalSeen = ?
            WHERE icao = ?
            """;

//    public List<tracker.model.Aircraft> getAllAircraft() {
//    }
//
    public Aircraft getAircraft(String icao) {
        try(Connection connection = DatabaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_AIRCRAFT);
            statement.setString(1, icao);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String reg = resultSet.getString("reg");
                String cou = resultSet.getString("cou");
                String type = resultSet.getString("type");
                float lat = resultSet.getFloat("lat");
                float lon = resultSet.getFloat("lon");
                Timestamp firstSeen = resultSet.getTimestamp("firstSeen");
                Timestamp lastSeen = resultSet.getTimestamp("lastSeen");
                int totalSeen = resultSet.getInt("totalSeen");
                return new Aircraft(icao, reg, cou, type, lat, lon, firstSeen, lastSeen, totalSeen);
            }
        } catch (SQLException e) {
            LOG.error("Error connecting to DB", e);
        }
        return null;
    }

    public void insertAircraft(Aircraft aircraft) {
        try(Connection connection = DatabaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_AIRCRAFT);
            statement.setString(1, aircraft.getIcao());
            statement.setString(2, aircraft.getReg());
            statement.setString(3, aircraft.getCou());
            statement.setString(4, aircraft.getType());
            statement.setFloat(5, aircraft.getLat());
            statement.setFloat(6, aircraft.getLon());
            statement.setTimestamp(7, aircraft.getFirstSeen());
            statement.setTimestamp(8, aircraft.getLastSeen());
            statement.setInt(9, aircraft.getTotalSeen());
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Error connecting to DB", e);
        }
    }

    public void updateAircraft(Aircraft aircraft) {
    }
}
