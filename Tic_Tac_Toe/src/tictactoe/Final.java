package tictactoe;

import java.util.Scanner;

public class Final {

    static Scanner scanner = new Scanner(System.in);
    static char[][] gameBoard = new char[][] {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
    };
    static int count = 0;

    public static void main(String[] args) {

        printGameBoard();

        while (true) {
            enterCoordinates();
            printGameBoard();

            if (checkResult()) {
                break;
            }
            count++;
        }

    }

    public static void printGameBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void enterCoordinates() {
        try {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            int x = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
            int y = Integer.parseInt(String.valueOf(coordinates.charAt(2)));

            if (x > 3 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                enterCoordinates();
            }
            else if (gameBoard[x - 1][y - 1] == ' ' || gameBoard[x - 1][y - 1] == '_') {
                gameBoard[x - 1][y - 1] = count % 2 == 0 ? 'X' : 'O';
            }
            else if (gameBoard[x - 1][y - 1] != ' ' || gameBoard[x - 1][y - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                enterCoordinates();
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            enterCoordinates();
        }
    }

    public static String checkWin() {
        int countXRow = 0, countXCol = 0, countXDg1 = 0, countXDg2 = 0;
        int countORow = 0, countOCol = 0, countODg1 = 0, countODg2 = 0;
        String str = "";

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == 'X') {
                    countXRow++;
                } else if (gameBoard[i][j] == 'O') {
                    countORow++;
                }
                if (gameBoard[j][i] == 'X') {
                    countXCol++;
                } else if (gameBoard[j][i] == 'O') {
                    countOCol++;
                }
                if (i + j == 2) {
                    if (gameBoard[i][j] == 'X') {
                        countXDg2++;
                    } else if (gameBoard[i][j] == 'O') {
                        countODg2++;
                    }
                }
            }
            if (gameBoard[i][i] == 'X') {
                countXDg1++;
            } else if (gameBoard[i][i] == 'O') {
                countODg1++;
            }
            if (countXRow == 3 || countXCol == 3) {
                str = "X";
            } else if (countORow == 3 || countOCol == 3) {
                str =  "O";
            }
            countXRow = 0;  countXCol = 0;  countORow = 0;  countOCol = 0;
        }

        if (countXDg1 == 3 || countXDg2 == 3) {
            str = "X";
        } else if (countODg1 == 3 || countODg2 == 3) {
            str = "O";
        }

        return str;
    }

    public static boolean checkDraw() {
        int c = 0;

        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    c++;
                }
            }
        }

        return "".equals(checkWin()) && c == 0;
    }

    public static boolean checkResult() {
        String result = checkWin();

        if ("X".equals(result)) {
            System.out.println("X wins");
            return true;
        }
        else if ("O".equals(result)) {
            System.out.println("O wins");
            return true;
        }
        else if (checkDraw()) {
            System.out.print("Draw");
            return true;
        }

        return false;
    }

}