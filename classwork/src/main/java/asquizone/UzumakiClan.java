package asquizone;

/**
 * Uzumaki clan
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class UzumakiClan extends Ninja{
    private String clan = "";

    /**
     * Instantiates a new Uzumaki clan. * * @param n of type String
     */
    UzumakiClan(String n){
        super(n);
        clan = "Uzumaki Clan";
    }

    /**
     * Display clan
     */
    void displayClan(){
        System.out.println(clan);
    }
}
