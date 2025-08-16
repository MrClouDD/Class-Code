package ITEC2150.asquizthree;

/**
 * Quiz three main
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class QuizThreeMain {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        AnyThree<String> a1;
        AnyThree<Integer> a2;
        AnyThree<Double> a3;
        a1 = new AnyThree("one", "two", "three");
        a2 = new AnyThree(1, 2, 3);
        a3 = new AnyThree(1.1, 1.2, 1.3);

        a1.printReverse();
        a2.printReverse();
        a3.printReverse();
    } 
}
