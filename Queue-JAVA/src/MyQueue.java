public class MyQueue {
    private int head;
    private int tail;
    private int[] queueArray;
    private int numOfElements;

    public MyQueue(int size) {
        this.head = 0;
        this.tail = 0;
        queueArray = new int[size];
        numOfElements = 0;
    }

    public void enqueue(int num) throws Exception {
        if (head == tail + 1 || (head == 0 && tail == queueArray.length)) {
            throw new Exception("The queue is full!");
        }
        queueArray[tail] = num;
        if (tail == queueArray.length)
            tail = 0;
        else
            tail++;
        numOfElements++;
    }

    public int dequeue() throws Exception {
        if (head == 0 && tail == 0)
            throw new Exception("The queue is empty!");
        int num = queueArray[head];
        if (head == queueArray.length)
            head = 0;
        else
            head++;
        numOfElements--;
        return num;
    }

    public void printQueue() {
        for (int i = 0; i < numOfElements; i++)
            System.out.println(queueArray[i]);
    }

    public static void main(String[] args) throws Exception {
        MyQueue q = new MyQueue(4);
        //q.dequeue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.printQueue();
        //q.enqueue(5);
        q.dequeue();
        q.printQueue();
        q.dequeue();
        q.printQueue();
    }
}
