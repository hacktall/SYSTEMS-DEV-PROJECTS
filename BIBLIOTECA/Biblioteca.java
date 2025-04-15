import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void emprestarLivro(String titulo, Usuario usuario) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && !livro.isEmprestado()) {
                livro.setEmprestado(true);
                Emprestimo emp = new Emprestimo(livro, usuario);
                emprestimos.add(emp);
                System.out.println("Livro emprestado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não disponível.");
    }

    public void devolverLivro(String titulo) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getLivro().getTitulo().equalsIgnoreCase(titulo) && emp.getDataDevolucao() == null) {
                emp.devolver();
                System.out.println("Livro devolvido.");
                long multa = emp.calcularMulta();
                System.out.println("Multa: R$ " + multa);
                return;
            }
        }
        System.out.println("Empréstimo não encontrado.");
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
