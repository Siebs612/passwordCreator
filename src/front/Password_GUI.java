package front;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import back.GeneratePassword;
/** Password_GUI
 * 
 * @author Paul Siebenaler
 * @date 12/04/2023
 * @version 1.0
 * 
 * @apiNote
 * This is the GUI for the Generate password program.
 * This will take in the user input and generate a password based on the length given
 * 
 */
public class Password_GUI implements KeyListener {
	
	Font master = new Font("Arial", Font.BOLD, 34);
	JFrame main;
	JTextPane password_field;
	String pass;
	
	JButton generate;
	JTextField number;
	JButton exit;
	JPanel buttHolder;
	
	
	public Password_GUI() {
		main = new JFrame("Password Generator");
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main.setLayout(new BoxLayout(main.getContentPane(), BoxLayout.Y_AXIS));
		main.setLocationRelativeTo(null);
		
		password_field = new JTextPane();
		password_field.setText("Please Enter a number");
	
		password_field.setBackground(Color.WHITE);
		password_field.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		password_field.setEditable(false);
		password_field.setFont(master);
		
		
		buttHolder = new JPanel();
		buttHolder.setLayout(new GridLayout(1,4,10,2));
		
		generate = new JButton("Generate");
		generate.addActionListener(e-> getPassword());
		
		exit = new JButton("Exit");
		exit.addActionListener(e-> main.dispose());
		
		number = new JTextField();
		number.setPreferredSize(new Dimension(100,30));
		
		JLabel numName = new JLabel("#: ");
		numName.setFont(new Font("Arial", Font.PLAIN, 24));
		JPanel hold = new JPanel();
		hold.setLayout(new FlowLayout());
		hold.add(numName);
		hold.add(number);
		
		buttHolder.add(generate);
		buttHolder.add(hold);
		buttHolder.add(exit);
		
		main.add(Box.createVerticalStrut(10));
		main.add(Box.createHorizontalStrut(500));
		main.add(new JScrollPane(password_field));
		main.add(Box.createVerticalStrut(10));
		main.add(buttHolder);
		main.add(Box.createVerticalStrut(10));
		number.addKeyListener(this);
		
		main.pack();
		main.setVisible(true);
		
	}
	
	public void getPassword() {
		if (number.getText().isBlank()){
			number.setBackground(Color.RED);
			password_field.setText("Please Enter a number first");
			main.pack();
			return;
		}
		String input = number.getText();
		for (int i=0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				number.setText("");
				number.setBackground(Color.RED);
				number.setForeground(Color.WHITE);
				password_field.setText("Please Enter a number first");
				return;
			}
		}
		if(number.getBackground()== Color.RED) {
			number.setBackground(Color.WHITE);
			number.setForeground(Color.BLACK);
		}
		pass = GeneratePassword.createPassword( (Integer.valueOf(number.getText())) );
		password_field.setText(pass);
		main.pack();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getPassword();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
