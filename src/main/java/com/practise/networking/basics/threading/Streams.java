/*

<<Multiple programs are stacked in here.
So this program might seem lengthy,
So please read the entire program.>>

    note:   FileInputStream == 1 Byte [used to read file byte by byte]
            DataInputStream == 8 Bytes [used to read java primitives like int, float, double...]

            InputStreamReader == [ used to read characters from InputStream of bytes]
            BufferedInputStream == [used to buffer the bytes]

            BufferedReader    ==  [used to buffer the characters not bytes]
            FileReader        == [reads Characters instead of bytes. each character may be > 1 Byte]

PrintWriter : takes both streams and files as its input. as it acts as both streams and readers.

   conclusion:  streams         : read bytes of data.
                reader          : read characters of data.
                streamReader    : its the mix, it takes streams as argument and reads characters.
                objectReader    : reads objects.

*/
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

            int c = INPUTSTREAM.read();  // it reads 1 Byte at a time. hence returns int
            while(c != -1)
            {
                print += (char)c; //String.valueOf(c);
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
        // datastreams is used to read multiple bytes upto 8 bytes(Double), using FileInputStream
        // note: file gets create beyond 2 parent folder
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
    { // when you wish to r/w characters to a file then use I/O-stream-R/W
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
        // its similar to BufferReader, except that it doesn't use buffering
        // both printStream and printWriter is used to write any type of data like int, text, float etc
        try
        {
            PrintStream out = new PrintStream("buffered_reader.txt");
            // PrintWriter out = new PrintWriter(new FileWriter(FILE));
            out.println("buffered writer and printStream happened....");
            System.out.println("file has been updated...");
        }
        catch(IOException ioe){System.out.println(ioe.getMessage());}
    }
}
