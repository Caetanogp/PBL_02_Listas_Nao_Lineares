public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(Player j) {
        root = insert(root, j);
    }

    private Node insert(Node current, Player j) {
        if (current == null) {
            return new Node(j);
        }
        if (j.getRanking() < current.player.getRanking()) {
            current.left = insert(current.left, j);
        } else if (j.getRanking() > current.player.getRanking()) {
            current.right = insert(current.right, j);
        }
        return current;
    }

    public boolean search(String name) {
        return search(root, name) != null;
    }

    private Node search(Node current, String name) {
        if (current == null) {
            return null;
        }
        if (current.player.getNickname().equalsIgnoreCase(name)) {
            return current;
        }
        Node achadoEsq = search(current.left, name);
        if (achadoEsq != null) {
            return achadoEsq;
        }
        return search(current.right, name);
    }

    public Player remove(String name) {
        Node alvo = search(root, name);
        if (alvo == null) {
            return null;
        }
        Player removido = alvo.player;
        root = remove(root, removido.getRanking());
        return removido;
    }

    private Node remove(Node current, int ranking) {
        if (current == null) {
            return null;
        }
        if (ranking < current.player.getRanking()) {
            current.left = remove(current.left, ranking);
        } else if (ranking > current.player.getRanking()) {
            current.right = remove(current.right, ranking);
        } else {
            return removeEncontrado(current);
        }
        return current;
    }

    private Node removeEncontrado(Node current) {
        if (current.left == null && current.right == null) {
            return null;
        }
        if (current.left == null) {
            return current.right;
        }
        if (current.right == null) {
            return current.left;
        }
        Node sucessor = menor(current.right);
        current.player = sucessor.player;
        current.right = remove(current.right, sucessor.player.getRanking());
        return current;
    }

    private Node menor(Node atual) {
        if (atual.left == null) {
            return atual;
        }
        return menor(atual.left);
    }

    public int getAltura() {
        return getAltura(root);
    }

    private int getAltura(Node atual) {
        if (atual == null) {
            return 0;
        }
        return 1 + Math.max(getAltura(atual.left), getAltura(atual.right));
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node atual) {
        if (atual == null) {
            return;
        }
        inOrder(atual.left);
        System.out.print(atual.player.getNickname() + "(" + atual.player.getRanking() + ") ");
        inOrder(atual.right);
    }
}
