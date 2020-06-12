import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


public class Server extends JFrame implements ActionListener {

	JPanel p1;
	JTextField t1;
	JButton b1;
	static JTextArea a1;

	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	Server() {
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		a1 = new JTextArea();
		a1.setBounds(2, 2, 340, 536);
		a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		add(a1);

		t1 = new JTextField();
		t1.setBounds(2, 540, 270, 30);
		t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		add(t1);

		b1 = new JButton("Send");
		b1.setBounds(273, 540, 70, 30);
		b1.setBackground(Color.WHITE);
		b1.addActionListener(this);
		add(b1);

		getContentPane().setBackground(Color.BLACK);
		setLayout(null);
		setSize(350, 600);
		setLocation(400, 200);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			String msg = t1.getText();
			a1.setText(a1.getText() + "\t\t\tYou: " + msg + "\n");
			dout.writeUTF(msg);
			t1.setText("");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Server().setVisible(true);

		String msginput = "";
		try {
			skt = new ServerSocket(6321);
			while (true) {
				s = skt.accept();
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				while (true) {
					msginput = din.readUTF();
					a1.setText(a1.getText() + "\nFriend: " + msginput);
					
				}
			}

		} catch (Exception e) {
		}
	}
}
