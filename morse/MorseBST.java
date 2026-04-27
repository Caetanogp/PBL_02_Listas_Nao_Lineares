public class MorseBST {

    private Node raiz;

    public MorseBST() {
        this.raiz = new Node();
    }

    public Node getRaiz() {
        return raiz;
    }

    public void insert(char letra, String codigo) {
        insert(raiz, letra, codigo, 0);
    }

    private void insert(Node atual, char letra, String codigo, int i) {
        if (i == codigo.length()) {
            atual.letra = letra;
            return;
        }
        char c = codigo.charAt(i);
        if (c == '.') {
            if (atual.left == null) {
                atual.left = new Node();
            }
            insert(atual.left, letra, codigo, i + 1);
        } else if (c == '-') {
            if (atual.right == null) {
                atual.right = new Node();
            }
            insert(atual.right, letra, codigo, i + 1);
        }
    }

    public String codifica(String palavra) {
        String resultado = "";
        for (int i = 0; i < palavra.length(); i++) {
            char c = Character.toUpperCase(palavra.charAt(i));
            if (c == ' ') {
                resultado = resultado + "/ ";
                continue;
            }
            String codigo = buscaLetra(raiz, c, "");
            if (codigo != null) {
                resultado = resultado + codigo + " ";
            }
        }
        return resultado.trim();
    }

    private String buscaLetra(Node atual, char alvo, String caminho) {
        if (atual == null) {
            return null;
        }
        if (atual.letra == alvo) {
            return caminho;
        }
        String esq = buscaLetra(atual.left, alvo, caminho + ".");
        if (esq != null) {
            return esq;
        }
        return buscaLetra(atual.right, alvo, caminho + "-");
    }

    public String decodifica(String sequencia) {
        String resultado = "";
        String[] palavras = sequencia.split("/");
        for (int p = 0; p < palavras.length; p++) {
            String[] letras = palavras[p].trim().split(" ");
            for (int i = 0; i < letras.length; i++) {
                if (letras[i].isEmpty()) {
                    continue;
                }
                char letra = percorre(raiz, letras[i], 0);
                if (letra != ' ') {
                    resultado = resultado + letra;
                }
            }
            if (p < palavras.length - 1) {
                resultado = resultado + " ";
            }
        }
        return resultado;
    }

    private char percorre(Node atual, String codigo, int i) {
        if (atual == null) {
            return ' ';
        }
        if (i == codigo.length()) {
            return atual.letra;
        }
        char c = codigo.charAt(i);
        if (c == '.') {
            return percorre(atual.left, codigo, i + 1);
        } else if (c == '-') {
            return percorre(atual.right, codigo, i + 1);
        }
        return ' ';
    }

    public int getAltura() {
        return getAltura(raiz);
    }

    private int getAltura(Node atual) {
        if (atual == null) {
            return 0;
        }
        return 1 + Math.max(getAltura(atual.left), getAltura(atual.right));
    }
}
