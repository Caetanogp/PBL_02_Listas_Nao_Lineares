import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JanelaPrincipal extends JFrame {

    private BinarySearchTree bst;
    private TreePanel treePanel;

    public JanelaPrincipal(BinarySearchTree bst) {
        this.bst = bst;
        this.treePanel = new TreePanel(bst);

        setTitle("Ranking de Jogadores - Árvore Binária de Busca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JScrollPane scroll = new JScrollPane(treePanel);
        add(scroll, BorderLayout.CENTER);
        add(criaPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criaPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());

        JButton btInserir = new JButton("Inserir");
        JButton btBuscar = new JButton("Buscar");
        JButton btRemover = new JButton("Remover");
        JButton btInOrder = new JButton("Imprimir InOrder");

        btInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inserirJogador();
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarJogador();
            }
        });

        btRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerJogador();
            }
        });

        btInOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bst.inOrder();
                JOptionPane.showMessageDialog(JanelaPrincipal.this,
                        "Percurso in-order impresso no console.");
            }
        });

        painel.add(btInserir);
        painel.add(btBuscar);
        painel.add(btRemover);
        painel.add(btInOrder);
        return painel;
    }

    private void inserirJogador() {
        String nick = JOptionPane.showInputDialog(this, "Nickname:");
        if (nick == null || nick.trim().isEmpty()) {
            return;
        }
        String rankStr = JOptionPane.showInputDialog(this, "Ranking (número inteiro):");
        if (rankStr == null || rankStr.trim().isEmpty()) {
            return;
        }
        try {
            int rank = Integer.parseInt(rankStr.trim());
            bst.insert(new Player(nick.trim(), rank));
            treePanel.atualizaTamanho();
            treePanel.repaint();
            JOptionPane.showMessageDialog(this, "Jogador inserido.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ranking inválido.");
        }
    }

    private void buscarJogador() {
        String nick = JOptionPane.showInputDialog(this, "Nickname a buscar:");
        if (nick == null || nick.trim().isEmpty()) {
            return;
        }
        boolean achou = bst.search(nick.trim());
        if (achou) {
            JOptionPane.showMessageDialog(this, "Jogador encontrado: " + nick);
        } else {
            JOptionPane.showMessageDialog(this, "Jogador não encontrado.");
        }
    }

    private void removerJogador() {
        String nick = JOptionPane.showInputDialog(this, "Nickname a remover:");
        if (nick == null || nick.trim().isEmpty()) {
            return;
        }
        Player removido = bst.remove(nick.trim());
        if (removido != null) {
            treePanel.atualizaTamanho();
            treePanel.repaint();
            JOptionPane.showMessageDialog(this,
                    "Removido: " + removido.getNickname() + " (ranking " + removido.getRanking() + ")");
        } else {
            JOptionPane.showMessageDialog(this, "Jogador não encontrado.");
        }
    }
}
