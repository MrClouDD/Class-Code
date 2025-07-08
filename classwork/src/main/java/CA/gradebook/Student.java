package CA.gradebook;

/**
 * Student subclass of Person representing a student with a score.
 * Manages name, address, and numeric score for grade calculations.
 * 
 * @author Ajitesh Sandhu
 */
public class Student extends Person implements Gradable{
    int score;

    public Student() {
        this.setName("John", "Doe");
        this.score = -1;  
    }
    
    public Student(String name, int score, String streetAddress){
        String[] names = name.split("\\s");
        this.setName(names[0], names.length > 1 ? names[1] : "");
        this.setStreetAddress(streetAddress);
        this.score = score;
    }

    // Get/Set name and street address are defined in Person
    
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
    
    
}
