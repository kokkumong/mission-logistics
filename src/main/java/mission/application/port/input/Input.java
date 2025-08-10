package mission.application.port.input;

import mission.application.domain.model.Place;

public interface Input {
    //장소에 대한 정보를 입력받아야함.
    Place departure();
    Place destination();
}
