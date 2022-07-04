import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;
public class client2Demo{
    public static void main(String[] args) {
        try{
            Socket a=new Socket("127.0.0.1",6667);
            OutputStream put = a.getOutputStream();
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            put.write(input.getBytes());
            a.close();
            sc.close();
        }catch(IOException e){
            System.out.println("Error"+e);
        }
    }
}