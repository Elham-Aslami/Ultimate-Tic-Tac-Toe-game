import java.util.Scanner;

public class Main {

    private static final String stringReset = "\u001B[0m";
    private static final String stringRed = "\u001B[31m";
    private static final String stringBlue = "\u001B[36m";

    public static void main(String [] args) {

        //declare the Scanner, BigBoard, and UltimateTicTacToe objects
        Scanner input = new Scanner(System.in);
        BigBoard big = new BigBoard();
        UltimateTicTacToe game = new UltimateTicTacToe();

        //greeting
        System.out.print(stringBlue +"Welcome to" + stringReset);
        System.out.print(stringRed + " Ultimate " + stringReset);
        System.out.println(stringBlue + "Tic-Tac-Toe" + stringReset);

        //rules
        System.out.println("\nRULES:");
        System.out.println("\t-Rows are top to bottom");
        System.out.println("\t-Columns are left to right");
        System.out.println("\t-Players take turns placing their mark (X or O) in the smaller boards");
        System.out.println("\t-Each move on a small board corresponds to the next players location on the big board");
        System.out.println("\t-A small board is won when the player gets 3 marks in a row");
        System.out.println("\t-If the previous player chooses a move on the small board that corresponds to an already filled board ( won by a player or tied ), then the current player can choose whatever small board is open");
        System.out.println("\t-The game is won when a player gets 3 small boards in a row");

        //print the empty board
        System.out.println();
        System.out.println("\nCurrent big board:");
        System.out.println();
        System.out.println(big);

        //tell the player whose turn it is
        if ( game.getTurn().equals("X")){
            System.out.println("It's " + stringRed + game.getTurn() + stringReset + "'s turn.");
        }
        else{
            System.out.println("It's " + stringBlue + game.getTurn() + stringReset + "'s turn.");
        }

        //ask the player to choose the board
        System.out.print("Choose sub-board row (1-3): ");
        int subRow = input.nextInt();
        System.out.print("Choose sub-board col (1-3): ");
        int subCol  = input.nextInt();

        //variable so that there is no repeat at the beginning
        int x = 0;

        //repeat the rounds until there is a winner or there is a draw
        while(big.overallWinner().equals("No Winner") && !big.isFull()){
            if ( x > 0 ){
                System.out.println("\nCurrent big board:");
                System.out.println(big);
                if ( game.getTurn().equals("X")){
                    System.out.println("It's " + stringRed + game.getTurn() + stringReset + "'s turn.");
                }
                else{
                    System.out.println("It's " + stringBlue + game.getTurn() + stringReset + "'s turn.");
                }
                System.out.println("Currently in Board (" + subRow + ", " + subCol + ")");
            }

            //make a new board variable based on the board that the player selected
            Board chosen = big.choose(subRow, subCol);

            //if the board that the player chooses is not in the range (1 - 3), then ask them to select another board
            while(chosen == null){
                System.out.println("Invalid sub-board. Try again.");
                System.out.print("Choose sub-board row (1-3): ");
                subRow = input.nextInt();
                System.out.print("Choose sub-board col (1-3): ");
                subCol = input.nextInt();
                chosen = big.choose(subRow, subCol);
            }

            //if the board chosen by the player is already won by X or O, ask them to choose another board
            if ( chosen.winner() == "X" || chosen.winner() == "O" ){
                if ( chosen.winner() == "X" ){
                    System.out.println("That board is already won by " + stringRed + chosen.winner() + stringReset + ". Choose any available board." );
                }
                else{
                    System.out.println("That board is already won by " + stringBlue + chosen.winner() + stringReset + ". Choose any available board." );
                }
                System.out.print("Choose sub-board row (1-3): ");
                subRow = input.nextInt();
                System.out.print("Choose sub-board col (1-3): ");
                subCol  = input.nextInt();
                while ( big.choose(subRow, subCol).winner() == "X" || big.choose(subRow, subCol).winner() == "O" ){
                    if ( big.choose(subRow, subCol).winner() == "X" ){
                        System.out.println("That board is already won by " + stringRed + big.choose(subRow, subCol).winner() + stringReset + ". Try again." );
                    }
                    else{
                        System.out.println("That board is already won by " + stringBlue + big.choose(subRow, subCol).winner() + stringReset + ". Try again." );
                    }
                    System.out.print("Choose sub-board row (1-3): ");
                    subRow = input.nextInt();
                    System.out.print("Choose sub-board col (1-3): ");
                    subCol = input.nextInt();

                }
                System.out.println("Currently in Board (" + subRow + ", " + subCol + ")");
            }

            //Moving inside the sub-board.
            System.out.print("Choose cell row (1-3) ");
            int cellRow = input.nextInt();
            System.out.print("Choose cell col (1-3) ");
            int cellCol = input.nextInt();

            //if the move is a success, set success to true
            boolean success = big.move(cellRow, cellCol, game.getTurn());

            //if the move is not a success, ask the player to move somewhere else
            while(!success){
                System.out.println("Invalid move. Try again.");
                System.out.print("Choose cell row (1-3) ");
                cellRow = input.nextInt();
                System.out.print("Choose cell col (1-3) ");
                cellCol = input.nextInt();
                success = big.move(cellRow, cellCol, game.getTurn());
            }
            big.move(cellRow, cellCol, game.getTurn());
            //switch the turn;
            game.switchTurn();

            //set the board to the corresponding grid chosen by the last player
            subRow = cellRow;
            subCol = cellCol;
            x++;

            //clear the console
            System.out.println("\033c");
            System.out.flush();
        }

        //print the final board
        System.out.println("\nFinal board:");
        System.out.println(big);
        String winner = big.overallWinner();

        //tell who the winner is
        if(!winner.equals("No Winner")){
            if ( winner.equals("X")){
                System.out.println("Player " + stringRed +  winner + stringReset + " wins the game!");
            }
            else{
                System.out.println("Player " + stringBlue +  winner + stringReset + " wins the game!");
            }
        }
        else{
            System.out.println("It's a draw!");
        }

    }

}