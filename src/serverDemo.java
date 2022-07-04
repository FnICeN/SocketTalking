import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
public class serverDemo extends JFrame implements ActionListener{
	JButton send_botton;
	JTextArea out = new JTextArea(1, 30);   //send box
	JTextArea in = new JTextArea(15, 30);   //receive box
	JPanel pan = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {        //when click the button
        System.out.println("hello");
    }

    public serverDemo(){      //create UI
		super("ChatServer");    //JFrame(title)
		Border border = BorderFactory.createLineBorder(Color.blue, 1);
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
        serverDemo a=new serverDemo();
        // BufferedReader clientin;
        // try{
        //     ServerSocket a = new ServerSocket(6666);

        //     Socket client=a.accept();
        //     System.out.println("connection suc!");
        //     System.out.println("IP:"+client.getInetAddress());
        //     System.out.println("Port:"+client.getPort());

        //     InputStream in = client.getInputStream();
        //     clientin = new BufferedReader(new InputStreamReader(in));

        //     String things=clientin.readLine();
        //     while(!things.equals("disconnect")){
        //         System.out.println(things);
        //         things=clientin.readLine();
        //     }
            
        //     System.out.println("connection dis!");
        //     a.close();
        // }catch(IOException e){
        //     System.out.println("Error:"+e);
        // }
    }

}
