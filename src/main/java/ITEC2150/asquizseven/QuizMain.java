package ITEC2150.asquizseven;

public class QuizMain {
    public static void main(String[] args) {
        WaitingQueue q = new WaitingQueue();
        q.enqueue(new Person("Arthur"));
        q.enqueue(new Person("Mera"));
        q.enqueue(new Person("Vulko"));
        q.enqueue(new Person("Atlanna"));

        // display queue vaule
	while (!q.isEmpty()) {
            System.out.println(q.dequeue().getName());
        }
        
        // Print author name
        System.out.println("Ajitesh Sandhu");
    }
}
