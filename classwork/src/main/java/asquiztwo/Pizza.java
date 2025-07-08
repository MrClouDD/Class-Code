package asquiztwo;

/**
 * Pizza
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
// update the code below to use FoodItem class as its super class
public class Pizza extends FoodItem{
    int size;

    /**
     * Instantiates a new Pizza. * * @param n of type String
     *
     * @param p of type double
     * @param s of type int
     */
    Pizza(String n, double p, int s) {
        super(n, p);
        size = s;
    }

    // put your code here for prepare() method.
    // The prepare() method should display "Preparing pizza with a size of " + size + " inches."
    // as shown in the example execution output


    @Override
    void prepare() {
        super.prepare();
        System.out.println("pizza with a size of " + size + " inches.");
    }
}
