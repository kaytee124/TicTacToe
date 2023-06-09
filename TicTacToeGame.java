import java.util.Scanner;

/**
 * This program implements a game of Tic Tac Toe.
 * It uses the TicTacToeBoard and TicTacToePlayer classes.
 * 
 * @author G. Ayorkor Korsah
 */
public class TicTacToeGame {

    /**
     * The function asks the user for a row and column, 
     * and then attempts to play the player's symbol
     * at that position.  
     * If the play is successful, the board is printed.  
     * If the play is not * successful, the user is asked to try again
     * 
     * @param board The board object that the game is being played on.
     * @param player The player who is making the move.
     */
    private static void play(TicTacToeBoard board, TicTacToePlayer player) {
        Scanner input = new Scanner(System.in);
        int row, col;
        boolean playSuccessful;

        // TODO: Complete the play method
        // Allow the player to play on the board
        
        System.out.println("Enter a desired row: ");
        row = input.nextInt();
        System.out.println("Enter a desired column: ");
        col = input.nextInt();
        playSuccessful= false;

        while(row < 0 || row >= TicTacToeBoard.SIZE|| col >= TicTacToeBoard.SIZE || col < 0)
        {
            System.out.println("Try again, Input rows and columns less than " + TicTacToeBoard.SIZE + " and greater than 0  ");
            System.out.println("Enter a desired row: ");
            row = input.nextInt();
            System.out.println("Enter a desired column: ");
            col = input.nextInt();

        }
        
        boolean check = board.play(row, col, player.getSymbol());

        if(check)
        {
            playSuccessful=true;     
            System.out.println("The board now looks like this: ");
            board.printBoard();           
        }
        else
        {
            while(playSuccessful==false)
            {
                System.out.println("Try again");
                System.out.println("Enter a desired row: ");
                row = input.nextInt();
                System.out.println("Enter a desired column: ");
                col = input.nextInt();
                if(board.play(row, col, player.getSymbol()))
                {
                    playSuccessful=true;       
                    System.out.println("The board now looks like this: ");
                    board.printBoard();         
                }
            }
        }

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        TicTacToeBoard board;
        TicTacToePlayer player1, player2; // The two players of the game
        TicTacToePlayer curPlayer; // a reference to the current player
        int whoseTurn; // a number to keep track of the player turn

        board = new TicTacToeBoard();
        player1 = new TicTacToePlayer();
        player2 = new TicTacToePlayer();

        System.out.println("Player 1, please enter your information: ");
        player1.getPlayerInfo();

        System.out.println("Player 2, please enter your information: ");

        do {
            player2.getPlayerInfo();
            if (player2.getName().equalsIgnoreCase(player1.getName()))
                System.out.println("Sorry, that name is being used by Player 1.");
            if (player2.getSymbol() == player1.getSymbol())
                System.out.println("Sorry, that symbol is being used by Player 1.");
        } while (player2.getName().equalsIgnoreCase(player1.getName()) ||
                player2.getSymbol() == player1.getSymbol());

        System.out.println("Okay, " + player1.getName() +
                " is player 1 and will use symbol " + player1.getSymbol());
        System.out.println(player2.getName() + " is player 2 and will use symbol " +
                player2.getSymbol());

        System.out.print("Who will go first? (Enter 1 or 2) ");
        whoseTurn = input.nextInt();
        if (whoseTurn == 1)
            System.out.println(player1.getName() + " will go first.");
        else
            System.out.println(player2.getName() + " will go first.");

        System.out.println("Initially, the board looks like: ");
        board.printBoard();

        do {
            if (whoseTurn == 1)
                curPlayer = player1;
            else
                curPlayer = player2;

            play(board, curPlayer);
            whoseTurn = whoseTurn % 2 + 1;

        } while (!board.isBoardFull() && !board.hasWinner());

        if (board.hasWinner()) {
            if (board.getWinningSymbol() == player1.getSymbol())
                System.out.println(player1.getName() + " wins!");
            else
                System.out.println(player2.getName() + " wins!");
        } else
            System.out.println("There is no winner.");

        input.close();
    }
}