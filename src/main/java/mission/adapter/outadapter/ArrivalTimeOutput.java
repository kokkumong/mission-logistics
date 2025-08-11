package mission.adapter.outadapter;

import java.time.LocalTime;
import mission.application.port.output.Output;

public class ArrivalTimeOutput implements Output {

    @Override
    public void getArrivalTime(LocalTime arrivalTime){
        System.out.println("이동 시간은 " + arrivalTime + "으로 예측됩니다.");
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
