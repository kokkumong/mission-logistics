package mission.adapter.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import mission.application.domain.model.Address;

public class AddressLoader{
    public static ArrayList<Address> loadFromCsv(String filePath){
        ArrayList<Address> addresses = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            br.readLine();

            while((line = br.readLine()) != null) {
                String [] parts = line.split(",");
                int addressId = Integer.parseInt(parts[0].trim());
                float addressLat = Float.parseFloat(parts[1].trim());
                float addressLng = Float.parseFloat(parts[2].trim());

                addresses.add(new Address(addressId, addressLat, addressLng));
            }
        }
        catch(FileNotFoundException e){
            System.err.println("position.csv : 파일을 찾을 수 없습니다.");
        }
        catch(IOException e) {
            System.err.println("position.csv : 입출력 오류!");
        }
        return addresses;
    }
}
