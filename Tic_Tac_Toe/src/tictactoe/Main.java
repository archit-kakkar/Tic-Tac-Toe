package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] twoDimArray = new char[3][3];
        System.out.print("Enter cells: ");
        String input = scanner.nextLine(); // O_OXXO_XX
        int index = 0;

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                twoDimArray[i][j] = input.charAt(index);
                System.out.print(input.charAt(index) + " ");
                index++;
            }
            System.out.println("|");
        }
        System.out.println("---------");

        enterCoordinate(twoDimArray);
        printPattern(twoDimArray);

        if (checkImpossible(twoDimArray)) {
            System.out.print("Impossible");
        } else {
            switch (checkWin(twoDimArray)) {
                case "X":
                    System.out.print("X wins");
                    break;
                case "O":
                    System.out.print("O wins");
                    break;
                case "":
                    checkDraw_checkGameNotFinished(twoDimArray);
                    break;
            }
        }

    }

    public static void enterCoordinate(char[][] twoDimArray) {
        try {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            int x = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
            int y = Integer.parseInt(String.valueOf(coordinates.charAt(2)));

            if (x > 3 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                enterCoordinate(twoDimArray);
            } else if (twoDimArray[x - 1][y - 1] == ' ' || twoDimArray[x - 1][y - 1] == '_') {
                twoDimArray[x - 1][y - 1] = 'X';
            } else if (twoDimArray[x - 1][y - 1] != ' ' || twoDimArray[x - 1][y - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                enterCoordinate(twoDimArray);
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            enterCoordinate(twoDimArray);
        }
    }

    public static void printPattern(char[][] twoDimArray) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(twoDimArray[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String checkWin(char[][] twoDimArray) {
        int countXRow = 0, countXCol = 0, countXDg1 = 0, countXDg2 = 0;
        int countORow = 0, countOCol = 0, countODg1 = 0, countODg2 = 0;
        String str = "";

        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                if (twoDimArray[i][j] == 'X') {
                    countXRow++;
                } else if (twoDimArray[i][j] == 'O') {
                    countORow++;
                }
                if (twoDimArray[j][i] == 'X') {
                    countXCol++;
                } else if (twoDimArray[j][i] == 'O') {
                    countOCol++;
                }
                if (i + j == 2) {
                    if (twoDimArray[i][j] == 'X') {
                        countXDg2++;
                    } else if (twoDimArray[i][j] == 'O') {
                        countODg2++;
                    }
                }
            }
            if (twoDimArray[i][i] == 'X') {
                countXDg1++;
            } else if (twoDimArray[i][i] == 'O') {
                countODg1++;
            }
            if (countXRow == 3 || countXCol == 3) {
                str = "X";
            } else if (countORow == 3 || countOCol == 3) {
                str =  str.concat("O");
            }
            countXRow = 0;
            countXCol = 0;
            countORow = 0;
            countOCol = 0;
        }
        if (countXDg1 == 3 || countXDg2 == 3) {
            str = "X";
        } else if (countODg1 == 3 || countODg2 == 3) {
            str = str.concat("O");
        }
        return str;
    }

    public static void checkDraw_checkGameNotFinished(char[][] twoDimArray) {
        int c = 0;

        for (char[] chars : twoDimArray) {
            for (char aChar : chars) {
                if (aChar == ' ' || aChar == '_') {
                    c++;
                }
            }
        }

        if("".equals(checkWin(twoDimArray)) && c == 0) {
            System.out.print("Draw");
        } else if ("".equals(checkWin(twoDimArray)) && c > 0) {
            System.out.print("Game not finished");
        }

    }

    public static boolean checkImpossible(char[][] twoDimArray) {
        int countX = 0;
        int countO = 0;
        String str = checkWin(twoDimArray);

        for (char[] chars : twoDimArray) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    countX++;
                } else if (aChar == 'O') {
                    countO++;
                }
            }
        }
        return countX - countO >= 2 || countO - countX >= 2 || str.length() >= 2;
    }

}
