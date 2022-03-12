package com.practise.networking.basics.threading;

public class ImplementRunnable implements Runnable
{
    private int index;
    ImplementRunnable(int index)
    {
        this.index = index;
    }
    public void run()
    {
        try
        {
            Thread.sleep(1000);
            int i = 0;
            while(i <= 10)
            {
                System.out.println(i + " " + index + " " + Thread.currentThread().getName());
                i++;
            }
        }
        catch(Exception e){}
    }
}
