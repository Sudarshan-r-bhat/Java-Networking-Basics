package com.practise.networking.basics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class HttpServerThread extends Thread {
    private Socket HTTPCONNECTION;
    private BufferedReader READER;
    private PrintWriter WRITE;
    private String CRLF = "\r\n";
    private boolean stop;

    HttpServerThread(Socket CONN) {
        this.HTTPCONNECTION = CONN;
        this.stop = false;
    }

    public void run() {
        try {
            while (!stop) {
                READER = new BufferedReader(new InputStreamReader(HTTPCONNECTION.getInputStream()));
                WRITE = new PrintWriter(new BufferedWriter(new OutputStreamWriter(HTTPCONNECTION.getOutputStream())), true);
                String line = "";
                String httpheader = "";
                String reqpage = "";

                // reading request
                while (true) {
                    line = READER.readLine();
                    if (line.equals(CRLF) || line.equals(""))
                        break;
                    httpheader += line + "\n";

                    if (line.contains("GET")) {
                        reqpage = line.substring(line.indexOf("GET") + 5, line.indexOf("HTTP")).strip();
                    }
                }
                System.out.println("header:\r\n" + httpheader);

                System.out.println("do we process this request ? = " + reqpage.equals("thealphavoice.online"));
                // sending response
                if (reqpage.equals("thealphavoice.online")) {
                    processRequest();
                }
                else {
                    System.out.println("requested page is " + reqpage);

                    WRITE.print("HTTP/1.1 404 Not Found" + CRLF);
                    Date date = new Date();
                    WRITE.print("Date: " + date.toString() + CRLF);
                    WRITE.print("Server: java web server" + CRLF);
                    WRITE.print("Connection: close" + CRLF);
                    WRITE.println("Content-Type: text/html; charset=utf-8" + CRLF);
                    //end of http header

                    //send file not found message
                    WRITE.println("<html><head>");
                    WRITE.println("<title>404 Not Found</title>");
                    WRITE.println("</head><body>");
                    WRITE.println("<h1>Not Found</h1>");
                    WRITE.println("<p>The requested URL /" + reqpage + " was not found on this server.</p>");
                    WRITE.println("</body></html>");
                    WRITE.println(CRLF);
                }
                READER.close();
                WRITE.close();
                HTTPCONNECTION.close();
                stop = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void processRequest() {
        try {

            //FileReader FREAD = new FileReader(file);
            String p = "C:\\workstation\\IdeaProjects\\networking and scrapping\\httpserver.html";
            File file = new File(p);
            BufferedReader READER2 = new BufferedReader(new FileReader(p));

            //sent the HTTP head (HTTP 200 OK)
            WRITE.print("HTTP/1.0 200 OK" + CRLF);
            Date date = new Date();
            WRITE.print("Date: " + date.toString() + CRLF);
            WRITE.print("Server: java tiny server" + CRLF);
            WRITE.print("Content-Type: text/html" + CRLF);
            WRITE.print("Content-Length: " + file.length() + CRLF);
            WRITE.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);
            //end of http header

            // send html file
            String line;
            while ((line = READER2.readLine()) != null) {
                WRITE.println(line);
            }

            READER2.close();
            System.out.println("The request has been satisfied successfully....");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//    private Socket socket;
//    private boolean isStop;
//    private BufferedReader in;
//    private PrintWriter out;
//    private File file;
//    final static String CRLF = "\r\n";
//
//    public HttpServerThread(Socket clientSocket) {
//        this.HTTPCONNECTION = clientSocket;
//        this.stop = false;
//        //this.socket = clientSocket;
//        //this.isStop = false;
//    }
//    public void run() {
//        try
//        {
//            while(!isStop)
//            {
//                //create a buffer reader
//                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                //create a PrintWriter
//                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
//                String line;
//                String httpHeader = ""; //stores the html header
//                String htmlFile =""; //stores the required html file
//                while (true) {
//                    line = in.readLine(); //read each line
//                    if (line.equals(CRLF) || line.equals("")) // end of header is reached?
//                    {
//                        break; // if yes, break
//                    }
//                    httpHeader = httpHeader + line + "\n"; //add a new line to the header
//                    if(line.contains("GET")) // if line contains get
//                    {
//                        //extract the html filename
//                        int beginIndex = line.indexOf("/");
//                        int endIndex = line.indexOf(" HTTP");
//                        htmlFile = line.substring(beginIndex+1, endIndex);
//                    }
//                }
//                System.out.println(httpHeader); // print httpHeader
//
//                //System.out.println("file: " + htmlFile); // print html file
//
//                processRequest(htmlFile); // process the request
//                closeConnection(); // close the connection
//
//            }
//        }
//        catch(Exception e) //print error stack trace
//        {
//            //System.out.println(e.printStackTrace());
//            e.printStackTrace();
//        }
//    }
//
//    public void processRequest(String htmlFile) throws Exception
//    {
//        File file = new File(htmlFile); //create a file variable
//        if(file.exists()) // if file exists
//        {
//            //create a BufferedReader to read the html file content
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//
//            //sent the HTTP head (HTTP 200 OK)
//            out.print("HTTP/1.0 200 OK" + CRLF);
//            Date date = new Date();
//            out.print("Date: " + date.toString() + CRLF);
//            out.print("Server: java tiny web server" + CRLF);
//            out.print("Content-Type: text/html" + CRLF);
//            out.print("Content-Length: " + file.length() + CRLF);
//            out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);
//            //end of http header
//
//            String line = "";
//            while((line = reader.readLine()) != null) //read a line from the html file
//            {
//                out.println(line); //write the line to the socket connection
//            }
//        }
//        else //if file does not exists
//        {
//            //sent the HTTP head (404 Not Found)
//            out.print("HTTP/1.1 404 Not Found" + CRLF);
//            Date date = new Date();
//            out.print("Date: " + date.toString() + CRLF);
//            out.print("Server: java tiny web server" + CRLF);
//            out.print("Connection: close" + CRLF);
//            out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);
//            //end of http header
//
//            //send file not found message
//            out.println("<html><head>");
//            out.println("<title>404 Not Found</title>");
//            out.println("</head><body>");
//            out.println("<h1>Not Found</h1>");
//            out.println("<p>The requested URL /" + htmlFile + " was not found on this server.</p>");
//            out.println("</body></html>");
//            out.println(CRLF);
//        }
//    }
//
//    private void closeConnection()
//    {
//        try
//        {
//            out.close(); // close output stream
//            in.close(); // close input stream
//            socket.close(); //close socket
//            isStop = true; //set isStop to true in order to exist the while loop
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.toString());
//        }
//    }
//}
//}

        //////////////////////////////////////////////////////////////////////////////////////////////////////////


