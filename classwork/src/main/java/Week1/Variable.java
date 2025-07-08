package Week1;/*
Name: Ajitesh Sandhu
Date: 5/27/2025
Description: Contains a program called add() that prints the added result in a specific format*/

public class Variable {
    public static void main(String[] args) {
        Variable a = new Variable();
        a.Add(5,7);
    }

    public void Add(int x, int y){
        int result = x + y;
        System.out.printf("%d + %d = %d\n", x, y, result);
        System.out.println("Ajitesh Sandhu");
    }
}
