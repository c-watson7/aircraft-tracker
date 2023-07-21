package tracker.service;

import tracker.model.Aircraft;
import tracker.model.AircraftFlightData;
import tracker.repository.AircraftDAO;

import java.util.List;

public class AircraftService {

    private static final AircraftDAO aircraftDAO = new AircraftDAO();

    public static void processAndSaveAircraft(List<AircraftFlightData> flightDataList) {
        for (AircraftFlightData flightData : flightDataList) {
            Aircraft aircraft = aircraftDAO.getAircraft(flightData.icao());
            //Query DB to see if Aircraft exists
            //If it does update entry
            if(aircraft != null) {
                aircraftDAO.updateAircraft(aircraft);
            } else {
                //If not, create new entry and insert
                aircraft = new Aircraft(flightData);
                aircraftDAO.insertAircraft(aircraft);
            }
        }
    }
    private AircraftService() {}
}
