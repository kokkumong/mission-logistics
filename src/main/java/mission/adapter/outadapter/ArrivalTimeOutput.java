package mission.adapter.outadapter;

import java.time.Duration;
import mission.application.port.output.Output;

public class ArrivalTimeOutput implements Output {
    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public void startDelivery(int id, Duration deliveryDuration){
        int totalMinutes = (int) deliveryDuration.toMinutes();
        int hour = totalMinutes / 60;
        int minute = totalMinutes % 60;
        System.out.println("배송이 시작되었습니다. (id: " + id + ", 예상 배송 시간 : " + hour + "시간 " + minute + "분)");
    }

    @Override
    public void completeDelivery(int id){
        System.out.println("배송이 완료되었습나다.(id: " + id + ")");
    }
}
