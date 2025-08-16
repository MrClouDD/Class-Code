package ITEC2150.astest2.test24;
// you can add your package information here


// do not modify the code below:
public class Person {
    private String name;
    Person(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
    public boolean isGreaterThan(Person p) {
        if (name.compareTo(p.getName()) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
