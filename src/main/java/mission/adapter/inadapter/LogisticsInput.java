package mission.adapter.inadapter;

import api.Console;
import mission.application.domain.model.Place;
import mission.application.port.input.Input;

public class LogisticsInput implements Input {
    public String departure(){
        return Console.readLine();
    }
    public String arrival(){
        return Console.readLine();
    }
}
