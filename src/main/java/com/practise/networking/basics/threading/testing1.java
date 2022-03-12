package com.practise.networking.basics.threading;
public class testing1 extends Thread
{
    // Remember constructor cannot have a return type.
    public testing1(String threadName)
    {
        this.setName(threadName);
    }

    public void run()
    {
        int i = 0;
        while(i <= 100)
        {
            try
            {
                Thread.sleep(1000);
                System.out.println(currentThread().getId() + " = thread ID = " + this.getId() + " " + currentThread().getName());
                System.out.println("count" + i);
                i++;
            }
            catch(Exception e){}
        }
    }
}
