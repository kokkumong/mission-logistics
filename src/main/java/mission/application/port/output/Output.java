package mission.application.port.output;

import java.time.Duration;

public interface Output {
    void output(String message);
    void startDelivery(int id, Duration deliveryDuration);
    void completeDelivery(int id);
}
