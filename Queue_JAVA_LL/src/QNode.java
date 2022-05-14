public class QNode<T> {
    private T value;
    private QNode<T> next;

    public QNode(T value) {
        this.value = value;
        this.next = null;
    }

    public QNode<T> getNext() {
        return next;
    }

    public void setNext(QNode<T> node) {
        this.next = node;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }

}
