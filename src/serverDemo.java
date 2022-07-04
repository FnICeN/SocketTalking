import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class serverDemo {
    public static void main(String[] args) {
        BufferedReader clientin;
        try{
            ServerSocket a = new ServerSocket(6666);

            Socket client=a.accept();
            System.out.println("connection suc!");
            System.out.println("IP:"+client.getInetAddress());
            System.out.println("Port:"+client.getPort());

            InputStream in = client.getInputStream();
            clientin = new BufferedReader(new InputStreamReader(in));

            String things=clientin.readLine();
            while(!things.equals("disconnect")){
                System.out.println(things);
                things=clientin.readLine();
            }
            
            System.out.println("connection dis!");
            a.close();
        }catch(IOException e){
            System.out.println("Error:"+e);
        }
    }
}
