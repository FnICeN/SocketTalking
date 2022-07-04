import java.net.*;
import java.awt.*;
import java.io.*;

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
            ServerSocket serverInter1 = new ServerSocket(6666);
            ServerSocket serverInter2 = new ServerSocket(6667);
            aThread v1 = new aThread(serverInter1,in,out,send_botton);
            v1.start();
            aThread v2 = new aThread(serverInter2,in,out,send_botton);
            v2.start();

            
        }catch(IOException e){
            System.out.println("Error:"+e);
        }
        

    }



    public static void main(String[] args) {
        serverDemo a=new serverDemo();
    }

}
