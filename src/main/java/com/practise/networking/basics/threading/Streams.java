
package com.practise.networking.basics.threading;

import java.io.*;
public class Streams
{
    public static void main(String[] args)
    {
        //fileStreams();
        //dataStreams();
        //characterStream();
        printStream();
    }

    private static void fileStreams()
    {
        String str0 = "written by fileStreams.....";
        String print = "";
        try
        {
            String path = "C:\\workstation\\IdeaProjects\\networking and scrapping\\Threading\\src\\output.txt";
            FileInputStream INPUTSTREAM = new FileInputStream(path);
            FileOutputStream OUTPUTSTREAM = new FileOutputStream(path, true);

            OUTPUTSTREAM.write(str0.getBytes());
            OUTPUTSTREAM.flush();
            OUTPUTSTREAM.close();

            int c = INPUTSTREAM.read();  
            while(c != -1)
            {
                print += (char)c; 
                c = INPUTSTREAM.read();
            }
            INPUTSTREAM.close();
            System.out.println(print);
        }
        catch(Exception e)
        {System.out.println(e.toString());}
    }


    private static void dataStreams()
    {
        File FILE = new File("data_streams.txt");
        try
        {
            if(!FILE.exists())
                if(FILE.createNewFile())
                    System.out.println("file not found hence created a new one");
        }
        catch(IOException ioe){System.out.println(ioe.toString());}

        try
        {
            DataInputStream INPUTSTREAM = new DataInputStream(new FileInputStream(FILE.getName()));
            DataOutputStream OUTPUTSTREAM = new DataOutputStream(new FileOutputStream(FILE.getAbsoluteFile()));

            OUTPUTSTREAM.writeInt(324);
            OUTPUTSTREAM.writeDouble(32.000001);
            OUTPUTSTREAM.writeBytes("these are the values");
            OUTPUTSTREAM.close();

            System.out.println("int, double = " + INPUTSTREAM.readInt()+ ", " + INPUTSTREAM.readDouble() + "\nbytes = " + new String(INPUTSTREAM.readAllBytes()));
        }
        catch(IOException ioe){System.out.println(ioe.toString());}
    }

    private static void characterStream()
    { 
        File FILE = new File("character_stream.txt");
        try
        {
            if(!FILE.exists())
                if(FILE.createNewFile())
                    System.out.println("file not found hence created a new one");
        }
        catch(IOException ioe){System.out.println(ioe.toString());}

        try
        {
            InputStreamReader READ = new InputStreamReader(new FileInputStream(FILE.getName()));
            OutputStreamWriter WRITE = new OutputStreamWriter(new FileOutputStream(FILE.getName()));

            String str0 = "this is OutputStreamWriter.....";

            WRITE.write(str0);
            WRITE.close();

            int c = READ.read();
            while(c != -1)
            {
                System.out.print((char)c);
                c = READ.read();
            }
        }
        catch(IOException ioe){ System.out.println(ioe.toString());}

    }

    private static void bufferedReader()
    {
        File FILE = new File("buffered_reader.txt");
        try
        {
            if(!FILE.exists())
                if(FILE.createNewFile())
                    System.out.println("file not found..! hence created a new file");
        }
        catch(IOException ioe){System.out.println(ioe.toString());}

        try
        {
            String str0 = "this is bufferedWriter...";
            BufferedReader READ = new BufferedReader(new FileReader(FILE));
            BufferedWriter WRITE = new BufferedWriter(new FileWriter(FILE));

            WRITE.write(str0);
            WRITE.newLine();
            WRITE.close();

            String line = null;
            while((line = READ.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch(IOException ioe){System.out.println(ioe.toString());}
    }

    private static void printStream()
    {
        try
        {
            PrintStream out = new PrintStream("buffered_reader.txt");
            out.println("buffered writer and printStream happened....");
            System.out.println("file has been updated...");
        }
        catch(IOException ioe){System.out.println(ioe.getMessage());}
    }
}
