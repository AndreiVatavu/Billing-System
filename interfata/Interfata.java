/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfata;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;


/**
 *
 * @author andre
 */


public class Interfata extends JFrame
{

	
	// Afisare & Administrare page components
	
	
	
	public Interfata ( String window_name )
	{
		super ( window_name );
		
		 try {
            // Set System L&F
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
		   // handle exception
		}
		catch (ClassNotFoundException e) {
		   // handle exception
		}
		catch (InstantiationException e) {
		   // handle exception
		}
		catch (IllegalAccessException e) {
		   // handle exception
		}
		
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setResizable(false);
		setMinimumSize ( new Dimension( 800, 600 ) );
		getContentPane().setBackground(Color.lightGray);
		setLayout ( null );
		
		// Taburile
		JTabbedPane jtp = new JTabbedPane();
		
		jtp.setBounds ( 0, 0, 800, 600 );
		
		// Setez dimensiunea butoanelor si adaug pagina de upload
		Upload up = new Upload();
		AfisareAdministrare af = new AfisareAdministrare();
		up.load.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				PrintStream fileStream;
				try
				{
					fileStream = new PrintStream ( up.out.getText() );
					
					up.gestiune.setFileProduse( up.produse.getText() );
					up.gestiune.setFileFacturi ( up.facturi.getText() );
					up.gestiune.setFileTaxe ( up.taxe.getText() );
					up.gestiune.setFileOut ( up.out.getText() );
					up.gestiune.readDate();
					
					fileStream.print ( up.gestiune.magazine );
					fileStream.close();
					
					af.scroll.setVisible(false);
					af.scroll = af.createTable ( af.gestiune );
					af.add ( af.scroll );
					
					LoadSucces succes = new LoadSucces ();
					
					//Interfata interfata = new Interfata ( "Interfata" );
					
				} catch (FileNotFoundException ex)
				{
					Logger.getLogger(Interfata.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		jtp.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Upload</body></html>", up );
		
		
		JPanel administrare = new JPanel ();
		administrare.setBounds ( 0, 0, 800, 600 );
		
		jtp.addTab("Afisare & Administrare", af );
		jtp.addTab("Statistici", new Statistici() );
		
		
		add ( jtp );
		//add ( p );
		show();
		pack();
	}

	class LoadSucces extends JFrame
	{
		private JButton okButton = new JButton ("OK");
		private JLabel text = new JLabel ("<html><center>Fisierele au fost<br/>incarcate cu succes<center/></html>");
		public LoadSucces ()
		{
			super ( "Succes" );
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			setMinimumSize ( new Dimension( 200, 200 ) );
			getContentPane().setBackground(Color.white);
			setLayout ( null );

			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			text.setFont ( font1 );

			text.setBounds ( 16, 0, 200, 100 );

			okButton.setBounds ( 45, 100, 100, 40 );
			okButton.setFont ( font1 );
			okButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					dispose();
				}
			});

			add ( text );
			add ( okButton );

			show();
			pack();
		}
	}
}