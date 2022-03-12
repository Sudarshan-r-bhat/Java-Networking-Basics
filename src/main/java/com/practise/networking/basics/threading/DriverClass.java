package com.practise.networking.basics.threading;

public class DriverClass
{
    // this is the DriverClass for ImplementRunnable class
    public static void main(String[] args)
    {
        ImplementRunnable runnable1 = new ImplementRunnable(1);
        Thread thread = new Thread(runnable1, "testing-thread-1");
        thread.start();
    }
}
