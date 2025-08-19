package ITEC2150.CA.linkedlist;

/**
 * Basic circular linked list implementation
 * @author Ajitesh Sandhu
 * @date July 14, 2025
 */

public class ListMain {
    public static void main(String[] args) {
        // Create the head node with value 2
        bsList head = new bsList(2);

        // Add nodes to create a circular linked list
        head.addNode(new bsList(1));
        head.addNode(new bsList(5));
        head.addNode(new bsList(0));

        // Traverse the circular linked list (print first complete cycle)
        bsList currNode = head;
        bsList startNode = head; // Keep track of starting node to detect cycle completion

        do {
            System.out.println(currNode.getValue());
            currNode = currNode.getNextNode();
        }
        while (currNode != startNode); // Stop when we've completed one full cycle

        // Print signature
        System.out.println("Created by Ajitesh Sandhu");

    }

    /**
     * Inner class representing a node in the circular linked list
     */
    private static class bsList{
        private final int value;
        private bsList nextNode;

        /**
         * Constructor to create a new node
         * @param value the integer value to store in this node
         */
        bsList(int value){
            this.value = value;
            this.nextNode = null;
        }

        /**
         * Get the value stored in this node
         * @return the integer value
         */
        int getValue(){
            return this.value;
        }

        /**
         * Check if this node has a next node
         * @return true if there is a next node, false otherwise
         */
        boolean hasNextNode(){
            return this.nextNode != null;
        }

        /**
         * Get the next node in the circular linked list
         * @return the next node
         */
        bsList getNextNode(){
            return this.nextNode;
        }

        /**
         * Add a new node to the end of the circular linked list
         * This method ensures the list remains circular by making the last node point to the head
         * @param node the new node to add
         */
        void addNode(bsList node) {
            bsList curr = this;
            bsList head = this; // Keep reference to head node
            
            // If this is the first node being added, make it circular
            if (!this.hasNextNode()) {
                this.nextNode = node;
                node.nextNode = head; // Point back to head to make it circular
                return;
            }
            
            // Traverse to find the last node (the one that points back to head)
            while (curr.hasNextNode() && curr.getNextNode() != head) {
                curr = curr.getNextNode();
            }
            
            // Insert the new node and maintain circular structure
            curr.nextNode = node;
            node.nextNode = head; // New node points back to head
        }
    }
}

