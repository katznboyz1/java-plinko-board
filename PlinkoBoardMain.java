//the main plinko board class
class PlinkoBoardMain {

    //the main method
    public static void main(String[] args) {

        //the size of the plinko board
        final int[] plinkoBoardSize = {40, 40}; 

        //create a new plinko board
        PlinkoBoard plinkoBoard = new PlinkoBoard(plinkoBoardSize[0], plinkoBoardSize[1], plinkoBoardSize[0] / 2);

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