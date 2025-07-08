package Week2;

/**
 * Student
 *
 * @author Ajitesh Sandhu created this on 2025-05-29
 */
public class Student {
    private String name, streetAddress;
    private double score;

    /**
     * Instantiates a new Student.
     */
    Student(){
        this.name = "";
        this.score = 0;
        this.streetAddress = "";
    }

    /**
     * Instantiates a new Student. * * @param name of type PsiType:String * @param score of type PsiType:double * @param streetAddress of type PsiType:String
     */
    Student(String name, double score, String streetAddress){
        this.name = name;
        this.score = score;
        this.streetAddress = streetAddress;
    }

    /**
     * Gets name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets street address.
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets street address.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Gets score.
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets score.
     */
    public void setScore(double score) {
        this.score = score;
    }
}
