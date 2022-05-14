import java.util.EmptyStackException;

public class MyStack<T> {
    private SNode<T> top;

    public MyStack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        SNode<T> newNode = new SNode<T>(value);
        newNode.setNext(top);
        top = newNode;
    }

    public T pop() {
        if (top == null)
            throw new EmptyStackException();
        T value = top.getValue();
        this.top = top.getNext();
        return value;
    }

    public T peek() {
        if (top == null)
            throw new EmptyStackException();
        return top.getValue();
    }

    public void printStack() {
        SNode<T> temp = top;
        while (temp != null) {
            System.out.println(temp.getValue());
            temp = temp.getNext();
        }
    }
}
