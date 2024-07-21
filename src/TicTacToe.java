import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String inPlay = "X";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain;
        do {
            clearBoard();
            display();
            boolean gameOver = false;
            while (!gameOver) {
                System.out.println(inPlay + ", please enter your move");
                int rowMove = getRangedInt(in, "Row: ", 1, 3) - 1;
                int colMove = getRangedInt(in, "Column: ", 1, 3) - 1;
                if (isValidMove(rowMove, colMove)) {
                    board[rowMove][colMove] = inPlay;
                    display();
                    if (isWin(inPlay)) {
                        System.out.println("You win, player " + inPlay);
                        gameOver = true;
                    } else if (isTie()) {
                        System.out.println("Tie.");
                        gameOver = true;
                    } else {
                        togglePlayer();
                    }
                } else {
                    System.out.println("Your move was not valid.");
                }
            }

            System.out.print("Wish to play again? Yes or no? ");
            playAgain = in.next().equalsIgnoreCase("yes");



        } while (playAgain);
        in.close();
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }



    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;

    }


    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }


        }
        return true;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
    }

    private static void display() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if (col < COL - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROW - 1) System.out.println("-----");
        }
    }

    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void togglePlayer() {
        inPlay = inPlay.equals("X") ? "O" : "X";
    }

    private static int getRangedInt(Scanner in, String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!in.hasNextInt()) {
                System.out.print(prompt);

                in.next();
            }
            input = in.nextInt();
        } while (input < min || input > max);
        return input;
    }
}


