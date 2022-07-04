import java.net.*;
import java.io.*;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.event.*;

public class aThread implements Runnable,ActionListener{
    private int port;
    private Socket clientLinking;
    private OutputStream outStream;
    private JTextArea in;
    private JTextArea out;
    private JButton send_botton;
    private String message_out;

    public aThread(int port,Socket clientLinking,JTextArea in,JTextArea out,JButton send_botton){
        this.port=port;
        this.clientLinking=clientLinking;
        this.in=in;
        this.out=out;
        this.send_botton=send_botton;
        this.send_botton.addActionListener(this);     //onclick
    }

    @Override
    public void actionPerformed(ActionEvent e) {        //when click the button
        System.out.println("aaa");
        try{
            this.message_out=this.out.getText();
            this.outStream.write((this.message_out+"\n").getBytes());
            this.outStream.flush();
        }catch(IOException f){
            System.out.println("Error"+f);
        }
        out.setText("");;
    }

    @Override
    public void run() {
        System.out.println("now v1 is running...");
        try{                    //try linking and get stream
            // ServerSocket serverInter = new ServerSocket(port);
            // Socket clientLinking = serverInter.accept();
            // in.setText("LINKING SUCCESS\n");
            // in.append("IP:"+clientLinking.getInetAddress()+"\n");
            // in.append("Port:"+clientLinking.getPort()+"\n");

            InputStream inStream = this.clientLinking.getInputStream();
            this.outStream=this.clientLinking.getOutputStream();

            BufferedReader clientin=new BufferedReader(new InputStreamReader(inStream));
            String message_in=clientin.readLine();
        
            while(!message_in.equals("disconnect")){
                System.out.println(message_in);
                this.in.append(message_in+"\n");
                message_in=clientin.readLine();
            }

            this.in.append("Linking disconnected!\n");
            clientLinking.close();
            
        }catch(IOException e){
            System.out.println("Error:"+e);
        }
    }
}
