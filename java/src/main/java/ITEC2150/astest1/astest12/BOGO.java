package ITEC2150.astest1.astest12;
// modify the code below to use Week4.astest12.PromotionItem as its super class
// implement its constructor using the Week4.astest12.PromotionItem constructor
// and howItWorks() method to print the message "buy one and get one free."

public class BOGO extends PromotionItem{
    BOGO(){
        super("");
    }

    BOGO(String n){
        super(n);
    }

    @Override
    public void howItWorks() {
        System.out.println("buy one get one free.");
    }
}
