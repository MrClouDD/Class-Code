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

    // Reverses the array
    public T[] reverse(){
        // if array has no values then return the array
        if (array.length == 0){
            return Arrays.copyOf(this.array, this.array.length);
        }
        
        // Create a new array that is the same type and size as the original
        T[] reverseArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        
        // Fill array in a reverse method
        for (int i = 0; i < this.array.length; i++) {
            reverseArray[i] = this.array[this.array.length - 1 - i];
        }   
        
        // return reversed array
        return reverseArray;
    }

    // Returns a resized array
    public T[] resize(int newSize){
        // if array has no values, return a copy of the original array
        if (array.length == 0) {
            return (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
        }

        // Return an array that is the copy of the original with a new size
        return (T[]) Arrays.copyOfRange(this.array, 0, newSize);
    }

    // Overridden equals methods to check if the passed Obj is the same as the original GArray
    @Override
    public boolean equals(Object obj){
        // If the two compared objects are the same then return true
        if (this == obj){
            return true;
        }

        // If obj is null or not an instance of GArray, return false
        if (obj == null || !(obj instanceof GArray)){
            return false;
        }
    
        // Cast obj into GArray to compare the two
        GArray<?> objGArray = (GArray<?>) obj;

        // Check if the array lengths are different
        if (this.array.length != objGArray.getSize()){
            return false;
        }

        // If any element differs return false
        for (int i = 0; i < this.getSize(); i++) {
            if (!(objGArray.getArray()[i].equals(this.getArray()[i]))){
                return false;
            }
        }

        // Else return true
        return true;
    }

    // toString that will print all of the elements in the array
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Generic array of type ");
        sb.append(this.array.getClass());
        sb.append(": [");

        for (int i = 0; i < this.array.length; i++){
           sb.append(this.array[i]);
            if (i < this.array.length - 1){
                sb.append(", \n");
            }
        }
        sb.append("]");
        return sb.toString();
    }
   
    // Simple hash coding that calls the default array hashing
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.array);
    }

}