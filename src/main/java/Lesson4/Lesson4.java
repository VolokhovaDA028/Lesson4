package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {

    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static final char DOT_EMPTY = '.';

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

        initField(3, 3);
        printField();
        while (true){
            humanTurn();
            printField();
            aiTurn();
            printField();
        }
    }

    //ход Ai
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(y, x));
        field[y][x] = DOT_0;
    }

    //ход игрока
    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты вашего хода X и Y, разделенные пробелами >>>");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y, x) || !isCellEmpty(y, x));
            field[y][x] = DOT_X;
    }

    //проверка заполненности поля
    private static boolean isCellEmpty(int x, int y){
        return field[y][x] == DOT_EMPTY;
    }

    //проверка введенных координат
    private static boolean isCellValid(int x, int y){
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;
    }

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
                System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
//            if (i % 2 == 0) {
//                System.out.print("-");
//            } else {
//                System.out.print(i / 2 + 1);
//            }
            }
            System.out.println();
            for (int i = 0; i < fieldSizeY; i++) {
                System.out.print(i + 1 + "|");
                for (int j = 0; j < fieldSizeX; j++) {
                    System.out.print(field[i][j] + "|");
                }
                System.out.println();
            }
            for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
                System.out.print("_");
            }
            System.out.println();
//        for (int y = 0; y < fieldSizeY; y++) {
//            for (int x = 0; x < fieldSizeX; x++) {
//                System.out.print(field[y][x] + " ");
//            }
//            System.out.println();
//        }
        }
    }
