public class Board{

    //grid class to be made;
    private Grid[][] g;

    //changing the color
    private static final String stringReset = "\u001B[0m";
    private static final String stringRed = "\u001B[31m";
    private static final String stringBlue = "\u001B[36m";


    //default constructor that declares each grid in the board
    public Board(){
        g = new Grid[3][3];
        for ( int x = 0; x < g.length; x++ ){
            for ( int y = 0; y < g[0].length; y++ ){
                g[x][y] = new Grid();
            }
        }
    }


    //moves board if row and col are within the range of 1 to 3, and xo is either X or O.
    //returns true if the move was successful, false otherwise
    public boolean move( int row, int col, String xo ){
        xo = xo.toUpperCase();
        if ( row >= 1 && row <= 3 && col >= 1 && col <= 3 ){
            if ( g[row-1][col-1].value().equals("-") ){
                if ( xo.equals("X") ){
                    g[row-1][col-1].setValue( stringRed + "X" + stringReset );
                    return true;
                }
                else if ( xo.equals("O") ){
                    g[row-1][col-1].setValue( stringBlue + "O" + stringReset );
                    return true;
                }
            }
        }
        return false;
    }

    //find who the winner of the board is
    //If there is a winner, set the entire board to the sign of the winner( X or O ), and return who won the board
    //if there is no winner return "No Winner"

    public String winner(){
        int xHorizontal = 0;
        int oHorizontal = 0;
        int xVertical = 0;
        int oVertical = 0;
        int xDiagonal = 0;
        int oDiagonal = 0;
        for ( int x = 0; x < g.length; x++ ){
            for ( int y = 0; y < g[0].length; y++ ){
                if ( g[y][x].value().equals(stringRed + "X" + stringReset ) ){
                    xVertical++;
                }
                else if ( g[y][x].value().equals(stringBlue + "O" + stringReset ) ){
                    oVertical++;
                }
                if ( xVertical == 3 ){
                    for ( int a = 0; a < g.length; a++ ){
                        for ( int b = 0; b < g[0].length; b++ ){
                            g[a][b].setValue( stringRed + "X" + stringReset );
                        }
                    }
                    return "X";
                }
                else if ( oVertical == 3 ){
                    for ( int a = 0; a < g.length; a++ ){
                        for ( int b = 0; b < g[0].length; b++ ){
                            g[a][b].setValue( stringBlue + "O" + stringReset );
                        }
                    }
                    return "O";
                }
                if ( g[x][y].value().equals(stringRed + "X" + stringReset ) ){
                    xHorizontal++;
                }
                else if ( g[x][y].value().equals(stringBlue + "O" + stringReset ) ){
                    oHorizontal++;
                }
                if ( xHorizontal == 3 ){
                    for ( int a = 0; a < g.length; a++ ){
                        for ( int b = 0; b < g[0].length; b++ ){
                            g[a][b].setValue( stringRed + "X" + stringReset );
                        }
                    }
                    return "X";
                }
                else if ( oHorizontal == 3 ){
                    for ( int a = 0; a < g.length; a++ ){
                        for ( int b = 0; b < g[0].length; b++ ){
                            g[a][b].setValue( stringBlue + "O" + stringReset );
                        }
                    }
                    return "O";
                }
            }
            xHorizontal = 0;
            oHorizontal = 0;
            xVertical = 0;
            oVertical = 0;
        }
        for ( int x = 0; x < g.length; x++ ){
            if ( g[x][x].value().equals(stringRed + "X" + stringReset ) ){
                xDiagonal++;
            }
            else if ( g[x][x].value().equals(stringBlue + "O" + stringReset ) ){
                oDiagonal++;
            }
        }
        if ( xDiagonal == 3 ){
            for ( int x = 0; x < g.length; x++ ){
                for ( int y = 0; y < g[0].length; y++ ){
                    g[x][y].setValue( stringRed + "X" + stringReset );
                }
            }
            return "X";
        }
        else if ( oDiagonal == 3 ){
            for ( int x = 0; x < g.length; x++ ){
                for ( int y = 0; y < g[0].length; y++ ){
                    g[x][y].setValue( stringBlue + "O" + stringReset );
                }
            }
            return "O";
        }
        xDiagonal = 0;
        oDiagonal = 0;

        int num = 0;
        for ( int x = g.length-1; x >= 0; x-- ){
            if ( g[x][num].value().equals(stringRed + "X" + stringReset ) ){
                xDiagonal++;
            }
            else if ( g[x][num].value().equals(stringBlue + "O" + stringReset ) ){
                oDiagonal++;
            }
            num++;
        }
        if ( xDiagonal == 3 ){
            for ( int x = 0; x < g.length; x++ ){
                for ( int y = 0; y < g[0].length; y++ ){
                    g[x][y].setValue( stringRed + "X" + stringReset );
                }
            }
            return "X";
        }
        else if ( oDiagonal == 3 ){
            for ( int x = 0; x < g.length; x++ ){
                for ( int y = 0; y < g[0].length; y++ ){
                    g[x][y].setValue( stringBlue + "O" + stringReset );
                }
            }
            return "O";
        }
        return "No Winner";

    }

    //return the grid at the position row, col of the board
    public Grid getGrid( int row, int col ){
        return g[row][col];
    }

    //returns a string of the 3x3 board
    public String toString(){
        String str = "";
        for ( int x = 0; x < g.length; x++ ){
            for ( int y = 0; y < g[0].length; y++ ){
                str += g[x][y].value() + " ";
            }
            str += "\n";
        }
        return str;
    }

}
