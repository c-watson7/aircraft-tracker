package tracker.model;

public record AircraftFlightData(int alt, String cou, int gnd, String icao,
                                 float lat, float lon, String reg,
                                 int spd, int talt, short trak, String type,
                                 int vsi) {
}
