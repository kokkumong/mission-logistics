package mission.application.service;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private int id = 0;

    private List<Place> places = PlaceLoader.loadFromCsv("src/main/resources/place.csv");
    private List<Address> addresses = AddressLoader.loadFromCsv("src/main/resources/position.csv");

    public LogisticsService(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void run(){
        output.output("배송 정보를 입력해 주세요. ex)봉화군청-숭실대학교 정보과학관(박호건)");
        String logisticsInformation = input.logisticsInformation();
        String departureInput = stringSplitter(logisticsInformation)[0];
        String arrivalInput = stringSplitter(logisticsInformation)[1];
        String purchaserName = stringSplitter(logisticsInformation)[2];

        output.output("배송이 정상적으로 입력되었습니다.");
        Address departure = placeToAddress(stringToPlace(departureInput));
        Address arrival = placeToAddress(stringToPlace(arrivalInput));
        delivery(departure, arrival);
    }

    private void delivery(Address departure, Address arrival) {
        id++;
        double distance = calculateDistance(departure.getAddressLat(), departure.getAddressLng(), arrival.getAddressLat(), arrival.getAddressLng());
        Duration deliveryDuration = measureDeliveryDuration(distance);
        output.startDelivery(id, deliveryDuration);
        delay(deliveryDuration);
        output.completeDelivery(id);
    }

    private void delay(Duration duration){
        long minutes = duration.toMinutes();
        long delayTime = minutes / 3;
        try{
            Thread.sleep(delayTime * 1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private String[] stringSplitter(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다. (예: 출발지-도착지(이름))");
        }
        String[] parts = new String[3];
        String regex = "(.+)-(.+)\\((.+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            parts[0] = matcher.group(1).trim();
            parts[1] = matcher.group(2).trim();
            parts[2] = matcher.group(3).trim();
        } else {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다. (예: 출발지-도착지(이름))");
        }
        return parts;
    }

    private Place stringToPlace(String input){
        return places.stream().filter(p -> p.getPlaceName().equals(input))
                .findFirst().orElseThrow(() -> new NoSuchElementException("IllegalArgumentException : \"" + input + "\"을(를) 이름으로 갖는 장소는 존재하지 않습니다."));
    }

    private Address placeToAddress(Place place){
        return addresses.stream().filter(a->a.getAddressId() == place.getPlaceId())
                .findFirst().orElseThrow(() -> new CsvNotFoundException("csv 파일에서 찾을 수 없습니다."));
    }

    private double calculateDistance(float lattitude1, float longitude1, float lattitude2, float longitude2) {
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

    private Duration measureDeliveryDuration(double distance){
        long totalMinutes = Math.round(distance);
        return Duration.ofMinutes(totalMinutes);
    }
}
