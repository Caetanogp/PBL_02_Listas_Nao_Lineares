import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JanelaMorse extends JFrame {

    private MorseBST bst;
    private TreePanel treePanel;
    private JTextField campoPalavra;
    private JTextField campoMorse;
    private JLabel resultadoCodifica;
    private JLabel resultadoDecodifica;

    public JanelaMorse(MorseBST bst) {
        this.bst = bst;
        this.treePanel = new TreePanel(bst);

        setTitle("Árvore Binária de Código Morse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JScrollPane scroll = new JScrollPane(treePanel);
        add(scroll, BorderLayout.CENTER);
        add(criaPainelInferior(), BorderLayout.SOUTH);
    }

    private JPanel criaPainelInferior() {
        JPanel painel = new JPanel(new GridLayout(2, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel linha1 = new JPanel();
        linha1.add(new JLabel("Palavra:"));
        campoPalavra = new JTextField(20);
        linha1.add(campoPalavra);
        JButton btCodifica = new JButton("Codificar");
        linha1.add(btCodifica);
        resultadoCodifica = new JLabel("-");
        linha1.add(resultadoCodifica);

        JPanel linha2 = new JPanel();
        linha2.add(new JLabel("Morse (letras separadas por espaço, palavras por /):"));
        campoMorse = new JTextField(20);
        linha2.add(campoMorse);
        JButton btDecodifica = new JButton("Decodificar");
        linha2.add(btDecodifica);
        resultadoDecodifica = new JLabel("-");
        linha2.add(resultadoDecodifica);

        btCodifica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String palavra = campoPalavra.getText();
                String morse = bst.codifica(palavra);
                resultadoCodifica.setText(morse);
                System.out.println("Codificado: " + palavra + " -> " + morse);
            }
        });

        btDecodifica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String morse = campoMorse.getText();
                String palavra = bst.decodifica(morse);
                resultadoDecodifica.setText(palavra);
                System.out.println("Decodificado: " + morse + " -> " + palavra);
            }
        });

        painel.add(linha1);
        painel.add(linha2);
        return painel;
    }
}
