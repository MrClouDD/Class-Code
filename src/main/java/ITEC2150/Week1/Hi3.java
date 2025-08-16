package ITEC2150.Week1;


/**
 * Hi with custom name and message
 *
 * @author Ajitesh Sandhu created this on 2025-05-27
 */
public class Hi3 {

    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        String message = "Hi";
        String name = "James";
        greetingWithMessageName(message, name);
        System.out.println("Ajitesh Sandhu");

    }


    /**
     * Greeting with message name
     *
     * @param m of type String
     * @param n of type String
     */
    static void greetingWithMessageName(String m, String n){
        System.out.printf("%s %s!\n", m, n);
    }
}
