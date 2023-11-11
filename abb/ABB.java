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

        raiz.altura = calcularAltura(raiz);
        raiz.nosEsq = calcularNosEsq(raiz);
        raiz.nosDir = calcularNosDir(raiz);

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

    public boolean ehCheia() {
        if(raiz == null) {
            return true;
        }

        int altura = raiz.altura;
        int n = raiz.nosDir + raiz.nosEsq + 1;

        if(altura == Math.log(n+1)/Math.log(2)) {
            return true;
        }

        return false;
    }

    public boolean ehCompleta() {
        if(raiz == null) {
            return true;
        }

        int altura = raiz.altura;
        int n = raiz.nosDir + raiz.nosEsq + 1;

        if(altura == 1 + Math.floor(Math.log(n) / Math.log(2))) {
            return true;
        }

        return false;
    }

    public void simetrica(Node raiz) {
        if(raiz != null) {
            simetrica(raiz.esq);
            System.out.println(raiz.valor + " Alt:" + raiz.altura + " NosEsq:" + raiz.nosEsq + " NosDir:" + raiz.nosDir);
            simetrica(raiz.dir);
        }
    }

    private int calcularAltura(Node no) {
        if(no.esq != null) {
            if(no.dir != null) {
                int maior_altura = no.esq.altura > no.dir.altura ? no.esq.altura : no.dir.altura;
                return maior_altura + 1;
            }
            else {
                return no.esq.altura + 1;
            }
        }
        else {
            if(no.dir != null) {
                return no.dir.altura + 1;
            }
            else {
                return 1;
            }
        }
    }

    private int calcularNosEsq(Node no) {
        if(no.esq != null) {
            return no.esq.nosEsq + no.esq.nosDir + 1;
        }

        return 0;
    }

    private int calcularNosDir(Node no) {
        if (no.dir != null) {
            return no.dir.nosEsq + no.dir.nosDir + 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        ABB a = new ABB();

        a.insert(20);
        a.insert(30);
        a.insert(25);
        a.insert(35);
        a.insert(10);
        a.insert(15);
        a.insert(5);

        System.out.println("Completa: " + a.ehCompleta());
        System.out.println("Cheia: " + a.ehCheia());
    }
}