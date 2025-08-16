package ITEC2150.astest1.astest11;

public abstract class Employee {
    String level;
    String name;
    Employee(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
    public void setLevel(String l) {
        level = l;
    }
    public String getLevel() {
        return level;
    }
    // put your code here for abstract method jobDescription() 
    // that returns String

    public String jobDescription(){
        return "";
    }
}
