// Network Text Server

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class netserver implements ActionListener{
	// Properties
	JFrame theframe = new JFrame("Network Server");
	JPanel thepanel = new JPanel();
	JTextArea thearea = new JTextArea();
	JScrollPane thescroll = new JScrollPane(thearea);
	JTextField thefield = new JTextField();
	SuperSocketMaster ssm;
	
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thefield){
			System.out.println("Going to send this out over network:  "+thefield.getText());
			
			ssm.sendText(thefield.getText());
			
			thefield.setText("");
			
		}else if(evt.getSource() == ssm){
			String strData;
			strData = ssm.readText();
			thearea.append(strData + "\n");
		}
	}
	
	// Constructor
	public netserver(){
		thepanel.setLayout(null);
		thepanel.setPreferredSize(new Dimension(400,500));
		
		thescroll.setSize(400,400);
		thescroll.setLocation(0,0);
		
		thepanel.add(thescroll);
		
		thefield.setSize(400,100);
		thefield.setLocation(0,400);
		thefield.addActionListener(this);
		
		thepanel.add(thefield);
		
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setVisible(true);
	
		ssm = new SuperSocketMaster(1337, this);
		System.out.println("My server ip is: "+ssm.getMyAddress());
		ssm.connect();
	}
	
	
	// Main Method
	public static void main(String[] args){
		new netserver();
	}
}
