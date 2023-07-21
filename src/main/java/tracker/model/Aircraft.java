package tracker.model;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Aircraft {

    private final String icao;
    private final String reg;
    private final String cou;
    private final String type;
    private final float lat;
    private final float lon;
    private final Timestamp firstSeen;
    private final Timestamp lastSeen;
    private int totalSeen;

    //For creating objects from queried aircraft
    public Aircraft(String icao, String reg, String cou, String type, float lat, float lon, Timestamp firstSeen, Timestamp lastSeen, int totalSeen) {
        this.icao = icao;
        this.reg = reg;
        this.cou = cou;
        this.type = type;
        this.lat = lat;
        this.lon = lon;
        this.firstSeen = firstSeen;
        this.lastSeen = getTimestamp();
        if(seenMoreThanThirtyMinutesAgo(lastSeen)) {
            this.totalSeen++;
        } else {
            this.totalSeen = totalSeen;
        }
    }

    //For creating new aircraft to insert into DB
    public Aircraft(AircraftFlightData flightData) {
        this.icao = flightData.icao();
        this.reg = flightData.reg();
        this.cou = flightData.cou();
        this.type = flightData.type();
        this.lat = flightData.lat();
        this.lon = flightData.lon();
        this.firstSeen = getTimestamp();
        this.lastSeen = getTimestamp();
        this.totalSeen = 1;
    }

    public String getIcao() {
        return icao;
    }

    public String getReg() {
        return reg;
    }

    public String getCou() {
        return cou;
    }

    public String getType() {
        return type;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public Timestamp getFirstSeen() {
        return firstSeen;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public int getTotalSeen() {
        return totalSeen;
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    private boolean seenMoreThanThirtyMinutesAgo(Timestamp lastSeen) {
        long minutesDifference = TimeUnit.MILLISECONDS.toMinutes(getTimestamp().getTime() - lastSeen.getTime());
        return minutesDifference > 30;
    }

}
