public class Main {
    public static void main(String[] args) throws Exception {
        MyStack s = new MyStack<Integer>();
        System.out.println(s.isEmpty());
        //s.pop();
        //s.peek();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.printStack();
        System.out.println("------------------");
        System.out.println(s.peek());
        System.out.println("------------------");
        int p1 = (int)s.pop();
        s.printStack();
        System.out.println("The popped item is: " + p1);
        System.out.println("------------------");
        s.printStack();
        System.out.println("------------------");
        int p2= (int)s.pop();
        s.printStack();
        System.out.println("The popped item is: " + p2);
        System.out.println("------------------");
        s.printStack();
        System.out.println("------------------");
        int p3 = (int)s.pop();
        s.printStack();
        System.out.println("The popped item is: " + p3);
        System.out.println("------------------");
        s.printStack();
    }
}
