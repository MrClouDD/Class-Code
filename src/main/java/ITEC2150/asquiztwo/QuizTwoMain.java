package ITEC2150.asquiztwo;

/**
 * Quiz two main
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class QuizTwoMain {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        Pizza p = new Pizza("Margherita Pizza", 14.99, 12);
        Burger b = new Burger("Big Mac", 5.65, "special");

        System.out.println("Pizza info:");
        p.displayDetails();
        p.prepare();

        System.out.println("Burger info:");
        b.displayDetails();
        b.prepare();

        System.out.println("\nAjitesh Sandhu");
    }
    
}
