package mission.application.domain.model;

public class Place {
    private int placeId;
    private String placeName;
    private String placeAddress;

    public Place(int placeId, String placeName, String placeAddress) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getPlaceId() {
        return placeId;
    }


}
