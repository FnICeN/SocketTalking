import java.net.*;
import java.io.*;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.event.*;

public class aThread extends Thread implements ActionListener{
    private Socket clientLinking;
    private ServerSocket serverInter;
    private OutputStream outStream;
    private JTextArea in;
    private JTextArea out;
    private JButton send_botton;
    private String message_out;

    public aThread(ServerSocket serverInter,JTextArea in,JTextArea out,JButton send_botton){
        this.serverInter=serverInter;
        this.in=in;
        this.out=out;
        this.send_botton=send_botton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {        //when click the button
        
        try{
            this.message_out=this.out.getText();
            this.outStream.write(("Server："+this.message_out+"\n").getBytes());
            this.outStream.flush();
        }catch(IOException f){
            System.out.println("Error"+f);
        }
        // out.setText("");
    }

    @Override
    public void run() {
        System.out.println("now "+Thread.currentThread().getName()+"is running...");
        try{
            this.clientLinking = this.serverInter.accept();
            this.in.append("LINKING SUCCESS\n");
            this.in.append("IP:"+this.clientLinking.getInetAddress()+"\n");
            this.in.append("Port:"+this.clientLinking.getPort()+"\n");

            

            this.send_botton.addActionListener(this);     //onclick after connect

            InputStream inStream = this.clientLinking.getInputStream();
            this.outStream=this.clientLinking.getOutputStream();

            sockets add = new sockets(this.outStream);
            

            BufferedReader clientin=new BufferedReader(new InputStreamReader(inStream));
            String message_in=clientin.readLine();
        
            while(!message_in.equals("disconnect")){
                this.in.append(message_in+"\n");

                // System.out.println("sending"+message_in);
                // this.outStream.write((message_in+"\n").getBytes());  //server transmit
                // this.outStream.flush();

                for(int i=0;i<sockets.index;i++){
                    System.out.println(i);
                    sockets.outStreams[i].write((message_in+"\n").getBytes());
                    sockets.outStreams[i].flush();
                }

                message_in=clientin.readLine();
            }

            this.in.append("Linking disconnected!\n");
            clientLinking.close();
            
        }catch(IOException e){
            System.out.println("Error:"+e);
        }
    }
}
