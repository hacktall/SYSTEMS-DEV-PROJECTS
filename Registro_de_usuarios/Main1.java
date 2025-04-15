import java.util.Scanner;

public class Main1 {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        OperationHistory history = new OperationHistory();
        UserService service = new UserService(history);

        int option;
        do {
            System.out.println("\n=== Sistema de Cadastro de Usuários ===");
            System.out.println("1. Criar usuário");
            System.out.println("2. Ler usuário");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Deletar usuário");
            System.out.println("5. Listar usuários");
            System.out.println("6. Ver histórico de operações");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    service.createUser(name, email);
                }
                case 2 -> {
                    System.out.print("ID do usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    User user = service.readUser(id);
                    if (user != null) System.out.println(user);
                    else System.out.println("Usuário não encontrado.");
                }
                case 3 -> {
                    System.out.print("ID do usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String email = scanner.nextLine();
                    service.updateUser(id, name, email);
                }
                case 4 -> {
                    System.out.print("ID do usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    service.deleteUser(id);
                }
                case 5 -> service.listUsers();
                case 6 -> history.showHistory();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);

        scanner.close();
    }
}
