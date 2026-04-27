public class Node {

    char letra;
    Node left;
    Node right;

    public Node() {
        this.letra = ' ';
        this.left = null;
        this.right = null;
    }

    public Node(char letra) {
        this.letra = letra;
        this.left = null;
        this.right = null;
    }
}
