using System;
namespace Stack
{
	public class Stack
	{
		int size;
		int top;
		int[] stackArray;

		public Stack(int size)
		{
			this.size = size;
			stackArray = new int[size];
			this.top = -1;
		}

		public Boolean IsEmpty()
        {
			return top == -1;
        }

		public void Push(int number)
        {
			if (top == size)
            {
				throw new Exception("Stack overflow!");
            }
			top++;
			this.stackArray[top] = number;
			
        }

		public int Pop()
        {
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
                Console.WriteLine(stackArray[i]);
            }
        }
	}
}

