package mission.application.domain.exception;

public class CsvNotFoundException extends RuntimeException {
    public CsvNotFoundException(String message) {
        super(message);
    }
}
