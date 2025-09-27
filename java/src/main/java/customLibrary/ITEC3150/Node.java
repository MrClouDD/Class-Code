package customLibrary.ITEC3150;

public class Node<E> {
    E element; // Element stored in this node
    int height;
    Node<E> left; // Left child
    Node<E> right; // Right child

    public Node(E element) {
        this.element = element;
    }
}
