public class Main {
    public static void main(String[] args) throws Exception {
        MyStack s = new MyStack(4);
        System.out.println(s.IsEmpty());
        s.Push(1);
        s.Push(2);
        s.Push(3);
        s.Push(4);
        System.out.println(s.Peak());
        s.Pop();
        System.out.println(s.Peak());
        s.PrintStack();
    }
}
