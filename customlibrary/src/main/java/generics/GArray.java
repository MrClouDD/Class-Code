package generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;


@SuppressWarnings({"unchecked", "unused"})
public class GArray<T> implements Iterable<T>{
    private T[] array;
    
    GArray(T[] customArray) {
        this.array = customArray;
    }
    
    
    // Call constructor with GArray(type, size)
    GArray(Class<T> type, int size){
        if (type == null){
            throw new IllegalArgumentException("Class type must not be null");
        } else if (!Object.class.isAssignableFrom(type)){
            throw new IllegalArgumentException("Type must be an object. Use wrappers for primitives if neccessary.");
        } else {
            this.array = (T[]) Array.newInstance(type, size);
        }
    }

    private void ensureIndexBounds(int index){
        if (index > 0 && index < array.length){
            throw new ArrayIndexOutOfBoundsException("Index must be between 0 and " + (index - 1));
        }
    }

    private void ensureNonNullity(T value){
        if (value == null){
            throw new IllegalArgumentException("Value must not be null");
        }
    }

    // Returns the array's size
    int getSize(){
        return this.array.length;
    }

    // Returns if the array is empty
    boolean isEmpty(){
        return this.array.length == 0;
    }

    // Returns the generic array
    GArray<T> get(){
        return this;
    }

    // Returns the stored array parameter
    T[] getArray(){
        return this.array;
    }

    // Returns the value given an index
    T getFromIndex(int index){
        ensureIndexBounds(index);
        return this.array[index];
    }

    // Sets the generic array with a provided generic array object
    void setArray(T[] customArray){
        this.array = customArray;
    }
    
    // Sets an index with a value
    void setFromIndex(int index, T value){
        ensureIndexBounds(index);
        //ensureNonNullity(value);
        this.array[index] = value;
    }

    // Checks if the array contains the value
    boolean contains(T value) {
        ensureNonNullity(value);
        for (T element: this.array){
            if (element.equals(value)){
                return true;
            }
        }
        return false;
    }

    // Returns the first index of a value (if it exists)
    int indexOf(T value){
        ensureNonNullity(value);
        for(int i = 0; i < this.array.length; i++){
            if (this.array[i] == value)
                return i;
        }
        return -1;
    }

    // Returns the last value
    T getLast(){
        return getFromIndex(this.array.length - 1);
    }

    // Copies the generic array
    GArray<T> copy() {
        return new GArray<>(this.array);
    }

    // Copies the array parameter 
    T[] copyArray(){
        return Arrays.copyOf(this.array, this.array.length);
    }

    // Copies value of one index to another
    void copyValue(int referenceIndex, int overwrittenIndex){
        setFromIndex(overwrittenIndex, getFromIndex(referenceIndex));
    }

    // Array for predefined classes
    private static final Class<?>[] classesArray = {
            Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Character.class, Boolean.class, String.class
        };  

    // Check if the value's class is predefined by Java
    boolean isPredefinedClass(T value){
        ensureNonNullity(value);
        
        for (Class<?> c: classesArray){
            if (value.getClass() == c){
                return true;
            }
        }
        return false;
    }

    // Fills the entire array with a value
    void fill(T value) {
        if (isPredefinedClass(value)){
            Arrays.fill(array, value);
        } else {
            for (int i = 0; i < array.length; i++){
                setFromIndex(i, value);
            }
        }
    }
    
    // Fills the array with null
    void clear(){
        fill(null);
    }

    // Swaps 2 indexes
    void swap(int index1, int index2){
        ensureIndexBounds(index1);
        ensureIndexBounds(index2);

        T temp = getFromIndex(index1);
        setFromIndex(index1, getFromIndex(index2));
        setFromIndex(index2, temp);
    }

    // Custom iterator
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private int index = 0;

            @Override
            public boolean hasNext(){
                return index < array.length;
            }

            @Override
            public T next(){
                if (!hasNext()){
                    throw new IllegalStateException("No more elements");
                }
                return getFromIndex(index++);
            }
        };
    }
    
    // TODO: Add a sort method that accepts a Comparator for custom sorting.
    // TODO: Add a sort method that accepts a String for predefined sorting methods.
    // TODO: Implement a resize method to dynamically change the array size.
    // TODO: Add a reverse method to reverse the array elements.
    // TODO: Implement an equality check method to compare two GArray objects.

    /*
    Common Sorting Comparators for String Functions:

    1. "ascending": Sorts elements in natural order (e.g., alphabetical for strings).
    2. "descending": Sorts elements in reverse natural order.
    3. "byLength": Sorts strings by their length in ascending order.
    4. "byLengthDescending": Sorts strings by their length in descending order.
    5. "caseInsensitive": Sorts strings alphabetically, ignoring case.
    6. "reverseCaseInsensitive": Sorts strings in reverse alphabetical order, ignoring case.
    7. "alphabeticalIgnoreSpecialChars": Sorts strings alphabetically while ignoring special characters.
    8. "numericValue": Sorts strings based on their numeric value if they represent numbers.
    9. "localeSensitive": Sorts strings based on locale-specific rules.
    10. "customPattern": Sorts strings based on a custom regex pattern match.
    11. "frequency": Sorts strings by their frequency of occurrence in the array.
    12. "vowelCount": Sorts strings by the number of vowels they contain.
    13. "startsWith": Sorts strings based on whether they start with a specific prefix.
    14. "endsWith": Sorts strings based on whether they end with a specific suffix.
    15. "containsSubstring": Sorts strings based on whether they contain a specific substring.
    */

    void sort(Comparator<T> comparator){
        Arrays.sort(this.array, comparator);
    }
    
    private final static String[] sortingStrings = {
        "ascending", "descending", "byLength", "byLengthDescending", "caseInsensitive", "reverseCaseInsensitive",
        "numericValue", "localeSensitive", "frequency", "vowelCount", "startsWith", "endsWith"
    };

    // Create and assign the predefined number sorting methods
    // private static Map<String, Comparator<? super Number>> numberSortingMap = Map.of(
    //         "ascending", Comparator.naturalOrder(),
    //         "descending", Comparator.reverseOrder()
    //     ); 

    
    void sort(String comparatorString){
        Object firstElement = this.array[0];

        if (firstElement instanceof Integer){
            
        }
    }

    void sortNumbers(String comparatorString){
    }
}