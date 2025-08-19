package ITEC2150.astest2.test21;
// you can add your package information below


// do not modify the code below:
import java.util.Random;

class TestMain21 {
    public static void main(String[] args) {
        int[] data = { 2, 3, 7, 8, 10, 12, 30, 32, 33, 33, 35, 35, 36, 48, 50, 78, 89, 91, 98, 99};
        BSearch s = new BSearch(data);
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int rno = rand.nextInt(100);
            int index = s.search(rno);
            if (index == -1) {
                System.out.printf("%d cannot be found!\n", rno);
            } else {
                System.out.printf("%d is found at index %d\n", rno, index);
            }
        } // end for
        // Print author information
        System.out.println("Created by Ajitesh Sandhu");
    } // end main()
} // end TestMain21

