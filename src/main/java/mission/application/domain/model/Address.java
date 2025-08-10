package mission.application.domain.model;

public class Address {
    private int addressId;
    private float addressLat;
    private float addressLng;

    public Address(int addressId, float addressLat, float addressLng) {
        this.addressId = addressId;
        this.addressLat = addressLat;
        this.addressLng = addressLng;
    }
}
