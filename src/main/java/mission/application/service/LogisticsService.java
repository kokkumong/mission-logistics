package mission.application.service;

import java.time.LocalTime;
import java.util.List;
import mission.adapter.loader.AddressLoader;
import mission.adapter.loader.PlaceLoader;
import mission.application.domain.exception.CsvNotFoundException;
import mission.application.domain.model.Address;
import mission.application.domain.model.Place;
import mission.application.port.input.Input;
import mission.application.port.output.Output;

public class LogisticsService {
    private final Input input;
    private final Output output;
    private static final double EARTH_RADIUS_KM = 6371.0;

    private List<Place> places = PlaceLoader.loadFromCsv("src/main/resources/place.csv");
    private List<Address> addresses = AddressLoader.loadFromCsv("src/main/resources/position.csv");

    public LogisticsService(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void run(){
        output.output("출발지를 입력하세요.");
        String departureInput = input.region();
        output.output("도착지를 입력하세요.");
        String arrivalInput = input.region();

        Address departure = departureAddress(departureInput);
        Address arrival = arrivalAddress(arrivalInput);

        double distance = calculateDistance(departure.getAddressLat(), departure.getAddressLng(), arrival.getAddressLat(), arrival.getAddressLng());
        LocalTime arrivalTime = measureArrivalTime(distance);
        output.getArrivalTime(arrivalTime);
    }

    public Address departureAddress(String input){
        try{
            Place DeparturePlace = places.stream().filter(p -> p.getPlaceName().equals(input))
                    .findFirst().orElse(null);
            return addresses.stream().filter(a->a.getAddressId() == DeparturePlace.getPlaceId())
                    .findFirst().orElse(null);
        } catch(Exception e){
            throw new CsvNotFoundException("csv 파일에서 정보를 찾을 수 없습니다.");
        }
    }

    public Address arrivalAddress(String input){
        Place ArrivalPlace =  places.stream().filter(p->p.getPlaceName().equals(input))
                .findFirst().orElse(null);
        return addresses.stream().filter(a->a.getAddressId() == ArrivalPlace.getPlaceId())
                .findFirst().orElse(null);
    }

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
