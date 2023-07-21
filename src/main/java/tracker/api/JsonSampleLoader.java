package tracker.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonSampleLoader {

    public String loadSampleJson() {
        try (InputStream stream = JsonSampleLoader.class.getResourceAsStream("/sample.json")) {
            if (stream == null) {
                throw new RuntimeException("Could not load sample.json");
            }
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sample.json", e);
        }
    }
}
