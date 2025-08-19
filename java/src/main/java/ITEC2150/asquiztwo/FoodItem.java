package ITEC2150.asquiztwo;

/**
 * The type Food item.
 */
public abstract class FoodItem {
    String name;
    double price;

    /**
     * Instantiates a new Food item. * * @param n of type String
     *
     * @param p of type double
     */
    FoodItem(String n, double p) {
        name = n;
        price = p;
    }

    /**
     * Display details
     */
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
    }

    /**
     * Prepare
     */
// put your code here for the abstract method void prepare()
    void prepare(){
        System.out.print("Preparing ");
    }
}
