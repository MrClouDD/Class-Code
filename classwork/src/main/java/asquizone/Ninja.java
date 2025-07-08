package asquizone;

/**
 * The type Ninja.
 */
public abstract class Ninja {
    /**
     * The Name.
     */
    String name;

    /**
     * Instantiates a new Ninja. * * @param n of type String
     */
    Ninja(String n){
         name = n;
     }

    /**
     * Display name
     */
    void displayName(){
         System.out.println(name);
     }
}
