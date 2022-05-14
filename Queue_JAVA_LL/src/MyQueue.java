public class MyQueue<T> {
    private QNode<T> head;
    private QNode<T> tail;

    public MyQueue() {
        head = tail = null;
    }

    public void enqueue(T value) {
        QNode<T> newNode = new QNode<T>(value);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            if (tail != null)
                tail.setNext(newNode);
            tail = newNode;
        }
    }

    public T dequeue() throws Exception {
        if (head == null)
            throw new Exception("The queue is empty!");
        T value = head.getValue();
        head = head.getNext();
        if (head == null)
            tail = null;
        return value;
    }

    public T peek() throws Exception {
        if (head == null)
            throw new Exception("The queue is Empty");
        return head.getValue();
    }

    public void printQueue() {
        QNode<T> temp = head;
        while(temp != null) {
            System.out.println(temp.getValue());
            temp = temp.getNext();
        }
    }

    public static void main(String[] args) throws Exception {
        MyQueue q = new MyQueue<Integer>();
        //q.dequeue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        System.out.println("Your queue is: ");
        q.printQueue();
        System.out.println("________________");
        int p1 = (int)q.peek();
        System.out.println("The top of the queue is: " + p1);
        System.out.println("________________");
        q.enqueue(5);
        System.out.println("Your queue is: ");
        q.printQueue();
        System.out.println("________________");
        int d1 = (int)q.dequeue();
        System.out.println("You enqueued (removed): " + d1);
        System.out.println("________________");
        int d2 = (int)q.dequeue();
        System.out.println("You enqueued (removed): " + d2);
        System.out.println("________________");
        int d3 = (int)q.dequeue();
        System.out.println("You enqueued (removed): " + d3);
        System.out.println("________________");
        int p = (int)q.peek();
        System.out.println("The top of the queue is: " + p);
        System.out.println("________________");
        System.out.println("Your queue is: ");
        q.printQueue();
    }
}
