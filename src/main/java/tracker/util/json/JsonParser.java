package tracker.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.model.AircraftFlightData;

import java.util.Arrays;
import java.util.List;

public class JsonParser {
    private static final Logger LOG = LoggerFactory.getLogger(JsonParser.class);

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static List<AircraftFlightData> parseFlightDataJson(String json) {
        try {
            LOG.info("Parsing flight data from JSON");
            JsonNode root = mapper.readTree(json);
            JsonNode acNode = root.path("ac");
            AircraftFlightData[] data = mapper.treeToValue(acNode, AircraftFlightData[].class);
            LOG.info("Parsing successful.");
            return Arrays.asList(data);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to parse JSON", e);
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    private JsonParser() {}
}
