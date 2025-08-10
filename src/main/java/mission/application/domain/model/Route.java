package mission.application.domain.model;

import java.time.LocalTime;

public class Route {
    private int place1Id;
    private int place2Id;
    private LocalTime transitTime;

    public Route(int place1Id, int place2Id, LocalTime transitTime) {
        this.place1Id = place1Id;
        this.place2Id = place2Id;
        this.transitTime = transitTime;
    }
}
