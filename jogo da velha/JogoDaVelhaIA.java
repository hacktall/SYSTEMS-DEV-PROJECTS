import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

public class JogoDaVelhaIA extends JFrame implements ActionListener {

    private JButton[][] botoes = new JButton[3][3];
    private boolean jogadorX = true; // true = jogador humano, false = IA
    private JLabel statusLabel = new JLabel("Sua vez (X)");

    public JogoDaVelhaIA() {
        setTitle("Jogo da Velha com IA");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelJogo = new JPanel(new GridLayout(3, 3));
        Font fonteBotao = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(fonteBotao);
                botoes[i][j].setFocusPainted(false);
                botoes[i][j].addActionListener(this);
                painelJogo.add(botoes[i][j]);
            }

        JButton resetBtn = new JButton("Reiniciar");
        resetBtn.addActionListener(e -> reiniciarJogo());

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(statusLabel, BorderLayout.CENTER);
        painelInferior.add(resetBtn, BorderLayout.EAST);

        add(painelJogo, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jogadorX) return; // só aceita jogada se for o jogador humano

        JButton botaoClicado = (JButton) e.getSource();
        if (!botaoClicado.getText().equals("")) return;

        botaoClicado.setText("X");
        botaoClicado.setForeground(Color.BLUE);

        if (verificarVencedor("X")) {
            statusLabel.setText("Você venceu!");
            desabilitarBotoes();
        } else if (empate()) {
            statusLabel.setText("Empate!");
        } else {
            jogadorX = false;
            statusLabel.setText("Vez da IA...");
            Timer timer = new Timer(500, evt -> {
                jogadaIA();
                ((Timer) evt.getSource()).stop();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void jogadaIA() {
        // 1. Tenta vencer
        if (tentarJogar("O")) return;

        // 2. Tenta bloquear o jogador
        if (tentarJogar("X")) return;

        // 3. Jogada aleatória
        List<Point> vazios = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (botoes[i][j].getText().equals("")) vazios.add(new Point(i, j));

        if (!vazios.isEmpty()) {
            Point p = vazios.get(new Random().nextInt(vazios.size()));
            botoes[p.x][p.y].setText("O");
            botoes[p.x][p.y].setForeground(Color.RED);
        }

        if (verificarVencedor("O")) {
            statusLabel.setText("IA venceu!");
            desabilitarBotoes();
        } else if (empate()) {
            statusLabel.setText("Empate!");
        } else {
            jogadorX = true;
            statusLabel.setText("Sua vez (X)");
        }
    }

    // Tenta completar uma linha com dois símbolos iguais e um espaço vazio
    private boolean tentarJogar(String simbolo) {
        for (int i = 0; i < 3; i++) {
            // Linha
            if (verificaLinhaOuColuna(botoes[i][0], botoes[i][1], botoes[i][2], simbolo)) return true;
            // Coluna
            if (verificaLinhaOuColuna(botoes[0][i], botoes[1][i], botoes[2][i], simbolo)) return true;
        }
        // Diagonal
        if (verificaLinhaOuColuna(botoes[0][0], botoes[1][1], botoes[2][2], simbolo)) return true;
        if (verificaLinhaOuColuna(botoes[0][2], botoes[1][1], botoes[2][0], simbolo)) return true;

        return false;
    }

    private boolean verificaLinhaOuColuna(JButton b1, JButton b2, JButton b3, String simbolo) {
        if (b1.getText().equals(simbolo) && b2.getText().equals(simbolo) && b3.getText().equals("")) {
            b3.setText("O");
            b3.setForeground(Color.RED);
            return true;
        }
        if (b1.getText().equals(simbolo) && b3.getText().equals(simbolo) && b2.getText().equals("")) {
            b2.setText("O");
            b2.setForeground(Color.RED);
            return true;
        }
        if (b2.getText().equals(simbolo) && b3.getText().equals(simbolo) && b1.getText().equals("")) {
            b1.setText("O");
            b1.setForeground(Color.RED);
            return true;
        }
        return false;
    }

    private boolean verificarVencedor(String simbolo) {
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(simbolo) &&
                botoes[i][1].getText().equals(simbolo) &&
                botoes[i][2].getText().equals(simbolo)) return true;

            if (botoes[0][i].getText().equals(simbolo) &&
                botoes[1][i].getText().equals(simbolo) &&
                botoes[2][i].getText().equals(simbolo)) return true;
        }

        if (botoes[0][0].getText().equals(simbolo) &&
            botoes[1][1].getText().equals(simbolo) &&
            botoes[2][2].getText().equals(simbolo)) return true;

        if (botoes[0][2].getText().equals(simbolo) &&
            botoes[1][1].getText().equals(simbolo) &&
            botoes[2][0].getText().equals(simbolo)) return true;

        return false;
    }

    private boolean empate() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (botoes[i][j].getText().equals("")) return false;
        return true;
    }

    private void desabilitarBotoes() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                botoes[i][j].setEnabled(false);
    }

    private void reiniciarJogo() {
        jogadorX = true;
        statusLabel.setText("Sua vez (X)");

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
            }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoDaVelhaIA());
    }
}
