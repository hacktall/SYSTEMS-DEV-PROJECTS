
import java.time.LocalDateTime;

public class OperationLog {
    private LocalDateTime timestamp;
    private String operation;
    private String details;

    public OperationLog(String operation, String details) {
        this.timestamp = LocalDateTime.now();
        this.operation = operation;
        this.details = details;
    }

    @Override
    public String toString() {
        return timestamp + " | " + operation.toUpperCase() + " | " + details;
    }
}

