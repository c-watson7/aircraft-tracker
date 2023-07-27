package tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.model.Aircraft;
import tracker.model.AircraftFlightData;
import tracker.repository.AircraftDAO;

import java.util.List;

public class AircraftService {
    private static final Logger LOG = LoggerFactory.getLogger(AircraftService.class);
    private static final AircraftDAO aircraftDAO = new AircraftDAO();

    public static void processAndSaveAircraft(List<AircraftFlightData> flightDataList) {
        int newAircraft = 0, updatedAircraft = 0;
        int numberOfAircraft = flightDataList.size();

        LOG.info("Writing data from " + numberOfAircraft + " aircraft.");

        for (AircraftFlightData flightData : flightDataList) {
            Aircraft aircraft = aircraftDAO.getAircraft(flightData.hex());
            //Query DB - if Aircraft exists update entry
            if(aircraft != null) {
                aircraftDAO.updateAircraft(aircraft);
                updatedAircraft++;
            } else {
                //If not, create new entry and insert
                aircraft = new Aircraft(flightData);
                aircraftDAO.insertAircraft(aircraft);
                newAircraft++;
            }
        }
        LOG.info("Total new aircraft: " + newAircraft);
        LOG.info("Total updated aircraft: " + updatedAircraft);
    }

    private AircraftService() {}
}
