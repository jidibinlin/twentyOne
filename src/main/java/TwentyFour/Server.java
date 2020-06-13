package TwentyFour;

import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class Server {
    private static final int Port=9433;
    InputStreamReader read;
    OutputStreamWriter write;
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

        } catch(IOException e){
            e.printStackTrace();

        }
    }
}