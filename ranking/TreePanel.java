import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class TreePanel extends JPanel {

    private BinarySearchTree bst;
    private final int raio = 28;
    private final int espacoVertical = 65;
    private final int larguraPorNo = 75;
    private final int margemX = 40;
    private final int margemY = 40;
    private int contador;

    public TreePanel(BinarySearchTree bst) {
        this.bst = bst;
        setBackground(Color.WHITE);
        atualizaTamanho();
    }

    public void atualizaTamanho() {
        int totalNos = contaNos(bst.getRoot());
        int altura = bst.getAltura();
        int largura = margemX * 2 + totalNos * larguraPorNo;
        if (largura < 800) {
            largura = 800;
        }
        int alturaTotal = margemY * 2 + altura * espacoVertical;
        if (alturaTotal < 400) {
            alturaTotal = 400;
        }
        setPreferredSize(new Dimension(largura, alturaTotal));
        revalidate();
    }

    private int contaNos(Node no) {
        if (no == null) {
            return 0;
        }
        return 1 + contaNos(no.left) + contaNos(no.right);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (bst.getRoot() == null) {
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("Árvore vazia. Carregue o CSV ou insira jogadores.", 20, 30);
            return;
        }

        contador = 0;
        desenhaNo(g2, bst.getRoot(), 1);
    }

    private int desenhaNo(Graphics2D g2, Node no, int nivel) {
        if (no == null) {
            return -1;
        }

        int xEsquerda = desenhaNo(g2, no.left, nivel + 1);
        int meuX = margemX + contador * larguraPorNo;
        int meuY = margemY + (nivel - 1) * espacoVertical;
        contador++;
        int xDireita = desenhaNo(g2, no.right, nivel + 1);

        int yFilho = margemY + nivel * espacoVertical;
        g2.setColor(Color.BLACK);
        if (no.left != null) {
            g2.drawLine(meuX, meuY + raio / 2, xEsquerda, yFilho - raio / 2);
        }
        if (no.right != null) {
            g2.drawLine(meuX, meuY + raio / 2, xDireita, yFilho - raio / 2);
        }

        g2.setColor(new Color(135, 206, 250));
        g2.fillOval(meuX - raio, meuY - raio / 2, raio * 2, raio);
        g2.setColor(Color.BLACK);
        g2.drawOval(meuX - raio, meuY - raio / 2, raio * 2, raio);

        String texto = no.player.getNickname();
        FontMetrics fm = g2.getFontMetrics();
        int larguraTexto = fm.stringWidth(texto);
        g2.drawString(texto, meuX - larguraTexto / 2, meuY + 4);

        return meuX;
    }
}
