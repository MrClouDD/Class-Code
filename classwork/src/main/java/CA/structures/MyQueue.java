package CA.structures;

public class MyQueue<T> {
//    T[] data = new T[100];
//
//    int front = 0, back = 0;
//
//    boolean isEmpty(){
//        return front == back;
//    }
//
//    boolean isFull(){
//        return front == (back + 1) % data.length;
//    }
//
//    boolean enqueue(int v){
//        if (!isFull()){
//            data[back] = v;
//            back = (back + 1) % data.length;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    int dequeue(){
//        if (!isEmpty()) {
//            int v = data[front];
//            front = (front + 1) % data.length;
//            return v;
//        } else {
//            return -99999;
//        }
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Ajitesh Sandhu");
//
//        MyQueue queue = new MyQueue();
//
//        queue.enqueue(2);
//        queue.enqueue(1);
//        queue.enqueue(5);
//        queue.enqueue(0);
//
//        for (int i = 0; i < 4; i++) {
//            System.out.println(queue.dequeue());
//        }
//    }
}
