package Lesson4;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {

    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static final char DOT_EMPTY = '.';

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static char dotHuman;
    private static char dotAi;

    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int scoreHuman;
    private static int scoreAi;
    private static int roundCounter;
    private static int vinLength;


    public static void main(String[] args) {

        play();

    }

    private static void play(){
        while (true){
            chooseTheDot();
            playRound();
        System.out.printf("Счет: Игрок    Ai\n" + "" +
                "       %d        %d\n", scoreHuman, scoreAi);
        System.out.println("Хотите ли сыграть еще раз? Y или N >>>");
        if (!scanner.next().toLowerCase().equals("y")){
            System.out.println("Good bye!");
            break;
            }
        }
    }
    //раунд
    private static void playRound() {
        System.out.printf("Раунд %d начали!!\n", ++roundCounter);
        initField(3, 3);
        printField();
        if (dotHuman == DOT_X) {
            humanFirstTurn();
        } else {
            aiFirstTurn();
        }
    }

    //первый ход Ai
    private static void aiFirstTurn() {
        while (true) {
            aiTurn();
            printField();
            if (checkGame(dotAi)) break;
            humanTurn();
            printField();
            if (checkGame(dotHuman)) break;
        }
    }
    //первый ход игрока
    private static void humanFirstTurn() {
        while (true) {
            humanTurn();
            printField();
            if (checkGame(dotHuman)) break;
            aiTurn();
            printField();
            if (checkGame(dotAi)) break;
        }
    }

    //распределение фишек Х и 0
    private static void chooseTheDot(){
        System.out.print("Если вы хотите играть 'X'  тогда введите 'X', иначе введити любой символ >>>");
        if (scanner.next().toLowerCase().equals("x")) {
            dotHuman = DOT_X;
            dotAi = DOT_0;
        } else {
            dotHuman = DOT_0;
            dotAi = DOT_X;
        }
    }
    //проверка игры
    private static boolean checkGame(char dot){
        if (checkWin(dot)) {
            if (dot == dotHuman){
            System.out.println("Победил игрок!");
            scoreHuman++;
            } else {
                System.out.println("Победил Ai!");
                scoreAi++;
            }
            return true;
        }
        if (checkDraw()) {
            return true;
        }
        return false;
    }
    //ход Ai
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(y, x));
        field[y][x] = dotAi;
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
        field[y][x] = dotHuman;
    }
    //победа
    private static boolean checkWin(char dot) {
        //горизонталь
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
        //вертикаль
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
        //диагональ
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;
        return false;
    }
    //ничья
    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (isCellEmpty(y, x)){
                    return false;
                }
            }

        }
        System.out.println("Ничья!");
        return true;
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
