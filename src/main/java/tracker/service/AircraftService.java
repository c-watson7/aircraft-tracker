package tracker.service;

import tracker.model.Aircraft;
import tracker.model.AircraftFlightData;
import tracker.repository.AircraftDAO;

public class AircraftService {

    private final AircraftDAO aircraftDAO = new AircraftDAO();

    public void processAndSaveAircraft(AircraftFlightData flightData) {
        // Perform computations and transformations on the aircraft object
        // ...

        // Then save it to the database
       // aircraftDAO.insertAircraft(aircraft);
    }

}
