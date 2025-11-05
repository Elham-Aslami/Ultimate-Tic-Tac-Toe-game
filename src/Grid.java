public class Grid{
    private String xO;


    //default constructor that sets the Grid to "-"
    public Grid(){
        xO = "-";
    }

    //get the value of grid
    public String value(){
        return xO;
    }

    //set the value of grid to the value in newVal
    public void setValue(String newVal){

        xO = newVal;
    }
}