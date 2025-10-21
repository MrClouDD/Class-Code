package ITEC3150;

import java.util.Scanner;

import customLibrary.ITEC3150.UnweightedGraph;

public class dimensionalTravel {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the first integer");
        int n = input.nextInt();
        input.nextLine();

        System.out.println();
        int m = input.nextInt();
        input.nextLine();

    }

    void graphCreation(int n, int m) {
        int NUMBER_OF_VERTICES = n * m;

        List<EdgeWithCost>

        UnweightedGraph<Integer> graph = new UnweightedGraph<>();
    }
}
