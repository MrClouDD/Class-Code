package ITEC2150.astest2.test22;
// you can add your package information here

// do not modify the code below:
public class TestMain22 {
    public static void main(String[] args) {
        List<String> currentNode;
        List<String> head = new List<>("James");

        currentNode  = head;
        List<String> tempNode = new List<>("Jill");
        currentNode.addNode(tempNode);

        currentNode = currentNode.getNextNode();
        tempNode = new List<>("Tilly");
        currentNode.addNode(tempNode);

        currentNode = currentNode.getNextNode();
        tempNode = new List<>("Felix");
        currentNode.addNode(tempNode);

        // print list content
        currentNode = head;
        System.out.print(currentNode.getValue() + " ");
        while (currentNode.hasNext()) {
            currentNode = currentNode.getNextNode();
            System.out.print(currentNode.getValue() + " ");
        }
        System.out.println("\n=====");

        List<Double> currentNode2;
        List<Double> head2 = new List<>(10.10);

        currentNode2  = head2;
        List<Double> tempNode2 = new List<>(30.30);
        currentNode2.addNode(tempNode2);

        currentNode2 = currentNode2.getNextNode();
        tempNode2 = new List<>(50.50);
        currentNode2.addNode(tempNode2);

        currentNode2 = currentNode2.getNextNode();
        tempNode2 = new List<>(70.70);
        currentNode2.addNode(tempNode2);

        currentNode2 = currentNode2.getNextNode();
        tempNode2 = new List<>(90.90);
        currentNode2.addNode(tempNode2);

        // print list content
        currentNode2 = head2;
        System.out.print(currentNode2.getValue() + " ");
        while (currentNode2.hasNext()) {
            currentNode2 = currentNode2.getNextNode();
            System.out.print(currentNode2.getValue() + " ");
        }
        System.out.println("");
        
        // Print author information
        System.out.println("Created by Ajitesh Sandhu");
    } // close main()
}
