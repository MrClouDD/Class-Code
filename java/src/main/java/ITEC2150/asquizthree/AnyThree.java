package ITEC2150.asquizthree;

/**
 * Any three
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class AnyThree<T> {
    private T first, second, third;

    /**
     * Instantiates a new Any three. * * @param first of type T
     *
     * @param second of type T
     * @param third  of type T
     */
    AnyThree(T first, T second, T third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Print reverse
     */
    void printReverse(){
        System.out.println(third + " " +  second + " " + first);
    }
}

