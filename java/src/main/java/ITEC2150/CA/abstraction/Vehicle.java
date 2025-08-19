package ITEC2150.CA.abstraction;

/**
 * The type Vehicle.
 */
public abstract class Vehicle {
    String name;

    /**
     * Instantiates a new Vehicle. * * @param n of type String
     */
    Vehicle(String n){
        name = n;
    }

    /**
     * Display name
     */
    void displayName(){
        System.out.println(name);
    }

    /**
     * Drive
     */
    void drive(){
        System.out.print("I am ");
    }
}
