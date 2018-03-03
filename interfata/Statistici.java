/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfata;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import tema.*;

/**
 *
 * @author andre
 */
public class Statistici extends JPanel
{
	private JTextArea textArea = new JTextArea();
	
	public Statistici ()
	{
		setBounds ( 0, 0, 800, 600 );
		setSize( new Dimension ( 800, 600 ) );
		setBackground(Color.white);
		setLayout( null );
		
		Gestiune gestiune = Gestiune.getInstance();
		textArea.setEditable(false);
		textArea.setText( gestiune.getTopMagazin() );
		
		String[] combo = {  "Magazinul cu cele mai mari vanzari", 
							"Magazinul cu cele mai mari vanzari pentru fiecare tara in parte", 
							"Magazinul cu cele mai mari vanzari pentru fiecare categorie in parte",
							"Factura cu suma totala cea mai mare"};
		
		JComboBox select = new JComboBox(combo);
		select.setBounds ( 200, 10, 400, 25 );
		select.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				switch ( select.getSelectedIndex() )
				{
					case 0:
						textArea.setText( gestiune.getTopMagazin() );
						break;
					
					case 1:
						textArea.setText( gestiune.topMagTari() );
						break;
					
					case 2:
						textArea.setText( gestiune.topMagCateg() );
						break;
					
					case 3:
						textArea.setText( gestiune.getTopFactura() );
						break;
				}
			}
			
		});
		
		
		JScrollPane scroll = new JScrollPane ( textArea );
		scroll.setBounds ( 150, 50, 502, 475 );
		
		add ( select );
		add ( scroll );
		show();
	}
	
	Image background = new ImageIcon("src\\interfata\\resurse\\statistics_background.jpg").getImage();
	
	@Override
	public void paintComponent ( Graphics g )
	{
		g.drawImage ( background, 0, 0, 800, 600, this );
	}
}
