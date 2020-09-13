//the main plinko board class
class PlinkoBoardMain {

    //the main method
    public static void main(String[] args) {

        //try statement (if it fails then the user gave incorrect arguments)
        try {

            //validate the inputs by converting them from strings to integers
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
            Integer.parseInt(args[2]);
        
        //the user gave incorrect arguments
        } catch (Exception e) {

            //tell the user that there is an issue
            System.out.println("There was an error processing your input.");
            System.out.println("The program should be run by doing \"java PlinkoBoardMain [width] [height] [ball_starting_x_coordinate]\"");

            //exit the program with a non 0 exit code to indicate a failure
            System.exit(1);
        }

        //create a new plinko board
        PlinkoBoard plinkoBoard = new PlinkoBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        //clear the screen
        plinkoBoard.clearScreen();

        //print out the plinko board (in the starting position)
        plinkoBoard.print();

        //continue looping while the plinko boards advance function returns false (false means that its life cycle isnt complete)
        while (!plinkoBoard.advanceTimelineOneStep()) {

            //clear the screen
            plinkoBoard.clearScreen();

            //print the current state of the board
            plinkoBoard.print();

            //sleep for x milliseconds
            try {Thread.sleep(500);} catch (Exception e) {}
        }

        //get the x coordinate of the circle
        int circleFinalXCoordinate = plinkoBoard.getPos()[0];

        //print the final position
        System.out.println("Final position: " + circleFinalXCoordinate);
    }
}
