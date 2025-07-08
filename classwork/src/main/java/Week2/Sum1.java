package Week2;

/**
 * Sum of all numbers between 1 and 20
 *
 * @author Ajitesh Sandhu created this on 2025-05-29
 */
public class Sum1 {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        int sum = total();
        System.out.printf("The sum of all numbers from 1 to 20 is %d!\n", sum);
        System.out.println("Ajitesh Sandhu");
    }

    /**
     * Total
     *
     * @return int
     */
    static int total(){
        int sum = 0;
        for (int i = 0; i <= 20; i++) {
            sum += i;
        }
        return sum;
    }
}
