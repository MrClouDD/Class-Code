package generics;

/**
 * TestMain class to demonstrate the capabilities of the GArray generic array class.
 * Shows usage with multiple types including Integer, String, and custom objects.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-10
 */
public class TestMain {
    
    /**
     * Simple Person class for testing custom objects
     */
    static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }
        
        @Override
        public int hashCode() {
            return name.hashCode() + age;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== GARRAY DEMONSTRATION ===\n");
        
        // Test with Integer arrays
        testIntegerArray();
        
        // Test with String arrays
        testStringArray();
        
        // Test with custom objects
        testPersonArray();
        
        // Test array operations
        testArrayOperations();
        
        System.out.println("\n=== ALL TESTS COMPLETED ===");

        // Print signature
        System.out.println("Created by Ajitesh Sandhu");
    }
    
    /**
     * Demonstrates GArray functionality with Integer type
     */
    private static void testIntegerArray() {
        System.out.println("--- INTEGER ARRAY TESTING ---");
        
        // Create integer array using constructor with type and size
        GArray<Integer> intArray = new GArray<>(Integer.class, 5);
        
        // Fill with values
        for (int i = 0; i < intArray.getSize(); i++) {
            intArray.setFromIndex(i, i * 10);
        }
        
        System.out.println("Original array: " + intArray);
        System.out.println("Array size: " + intArray.getSize());
        System.out.println("Is empty: " + intArray.isEmpty());
        System.out.println("Element at index 2: " + intArray.getFromIndex(2));
        System.out.println("Last element: " + intArray.getLast());
        
        // Test contains and indexOf
        System.out.println("Contains 30: " + intArray.contains(30));
        System.out.println("Index of 30: " + intArray.indexOf(30));
        
        // Test iterator
        System.out.print("Iterating through array: ");
        for (Integer value : intArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Test reverse
        Integer[] reversed = intArray.reverse();
        System.out.println("Reversed array: " + java.util.Arrays.toString(reversed));
        
        // Test resize
        Integer[] resized = intArray.resize(3);
        System.out.println("Resized to 3: " + java.util.Arrays.toString(resized));
        
        System.out.println();
    }
    
    /**
     * Demonstrates GArray functionality with String type
     */
    private static void testStringArray() {
        System.out.println("--- STRING ARRAY TESTING ---");
        
        // Create string array using custom array constructor
        String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};
        GArray<String> stringArray = new GArray<>(fruits);
        
        System.out.println("Original string array: " + stringArray);
        
        // Test fill operation
        GArray<String> testFill = new GArray<>(String.class, 3);
        testFill.fill("Hello");
        System.out.println("Filled array: " + testFill);
        
        // Test swap
        stringArray.swap(0, 4);
        System.out.println("After swapping indices 0 and 4: " + stringArray);
        
        // Test copy operations
        GArray<String> copiedArray = stringArray.copy();
        System.out.println("Copied array: " + copiedArray);
        System.out.println("Arrays are equal: " + stringArray.equals(copiedArray));
        
        // Test copyValue
        stringArray.copyValue(1, 3);
        System.out.println("After copying value from index 1 to 3: " + stringArray);
        
        // Test clear
        testFill.clear();
        System.out.println("After clearing: " + testFill);
        
        System.out.println();
    }
    
    /**
     * Demonstrates GArray functionality with custom Person objects
     */
    private static void testPersonArray() {
        System.out.println("--- PERSON ARRAY TESTING ---");
        
        // Create array of Person objects
        GArray<Person> personArray = new GArray<>(Person.class, 4);
        
        // Fill with Person objects
        personArray.setFromIndex(0, new Person("Alice", 25));
        personArray.setFromIndex(1, new Person("Bob", 30));
        personArray.setFromIndex(2, new Person("Charlie", 35));
        personArray.setFromIndex(3, new Person("Diana", 28));
        
        System.out.println("Person array: " + personArray);
        
        // Test contains with custom object
        Person searchPerson = new Person("Bob", 30);
        System.out.println("Contains Bob (30): " + personArray.contains(searchPerson));
        
        // Test indexOf with custom object
        System.out.println("Index of Bob (30): " + personArray.indexOf(searchPerson));
        
        // Test isPredefinedClass
        System.out.println("Is Person predefined class: " + personArray.isPredefinedClass(searchPerson));
        
        // Test hashCode
        System.out.println("Array hash code: " + personArray.hashCode());
        
        System.out.println();
    }
    
    /**
     * Demonstrates various array operations and edge cases
     */
    private static void testArrayOperations() {
        System.out.println("--- ARRAY OPERATIONS TESTING ---");
        
        // Test with Double array
        GArray<Double> doubleArray = new GArray<>(Double.class, 4);
        doubleArray.setFromIndex(0, 3.14);
        doubleArray.setFromIndex(1, 2.71);
        doubleArray.setFromIndex(2, 1.41);
        doubleArray.setFromIndex(3, 1.73);
        
        System.out.println("Double array: " + doubleArray);
        
        // Test predefined class detection
        System.out.println("Is Double predefined: " + doubleArray.isPredefinedClass(3.14));
        
        // Test with Boolean array
        GArray<Boolean> boolArray = new GArray<>(Boolean.class, 3);
        boolArray.fill(true);
        System.out.println("Boolean array filled with true: " + boolArray);
        
        // Test empty array
        GArray<String> emptyArray = new GArray<>(String.class, 0);
        System.out.println("Empty array size: " + emptyArray.getSize());
        System.out.println("Empty array is empty: " + emptyArray.isEmpty());
        
        // Test equality
        GArray<Integer> array1 = new GArray<>(Integer.class, 2);
        GArray<Integer> array2 = new GArray<>(Integer.class, 2);
        
        array1.setFromIndex(0, 10);
        array1.setFromIndex(1, 20);
        array2.setFromIndex(0, 10);
        array2.setFromIndex(1, 20);
        
        System.out.println("Array1: " + array1);
        System.out.println("Array2: " + array2);
        System.out.println("Arrays are equal: " + array1.equals(array2));
        
        // Test with different values
        array2.setFromIndex(1, 30);
        System.out.println("After changing array2[1] to 30:");
        System.out.println("Arrays are equal: " + array1.equals(array2));
        
        // Test Character array
        GArray<Character> charArray = new GArray<>(Character.class, 5);
        charArray.setFromIndex(0, 'H');
        charArray.setFromIndex(1, 'e');
        charArray.setFromIndex(2, 'l');
        charArray.setFromIndex(3, 'l');
        charArray.setFromIndex(4, 'o');
        
        System.out.println("Character array: " + charArray);
        
        // Test reverse on character array
        Character[] reversedChars = charArray.reverse();
        System.out.println("Reversed characters: " + java.util.Arrays.toString(reversedChars));
        
        System.out.println();
    }
}
