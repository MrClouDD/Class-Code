package ITEC2150.astest1.astest12;

public class PromotionItem {
    String name;
    PromotionItem(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
    public void howItWorks() {
        System.out.println("discounted");
    }
}
