package mission.adapter.inadapter;

import api.Console;
import mission.application.domain.exception.InvalidArgumentException;
import mission.application.port.input.Input;

public class LogisticsInput implements Input {
    public String region(){
        try{
            return Console.readLine();
        } catch (Exception e){
            throw new InvalidArgumentException("InvalidArgumentException : " + "\"Console.readLine()\"" + "을(를) 이름으로 갖는 장소는 존재하지 않습니다.");
        }
    }
}
