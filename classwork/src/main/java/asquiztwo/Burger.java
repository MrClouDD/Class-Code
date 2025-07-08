package asquiztwo;

/**
 * Burger
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
// update the code below to use the FoodItem class as its super class.
public class Burger extends FoodItem {
    String type;

    /**
     * Instantiates a new Burger. * * @param n of type String
     *
     * @param p of type double
     * @param t of type String
     */
    Burger(String n, double p, String t) {
        super(n, p);
        type = t;
    }
    // put your code here for prepare() method.
    // The prepare() method should display "Preparing a " + type + " burger."
    // as shown in the example execution output 


    @Override
    void prepare() {
        super.prepare();
        System.out.println("a " + type + " burger.");
    }
}
