import java.util.Scanner;

/**
 * This class represents the board for a game of 
 * TicTacToe
 * 
 * @author G. Ayorkor Korsah
 */
public class TicTacToeBoard {

    private String[][] board; // the board 
    private int numEmpty; // The number of empty slots
    private boolean detectedWin; // Whether or not a winner has been seen
    private String winningSymbol; // The symbol of the winner
    public static final int SIZE = 3;
    public static final String EMPTY = ".";

    /**
     * The constructor for the class. 
     * It instantiates the 3x3 board and initializes the board to be empty.
     * You should also initialize the number of empty slots
     * 
     */
    public TicTacToeBoard() {
        // TODO: Complete the method
        board = new String [SIZE][SIZE];
        for(int i =0; i< board.length; i++)
        {
            for(int j = 0; j< board[i].length; j++)
            {
                board[i][j]=EMPTY;
            }
        } 

        // This part below has been done for you, for free 😄
        detectedWin = false;
        winningSymbol = "";
        numEmpty=9;
    }

    /**
     * It prints the board
     */
    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * If the number of empty spaces is 0, then the board is full
     * 
     * @return whether or not the board is full.
     */
    public boolean isBoardFull() {
        if(numEmpty==0)
        {
            return true; // TODO: Correct the method
        }
        return false;
    }

    /**
     * Return true if a given location on the board, 
     * specified by a given row index and column index, is empty
     * 
     * @param row The row of the board.
     * @param col the column of the board
     * @return Whether or not the location is empty.
     */
    public boolean isEmpty(int row, int col) {
        if(board[row][col]== EMPTY)
        {
            return true; // TODO: Correct the method
        }
        return false;
    }

    /**
     * check the game for a win
     * If all the elements a row, column, or diagonal are the same, 
     * then a win has been detected.
     * 
     * If a win is detected, the detectedWin instance variable should be set to true, 
     * and the winningSymbol instance variable should be set to the symbol of the winner.
     *
     * Hint: There are 8 ways to win a 3x3 game of tic-tac-toe
     */
    private void detectWin() {
        // TODO: Correct the method
        String [] element = new String[SIZE*SIZE];
        int num = 0;
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                element[num]= board[row][col];
                num++;
            }
        }
        if(element[0].equals(element[1])&& element[1].equals(element[2])&& element[0]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[0];
        }
        else if (element[3].equals(element[4])&& element[4].equals(element[5])&& element[3]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[3];
        }
        else if (element[6].equals(element[7])&& element[7].equals(element[8])&& element[6]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[6];
        }
        else if (element[0].equals(element[3])&& element[3].equals(element[6])&& element[0]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[0];
        }
        else if (element[1].equals(element[4])&& element[4].equals(element[7])&& element[1]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[1];
        }
        else if (element[2].equals(element[5])&& element[5].equals(element[8])&& element[2]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[2];
        }
        else if (element[0].equals(element[4])&& element[4].equals(element[8])&& element[0]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[0];
        }
        else if (element[2].equals(element[4])&& element[4].equals(element[6])&& element[2]!=EMPTY)
        {
            detectedWin=true;
            winningSymbol=element[2];
        }

    }

    /**
     * If the board is empty at the given row and column, 
     * then place the symbol on the board, 
     * decrement the number of empty spaces, 
     * and check for a win
     * 
     * If the board is not empty at the given row and column, 
     * then the play is not successful and the method returns {@code false}. 
     * 
     * 
     * @param row the row of the board (0 indexed)
     * @param col The column number of the board (0 indexed).
     * @param symbol the symbol to be placed on the board
     * @return Whether a move was successful or not.
     */
    public boolean play(int row, int col, String symbol) {
        // TODO: Implement the method
        if(isEmpty(row, col))
        {
            board[row][col] = symbol;
            numEmpty--;
            detectWin();
            return true;

        }
        else 
        {
            return false;
        }
    }
        

    /**
     * If the game is over,and has a winner, return true. Otherwise, return false
     * 
     * @return The boolean value of detectedWin.
     */
    public boolean hasWinner() {
        if(isBoardFull() && detectedWin)
        {
            return detectedWin;
        }
        
        return detectedWin; // TODO: Correct the method
    }

    /**
     * This function returns the winning symbol.
     * It is only valid to call this method if hasWinner returns true.
     * 
     * @return The winningSymbol.
     */
    public String getWinningSymbol() {
        if(hasWinner())
        {
            return winningSymbol;
        }
        return EMPTY; // TODO: Correct the method
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TicTacToeBoard board = new TicTacToeBoard();
        board.printBoard();

        int row, col;
        boolean xTurn = true;
        while (!board.isBoardFull() && !board.hasWinner()) {
            System.out.print("Enter row & col to play: ");
            row = input.nextInt();
            col = input.nextInt();
            board.play(row, col, xTurn ? "X" : "O");
            board.printBoard();
            if (board.isBoardFull())
                System.out.println("Board is full.");
            if (board.hasWinner())
                System.out.println("Has winner: " + board.getWinningSymbol());
            xTurn = !xTurn;
        }
        System.out.println("Goodbye!");
        input.close();
    }
}