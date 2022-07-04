import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;

public class clientDemo extends JFrame implements ActionListener{
    String message_out;
    JButton send_botton;
	JTextArea out = new JTextArea(1, 30);   //send box
	JTextArea in = new JTextArea(15, 30);   //receive box
	JPanel pan = new JPanel();

    BufferedReader clientin;

    OutputStream outStream;
    Socket a;

    public clientDemo(){
        super("ClientServer");    //JFrame(title)
		Border border = BorderFactory.createLineBorder(Color.orange, 1);
	    in.setBorder(border);
	    out.setBorder(border);
		send_botton = new JButton("Send");
		send_botton.addActionListener(this);     //onclick
        
		pan.setLayout(new FlowLayout());
		pan.add(in);
	    pan.add(out);
		pan.add(send_botton);

		add(pan);
		setSize(350,370);
		setVisible(true);


        try{
            a=new Socket("127.0.0.1",6666);
            outStream = a.getOutputStream();

            InputStream inStream = a.getInputStream();
            outStream=a.getOutputStream();
            clientin=new BufferedReader(new InputStreamReader(inStream));
        }catch(IOException e){
            System.out.println("Error"+e);
        }

        try{      //receiving messages
            String message_in=clientin.readLine();
        
            while(!message_in.equals("disconnect")){
                in.append(message_in+"\n");
                message_in=clientin.readLine();
            }
        }catch(IOException e){
            System.out.println("Error:"+e);
        }

        try{a.close();}catch(IOException e){System.out.println("Error:"+e);}
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            message_out=out.getText();
            outStream.write((message_out+"\n").getBytes());
            outStream.flush();
        }catch(IOException f){
            System.out.println("Error"+f);
        }
        out.setText("");
    }

    public static void main(String[] args) {
        clientDemo a = new clientDemo();
    }
}