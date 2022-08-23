package com.alls.snake;

import java.util.Scanner;

public class Snake {

    public static void printarTabuleiro(String[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }
    }

    public static void preencherTabuleiro(String[][] tabuleiro) { // TODO refactor
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (j == 0) {
                    if (i == tabuleiro.length - 1) {
                        if (tabuleiro.length >= 11) {
                            tabuleiro[i][j] = "   ";
                        } else {
                            tabuleiro[i][j] = "  ";
                        }
                    } else {
                        if (tabuleiro.length >= 11 && i < 9) {
                            tabuleiro[i][j] = Integer.toString(i + 1) + "  ";
                        } else {
                            tabuleiro[i][j] = Integer.toString(i + 1) + ' ';
                        }
                    }
                } else if (i == tabuleiro.length - 1) {
                    tabuleiro[i][j] = (char) (64 + j) + " ";
                } else {
                    tabuleiro[i][j] = "□ "; // TODO criar char tabuleiro variavel
                }
            }
        }
    }

    public static boolean posicaoValida(int x, int y, int tamanhoTabuleiro) {
        return true; // TODO
    }

    public static String[][] colocarFruta(int x, int y, String[][] tabuleiro) {
        if (posicaoValida(x, y, tabuleiro.length)) {
            String[][] novoTabuleiro = tabuleiro;
            novoTabuleiro[x][y] = "F "; // TODO criar char fruta 
            return novoTabuleiro;
        } else {
            //print erro funcao
        }
        return tabuleiro;
    }

    public static int[] inputFruta(Scanner scanner) {
        int[] input = new int[2];
        
        System.out.println("Input");
        String temp = scanner.next();

        input[0] = Character.getNumericValue(temp.charAt(0)) - 1;
        input[1] = temp.charAt(1) - 64;
        
        return input;
    }

    public static void gameLoop(String[][] tabuleiro, Scanner scanner) { // TODO running/clear
        int[] input;

        while (true) {
            printarTabuleiro(tabuleiro);
            input = inputFruta(scanner);
            tabuleiro = colocarFruta(input[0], input[1], tabuleiro);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira tamanho tabuleiro:");
        int tamanhoTabuleiro = scanner.nextInt() + 1;

        if (tamanhoTabuleiro > 27) {
            System.out.println("max 26");
            tamanhoTabuleiro = 27;
        } else if (tamanhoTabuleiro < 8) {
            System.out.println("min 7");
            tamanhoTabuleiro = 8;
        }

        String[][] tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro];
        preencherTabuleiro(tabuleiro);

        gameLoop(tabuleiro, scanner);
    }
}

// TODO muder de i e j pra l e c
// TODO dificuldades com geracao mapas
