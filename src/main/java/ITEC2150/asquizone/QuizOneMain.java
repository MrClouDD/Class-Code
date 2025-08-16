package ITEC2150.asquizone;

/**
 * Quiz one main
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class QuizOneMain {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        UzumakiClan naruto = new UzumakiClan("Naruto Uzumaki");
        System.out.print("My name is ");
        naruto.displayName();
        System.out.print("I am in ");
        naruto.displayClan();

        UchihaClan sasuke = new UchihaClan("Sasuke Uchiha");
        System.out.print("My name is ");
        sasuke.displayName();
        System.out.print("I am in ");
        sasuke.displayClan();

        System.out.println("\nAjitesh Sandhu");
    }
}
