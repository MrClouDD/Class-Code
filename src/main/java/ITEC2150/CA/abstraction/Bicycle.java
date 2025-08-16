package ITEC2150.CA.abstraction;

/**
 * Bicycle
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class Bicycle extends Vehicle{

    /**
     * Instantiates a new Bicycle. * * @param n of type String
     */
    Bicycle(String n){
        super(n);
    }

    @Override
    void displayName() {
        super.displayName();
    }

    @Override
    void drive() {
        super.drive();
        System.out.println("riding a bicycle");
    }
}
