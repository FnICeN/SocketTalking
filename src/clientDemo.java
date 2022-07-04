import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;
public class clientDemo{
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);

            Socket a=new Socket("127.0.0.1",6666);
            OutputStream put = a.getOutputStream();

            String input = sc.nextLine();
            while(!input.equals("disconnect")){
                put.write((input+"\n").getBytes());
                put.flush();
                input=sc.nextLine();
            }
            put.write(input.getBytes());
            put.flush();
            a.close();
            sc.close();
        }catch(IOException e){
            System.out.println("Error"+e);
        }
    }
}