package mission.adapter.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import mission.application.domain.model.Route;

public class RouteLoader {
    public static ArrayList<Route> loadFromCsv(String filePath){
        ArrayList<Route> routes = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int place1Id = Integer.parseInt(parts[0]);
                int place2Id = Integer.parseInt(parts[1]);
                LocalTime transmitTime = LocalTime.parse(parts[2]);

                routes.add(new Route(place1Id, place2Id, transmitTime));
            }
        }
        catch(FileNotFoundException e){
            System.err.println("route.csv : 파일을 찾을 수 없음!");
        }
        catch(IOException e){
            System.err.println("route.csv : 입출력 오류!");
        }
        return routes;
    }
}
