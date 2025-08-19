package ITEC2150.astest1.astest12;
// modify the code below to use Week4.astest12.PromotionItem as its super class
// implement its constructor using the Week4.astest12.PromotionItem constructo
// and howItWorks() method to print "2 for the price of 1."

public class MultiBuys extends PromotionItem{
    MultiBuys(){
        super("");
    }

    MultiBuys(String n){
        super(n);
    }

    @Override
    public void howItWorks() {
        System.out.println("2 for the price of 1.");
    }
}
