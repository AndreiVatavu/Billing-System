/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfata;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author andre
 */
public class LogIn extends JFrame
{
	private JButton button;
	private JButton creareCont;

	private JTextField user;
	private JPasswordField pass;
	
	public LogIn ( String window_name )
	{
		super ( window_name );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setMinimumSize ( new Dimension( 800, 600 ) );
		getContentPane().setBackground(Color.white);
		setLayout ( new GridBagLayout() );
		
		// Imaginea
		JLabel background = new JLabel ( new ImageIcon("src\\interfata\\resurse\\lock.png") );
		
		//background.setBounds ( 0, 0, 100, 100 );
		GridBagConstraints con = new GridBagConstraints();
		add(background, con);
		con.gridy = 1;
		con.ipady = 100;
		
		
		// Panel-ul de log in
		JPanel p = new JPanel();
		p.setSize( new Dimension ( 100, 100 ) );
		p.setBackground(Color.white);
		p.setLayout( new GridBagLayout() );
		
		GridBagConstraints c = new GridBagConstraints();	
		
		c.ipadx = 10;
		
		// Username
		JLabel user_label = new JLabel ("Username");
		user = new JTextField(15);
		p.add ( user_label, c );
		p.add (user, c);
		
		// Password
		JLabel pass_label = new JLabel ("Password");
		pass = new JPasswordField (15);
		c.gridy = 1;
		p.add ( pass_label, c );
		p.add (pass, c);
		
		// Log in button
		button = new JButton ( "Log in" );
		button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					RandomAccessFile file = new RandomAccessFile ( "src\\resurse\\login.txt", "r" );
					String s = file.readLine();
					boolean exista = false;
					// Cauta in baza de date daca exista un cont cu userul si parola introduse
					while ( s != null )
					{
						StringTokenizer st = new StringTokenizer ( s, ":" );
						if ( user.getText().equals( st.nextToken() ) && pass.getText().equals(st.nextToken()) )
						{
							exista = true;
							dispose();
							Interfata interfata = new Interfata ( "Interfata" );
						}
						s = file.readLine();
					}
					
					if ( !exista )
					{
						JFrame f = new JFrame("Nume si parola gresite");
						f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						f.setResizable(false);
						f.setMinimumSize ( new Dimension( 300, 100 ) );
						f.getContentPane().setBackground(Color.white);
						f.setLayout ( new GridBagLayout() );
						
						JButton okButton = new JButton ( "OK" );
						okButton.setFont ( new Font("SansSerif", Font.PLAIN, 15) );
						
						okButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae)
							{
								f.dispose();
							}
						});

						f.add ( okButton );
						
						f.show();
					}
					
				} catch (FileNotFoundException ex)
				{
					Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex)
				{
					Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		
		});
		
		creareCont = new JButton ( "Creare Cont" );
		creareCont.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				JLabel lUser;
				JLabel lPass;

				JTextField tUser;
				JTextField tPass;
		
				JButton creare = new JButton( "Creare cont" );
				
				JFrame f = new JFrame("Creare cont");
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setResizable(false);
				f.setMinimumSize ( new Dimension( 300, 200 ) );
				f.getContentPane().setBackground(Color.white);
				f.setLayout ( new GridBagLayout() );
				
				GridBagConstraints c = new GridBagConstraints();	
		
				c.ipadx = 10;

				// Username
				lUser = new JLabel ("Username");
				tUser = new JTextField(15);
				f.add ( lUser, c );
				f.add ( tUser, c );

				// Password
				lPass = new JLabel ("Password");
				tPass = new JPasswordField (15);
				c.gridy = 1;
				f.add ( lPass, c );
				f.add (tPass, c);
				
				creare.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae)
					{
						try
						{
							// Numele si parola introduse
							String newUsername = tUser.getText();
							String newPass = tPass.getText();
							String newAccount = newUsername + ":" + newPass;
							System.out.println (newAccount);
							boolean exista = false;
							
							// Verific daca nu cumva se afla deja in baza de date
							RandomAccessFile file = new RandomAccessFile ( "src\\resurse\\login.txt", "r" );
							String s = file.readLine();
							while ( s != null )
							{
								StringTokenizer st = new StringTokenizer ( s, ":" );
								if ( st.nextToken().equals ( newUsername ) )
									exista = true;
								s = file.readLine();
							}
							
							if ( !exista )
							{
								PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src\\resurse\\login.txt", true)));
								out.print ( "\r\n" + newAccount );
								out.flush();
								out.close();
							}
								
        
							
							f.dispose();
						} catch (FileNotFoundException ex)
						{
							Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex)
						{
							Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				});
				
				c.gridy = 2;
				f.add ( new JLabel ( " " ), c );
				
				c.gridy = 3;
				c.gridx = 1;
				f.add ( creare, c );
				
				f.show();
			}
		
		});
		
		c.gridy = 2;
		c.gridx = 1;
		p.add (button, c);
		
		c.gridy = 3;
		p.add ( new JLabel(" "), c );
		c.gridy = 4;
		p.add ( new JLabel(" "), c );
		c.gridy = 5;
		p.add (creareCont, c);
		
		// Adaugarea panel-ului de log in
		add (p, con);
		
		show();
		pack();	
	}
	
	
	
	public static void main(String[] args)
	{
		LogIn text_window = new LogIn ( "Log in" );
	}
}
