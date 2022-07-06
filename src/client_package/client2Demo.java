package client_package;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;

public class client2Demo extends JFrame implements ActionListener{
    static int ID=1;
    String message_out;
    JButton send_button;
    JButton select_button;
    JTextArea user = new JTextArea("user2",1,30);   //username
	JTextArea out = new JTextArea(1, 30);   //send box
	JTextArea in = new JTextArea(15, 30);   //receive box
	JPanel pan = new JPanel();

    BufferedReader clientin;

    OutputStream outStream;
    Socket a;


    public client2Demo(){
        super("ChatClient");    //JFrame(title);
		Border border = BorderFactory.createLineBorder(Color.orange, 1);
        user.setBorder(border);
	    in.setBorder(border);
	    out.setBorder(border);
		send_button = new JButton("发送");
        select_button = new JButton("私聊");
		send_button.addActionListener(this);     //onclick
        select_button.addActionListener(new selectDo2(this));
        
		pan.setLayout(new FlowLayout());
        pan.add(user);
		pan.add(in);
	    pan.add(out);
		pan.add(send_button);
        pan.add(select_button);

		add(pan);
		setSize(350,370);
		setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        try{
            a=new Socket("127.0.0.1",6667);
            outStream = a.getOutputStream();
            InputStream inStream = a.getInputStream();
            // new sockets(outStream,inStream,"user2",ID);
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
            outStream.write((selectUser.temp+user.getText()+"： "+message_out+"\n").getBytes());
            outStream.flush();
        }catch(IOException f){
            System.out.println("Error"+f);
        }
        out.setText("");
    }

    public static void main(String[] args) {
        new client2Demo();
    }
}


class selectDo2 implements ActionListener{
    client2Demo a;
    public selectDo2(client2Demo a){
        this.a=a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new selectUser(a.user.getText());
    }
}