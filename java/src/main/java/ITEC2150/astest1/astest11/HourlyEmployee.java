package ITEC2150.astest1.astest11;

// modify the code below to use Employee class as its super class 
// and implement jobDescription() method

public class HourlyEmployee extends Employee{
    HourlyEmployee(String n) {
        super(n);
        super.setLevel("H");
    }

    @Override
    public String jobDescription() {
        return "work hourly";
    }
}
