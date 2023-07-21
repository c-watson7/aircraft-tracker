package tracker.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tracker.model.Aircraft;

import java.util.Arrays;
import java.util.List;

public class JsonParser {

    private static final ObjectMapper mapper = new ObjectMapper();
    private JsonParser() {}

    public static List<Aircraft> parseAircraftJson(String json) {
        try {
            List<Aircraft> aircrafts = Arrays.asList(mapper.readValue(json, Aircraft[].class));
            return aircrafts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
