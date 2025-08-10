package mission.adapter.inadapter;

import api.Console;
import mission.application.domain.model.Place;
import mission.application.port.input.Input;

public class LogisticsInput implements Input {
    public Place departure(){
        String response = Console.readLine();
        return null;
    }
    public Place destination(){
        String response = Console.readLine();
        return null;
    }
}
