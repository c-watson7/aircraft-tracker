package tracker.model;

public record AircraftFlightData(int alt, String cou, int gnd, String hex,
                                 float lat, float lon, String reg,
                                 float spd, int talt, float trak, String t,
                                 int vsi) {
}
