package TwentyFour;

import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class Server {
    private static final int Port=9433;
    private InputStreamReader read;
    private OutputStreamWriter write;

    public BufferedReader reader = null;
    public BufferedWriter writer = null;
    Socket server;
    public void connect(){
        try {
            server = new Socket("106.14.215.185",Port);
            
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        try{
            read = new InputStreamReader(server.getInputStream());
            write = new OutputStreamWriter(server.getOutputStream());

            reader = new BufferedReader(read);
            writer = new BufferedWriter(write);

        } catch(IOException e){
            e.printStackTrace();

        }
    }

    public void close(){
        try {
            server.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}