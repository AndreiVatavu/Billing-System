/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfata;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import tema.*;

/**
 *
 * @author andre
 */
public class Upload extends JPanel
{
	public Gestiune gestiune = Gestiune.getInstance();
	
	// Upload page components
	public JTextField produse;
	public JTextField facturi;
	public JTextField taxe;
	public JTextField out;
	private JButton produse_button;
	private JButton facturi_button;
	private JButton taxe_button;
	private JButton out_button;

	public JButton load;
	
	public Upload ()
	{
		// Initializari
		
		// Label-uri
		JLabel produse_label = new JLabel ("Produse");
		JLabel facturi_label = new JLabel ("Facturi");
		JLabel taxe_label = new JLabel ("Taxe");
		JLabel out_label = new JLabel ("Output");
		JLabel titlu = new JLabel ( "Incarcare fisiere" );
		
		// Fontul label-urilor
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 40);

		produse_label.setFont ( font1 );
		facturi_label.setFont ( font1 );
		taxe_label.setFont ( font1 );
		out_label.setFont ( font1 );
		titlu.setFont ( font2 );
		
		// TextField
		
		produse = new JTextField ( 30 );
		facturi = new JTextField ( 30 );
		taxe = new JTextField ( 30 );
		out = new JTextField ( 30 );
		
		// Butoane
		produse_button = new JButton ( "Choose file" );
		facturi_button = new JButton ( "Choose file" );
		taxe_button = new JButton ( "Choose file" );
		out_button = new JButton ( "Choose file" );
		load = new JButton ( "Load" );

		
		// Coordonatele label-urilor
		int x = 120, y = 170;
		produse_label.setBounds ( x, y, 100, 30 );
		facturi_label.setBounds ( x, y + 60, 100, 30 );
		taxe_label.setBounds ( x, y + 120, 100, 30 );
		out_label.setBounds ( x, y + 180, 100, 30 );
		titlu.setBounds ( 230, 30, 400, 100 );

		
		// Adaug casutele text
		x += 100;
		produse.setText("src\\Resurse\\produse.txt");
		produse.setEnabled(false);
		produse.setBounds ( x, y, 300, 30 );
		
		facturi.setText("src\\Resurse\\facturi.txt");
		facturi.setEnabled ( false );
		facturi.setBounds ( x, y + 60, 300, 30 );
		
		taxe.setText("src\\Resurse\\taxe.txt");
		taxe.setEnabled ( false );
		taxe.setBounds ( x, y + 120, 300, 30 );
		
		out.setText("src\\Resurse\\out.txt");
		out.setEnabled ( false );
		out.setBounds ( x, y + 180, 300, 30 );
		
		// Coordonatele butoanelor
		x += 350;
		produse_button.setBounds ( x, y, 100, 30 );
		produse_button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
				  File selectedFile = fileChooser.getSelectedFile();
				  produse.setText ( selectedFile.getPath() );
				  produse_button.setBackground(Color.green);
				}
			}
		});
		facturi_button.setBounds ( x, y + 60, 100, 30 );
		facturi_button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
				  File selectedFile = fileChooser.getSelectedFile();
				  facturi.setText ( selectedFile.getPath() );
				}
			}
		});
		taxe_button.setBounds ( x, y + 120, 100, 30 );
		taxe_button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
				  File selectedFile = fileChooser.getSelectedFile();
				  taxe.setText ( selectedFile.getPath() );
				}
			}
		});
		out_button.setBounds ( x, y + 180, 100, 30 );
		out_button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
				  File selectedFile = fileChooser.getSelectedFile();
				  out.setText ( selectedFile.getPath() );
				}
			}
		});
		load.setBounds ( x - 220, y + 250, 130, 40 );
		load.setFont ( font1 );
		
		setBounds ( 0, 0, 800, 600 );
		setSize( new Dimension ( 800, 600 ) );
		setBackground(Color.white);
		setLayout( null );
		
		// Adaugarea componentelor
		add ( titlu );
		
		add ( produse_label );
		add ( produse );
		add ( produse_button );
		
		add ( facturi_label );
		add ( facturi );
		add ( facturi_button );
		
		add ( taxe_label );
		add ( taxe );
		add ( taxe_button );
		
		add ( out_label );
		add ( out );
		add ( out_button );
		
		add ( load );
	}
	
	Image background = new ImageIcon("src\\interfata\\resurse\\upload_background2.jpg").getImage();
	
	@Override
	public void paintComponent ( Graphics g )
	{
		g.drawImage ( background, 0, 0, 800, 600, this );
	}
}