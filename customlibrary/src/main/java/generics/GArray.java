package generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;


/**
 * Generic array class that provides various utility methods for creating a custom array.
 * Supports filling, copying, resizing, reversing, and other operations.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-05
 * @param <T> The type of elements stored in the array.
 */
@SuppressWarnings({"unchecked"})
public class GArray<T> implements Iterable<T> {
    /**
     * The internal array storing elements.
     */
    private T[] array;

    /**
     * Constructor to initialize the generic array with a custom array.
     * 
     * @param customArray The custom array to initialize.
     */
    GArray(T[] customArray) {
        this.array = customArray;
    }

    /**
     * Constructor to initialize the generic array with a specified type and size.
     * 
     * @param type The class type of the elements.
     * @param size The size of the array.
     * @throws IllegalArgumentException If the type is null or not an object type.
     */
    GArray(Class<T> type, int size) {
        if (type == null) {
            throw new IllegalArgumentException("Class type must not be null");
        } else if (!Object.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException("Type must be an object. Use wrappers for primitives if necessary.");
        } else {
            this.array = (T[]) Array.newInstance(type, size);
        }
    }

    /**
     * Ensures the index is within bounds.
     * 
     * @param index The index to check.
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
     */
    private void ensureIndexBounds(int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index must be between 0 and " + (array.length - 1));
        }
    }

    /**
     * Ensures the value is not null.
     * 
     * @param value The value to check.
     * @throws IllegalArgumentException If the value is null.
     */
    private void ensureNonNullity(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
    }

    /**
     * Returns the size of the array.
     * 
     * @return The size of the array.
     */
    final int getSize() {
        return this.array.length;
    }

    /**
     * Checks if the array is empty.
     * 
     * @return True if the array is empty, false otherwise.
     */
    final boolean isEmpty() {
        return this.array.length == 0;
    }

    /**
     * Returns the generic array object.
     * 
     * @return The generic array object.
     */
    final GArray<T> get() {
        return this;
    }

    /**
     * Returns the internal array.
     * 
     * @return The internal array.
     */
    final T[] getArray() {
        return this.array;
    }

    /**
     * Returns the value at the specified index.
     * 
     * @param index The index to retrieve the value from.
     * @return The value at the specified index.
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
     */
    final T getFromIndex(int index) {
        ensureIndexBounds(index);
        return this.array[index];
    }

    /**
     * Sets the internal array with a provided custom array.
     * 
     * @param customArray The custom array to set.
     */
    final void setArray(T[] customArray) {
        this.array = customArray;
    }

    /**
     * Sets the value at the specified index.
     * 
     * @param index The index to set the value at.
     * @param value The value to set.
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
     */
    final void setFromIndex(int index, T value) {
        ensureIndexBounds(index);
        this.array[index] = value;
    }

    /**
     * Checks if the array contains the specified value.
     * 
     * @param value The value to check for.
     * @return True if the array contains the value, false otherwise.
     * @throws IllegalArgumentException If the value is null.
     */
    final boolean contains(T value) {
        ensureNonNullity(value);
        for (T element : this.array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the first index of the specified value.
     * 
     * @param value The value to find.
     * @return The first index of the value, or -1 if not found.
     * @throws IllegalArgumentException If the value is null.
     */
    final int indexOf(T value) {
        ensureNonNullity(value);
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && this.array[i].equals(value))
                return i;
        }
        return -1;
    }

    /**
     * Returns the last value in the array.
     * 
     * @return The last value in the array.
     */
    final T getLast() {
        return getFromIndex(this.array.length - 1);
    }

    /**
     * Creates a copy of the generic array.
     * 
     * @return A new GArray object containing the same elements.
     */
    final GArray<T> copy() {
        return new GArray<>(this.array);
    }

    /**
     * Creates a copy of the internal array.
     * 
     * @return A new array containing the same elements.
     */
    final T[] copyArray() {
        return Arrays.copyOf(this.array, this.array.length);
    }

    /**
     * Copies the value from one index to another.
     * 
     * @param referenceIndex The index to copy the value from.
     * @param overwrittenIndex The index to overwrite the value at.
     * @throws ArrayIndexOutOfBoundsException If any index is out of bounds.
     */
    final void copyValue(int referenceIndex, int overwrittenIndex) {
        setFromIndex(overwrittenIndex, getFromIndex(referenceIndex));
    }

    private static final Set<Class<?>> predefinedClasses = Set.of(
        Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, 
        Character.class, Boolean.class, String.class
    );

    /**
     * Checks if the value's class is predefined by Java.
     * 
     * @param value The value to check.
     * @return True if the value's class is predefined, false otherwise.
     * @throws IllegalArgumentException If the value is null.
     */
    final boolean isPredefinedClass(T value) {
        ensureNonNullity(value);
        return predefinedClasses.contains(value.getClass());
    }

    /**
     * Fills the entire array with the specified value.
     * 
     * @param value The value to fill the array with.
     */
    final void fill(T value) {
        if (value != null && isPredefinedClass(value)) {
            Arrays.fill(array, value);
        } else {
            for (int i = 0; i < array.length; i++) {
                this.array[i] = value;
            }
        }
    }

    /**
     * Clears the array by filling it with null values.
     */
    final void clear() {
        fill(null);
    }

    /**
     * Swaps the values at two specified indexes.
     * 
     * @param index1 The first index.
     * @param index2 The second index.
     * @throws ArrayIndexOutOfBoundsException If any index is out of bounds.
     */
    final void swap(int index1, int index2) {
        ensureIndexBounds(index1);
        ensureIndexBounds(index2);

        T temp = getFromIndex(index1);
        setFromIndex(index1, getFromIndex(index2));
        setFromIndex(index2, temp);
    }

    /**
     * Returns an iterator for the array.
     * 
     * @return An iterator for the array.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                return getFromIndex(index++);
            }
        };
    }

    /**
     * Reverses the array.
     * 
     * @return A new array with elements in reverse order.
     */
    public final T[] reverse() {
        if (array.length == 0) {
            return Arrays.copyOf(this.array, this.array.length);
        }

        T[] reverseArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        for (int i = 0; i < this.array.length; i++) {
            reverseArray[i] = this.array[this.array.length - 1 - i];
        }

        return reverseArray;
    }

    /**
     * Resizes the array to a new size.
     * 
     * @param newSize The new size of the array.
     * @return A new array with the specified size.
     */
    public final T[] resize(int newSize) {
        if (array.length == 0) {
            return (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
        }

        return (T[]) Arrays.copyOfRange(this.array, 0, newSize);
    }

    /**
     * Checks if the current array is equal to another GArray object.
     * 
     * @param obj The object to compare with.
     * @return True if the arrays are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof GArray)) {
            return false;
        }

        GArray<?> objGArray = (GArray<?>) obj;

        if (this.array.length != objGArray.getSize()) {
            return false;
        }

        for (int i = 0; i < this.getSize(); i++) {
            if (!(objGArray.getArray()[i].equals(this.getArray()[i]))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a string representation of the array.
     * 
     * @return A string representation of the array.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Generic array of type ");
        sb.append(this.array.getClass());
        sb.append(": [");

        for (int i = 0; i < this.array.length; i++) {
            sb.append(this.array[i]);
            if (i < this.array.length - 1) {
                sb.append(", \n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns the hash code of the array.
     * 
     * @return The hash code of the array.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.array);
    }
}