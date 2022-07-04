import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;

public class clientDemo extends JFrame implements ActionListener{
    JButton send_botton;
	JTextArea out = new JTextArea(1, 30);   //send box
	JTextArea in = new JTextArea(15, 30);   //receive box
	JPanel pan = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hi");
    }

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
    }

    public static void main(String[] args) {
        clientDemo a = new clientDemo();
        // try{
        //     Scanner sc = new Scanner(System.in);

        //     Socket a=new Socket("127.0.0.1",6666);
        //     OutputStream put = a.getOutputStream();

        //     String input = sc.nextLine();
        //     while(!input.equals("disconnect")){
        //         put.write((input+"\n").getBytes());
        //         put.flush();
        //         input=sc.nextLine();
        //     }
        //     put.write(input.getBytes());
        //     put.flush();
        //     a.close();
        //     sc.close();
        // }catch(IOException e){
        //     System.out.println("Error"+e);
        // }
    }
}