package ITEC2150.CA.interfaces;

public class Dinosaur implements RideAble{
    String name;

    Dinosaur(String n){
        name = n;
    }

    Dinosaur(){
        this("");
    }

    @Override
    public void rideOn() {
        System.out.println("Good luck!");
    }
}
