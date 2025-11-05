/*This class represent the overall 3x3 grid of tic-tac-toe board in Ultimate Tic-tac-toe game.*/
public class BigBoard{
    //a 3x3 grid of smaller tic-tac-toe board.
    private Board[][] boards;

    //This holds the curent board that the player is playing on.
    private Board oneBoard;

    //Constructor that initializes all 9 small boards in the 3x3 grid.
    public BigBoard(){
        boards = new Board[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boards[i][j] = new Board();
            }
        }
    }

    //This method allows a player to choose one of the 9 small boards by its row and column.
    public Board choose( int row, int col ){
        if(row < 1 || row > 3 || col <1 || col > 3){
            //invalid choice, return nothing.
            return null;
        }
        //sets the chosen boardd
        oneBoard = boards[row-1][col-1];
        return oneBoard;
    }

    //this method makes a move on the current chosen small board.
    //it returns true if the move is successful and false otherwise.
    public boolean move(int r, int c, String xo){
        if(oneBoard == null)
            return false;
        return oneBoard.move(r,c,xo);
    }

    //This method checks if there is an overall winner in the big 3x3 grid.
    //checks which player won each small board and checks for 3 in a row.
    public String overallWinner(){
        String[][] miniWinners = new String [3][3];

        //collects winner from all small boards.
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                miniWinners[i][j] = boards[i][j].winner();
            }
        }

        //Check for horizontal or bertical wins in the big grid.
        for(int i = 0; i < 3; i++){
            if(miniWinners[i][0].equals(miniWinners[i][1]) && miniWinners[i][1].equals(miniWinners[i][2]) && !(miniWinners[i][0].equals("No Winner"))){
                //row win
                return miniWinners[i][0];
            }

            if(miniWinners[0][i].equals(miniWinners[1][i]) && miniWinners[1][i].equals(miniWinners[2][i]) && !(miniWinners[0][i].equals("No Winner"))){
                //column win
                return miniWinners[0][i];
            }
        }

        //checking for a diagonal win
        if(miniWinners[0][0].equals(miniWinners[1][1]) && miniWinners[1][1].equals(miniWinners[2][2]) && !miniWinners[0][0].equals("No Winner")){
            //diagonal win from top-left to bottom-right.
            return miniWinners[0][0];
        }

        if(miniWinners[0][2].equals(miniWinners[1][1]) && miniWinners[1][1].equals(miniWinners[2][0]) && !miniWinners[0][2].equals("No Winner")){
            //diagonal win from top-right to bottom-left.
            return miniWinners[0][2];
        }

        //nobody has won yet.
        return "No Winner";
    }

    //toString of the whole big board, creates the visual string of the full big board by combining all small boards.
    public String toString(){
        String str = "";

        //looping through each row of the big boards.
        for(int bigRow = 0; bigRow < 3; bigRow++){
            for(int smallRow = 0; smallRow < 3; smallRow++){

                //looping through each column of the big boards
                for(int bigCol = 0; bigCol < 3; bigCol++){
                    for(int smallCol = 0; smallCol < 3; smallCol++){
                        str += boards[bigRow][bigCol].getGrid(smallRow, smallCol).value();
                        str += " ";
                    }
                    if ( bigCol < 2 ){
                        //vertical separator between big boards.
                        str += "|";
                    }
                }
                str += "\n";
            }
            if ( bigRow < 2 ){
                //horizontal separator between big board rows.
                str +=  "------+------+-----\n";
            }
        }
        return str;
    }

    //This method checks if all the small board have a winner 
    public boolean isFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(boards[i][j].winner().equals("No Winner")){
                    return false;
                }
            }
        }
        //all board is won or tied.
        return true;
    }
}