import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                    board[i][1] == currentPlayer &&
                    board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer &&
                    board[1][i] == currentPlayer &&
                    board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer &&
                        board[1][1] == currentPlayer &&
                        board[2][0] == currentPlayer);
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == EMPTY) {
                board[row][col] = currentPlayer;
                return true;
            } else {
                System.out.println("This position is already filled. Try another position.");
            }
        } else {
            System.out.println("Invalid position. Please enter row and column between 0 and 2.");
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean gameWon = false;

        System.out.println("Welcome to Tic Tac Toe!");
        game.printBoard();

        while (!game.isBoardFull()) {
            int row = -1, col = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Player " + game.currentPlayer + ", enter your move (row and column): ");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                    validInput = game.placeMark(row, col);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers between 0 and 2.");
                    scanner.next(); // Clear the invalid input
                }
            }

            game.printBoard();

            if (game.checkWin()) {
                System.out.println("Player " + game.currentPlayer + " wins!");
                gameWon = true;
                break;
            }

            game.changePlayer();
        }

        if (!gameWon) {
            System.out.println("The game is a draw!");
        }

        scanner.close();
    }
}
