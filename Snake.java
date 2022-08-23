package com.alls.snake;

import java.util.Scanner;

public class Snake {

    public static void printarTabuleiro(String[][] tabuleiro) {
        for (int linhas = 0; linhas < tabuleiro.length; linhas++) {
            for (int colunas = 0; colunas < tabuleiro.length; colunas++) {
                System.out.print(tabuleiro[linhas][colunas]);
            }
            System.out.println();
        }
    }

    public static String[][] preencherTabuleiro(int tamanhoTabuleiro) { // TODO refactor
        String[][] tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro];
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
                if (coluna == 0) {
                    if (linha == tabuleiro.length - 1) {
                        if (tabuleiro.length >= 11) {
                            tabuleiro[linha][coluna] = "   ";
                        } else {
                            tabuleiro[linha][coluna] = "  ";
                        }
                    } else {
                        if (tabuleiro.length >= 11 && linha < 9) {
                            tabuleiro[linha][coluna] = Integer.toString(linha + 1) + "  ";
                        } else {
                            tabuleiro[linha][coluna] = Integer.toString(linha + 1) + ' ';
                        }
                    }
                } else if (linha == tabuleiro.length - 1) {
                    tabuleiro[linha][coluna] = (char) (64 + coluna) + " ";
                } else {
                    tabuleiro[linha][coluna] = "□ "; // TODO criar char tabuleiro variavel
                }
            }
        }
        return tabuleiro;
    }

    public static boolean posicaoValida(int[] input, int tamanhoTabuleiro) {
        return true; // TODO
    }

    public static String[][] colocarFruta(int[] input, String[][] tabuleiro) {
        tabuleiro[input[0]][input[1]] = "F "; // TODO criar char fruta
        return tabuleiro;
    }

    public static int[] inputFruta(Scanner scanner, String[][] tabuleiro) {
        System.out.println("Input");
        String temp = scanner.next();

        String coordenadaNumerica = temp.substring(0, temp.length() - 1);
        char coordenadaAlfabetica = temp.charAt(temp.length() - 1);

        int[] input = new int[2];
        input[0] = Integer.parseInt(coordenadaNumerica) - 1;
        input[1] = coordenadaAlfabetica - 64;

        if (posicaoValida(input, tabuleiro.length)) {
            return input;
        }
        System.out.println("Input n valido");
        return inputFruta(scanner, tabuleiro);
    }

    public static int[] inputCobra(Scanner scanner, String[][] tabuleiro) {
    }
    
    public static String[][] moverCobra(int[] input, boolean naoHaFrutaTabuleiro, String[][] tabuleiro) {

    }

    public static void gameLoop(String[][] tabuleiro, boolean naoHaFrutaTabuleiro, Scanner scanner) { // TODO running/clear
        int[] input;

        while (true) {
            printarTabuleiro(tabuleiro);
            input = inputFruta(scanner, tabuleiro);
            if (naoHaFrutaTabuleiro) {
                tabuleiro = colocarFruta(input, tabuleiro);
                naoHaFrutaTabuleiro = false;
            }
            input = inputCobra(scanner, tabuleiro);
            tabuleiro = moverCobra(input, naoHaFrutaTabuleiro, tabuleiro);
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

        String[][] tabuleiro = preencherTabuleiro(tamanhoTabuleiro);
        int[] input = inputFruta(scanner, tabuleiro);
        tabuleiro = colocarFruta(input, tabuleiro);

        gameLoop(tabuleiro, false, scanner);
    }
}

// TODO muder de i e j pra l e c
// TODO dificuldades com geracao mapas