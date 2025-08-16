package ITEC2150.Week3;

    public class Student extends Person {
        double score;

        Student(){
            super();
            this.setScore(0);
        }

        Student(int id, String fName, String lName, double score, String streetAddress){
            super(900000000, "John Doe", "123 Main Street Atlanta GA 30043");
            this.score = score;
        }

        public double getScore() {
            return this.score;
        }

        public void setScore(double score) {
            if (score >= 0)
                this.score = score;
            else
                throw new NumberFormatException("Score must be non-negative");
        }

        @Override
        public String toString() {
            return "Name: " + getName() + "\n ID: " + getId() + " \n Street Address: " + getStreetAddress() + "\n Score: " + getScore();
        }
    }
