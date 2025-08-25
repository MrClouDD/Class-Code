package ITEC3150;

/**
 * @author Ajitesh Sandhu
 * @date 8.20.2025
 */

public class towerOfHannoi {
    // Set tower height and counter
    static int TOWERSIZE = 10;
    static int counter = 1;

    // Initialize each tower to tower size
    static int[] towerA = new int[TOWERSIZE];
    static int[] towerB = new int[TOWERSIZE];
    static int[] towerC = new int[TOWERSIZE];

    public static void main(String[] args) {
        fillTower(towerA);
        solveHanoi(TOWERSIZE, towerA, towerB, towerC);
        validatePuzzleCompletion(towerB);
    }

    // Hanoi tower logic
    static void solveHanoi(int towerSize, int[] startingTower, int[] endingTower, int[] auxillaryTower) {
        if (towerSize == 1) {
            moveTopPlate(startingTower, endingTower);
            return;
        }
        solveHanoi(towerSize - 1, startingTower, auxillaryTower, endingTower);

        moveTopPlate(startingTower, endingTower);

        solveHanoi(towerSize - 1, auxillaryTower, endingTower, startingTower);

    }

    // Fill tower based on tower size
    static void fillTower(int[] tower) {
        for (int i = 0; i < TOWERSIZE; i++) {
            tower[TOWERSIZE - i - 1] = i + 1;
        }
    }

    // Move top from one tower to another
    public static void moveTopPlate(int[] moveFrom, int[] moveTo) {

        // Check if the starting tower has elements to transfer
        if (!doesTowerHavePlates(moveFrom)) {
            throw new IllegalStateException("The tower being move from has no elements");
        }

        // Get the disk to move
        int fromIndex = getTopPlateIndex(moveFrom);
        int diskToMove = moveFrom[fromIndex];

        // Determine where to place it and validate the move
        int toIndex;
        if (getTopPlateIndex(moveTo) == -1) {
            // Empty tower - place at bottom
            toIndex = 0;
        } else {
            // Non-empty tower - get current top disk and check if move is valid
            int currentTopIndex = getTopPlateIndex(moveTo);
            int currentTopDisk = moveTo[currentTopIndex];

            // Validate the move before determining position
            if (!isValidMove(diskToMove, currentTopDisk)) {
                return; // Invalid move, don't proceed
            }

            // Place one position higher than current top
            toIndex = currentTopIndex + 1;
        }

        // Move the disk
        moveTo[toIndex] = diskToMove;
        moveFrom[fromIndex] = 0;

        // Increment counter
        counter += 1;

        // Print tower
        printTower();

    }

    // Check if the finalTower follows the correct order
    public static void validatePuzzleCompletion(int[] finalTower) {
        int[] referenceTower = new int[TOWERSIZE];
        fillTower(referenceTower);
        Boolean solved = true;

        for (int i = 0; i < TOWERSIZE; i++) {
            if (finalTower[i] != referenceTower[i])
                solved = false;
        }

        if (solved) {
            System.out.printf("It took %d moves to solve the puzzle\n", counter);
        }
    }

    // Checks the highest index with a non-zero value (top of stack)
    public static int getTopPlateIndex(int[] tower) {
        for (int i = TOWERSIZE - 1; i >= 0; i--) {
            if (tower[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    // Return if the tower is empty
    public static boolean doesTowerHavePlates(int[] tower) {
        return getTopPlateIndex(tower) != -1;
    }

    // Returns true if the from is smaller than where it is going
    public static boolean isValidMove(int fromValue, int toValue) {
        // Can always place on empty tower (toValue == 0)
        if (toValue == 0) {
            return true;
        }
        // Can only place smaller disk on larger disk
        if (fromValue < toValue) {
            return true;
        }
        // Invalid move: trying to place larger disk on smaller disk
        return false;
    }

    // Print the towers in their current order
    public static void printTower() {
        System.out.printf("Iteration %d\n", counter);
        for (int i = TOWERSIZE - 1; i >= 0; i--) {
            System.out.printf("%2s %2s %2s\n",
                    towerA[i] == 0 ? "" : towerA[i],
                    towerB[i] == 0 ? "" : towerB[i],
                    towerC[i] == 0 ? "" : towerC[i]);
        }
        System.out.printf("%2s %2s %2s\n\n", "-", "-", "-");
    }
}
