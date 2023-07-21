package tracker;

import tracker.api.AircraftRetriever;
import tracker.model.AircraftFlightData;
import tracker.service.AircraftService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            List<AircraftFlightData> flightData = AircraftRetriever.getAircraftFlightData();
            AircraftService.processAndSaveAircraft(flightData);
        };
        executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.MINUTES);
    }
}
