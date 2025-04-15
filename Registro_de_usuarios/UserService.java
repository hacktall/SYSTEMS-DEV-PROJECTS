
import java.util.*;

public class UserService {
    private Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;
    private OperationHistory history;

    public UserService(OperationHistory history) {
        this.history = history;
    }

    public void createUser(String name, String email) {
        User user = new User(nextId++, name, email);
        users.put(user.getId(), user);
        history.addLog("CREATE", user.toString());
    }

    public User readUser(int id) {
        User user = users.get(id);
        history.addLog("READ", "ID: " + id + " -> " + (user != null ? user.toString() : "Usuário não encontrado"));
        return user;
    }

    public void updateUser(int id, String name, String email) {
        User user = users.get(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            history.addLog("UPDATE", user.toString());
        } else {
            history.addLog("UPDATE", "Tentativa de atualizar ID inexistente: " + id);
        }
    }

    public void deleteUser(int id) {
        User removed = users.remove(id);
        if (removed != null) {
            history.addLog("DELETE", removed.toString());
        } else {
            history.addLog("DELETE", "Tentativa de deletar ID inexistente: " + id);
        }
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (User user : users.values()) {
                System.out.println(user);
            }
        }
        history.addLog("LIST", "Listagem de usuários.");
    }
}





