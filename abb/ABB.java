package abb;

public class ABB {
    private Node raiz;

    public ABB() {
        raiz = null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public Node insert(int valor) {
        if(raiz == null) {
            raiz = new Node(valor);
            return raiz;
        }
        else {
            return insertRec(valor, raiz);
        }
    }

    private Node insertRec(int valor, Node raiz) {
        if(raiz == null) {
            return new Node(valor);
        } else if(valor < raiz.valor) {
            raiz.esq = insertRec(valor, raiz.esq);
        } else if(valor > raiz.valor) {
            raiz.dir = insertRec(valor, raiz.dir);
        }

        if(raiz.esq != null) {
            if(raiz.dir != null) {
                raiz.nosEsq = raiz.esq.nosEsq + raiz.esq.nosDir + 1;
                raiz.nosDir = raiz.dir.nosEsq + raiz.dir.nosDir + 1;
            }
            else {
                raiz.nosEsq = raiz.esq.nosEsq + raiz.esq.nosDir + 1;
            }
        }
        else {
            if(raiz.dir != null) {
                raiz.nosDir = raiz.dir.nosEsq + raiz.dir.nosDir + 1;
            }
        }

        return raiz;
    }

    public void remover(int x){}

    public Node buscar(int x) {
        Node noAtual = raiz;
        
        while(noAtual.valor != x) {
            if(noAtual.valor > x) {
                if(noAtual.esq == null) {
                    return null;
                }
                noAtual = noAtual.esq;
            }
            else {
                if(noAtual.dir == null) {
                    return null;
                }
                noAtual = noAtual.dir;
            }
        }

        return noAtual;
    }

    public int enesimoElemento(int n) {
        Node noAtual = raiz;
        int idxAtual = raiz.nosEsq + 1;

        while(idxAtual != n) {
            if(idxAtual > n) {
                noAtual = noAtual.esq;
                idxAtual = idxAtual - 1 - noAtual.nosDir;
            }
            else {
                noAtual = noAtual.dir;
                idxAtual = idxAtual + 1 + noAtual.nosEsq;
            }
        }

        return noAtual.valor;
    }

    public int posicao(int x) {
        Node noAtual = raiz;
        int idxAtual = raiz.nosEsq + 1;

        while(noAtual.valor != x) {
            if(noAtual.valor > x) {
                if(noAtual.esq == null) {
                    return 0;
                }
                noAtual = noAtual.esq;
                idxAtual = idxAtual - 1 - noAtual.nosDir;
            }
            else {
                if(noAtual.dir == null) {
                    return 0;
                }
                noAtual = noAtual.dir;
                idxAtual = idxAtual + 1 + noAtual.nosEsq;
            }
        }

        return idxAtual;
    }

    public int mediana() {
        int n = raiz.nosEsq + raiz.nosDir + 1;

        if(n % 2 == 0) {
            int med1 = enesimoElemento(n/2);
            int med2 = enesimoElemento(n/2 + 1);
            return med1 < med2 ? med1 : med2;
        }
        else {
            return enesimoElemento(n/2 + 1);
        }
    }

    public double media(int x) {
        Node raiz = buscar(x);
        int n = raiz.nosEsq + raiz.nosDir + 1;

        return (double)soma(raiz) / n;
    }

    public int soma(Node raiz) {
        if(raiz == null) {
            return 0;
        }

        return soma(raiz.esq) + soma(raiz.dir) + raiz.valor;
    }

    public String preOrdem(Node raiz) {
        String s = Integer.toString(raiz.valor);

        if(raiz.esq != null) {
            s += " " + preOrdem(raiz.esq);
        }
        if(raiz.dir != null) {
            s += " " + preOrdem(raiz.dir);
        }

        return s;
    }

    public static void main(String[] args) {
        ABB a = new ABB();

        a.insert(20);
        a.insert(15);
        a.insert(10);
        a.insert(30);
        a.insert(45);
        a.insert(35);
        a.insert(50);

        System.out.println(a.preOrdem(a.getRaiz()));
    }
}