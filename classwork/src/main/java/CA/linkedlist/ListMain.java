package CA.linkedlist;

public class ListMain {
    public static void main(String[] args) {
        bsList head = new bsList(2);

        head.addNode(new bsList(1));
        head.addNode(new bsList(5));
        head.addNode(new bsList(0));

        bsList currNode = head;

        do {
            System.out.println(currNode.getValue());
            currNode = currNode.getNextNode();
        }
        while (currNode != null);

    }

    private static class bsList{
        private int value;
        private bsList nextNode;

        bsList(int value){
            this.value = value;
            this.nextNode = null;
        }

        int getValue(){
            return this.value;
        }

        boolean hasNextNode(){
            return this.nextNode != null;
        }

        bsList getNextNode(){
            return this.nextNode;
        }

        void addNode(bsList node) {
            bsList curr = this;
            while (curr.hasNextNode()) {
                curr = curr.getNextNode();
            }
            curr.nextNode = node;
        }
    }
}

