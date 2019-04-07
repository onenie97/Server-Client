import java.net.*;
import java.io.*;

class Build implements Runnable
{
    int  port = 3500;
    String input1;
    ServerSocket server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Console reader;

    public void work()
    {
        try
        {
            server = new ServerSocket(port);
            socket = server.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            reader = System.console();
        }

        catch(IOException e){
        e.printStackTrace();}
    }

    public void speak()
    {
        try
        {
            while(true)
            {
                input1 = reader.readLine();
                out.writeUTF(input1);
                if(input1.equals("stop"))
                {
                    in.close();
                    out.close();
                    socket.close();
                    server.close();
                    System.exit(0);
                }               
            }
        }
        catch (IOException e){
        e.printStackTrace();}
    }

    public void run()
    {
        String input2;

        try
        {
            while(true)
            {
                input2 = in.readUTF();
                System.out.println(input2);
                if(input2.equals("stop"))
                {
                    in.close();
                    out.close();
                    socket.close();
                    server.close();
                    System.exit(0);
                }
             }
         }
        catch(IOException e){
        e.printStackTrace();}
     }
}

public class server
{
    public static void main(String[]args)
    {
        Build object = new Build();
        object.work();
        Thread t1 = new Thread(object);
        t1.start();
        object.speak();
    }
}
