package customLibrary.ITEC3150;

public class Node<E> {
    E element; // Element stored in this node
    int size;
    int height;
    Node<E> left; // Left child
    Node<E> right; // Right child

    public Node(E element) {
        this.element = element;
    }

    public E getElement() {
        return this.element;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public Node<E> getLeftChild() {
        return this.left;
    }

    public void setLeftChild(Node<E> c) {
        this.left = c;
    }

    public Node<E> getRightChild() {
        return this.right;
    }

    public void setRightChild(Node<E> c) {
        this.right = c;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int s) {
        this.size = s;
    }

}
