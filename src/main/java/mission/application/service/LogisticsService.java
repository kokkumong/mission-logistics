package mission.application.service;

import java.time.LocalTime;
import java.util.List;
import mission.adapter.inadapter.LogisticsInput;
import mission.adapter.loader.AddressLoader;
import mission.adapter.loader.PlaceLoader;
import mission.application.domain.model.Address;
import mission.application.domain.model.Place;

public class LogisticsService {
    private static final double EARTH_RADIUS_KM = 6371.0;
    LogisticsInput logisticsInput = new LogisticsInput();
    private List<Place> places = PlaceLoader.loadFromCsv("src/main/resources/place.csv");
    private List<Address> addresses = AddressLoader.loadFromCsv("src/main/resources/address.csv");

    public LogisticsService() {

    }

    //출발지 addressId 생성
    public Address departureAddress(){
        String input = logisticsInput.departure();
        Place DeparturePlace = places.stream().filter(p -> p.getPlaceName().equals(input))
                .findFirst().orElse(null);
        return addresses.stream().filter(a->a.getAddressId() == DeparturePlace.getPlaceId())
                .findFirst().orElse(null);
    }

    //도착지 addressId 생성
    public Address arrivalAddress(){
        String input = logisticsInput.arrival();
        Place ArrivalPlace =  places.stream().filter(p->p.getPlaceName().equals(input))
                .findFirst().orElse(null);
        return addresses.stream().filter(a->a.getAddressId() == ArrivalPlace.getPlaceId())
                .findFirst().orElse(null);
    }

    //위도, 경도 이용한 도착시간 예측
    public double calculateDistance(float lattitude1, float longitude1, float lattitude2, float longitude2) {
        double latRad1 = Math.toRadians(lattitude1);
        double lonRad1 = Math.toRadians(longitude1);
        double latRad2 = Math.toRadians(lattitude2);
        double lonRad2 = Math.toRadians(longitude2);

        double dLat = latRad2 - latRad1;
        double dLon = lonRad2 - lonRad1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(latRad1) * Math.cos(latRad2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    public LocalTime measureArrivalTime(double distance){
        double time = distance / 60;
        int hourPart = (int) time;
        int minutePart = (int) Math.round((time - hourPart) * 60);
        return LocalTime.of(hourPart % 24, minutePart);
    }
}
