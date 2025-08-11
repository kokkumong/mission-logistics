package mission.adapter.inadapter;

import api.Console;
import mission.application.domain.model.Place;
import mission.application.port.input.Input;

public class LogisticsInput implements Input {
    public String departure(){
        System.out.println("출발지를 입력해주세요.");
        System.out.print(">");
        return Console.readLine();
    }
    public String arrival(){
        System.out.println("도착지를 입력해주세요.");
        System.out.print(">");
        return Console.readLine();
    }
}
