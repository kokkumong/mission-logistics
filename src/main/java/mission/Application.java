package mission;

import api.Console;
import mission.adapter.inadapter.LogisticsInput;
import mission.adapter.outadapter.ArrivalTimeOutput;
import mission.application.service.LogisticsService;

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현
        LogisticsService logisticsService = new LogisticsService(
                new LogisticsInput(),
                new ArrivalTimeOutput()
        );
        logisticsService.run();
    }
}