package com.practise.networking.basics.threading;
public class Main
{
    // this is the driver class for testing1 class-thread.
    public static void main(String[] args)
    {
        testing1 thread = new testing1("testing-thread-1");
        thread.start();
        testing1 thread2 = new testing1("testing-thread-2");
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.start();
    }
}
