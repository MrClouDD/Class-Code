package ITEC2150.Week1;

/**
 * Hi using custom name
 *
 * @author Ajitesh Sandhu created this on 2025-05-27
 */
public class Hi2 {

    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        String name = "James";
        greetingWithName(name);
        System.out.println("Ajitesh Sandhu");
    }

    /**
     * Greeting with name
     *
     * @param name of type String
     */
    static void greetingWithName(String name) {
        System.out.printf("Hi %s!\n", name);
    }
}
