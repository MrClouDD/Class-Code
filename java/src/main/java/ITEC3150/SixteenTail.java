package ITEC3150;

import customLibrary.ITEC3150.SixteenTailModel;

public class SixteenTail {
    public static void main(String[] args) {
        SixteenTailModel model = new SixteenTailModel();

        // Test 1: All heads - should reach all tails
        System.out.println("=== Test 1: All Heads ===");
        String test1 = "HHHHHHHHHHHHHHHH";
        System.out.println("Initial configuration: " + test1);
        char[] initialNode1 = test1.toCharArray();
        java.util.List<Integer> path1 = model.getShortestPath(SixteenTailModel.getIndex(initialNode1));
        System.out.println("Number of steps: " + (path1.size() - 1));
        System.out.println("The steps to flip the coins are:");
        for (int i = 0; i < path1.size(); i++)
            SixteenTailModel.printNode(SixteenTailModel.getNode(path1.get(i)));

        // Test 2: Single head in center
        System.out.println("=== Test 2: Single Head in Center (Unsolvable)===");
        String test2 = "TTTTTTHTTTTTTTTT";
        System.out.println("Initial configuration: " + test2);
        char[] initialNode3 = test2.toCharArray();
        java.util.List<Integer> path2 = model.getShortestPath(SixteenTailModel.getIndex(initialNode3));
        if (path2.size() > 1) {
            System.out.println("Number of steps: " + (path2.size() - 1));
            System.out.println("The steps to flip the coins are:");
            for (int i = 0; i < path2.size(); i++)
                SixteenTailModel.printNode(SixteenTailModel.getNode(path2.get(i)));
        } else {
            System.out.println("No solution exists for this configuration.");
        }
    }
}
