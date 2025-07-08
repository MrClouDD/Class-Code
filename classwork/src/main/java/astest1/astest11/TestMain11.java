package astest1.astest11;

class TestMain11 {
    public static void main(String[] args) {
        Manager m1 = new Manager("Pepper Potts");
        HourlyEmployee e1 = new HourlyEmployee("Natalie Rushman");

        System.out.println("Hi! My name is " + m1.getName() + ".");
        System.out.println("My job is " + m1.jobDescription() + ".");

        System.out.println("Hi! My name is " + e1.getName() + ".");
        System.out.println("My job is " + e1.jobDescription() + ".");

        System.out.println("\nCreated by: Ajitesh Sandhu");
        System.out.println("Created on: 6/15/2025");
    }
}
