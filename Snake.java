package com.alls.snake;

import java.util.Scanner;

public class Snake {

    public static String caracterTabuleiro = "  "; // TODO fazer espaço fixo nas funcoes

    public static int[] getPosicaoCalda(int[][] posicaoSegmentos) { // TODO refactor
        int[] posicaoCalda = {posicaoSegmentos[posicaoSegmentos.length - 1][0], posicaoSegmentos[posicaoSegmentos.length - 1][1]};
        return posicaoCalda;
    }

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
                    tabuleiro[linha][coluna] = caracterTabuleiro; // TODO criar char tabuleiro variavel
                }
            }
        }
        return tabuleiro;
    }

    public static boolean posicaoValida(int[] input, int tamanhoTabuleiro) {
        return true; // TODO
    }

    public static String[][] colocarFruta(String[][] tabuleiro, int[] inputFruta) {
        tabuleiro[inputFruta[0]][inputFruta[1]] = "F "; // TODO criar char fruta
        return tabuleiro;
    }

    public static boolean podeComerFruta(String[][] tabuleiro, char inputCobra, int[][] posicoesSegmentos) {
        String string;
        switch (inputCobra) {
            case 'w' -> {
                string = tabuleiro[posicoesSegmentos[0][0] - 1][posicoesSegmentos[0][1]];
                if ("F ".equals(string)) {
                    return true;
                }
            }
            case 'a' -> {
                string = tabuleiro[posicoesSegmentos[0][0]][posicoesSegmentos[0][1] - 1];
                if ("F ".equals(string)) {
                    return true;
                }
            }
            case 's' -> {
                string = tabuleiro[posicoesSegmentos[0][0] + 1][posicoesSegmentos[0][1]];
                if ("F ".equals(string)) {
                    return true;
                }
            }
            case 'd' -> {
                string = tabuleiro[posicoesSegmentos[0][0]][posicoesSegmentos[0][1] + 1];
                if ("F ".equals(string)) {
                    return true;
                }
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    public static int[] inputFruta(String[][] tabuleiro, Scanner scanner) {
        System.out.println("Input fruta");
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
        return inputFruta(tabuleiro, scanner);
    }

    public static int[][] aumentarCobra(int[] inputFruta, int[][] posicaoSegmentos) {
        int[][] novaPosicaoSegmentos = new int[posicaoSegmentos.length + 1][];
        System.arraycopy(posicaoSegmentos, 0, novaPosicaoSegmentos, 1, posicaoSegmentos.length);
        novaPosicaoSegmentos[0] = inputFruta;
        return novaPosicaoSegmentos;
    }

    public static char inputCobra(Scanner scanner) {
        return scanner.next().charAt(0);
    }

    public static int[][] iniciarCobra() { // TODO random?
        int[][] posicaoSegmentos = {{10, 10}};
        return posicaoSegmentos;
    }

    public static String[][] colocarSegmentos(String[][] tabuleiro, int[][] posicaoSegmentos) {
        tabuleiro[posicaoSegmentos[0][0]][posicaoSegmentos[0][1]] = "o ";
        for (int i = 1; i < posicaoSegmentos.length; i++) {
            tabuleiro[posicaoSegmentos[i][0]][posicaoSegmentos[i][1]] = "O ";
        }
        return tabuleiro;
    }

    public static int[][] avancarSegmentos(int[][] posicaoSegmentos, int[][] temp) {
        for (int i = 1; i < temp.length; i++) {
            posicaoSegmentos[i][0] = temp[i - 1][0];
            posicaoSegmentos[i][1] = temp[i - 1][1];
        }
        return posicaoSegmentos;
    }

    public static int[][] moverCobra(char inputCobra, int[][] posicaoSegmentos) {
        int[][] temp = new int[posicaoSegmentos.length][2];
        for (int i = 0; i < posicaoSegmentos.length; i++) {
            System.arraycopy(posicaoSegmentos[i], 0, temp[i], 0, posicaoSegmentos[i].length);
        }

        switch (inputCobra) {
            case 'w' -> {
                posicaoSegmentos[0][0] = temp[0][0] - 1;
                break;
            }
            case 'a' -> {
                posicaoSegmentos[0][1] = temp[0][1] - 1;
                break;
            }
            case 's' -> {
                posicaoSegmentos[0][0] = temp[0][0] + 1;
                break;
            }
            case 'd' -> {
                posicaoSegmentos[0][1] = temp[0][1] + 1;
                break;
            }
            default -> {
            }
        }
        posicaoSegmentos = avancarSegmentos(posicaoSegmentos, temp);
        return posicaoSegmentos;
    }

    public static String[][] test(String[][] tabuleiro, int[] posicaoCalda) {
        tabuleiro[posicaoCalda[0]][posicaoCalda[1]] = caracterTabuleiro;
        return tabuleiro;
    }

    public static void gameLoop(String[][] tabuleiro, Scanner scanner, int[][] posicaoSegmentos) {
        char inputCobra;
        int[] inputFruta = inputFruta(tabuleiro, scanner);
        tabuleiro = colocarFruta(tabuleiro, inputFruta);
        tabuleiro = colocarSegmentos(tabuleiro, posicaoSegmentos);

        while (true) {
            printarTabuleiro(tabuleiro);

            inputCobra = inputCobra(scanner);

            if (podeComerFruta(tabuleiro, inputCobra, posicaoSegmentos)) {
                posicaoSegmentos = aumentarCobra(inputFruta, posicaoSegmentos);
                inputFruta = inputFruta(tabuleiro, scanner);
                tabuleiro = colocarFruta(tabuleiro, inputFruta);
                tabuleiro = colocarSegmentos(tabuleiro, posicaoSegmentos);
            } else {
                int[] posicaoCalda = getPosicaoCalda(posicaoSegmentos);
                posicaoSegmentos = moverCobra(inputCobra, posicaoSegmentos);
                tabuleiro = colocarSegmentos(tabuleiro, posicaoSegmentos);
                tabuleiro = test(tabuleiro, posicaoCalda);
            }
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

        int[][] posicaoSegmentos = iniciarCobra();

        gameLoop(tabuleiro, scanner, posicaoSegmentos);
    }
}

// ERRO tamanho teste 5, input fruta 5A
// TODO muder de i e j pra l e c
// TODO dificuldades com geracao mapas
