import services.UsuarioService;
import ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        LoginFrame loginFrame = new LoginFrame(usuarioService);
        loginFrame.setVisible(true);
    }
}
