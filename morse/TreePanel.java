import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class TreePanel extends JPanel {

    private MorseBST bst;
    private final int raio = 18;
    private final int espacoVertical = 70;

    public TreePanel(MorseBST bst) {
        this.bst = bst;
        setBackground(Color.WHITE);
        atualizaTamanho();
    }

    public void atualizaTamanho() {
        int altura = bst.getAltura();
        int largura = (int) Math.pow(2, altura) * 40;
        if (largura < 900) {
            largura = 900;
        }
        int alturaTotal = 60 + altura * espacoVertical;
        setPreferredSize(new Dimension(largura, alturaTotal));
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (bst.getRaiz() == null) {
            return;
        }
        int largura = getWidth();
        desenhaNo(g2, bst.getRaiz(), largura / 2, 40, largura / 4, true);
    }

    private void desenhaNo(Graphics2D g2, Node no, int x, int y, int offset, boolean ehRaiz) {
        if (no == null) {
            return;
        }

        if (no.left != null) {
            int novoX = x - offset;
            int novoY = y + espacoVertical;
            g2.setColor(Color.BLACK);
            g2.drawLine(x, y + raio, novoX, novoY - raio);
            int meioX = (x + novoX) / 2;
            int meioY = (y + novoY) / 2;
            g2.setColor(Color.BLUE);
            g2.drawString(".", meioX - 3, meioY);
            desenhaNo(g2, no.left, novoX, novoY, offset / 2, false);
        }

        if (no.right != null) {
            int novoX = x + offset;
            int novoY = y + espacoVertical;
            g2.setColor(Color.BLACK);
            g2.drawLine(x, y + raio, novoX, novoY - raio);
            int meioX = (x + novoX) / 2;
            int meioY = (y + novoY) / 2;
            g2.setColor(Color.RED);
            g2.drawString("-", meioX - 3, meioY);
            desenhaNo(g2, no.right, novoX, novoY, offset / 2, false);
        }

        g2.setColor(new Color(255, 215, 120));
        g2.fillOval(x - raio, y - raio, raio * 2, raio * 2);
        g2.setColor(Color.BLACK);
        g2.drawOval(x - raio, y - raio, raio * 2, raio * 2);

        String texto;
        if (ehRaiz) {
            texto = "*";
        } else {
            texto = String.valueOf(no.letra);
        }

        FontMetrics fm = g2.getFontMetrics();
        int larguraTexto = fm.stringWidth(texto);
        g2.drawString(texto, x - larguraTexto / 2, y + 5);
    }
}
