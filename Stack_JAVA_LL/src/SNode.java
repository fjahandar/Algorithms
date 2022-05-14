class SNode<T> {
    private T value;
    private SNode<T> next;

    public SNode(T value) {
        this.value = value;
        this.next = null;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> node) {
        this.next = node;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }

}
