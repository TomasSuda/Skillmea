package org.example;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome in Tic Tac Toe game");
        int inputedPosition = 0;
        int attemp = 1;
        char shapeOfChar = 'X';
        List<Integer> listOfTriedPositions = new ArrayList<>();
        List<Integer> player1Attemps = new ArrayList<>();
        List<Integer> player2Attemps = new ArrayList<>();
        boolean winner = true;

        while (winner == true) {
            System.out.println("Insert number of position from 1 - 9");
            shapeOfChar = getShape(attemp);
            inputedPosition = checkPosition(new Scanner(System.in), listOfTriedPositions);
            listOfTriedPositions.add(inputedPosition);

            playingField(defaultTicTacToe, inputedPosition, shapeOfChar);

            if (attemp % 2 != 0) {
                player1Attemps.add(inputedPosition);
            } else {
                player2Attemps.add(inputedPosition);
            }

            attemp++;
            winner = winnerOfGame(player1Attemps, player2Attemps, attemp);
        }
    }

    // private static boolean checkDraw(int attemp) {
    //     if (attemp == 10) {
    //         System.out.println("DRAWWW !!!!!");
    //         return false;
    //     } else {
    //         return true;
    //     }
    // }

    // method for changing players and shape of char (X --> O)
    private static char getShape(int attemp) {
        char shape;
        if (attemp % 2 == 0) {
            System.out.println("Player's TWO (0) turn");
            shape = '0';
        } else {
            System.out.println("Player's ONE (X) turn");
            shape = 'X';
        }
        return shape;
    }

    // method for inputing position and checking validity
    private static int checkPosition(Scanner scanner, List<Integer> inputs) {
        while (true) {
            try {
                final Integer position = scanner.nextInt();
                for (Integer input : inputs) {
                    if (input.equals(position)) {
                        throw new Exception("Number has already been used");
                    }
                }

                if (position > 0 && position <= 9) {
                    return position;

                } else {
                    System.out.println(("Inserted number is out of range. Repeat action !!!"));
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input: Incorrect format, use intiger!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Wrong input: " + e.getMessage() + ". Repeat action !!!");
                scanner.nextLine();
            }

        }

    }

    private static char[][] defaultTicTacToe = {{' ', '|', ' ', '|', ' '},
            {'-', '-', '-', '-', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '-', '-', '-', '-'},
            {' ', '|', ' ', '|', ' '}};

    // method for drawing playing field and updating players tries
    private static char[][] playingField(char[][] ticTacToe, int position, char shape) {
        int row = 0;
        int column = 0;
        if (position <= 3) {
            for (int i = 1; i < position; i++) {
                column += 2;
            }
        } else if (position > 3 && position <= 6) {
            row = 2;
            for (int i = 4; i < position; i++) {
                column += 2;
            }
        } else if (position >= 7 && position <= 9) {
            row = 4;
            for (int i = 7; i < position; i++) {
                column += 2;
            }
        } else {
            System.out.println("Wrong input");
        }
        for (int i = 0; i < ticTacToe.length; i++) {
            for (int j = 0; j < 5; j++) {
                ticTacToe[row][column] = shape;
                System.out.print(ticTacToe[i][j]);
            }
            System.out.println();
        }
        return ticTacToe;
    }
        // method evaluates winner of game
    private static boolean winnerOfGame(List<Integer> plOne, List<Integer> plTwo, int attemp) {
        List<List<Integer>> experiments = new ArrayList<>();
        experiments.add(Arrays.asList(1, 2, 3));
        experiments.add(Arrays.asList(4, 5, 6));
        experiments.add(Arrays.asList(7, 8, 9));
        experiments.add(Arrays.asList(1, 4, 7));
        experiments.add(Arrays.asList(2, 5, 8));
        experiments.add(Arrays.asList(3, 6, 9));
        experiments.add(Arrays.asList(1, 5, 9));
        experiments.add(Arrays.asList(3, 5, 7));

        for (List<Integer> experiment : experiments) {
            if (plOne.containsAll(experiment)) {
                System.out.println("Congratulation player ONE is winner !!!");
                return false;
            } else if (plTwo.containsAll(experiment)) {
                System.out.println("Congratulation player TWO is winner !!!");
                return false;
            } else if (attemp == 10) {
                System.out.println("DRAWWW !!!!!");
                return false;
            }
        }
        return true;
    }
}
