package tracker.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tracker.model.AircraftFlightData;

import java.util.Arrays;
import java.util.List;

public class JsonParser {

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private JsonParser() {}

    public static List<AircraftFlightData> parseFlightDataJson(String json) {
        try {
            JsonNode root = mapper.readTree(json);
            JsonNode acNode = root.path("ac");
            AircraftFlightData[] data = mapper.treeToValue(acNode, AircraftFlightData[].class);
            return Arrays.asList(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
