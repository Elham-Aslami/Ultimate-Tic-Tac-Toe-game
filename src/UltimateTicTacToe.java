//This class controls whose turn it is in the Ultimate Tic-Tac-Toe game.
public class UltimateTicTacToe{

    //'turn' is true whe it's X's turn, and false when it's O's turn.
    private boolean turn;

    //Constructor to start the game by default with X's turn.
    public UltimateTicTacToe(){
        turn = true;
    }

    //This method returns whose turn it is right now("x" or "o").
    public String getTurn(){
        if(turn){
            return "X";
        }else{
            return "O";
        }
    }

    //This method switches the turn from X to O or from O to X.
    public void switchTurn(){
        turn = !turn;
    }
}