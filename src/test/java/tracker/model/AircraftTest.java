package tracker.model;



import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {

    @Test
    public void testTimestampDifference() {
        long fortyMinutesInMilliseconds = TimeUnit.MINUTES.toMillis(40);
        Timestamp lastSeen = new Timestamp(System.currentTimeMillis() - fortyMinutesInMilliseconds);

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        long minutesDifference = TimeUnit.MILLISECONDS.toMinutes(currentTimestamp.getTime() - lastSeen.getTime());

        assertTrue(minutesDifference > 30, "Difference should be more than 30 minutes");
    }

}