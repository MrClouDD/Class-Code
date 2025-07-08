package asquizone;

/**
 * Uchiha clan
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class UchihaClan extends Ninja{
    private String clan = "";

    /**
     * Instantiates a new Uchiha clan. * * @param n of type String
     */
    UchihaClan(String n){
        super(n);
        clan = "Uchiha Clan";
    }

    /**
     * Display clan
     */
    void displayClan(){
        System.out.println(clan);
    }
}
