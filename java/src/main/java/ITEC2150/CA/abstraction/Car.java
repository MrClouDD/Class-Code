package ITEC2150.CA.abstraction;

/**
 * Car
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class Car extends Vehicle{

    /**
     * Instantiates a new Car. * * @param n of type String
     */
    Car(String n){
        super(n);
    }

    @Override
    void displayName() {
        System.out.print("This is a ");
        super.displayName();
    }

    @Override
    void drive() {
        super.drive();
        System.out.println("driving a car");
    }
}
