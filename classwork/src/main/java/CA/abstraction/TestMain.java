package CA.abstraction;

/**
 * Test main
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class TestMain {

    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        Car car = new Car("Honda Odyssey");
        Bicycle bicycle = new Bicycle("Bicycle");

        Vehicle vehicle1 = bicycle;
        Vehicle vehicle2 = car;

        divider("-", 10);
        divider("=", 15);

        bicycle.displayName();
        bicycle.drive();

        divider("=", 15);
        car.displayName();
        car.drive();

        divider("=", 15);
        vehicle1.displayName();
        vehicle1.drive();

        divider("=", 15);
        vehicle2.displayName();
        vehicle2.drive();
    }

    /**
     * Divider
     *
     * @param div    of type String
     * @param length of type int
     */
    static void divider(String div, int length){
        System.out.printf("%s\n", div.repeat(length));
    }
}
