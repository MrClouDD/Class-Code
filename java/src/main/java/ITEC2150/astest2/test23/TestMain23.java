package ITEC2150.astest2.test23;
// you can add your package information here


// do not modify the code below:
public class TestMain23 {
    public static void main(String[] args) {
        PersonStack ps = new PersonStack();
        ps.push(new Person("James"));
        ps.push(new Person("Jill"));
        ps.push(new Person("Felix"));
        ps.push(new Person("Simmons"));
        System.out.println("===== output =====");
        while (!ps.isEmpty()) {
            System.out.println(ps.pop().getName() + " ");
        }
        
        // Print author information
        System.out.println("Created by Ajitesh Sandhu");
    }
}
