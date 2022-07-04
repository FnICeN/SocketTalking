import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.border.Border;
public class serverDemo extends JFrame{
    String message_out;

	JButton send_botton;
	JTextArea out = new JTextArea(1, 30);   //send box
	JTextArea in = new JTextArea(15, 30);   //receive box
	JPanel pan = new JPanel();

    BufferedReader clientin;

    OutputStream outStream;
    Socket clientLinking;          //to close()
    
    public serverDemo(int i){} 

    public serverDemo(){      //create UI
		super("ChatServer");    //JFrame(title)
		Border border = BorderFactory.createLineBorder(Color.blue, 1);
	    in.setBorder(border);
	    out.setBorder(border);
		send_botton = new JButton("Send");
		// send_botton.addActionListener(this);     //onclick

		pan.setLayout(new FlowLayout());
		pan.add(in);
	    pan.add(out);
		pan.add(send_botton);

		add(pan);
		setSize(350,370);
		setVisible(true);

        try{                    //try linking and get stream
            ServerSocket serverInter = new ServerSocket(6666);
            clientLinking = serverInter.accept();
            in.setText("LINKING SUCCESS\n");
            in.append("IP:"+clientLinking.getInetAddress()+"\n");
            in.append("Port:"+clientLinking.getPort()+"\n");

            int port=clientLinking.getPort();
            aThread a = new aThread(port,clientLinking,in,out,send_botton);
            Thread v1 = new Thread(a,"v1");
            v1.start();

            // InputStream inStream = clientLinking.getInputStream();
            // outStream=clientLinking.getOutputStream();

            // clientin=new BufferedReader(new InputStreamReader(inStream));
            
        }catch(IOException e){
            System.out.println("Error:"+e);
        }
        
        
        
        // try{      //receiving messages
        //     String message_in=clientin.readLine();
        
        //     while(!message_in.equals("disconnect")){
        //         in.append(message_in+"\n");
        //         message_in=clientin.readLine();
        //     }
        // }catch(IOException e){
        //     System.out.println("Error:"+e);
        // }
        
        // in.append("Linking disconnected!\n");
        
        // try{clientLinking.close();}catch(IOException e){System.out.println("Error:"+e);}

    }

    // @Override
    // public void actionPerformed(ActionEvent e) {        //when click the button
    //     System.out.println("aaa");
    //     try{
    //         message_out=out.getText();
    //         outStream.write((message_out+"\n").getBytes());
    //         outStream.flush();
    //     }catch(IOException f){
    //         System.out.println("Error"+f);
    //     }
    //     out.setText("");;
    // }


    public static void main(String[] args) {
        serverDemo a=new serverDemo();
    }

}
