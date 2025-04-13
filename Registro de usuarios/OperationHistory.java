import java.util.ArrayList;
import java.util.List;

public class OperationHistory {
    private List<OperationLog> history = new ArrayList<>();

    public void addLog(String operation, String details) {
        history.add(new OperationLog(operation, details));
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("Nenhuma operação registrada.");
        } else {
            for (OperationLog log : history) {
                System.out.println(log);
            }
        }
    }
}
