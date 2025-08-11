package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    @Test
    void testApplication() {
        run(List.of("봉화군청", "숭실대학교 정보과학관"));
        assertTrue(output().contains("이동 시간은 2시간 51분으로 예측됩니다."));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
