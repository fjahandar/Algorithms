using System;

namespace Stack // Note: actual namespace depends on the project name.
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Stack s = new Stack(4);
            Console.WriteLine(s.IsEmpty());
            s.Push(1);
            s.Push(2);
            s.Push(3);
            s.Push(4);
            Console.WriteLine(s.Peak());
            s.Pop();
            Console.WriteLine(s.Peak());
            s.PrintStack();
        }
    }
}

