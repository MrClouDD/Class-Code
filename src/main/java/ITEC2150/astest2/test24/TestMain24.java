package ITEC2150.astest2.test24;
// you can add your package information here


// do not modify the code below:
public class TestMain24 {
    public static void main(String[] args) {
        Person[] data = new Person[5];
        data[0] = new Person("James");
        data[1] = new Person("Jill");
        data[2] = new Person("Tilly");
        data[3] = new Person("Felix");
        data[4] = new Person("Simmons");

        InsertionSort s = new InsertionSort(data);
        s.sort();

        System.out.println("=== output ===");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i].getName() + " ");
        }
        System.out.println("\n=============");
        
        // Print author information
        System.out.println("Created by Ajitesh Sandhu");
    }    
}
