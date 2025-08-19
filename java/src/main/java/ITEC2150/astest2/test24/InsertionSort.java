package ITEC2150.astest2.test24;
// you can put your package information here

/**
 * Insertion sort implementation for Person objects
 * @author Ajitesh Sandhu
 */
// Complete the InsertionSort class below
// The class should have the class constructor and sort() method.
//
// *** hint ***
// When comparing the order of two Person class objects, 
// use the isGreaterThan() method in the Person class to check.
// The isGreaterThan() method will return true, if the person object is
// greater than the person object in the parameter.
// For example, 
// Person p1 = new Person("A");
// Person p2 = new Person("B");
// p1.isGreaterThan(p2) will return false;
// p2.isGreaterThan(p1) will return true;

public class InsertionSort {
    private Person[] data; // Array of Person objects to sort
    
    /**
     * Constructor that takes an array of Person objects
     * @param data Array of Person objects to be sorted
     */
    InsertionSort(Person[] data) {
        this.data = data;
    }
    
    /**
     * Sorts the array of Person objects using insertion sort algorithm
     * Sorts in ascending order (A to Z) based on person names
     */
    public void sort() {
        // Insertion sort algorithm
        for (int i = 1; i < data.length; i++) {
            Person key = data[i]; // Current person to be positioned
            int j = i - 1;
            
            // Move elements that are greater than key one position ahead
            // Use isGreaterThan() to compare Person objects
            while (j >= 0 && data[j].isGreaterThan(key)) {
                data[j + 1] = data[j]; // Shift element to the right
                j = j - 1;
            }
            data[j + 1] = key; // Insert key at correct position
        }
    }
}
