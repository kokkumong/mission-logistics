package mission.adapter.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import mission.application.domain.model.Place;

public class PlaceLoader {
    public static ArrayList<Place> loadFromCsv(String filePath){
        ArrayList<Place> places = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int placeId = Integer.parseInt(parts[0].trim());
                String placeName = parts[1];
                String placeAddress = parts[2];

                places.add(new Place(placeId, placeName, placeAddress));
            }
        }
        catch(FileNotFoundException e){
            System.err.println("place.csv : 파일을 찾을 수 없음!");
        }
        catch(IOException e){
            System.err.println("place.csv : 입출력 오류!");
        }
        return places;
    }
}
