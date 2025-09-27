package ITEC3150;

import customLibrary.ITEC3150.AVLTree;
import customLibrary.ITEC3150.Node;

/** @author Ajitesh Sandhu */
public class gettingKthElement<E> extends AVLTree<E> {
    public static void main(String[] args) {
        gettingKthElement<Integer> findKthElement = new gettingKthElement<>();

        Integer[] inputArr = { 1, 4, 2, 9, 23, 3, 46, 8, 12, 43, 21, 91, 22, 78, 20 };
        int k = 1001;

        AVLTree<Integer> AVL = new AVLTree<Integer>(inputArr);

        if (findKthElement.find(k, AVL.getRoot()) == null) {
            System.out.printf("You entered %d. Pick a number between %d and %d.\n", k, 1, AVL.getRoot().getSize());
        } else {
            System.out.printf("The %d%s smallest number is: %d\n", k, ordinalString(k),
                    findKthElement.find(k, AVL.getRoot()));
        }
    }

    public E find(int k, Node<E> root) {
        if (root == null) {
            return null;
        }
        if (k < 1 || k > root.getSize()) {
            return null;
        }

        Node<E> A = root.getLeftChild();
        Node<E> B = root.getRightChild();

        if (A == null && k == 1) {
            return root.getElement();
        }
        if (A == null && k == 2) {
            return B.getElement();
        }

        if (k <= A.getSize()) {
            return find(k, A);
        } else if (k == A.getSize() + 1) {
            return root.getElement();
        } else {
            return find(k - A.getSize() - 1, B);
        }

    }

    static String ordinalString(int num) {
        char lastDigit = ((Integer) num).toString().charAt(((Integer) num).toString().length() - 1);

        if (lastDigit == '1') {
            return "st";
        } else if (lastDigit == '2') {
            return "nd";
        } else if (lastDigit == '3') {
            return "rd";
        } else {
            return "th";
        }
    }

}
