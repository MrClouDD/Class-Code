package ITEC2150.astest1.astest11;

// modify the code below to use Employee class as its super class 
// and implement jobDescription() method

public class Manager extends Employee{
    Manager(String n) {
        super(n);
        super.setLevel("M");
    }

    @Override
    public String jobDescription() {
        return "managing people";
    }
}
