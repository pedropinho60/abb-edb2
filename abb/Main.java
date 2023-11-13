package abb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Número de argumentos inválidos, necessários 2");
            return;
        } 
        ABB a = new ABB();

        try {
            File arq1 = new File(args[0]);
            Scanner sc1 = new Scanner(arq1);
            while(sc1.hasNextInt()) {
                a.insert(sc1.nextInt());
            }
            sc1.close();

            File arq2 = new File(args[1]);
            Scanner sc2 = new Scanner(arq2);
            while(sc2.hasNextLine()) {
                String s = sc2.next();

                if(s.equals("ENESIMO")) {
                    int n = sc2.nextInt();
                    System.out.println(a.enesimoElemento(n));
                }
                else if(s.equals("POSICAO")) {
                    int n = sc2.nextInt();
                    System.out.println(a.posicao(n));
                }
                else if(s.equals("MEDIANA")) {
                    System.out.println(a.mediana());
                }
                else if(s.equals("MEDIA")) {
                    int n = sc2.nextInt();
                    System.out.println(a.media(n));
                }
                else if(s.equals("CHEIA")) {
                    if(a.ehCheia()) {
                        System.out.println("A árvore é cheia");
                    }
                    else {
                        System.out.println("A árvore não é cheia");
                    }
                }
                else if(s.equals("COMPLETA")) {
                    if(a.ehCompleta()) {
                        System.out.println("A árvore é completa");
                    }
                    else {
                        System.out.println("A árvore não é completa");
                    }
                }
                else if(s.equals("IMPRIMA")) {
                    int n = sc2.nextInt();
                    a.imprimeArvore(n);
                }
                else if(s.equals("REMOVA")) {
                    int n = sc2.nextInt();
                    boolean t = a.remover(n);
                    if(t) {
                        System.out.println(n + " removido");
                    }
                    else {
                        System.out.println(n + " não está na árvore, não pode ser removido");
                    }
                }
                else if(s.equals("INSIRA")) {
                    int n = sc2.nextInt();
                    boolean t = a.insert(n);
                    if(t) {
                        System.out.println(n + " adicionado");
                    }
                    else {
                        System.out.println(n + " já está na árvore, não pode ser inserido");
                    }
                }
                else if(s.equals("BUSCAR")) {
                    int n = sc2.nextInt();
                    Node t = a.buscar(n);
                    if(t == null) {
                        System.out.println("Chave não encontrada");
                    }
                    else {
                        System.out.println("Chave encontrada");
                    }
                }
                else if(s.equals("PREORDEM")) {
                    System.out.println(a.preOrdem(a.getRaiz()));
                }
                else {
                    System.out.println("Comando desconhecido: \'" + s + "\'");
                }
            }

            sc2.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não existe");
        }
    }
}
