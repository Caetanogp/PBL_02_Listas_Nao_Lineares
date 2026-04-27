import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        final MorseBST bst = new MorseBST();
        preencheAlfabeto(bst);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaMorse(bst).setVisible(true);
            }
        });
    }

    private static void preencheAlfabeto(MorseBST bst) {
        bst.insert('E', ".");
        bst.insert('T', "-");
        bst.insert('I', "..");
        bst.insert('A', ".-");
        bst.insert('N', "-.");
        bst.insert('M', "--");
        bst.insert('S', "...");
        bst.insert('U', "..-");
        bst.insert('R', ".-.");
        bst.insert('W', ".--");
        bst.insert('D', "-..");
        bst.insert('K', "-.-");
        bst.insert('G', "--.");
        bst.insert('O', "---");
        bst.insert('H', "....");
        bst.insert('V', "...-");
        bst.insert('F', "..-.");
        bst.insert('L', ".-..");
        bst.insert('P', ".--.");
        bst.insert('J', ".---");
        bst.insert('B', "-...");
        bst.insert('X', "-..-");
        bst.insert('C', "-.-.");
        bst.insert('Y', "-.--");
        bst.insert('Z', "--..");
        bst.insert('Q', "--.-");
        bst.insert('5', ".....");
        bst.insert('4', "....-");
        bst.insert('3', "...--");
        bst.insert('2', "..---");
        bst.insert('1', ".----");
        bst.insert('6', "-....");
        bst.insert('7', "--...");
        bst.insert('8', "---..");
        bst.insert('9', "----.");
        bst.insert('0', "-----");
    }
}
