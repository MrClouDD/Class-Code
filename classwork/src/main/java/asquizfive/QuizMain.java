package asquizfive;

public class QuizMain {
    public static void main(String[] args) {
        PersonList currentNode;
        PersonList head = new PersonList(new Person("James"));

        currentNode  = head;
        PersonList tempNode = new PersonList(new Person("Paloma"));
        currentNode.addNode(tempNode);

        currentNode = currentNode.getNextNode();
        tempNode = new PersonList(new Person("Lyutsifer"));
        currentNode.addNode(tempNode);

        currentNode = currentNode.getNextNode();
        tempNode = new PersonList(new Person("Madeleine"));
        currentNode.addNode(tempNode);

        currentNode = currentNode.getNextNode();
        tempNode = new PersonList(new Person("Nomi"));
        currentNode.addNode(tempNode);

        // print list content
        currentNode = head;
        System.out.println(currentNode.getValue().getName());
        while (currentNode.hasNext()) {
            currentNode = currentNode.getNextNode();
            System.out.println(currentNode.getValue().getName() + " ");
        }
        System.out.println("Created by Ajitesh Sandhu on 6/30/25");
    }


}
