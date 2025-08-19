package ITEC2150.CA.interfaces;

public class Bicycle implements RideAble{
    String name;

    Bicycle(String n){
        name = n;
    }

    Bicycle(){
        this("");
    }
    @Override
    public void rideOn() {
        System.out.println("Sit on the saddle");
    }

}
