package astest1.astest12;

public class TestMain12 {
    public static void main(String[] args) {
        BOGO milk = new BOGO("Horizon milk");
        MultiBuys chips = new MultiBuys("Pringles");

        PromotionItem pi = milk;
        System.out.print(pi.getName() + " is on sale as ");
        pi.howItWorks();

        pi = chips;
        System.out.print(pi.getName() + " is on sale as ");
        pi.howItWorks();

        System.out.println("\nCreated by: Ajitesh Sandhu");
        System.out.println("Created on: 6/15/2025");
    }
}
