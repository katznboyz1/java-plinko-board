//import the random class
import java.util.Random;

//the plinko board class (the actual board class, the main class is just the controller)
class PlinkoBoard {

    //the 2d array that will make up the board
    private char[][] plinkoBoardArray;

    //the size of the board
    private int[] plinkoBoardSize = new int[2];

    //the starting starting position
    private int[] plinkoBoardCircleCoordinates = new int[2];

    //whether or not the plinko boards process is complete
    private boolean done = false;

    //the random number generator
    private Random randomNumberGenerator = new Random();

    //method to get the position of the circle
    int[] getPos() {

        //return the position of the circle
        return plinkoBoardCircleCoordinates;
    }

    //method to clear the screen (https://stackoverflow.com/a/32295974)
    void clearScreen() {  

        //print out the clear screen ansii escape codes
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    //the plinko board class initializer
    PlinkoBoard(int width, int height, int startingPosition) {
        
        //create the board
        plinkoBoardArray = new char[height][width];
        
        //set the size (to save on counting later)
        plinkoBoardSize[0] = width;
        plinkoBoardSize[1] = height;

        //set the position of the circle
        plinkoBoardCircleCoordinates[0] = startingPosition;
        plinkoBoardCircleCoordinates[1] = 0;

        //generate the board
        //loop through each row
        for (int row = 0; row < plinkoBoardSize[1]; row++) {
            
            //loop through each column
            for (int column = 0; column < plinkoBoardSize[0]; column++) {

                //the value that will fill this spot
                char currentCoordinateValue = (
                    (column % 2 == 0 && row % 2 == 0 && row % 4 != 0)
                    || 
                    (column % 2 == 1 && row % 4 == 0)
                ) ? '^' : ' ';

                //set the coordinate to the desired value
                plinkoBoardArray[row][column] = currentCoordinateValue;
            }
        }
    }

    //a function to print a bar, which could be used as the top or bottom of the board
    void printBar() {

        //print the bar
        for (int i = 0; i < plinkoBoardSize[0] + 2; i++) {System.out.print("\u2588");}
        System.out.print("\n");
    }

    //a function to advance the boards timeline by one step
    boolean advanceTimelineOneStep() {

        //increase the y position of the circle (make it fall)
        plinkoBoardCircleCoordinates[1]++;

        //check if the circle has reached the bottom of the board
        if (plinkoBoardCircleCoordinates[1] >= plinkoBoardSize[1]) {

            //the board is done
            done = true;
        
        //the circle hasnt reached the bottom
        } else {

            //check if the circle is touching an obstacle
            if (plinkoBoardArray[plinkoBoardCircleCoordinates[1]][plinkoBoardCircleCoordinates[0]] != ' ') {
                
                //decide whether the circle will move left or right 
                //generate a number between 0 and 1, if it is , move to the left, if it is 1, move to the right
                plinkoBoardCircleCoordinates[0] += randomNumberGenerator.nextInt(2) == 0 ? -1 : 1;
            }

            //check that the circle isnt touching/outside of the boards bounds
            //check if its outside of the left wall
            if (plinkoBoardCircleCoordinates[0] <= 0) {

                //add two to make it go in the opposite direction
                plinkoBoardCircleCoordinates[0] += 2;
            
            //check if its outside the right wall
            } else if (plinkoBoardCircleCoordinates[0] > plinkoBoardSize[0]) {

                //subtract two to make it go in the opposite direction
                plinkoBoardCircleCoordinates[0] -= 2;
            }
        }

        //return whether or not the board is done doing its thing
        return done;
    }

    //the method to print the board
    void print() {

        //print the top of the board
        printBar();

        //loop through each row in the board
        for (int row = 0; row < plinkoBoardArray.length; row++) {

            //print the side of the row (using full block character)
            System.out.print("\u2588");
            
            //loop through each column in the row
            for (int column = 0; column < plinkoBoardArray[row].length; column++) {

                //the character that will be printed
                char currentCoordinatePrintValue = ((row == plinkoBoardCircleCoordinates[1]) && (column == plinkoBoardCircleCoordinates[0])) ? 'O' : plinkoBoardArray[row][column];

                //print the column
                System.out.print(currentCoordinatePrintValue);
            }

            //print the other side of the row make a new line
            System.out.print("\u2588\n");
        }

        //print out the bottom of the board
        printBar();
    }
}