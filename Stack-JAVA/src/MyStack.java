public class MyStack {
    int size;
    int top;
    int[] stackArray;

    public MyStack(int size)
    {
        this.size = size;
        stackArray = new int[size];
        this.top = -1;
    }

    public Boolean IsEmpty()
    {
        return top == -1;
    }

    public void Push(int number) throws Exception {
        if (top == size)
        {
            throw new Exception("Stack overflow!");
        }
        top++;
        this.stackArray[top] = number;

    }

    public int Pop() throws Exception {
        if (IsEmpty())
        {
            throw new Exception("Stack underflow!");
        }
        top--;
        return stackArray[top + 1];
    }

    public int Peak()
    {
        return stackArray[top];
    }

    public void PrintStack()
    {
        for (int i = 0; i <= top; i++)
        {
            System.out.println(stackArray[i]);
        }
    }


}
