package CA.interfaces;

/**
 * Test interface
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class TestInterface {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        RideAble r1 = new Bicycle("Bike");
        RideAble r2 = new Dinosaur("George");


        System.out.println("Bike:");
        r1.rideOn();
        System.out.println("Dinosaur:");
        r2.rideOn();
    }
}
