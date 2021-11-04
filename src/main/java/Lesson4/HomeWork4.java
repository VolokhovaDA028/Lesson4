package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {

    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static final char DOT_EMPTY = '_';

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int scoreHuman;
    private static int scoreAi;
    private static int roundCounter;
    private static int vinLength;

    public static void main(String[] args) {
        sizeField();//размеры поля
       //ход игрока
        int x;
        int y;

        System.out.println("Введите координаты вашего хода X и Y, разделенные пробелами >>>");
        x = scanner.nextInt() - 1;
        y = scanner.nextInt() - 1;
        field[y][x] = DOT_X;

        printField();
    }
    //размеры поля
    private static void sizeField() {
        int y;
        int x;

        System.out.print("Введите размер поля >>>\n");
        y = scanner.nextInt();
        x = scanner.nextInt();
        initField(y, x);
        printField();
    }
    //инициализация поля
    private static void initField(int sizeX, int sizeY) {
        fieldSizeX = sizeX;
        fieldSizeY = sizeY;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }
    //печать поля
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "|" : i / 2 + 1);
        }
        System.out.println();
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|" );
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|" );
            }
            System.out.println();
        }
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
