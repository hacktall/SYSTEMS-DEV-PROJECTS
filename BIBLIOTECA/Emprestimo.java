import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
    }

    public void devolver() {
        this.dataDevolucao = LocalDate.now();
        livro.setEmprestado(false);
    }

    public long calcularMulta() {
        LocalDate hoje = dataDevolucao != null ? dataDevolucao : LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataEmprestimo, hoje);
        return dias > 7 ? (dias - 7) * 2 : 0; // 2 reais por dia de atraso após 7 dias
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
}
