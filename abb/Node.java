package abb;

public class Node {
    int valor;
    Node esq;
    Node dir;
    int nosEsq;
    int nosDir;
    int altura;

    public Node(int valor) {
        this.valor = valor;
        this.esq = null;
        this.dir = null;
        this.nosEsq = 0;
        this.nosDir = 0;
        altura = 1;
    }
}
