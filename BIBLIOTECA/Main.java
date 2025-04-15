import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        biblioteca.adicionarLivro(new Livro("Dom Casmurro", "Machado de Assis"));
        biblioteca.adicionarLivro(new Livro("O Alquimista", "Paulo Coelho"));

        while (true) {
            System.out.println("\n1. Emprestar Livro\n2. Devolver Livro\n 3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    biblioteca.emprestarLivro(titulo, new Usuario(nome));
                    break;

                case 2:
                    System.out.print("Título do livro: ");
                    titulo = scanner.nextLine();
                    biblioteca.devolverLivro(titulo);
                    break;


                case 3:
                    System.out.println("Saindo...");
                    return;
            }
        }
    }
}
