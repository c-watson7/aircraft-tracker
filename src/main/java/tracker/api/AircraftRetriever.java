package tracker.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.model.AircraftFlightData;
import tracker.util.config.ConfigLoader;
import tracker.util.json.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AircraftRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(AircraftRetriever.class);

    private static final ConfigLoader config = new ConfigLoader();
    private static final String apiKey = config.getProperty("API_KEY");
    private static final String apiHost = config.getProperty("API_HOST");
    private static final String apiUri = config.getProperty("API_URI");

    public static List<AircraftFlightData> getAircraftFlightData() {
        return JsonParser.parseFlightDataJson(getRawJsonFromApi());
    }

    private static String getRawJsonFromApi() {
        if (apiKey == null || apiKey.isEmpty()) {
            String response = sampleResponse();
            LOG.info("Read JSON from sample");
            return response;
        } else {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUri))
                    .header("X-RapidAPI-Key", apiKey)
                    .header("X-RapidAPI-Host", apiHost)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            try {
                LOG.info("Attempting to retrieve flight data from ADS-B API.");
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                switch (response.statusCode()) {
                    case 200 -> {
                        LOG.info("Successful ADS-B API response {}", response.body());
                        return response.body();
                    }
                    case 404 -> {
                        LOG.error("Unsuccessful ADS-B API response");
                        return null;
                    }
                    default -> {
                        LOG.error("Unsuccessful ADS-B API response: {}", response.statusCode());
                        throw new RuntimeException("ADSB Exchange API call failed with status code " + response.statusCode());
                    }
                }
            } catch (IOException | InterruptedException e) {
                LOG.error("Could not call ADSB Exchange API", e);
                throw new RuntimeException("Could not call ADSB Exchange API", e);
            }
        }
    }

    private AircraftRetriever() {
    }

    private static String sampleResponse() {
        JsonSampleLoader loader = new JsonSampleLoader();
        return loader.loadSampleJson();
    }
}
