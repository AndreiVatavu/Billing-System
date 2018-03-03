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
public class AfisareAdministrare extends JPanel
{
	JTable table;
	public JScrollPane scroll;
	private JPanel panel;
	
	
	private JButton adauga;
	private JButton sterge;
	private JButton editare;
	private JButton sortare;
	private JButton cautare;
	private JButton reload;
	
	
	public Gestiune gestiune = Gestiune.getInstance();
	
	public AfisareAdministrare ()
	{
		setBounds ( 0, 0, 800, 600 );
		setSize( new Dimension ( 800, 600 ) );
		setBackground(Color.white);
		setLayout( null );
		
		panel = this;
		
		JLabel titlu = new JLabel ("Lista Produse");
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.PLAIN, 15);
		titlu.setFont ( font1 );
		titlu.setBounds ( 200, 10, 200, 30 );
		
		// Administrare
		
		// Butoane
		scroll = createTable ( gestiune );
		
		// Adaugarea unui produs
		adauga = new JButton ( "Adaugare Produs" );
		adauga.setBounds ( 600, 100, 150, 40 );
		adauga.setFont ( font2 );
		adauga.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
//				gestiune.readDate();
//				
//				scroll.setVisible(false);
//				scroll = createTable ( gestiune );
				AddProdus f = new AddProdus( "Adaugare produs");
			//	add ( scroll );
			}
		});
		
		// Stergerea unui produs
		sterge = new JButton ( "Stergere Produs" );
		sterge.setBounds ( 600, 100 + 60, 150, 40 );
		sterge.setFont ( font2 );
		sterge.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				StergereProdus delWin = new StergereProdus();
			}
		});
		
		// Editarea unui produs
		editare = new JButton ( "Editare Produs" );
		editare.setBounds ( 600, 100 + 120, 150, 40 );
		editare.setFont ( font2 );
		editare.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				EditareProdus delWin = new EditareProdus();
			}
		});
		
		// Sortarea produselor
		sortare = new JButton ( "Sortare" );
		sortare.setBounds ( 600, 100 + 180, 150, 40 );
		sortare.setFont ( font2 );
		sortare.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				Collections.sort(gestiune.produse, new ComparatorProduse() );
				
				// Actualizez tabelul
				scroll.setVisible(false);
				scroll = createTable ( gestiune );
				add ( scroll );
			}
		});
		
		cautare = new JButton ( "Cautare" );
		cautare.setBounds ( 600, 100 + 240, 150, 40 );
		cautare.setFont ( font2 );
		cautare.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				CautareProdus cautareWindow = new CautareProdus();
			}
		});
		
		reload = new JButton ( "Reload" );
		reload.setBounds ( 600, 100 + 300, 150, 40 );
		reload.setFont ( font2 );
		reload.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{				
				// Actualizez tabelul
				scroll.setVisible(false);
				scroll = createTable ( gestiune );
				add ( scroll );
			}
		});
		
		add ( titlu );
		add ( scroll );
		
		add ( adauga );
		add ( sterge );
		add ( editare );
		add ( sortare );
		add ( cautare );
		add ( reload );
		
		revalidate();
	}
	
	public JScrollPane createTable ( Gestiune gestiune )
	{
		// Coloanele
		Object[] columnNames = {"Denumire",
                        "Categorie",
                        "Tara",
                        "Pret"};
		
		// Crearea unei matrici cu detaliile unui produs
		Object[][] data = new Object[100][4];
		int i = 0;
		for ( Produs produs : gestiune.produse )
		{
			data[i][0] = produs.getDenumire();
			data[i][1] = produs.getCategorie();
			data[i][2] = produs.getTaraOrigine();
			data[i][3] = produs.getPret();
			i++;
		}
		// Crearea tabelului cu produse
		table = new JTable(data, columnNames);
		table.getTableHeader().setReorderingAllowed ( false );
		
		JScrollPane scroll = new JScrollPane ( table );
		scroll.setBounds ( 10, 50, 550, 475 );
		
		return scroll;
	}
	
	public JScrollPane createTable ( Gestiune gestiune,	String denumire )
	{
		// Coloanele
		Object[] columnNames = {"Denumire",
                        "Categorie",
                        "Tara",
                        "Pret"};
		
		// Crearea unei matrici cu detaliile unui produs
		Object[][] data = new Object[100][4];
		int i = 0;
		for ( Produs produs : gestiune.produse )
		{
			if ( produs.getDenumire().equals(denumire) )
			{
				data[i][0] = produs.getDenumire();
				data[i][1] = produs.getCategorie();
				data[i][2] = produs.getTaraOrigine();
				data[i][3] = produs.getPret();
				i++;
			}
		}
		// Crearea tabelului cu produse
		table = new JTable(data, columnNames);
		table.getTableHeader().setReorderingAllowed ( false );
		
		JScrollPane scroll = new JScrollPane ( table );
		scroll.setBounds ( 10, 50, 550, 475 );
		
		return scroll;
	}
	
	// Cautarea unui produs
	class CautareProdus extends JFrame
	{
		private JLabel denumire;
		private JTextField produs;
		private JButton cauta;
		
		public CautareProdus()
		{
			super ( "Cautare" );
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			setMinimumSize ( new Dimension( 360, 300 ) );
			getContentPane().setBackground(Color.white);
			setLayout ( null );
			
			Font font2 = new Font("SansSerif", Font.PLAIN, 15);
			
			denumire = new JLabel ( "Denumire produs" );
			denumire.setFont ( font2 );
			denumire.setBounds ( 10, 70, 140, 30 );
			
			produs = new JTextField();
			produs.setBounds ( 140, 70, 200, 30 );
			produs.setFont ( font2 );
			
			cauta = new JButton ( "Cautare" );
			cauta.setBounds ( 85, 150, 150, 40 );
			cauta.setFont ( font2 );
			cauta.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String denumire = produs.getText();
				if ( !denumire.equals("") )
				{
					// Actualizez tabelul
					scroll.setVisible(false);
					scroll = createTable ( gestiune, denumire );
					panel.add ( scroll );
				}
				dispose();
			}
		});
			
			add ( denumire );
			add ( produs );
			add ( cauta );
			
			show();
		}
	}
	
	// Adaugare unui produs
	class AddProdus extends JFrame
	{
		private JLabel lDenumire;
		private JLabel lCategorie;
		private JLabel lTara;
		private JLabel lPret;

		private JTextField tDenumire;
		private JTextField tCategorie;
		private JTextField tTara;
		private JTextField tPret;

		private JButton add;
		private JButton cancelButton = new JButton ( "Cancel" );

		public AddProdus ( String window_name )
		{
			super ( window_name );
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			setMinimumSize ( new Dimension( 340, 320 ) );
			getContentPane().setBackground(Color.white);
			setLayout ( null );

			// Labelurile cu text
			int x = 10, y = 20;
			Font font2 = new Font("SansSerif", Font.PLAIN, 15);
			lDenumire = new JLabel ( "Denumire" );
			lDenumire.setBounds ( x, y, 80, 30 );
			lDenumire.setFont ( font2 );

			lCategorie = new JLabel ( "Categorie" );
			lCategorie.setBounds ( x, y + 40, 80, 30 );
			lCategorie.setFont ( font2 );

			x += 30;
			lTara = new JLabel ( "Tara" );
			lTara.setBounds ( x, y + 80, 50, 30 );
			lTara.setFont ( font2 );

			lPret = new JLabel ( "Pret" );
			lPret.setBounds ( x, y + 120, 50, 30 );
			lPret.setFont ( font2 );

			// Campurile text
			x += 50;
			tDenumire = new JTextField("");
			tDenumire.setBounds ( x, y, 200, 30 );
			tDenumire.setFont ( font2 );

			tCategorie = new JTextField ("");
			tCategorie.setBounds ( x, y + 40, 200, 30 );
			tCategorie.setFont ( font2 );

			tTara = new JTextField ("");
			tTara.setBounds ( x, y + 80, 200, 30 );
			tTara.setFont ( font2 );

			tPret = new JTextField ("");
			tPret.setBounds ( x, y + 120, 200, 30 );
			tPret.setFont ( font2 );
			
			cancelButton.setBounds ( 95, 235, 150, 40 );
			cancelButton.setFont ( font2 );
			cancelButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					dispose();
				}
			});
			
			// Butonul de adaugare
			add = new JButton ( "Adaugare Produs" );
			add.setFont ( font2 );
			add.setBounds ( 95, 190, 150, 40 );
			add.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					String denumire = tDenumire.getText();
					String categorie = tCategorie.getText();
					String tara = tTara.getText();
					String pret = tPret.getText();
					boolean exista = false;
					boolean add = true;
					
					if ( !( denumire.equals("") && categorie.equals("") && tara.equals("") && pret.equals("") ) )
					{
						try {
							// Deschid fisierul ce contine produsele
							RandomAccessFile input = new RandomAccessFile ( gestiune.getFileProduse(), "r" );
							
							// Citesc prima linie
							String line = input.readLine();
							String s = line + "\r\n";
							line = input.readLine();
							
							while ( line != null )
							{
								// Extrag informatiile unui produs
								StringTokenizer st = new StringTokenizer ( line );
								String newLine = "";
								String fDenumire = st.nextToken();
								String fCategorie = st.nextToken();
								// Verific daca produsul selectat se afla pe linia curenta
								if ( fDenumire.equals ( denumire ) && fCategorie.equals ( categorie ) )
								{
									newLine = newLine + fDenumire + " " + fCategorie;
									int i;
									// Daca da, verific daca se afla in tara dorita
									for ( i = 0; i < gestiune.tari.size(); i++ )
									{
										if ( gestiune.tari.get(i).equals(tara) )
										{
											add = false;
											// Verific daca produsul exista deja (daca pretul este diferit de -1)
											String pret2 = st.nextToken();
											if ( pret2.equals("-1") )
											{
												exista = false;
												pret2 = pret;
											}
											else
												exista = true;
											newLine = newLine + " " + pret2;
										}
										else
										{
											String pret2 = st.nextToken();
											newLine = newLine + " " + pret2;
										}
									}
									s = s + newLine + "\r\n";
								}
								else
									s = s + line + "\r\n";
								// Citesc urmatoarea linie
								line = input.readLine();
							}
							
							if ( add )
							{
								s = s + denumire + " " + categorie;
								for ( int i = 0; i < gestiune.tari.size(); i++ )
								{
									if ( gestiune.tari.get(i).equals(tara) )
										s = s + " " + pret;
									else
										s = s + " " + "-1";
								}
								
							}
							
							// Actualizez gestiunea
							PrintStream output = new PrintStream ( gestiune.getFileProduse() );
							output.print ( s );
							
							gestiune.readDate();
							PrintStream output2 = new PrintStream ( gestiune.getFileOutput() );
							output2.print ( gestiune.magazine );

							output.close();
							output2.close();
							
							// Actualizez tabelul
							gestiune.readDate();
							scroll.setVisible(false);
							scroll = createTable ( gestiune );
							panel.add ( scroll );
							
							if ( exista )
							{
								MsjAddProdus asd = new MsjAddProdus ( false, false );
							}
							else
							{
								MsjAddProdus asd = new MsjAddProdus ( true, false );
							}
							
						} catch (FileNotFoundException ex) {
							Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex)
						{
							Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
					dispose();
				}
			});

			add ( lDenumire );
			add ( tDenumire );

			add ( lCategorie );
			add ( tCategorie );

			add ( lTara );
			add ( tTara );

			add ( lPret );
			add ( tPret );

			add ( add );
			add ( cancelButton );
			
			show();
		}
	}
	
	class StergereProdus extends JFrame
	{
		private JButton daButton = new JButton ("DA");
		private JButton nuButton = new JButton ("NU");

		private JLabel text = new JLabel ("<html><center>Sunteti sigur ca doriti<br/>sa stergeti produsul selectat? <center/></html>");
		public StergereProdus ( )
		{
			super ( "Stergere" );

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			setMinimumSize ( new Dimension( 300, 200 ) );
			getContentPane().setBackground(Color.white);
			setLayout ( null );

			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			text.setFont ( font1 );

			text.setBounds ( 40, 0, 200, 100 );

			daButton.setBounds ( 30, 100, 100, 40 );
			daButton.setFont ( font1 );
			daButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						// Obtin linia selectata din tabel
						int row = table.getSelectedRow();
						// Obtin datele din linia respectiva
						String denumire = table.getModel().getValueAt(row, 0).toString();
						String categorie = table.getModel().getValueAt(row, 1).toString();
						String tara = table.getModel().getValueAt(row, 2).toString();

						// Deschid fisierul ce contine produsele
						RandomAccessFile input = new RandomAccessFile ( gestiune.getFileProduse(), "r" );

						// Citesc prima linie
						String line = input.readLine();
						String s = "";

						while ( line != null )
						{
							// Extrag informatiile unui produs
							StringTokenizer st = new StringTokenizer ( line );
							String newLine = "";
							String fDenumire = st.nextToken();
							String fCategorie = st.nextToken();
							// Verific daca produsul selectat se afla pe linia curenta
							if ( fDenumire.equals ( denumire ) && fCategorie.equals ( categorie ) )
							{
								boolean sterge = true;
								newLine = newLine + fDenumire + " " + fCategorie;
								int i;
								// Daca da ii schimb pretul in -1
								for ( i = 0; i < gestiune.tari.size(); i++ )
								{
									String pret = st.nextToken();
									if ( gestiune.tari.get(i).equals(tara) )
										newLine = newLine + " " + "-1";
									else
									{
										if ( !pret.equals("-1") )
											sterge = false;
										newLine = newLine + " " + pret;
									}
								}
								// Daca produsul are pretul -1 in toate tarile, voi sterge linia respectiva din fisier
								if ( !sterge )
									s = s + newLine + "\r\n";
							}
							else
								s = s + line + "\r\n";
							// Citesc urmatoarea linie
							line = input.readLine();
						}

						// Actualizez gestiunea
						PrintStream output = new PrintStream ( gestiune.getFileProduse() );
						output.print ( s );
						gestiune.readDate();
						PrintStream output2 = new PrintStream ( gestiune.getFileOutput() );
						output2.print ( gestiune.magazine );

						output.close();
						output2.close();

						// Actualizez tabelul
						scroll.setVisible(false);
						scroll = createTable ( gestiune );
						panel.add ( scroll );

					} catch (FileNotFoundException ex)
					{
						Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex)
					{
						Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
					}
					dispose();
				}
			});
			
			nuButton.setBounds ( 150, 100, 100, 40 );
			nuButton.setFont ( font1 );
			nuButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					dispose();
				}
			});

			add ( text );
			add ( daButton );
			add ( nuButton );

			show();
			pack();
		}
	}
	
	class EditareProdus extends JFrame
	{
		private JButton editareButton = new JButton ("Editare");
		private JButton cancelButton = new JButton ("Cancel");
		
		private JLabel lDenumire;
		private JLabel lCategorie;
		private JLabel lTara;
		private JLabel lPret;

		private JTextField tDenumire;
		private JTextField tCategorie;
		private JTextField tTara;
		private JTextField tPret;

		public EditareProdus ( )
		{
			super ( "Editare" );

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			setMinimumSize ( new Dimension( 340, 320 ) );
			getContentPane().setBackground(Color.white);
			setLayout ( null );

			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			
			// Labelurile cu text pt produsul selectat
			int x = 10, y = 20;
			Font font2 = new Font("SansSerif", Font.PLAIN, 15);
			lDenumire = new JLabel ( "Denumire" );
			lDenumire.setBounds ( x, y, 80, 30 );
			lDenumire.setFont ( font2 );

			lCategorie = new JLabel ( "Categorie" );
			lCategorie.setBounds ( x, y + 40, 80, 30 );
			lCategorie.setFont ( font2 );

			x += 30;
			lTara = new JLabel ( "Tara" );
			lTara.setBounds ( x, y + 80, 50, 30 );
			lTara.setFont ( font2 );

			lPret = new JLabel ( "Pret" );
			lPret.setBounds ( x, y + 120, 50, 30 );
			lPret.setFont ( font2 );

			// Campurile text
			// Linia selectata
			int row = table.getSelectedRow();
			// Obtin datele din linia respectiva
			String denumire = table.getModel().getValueAt(row, 0).toString();
			String categorie = table.getModel().getValueAt(row, 1).toString();
			String tara = table.getModel().getValueAt(row, 2).toString();
			String pret = table.getModel().getValueAt(row, 3).toString();
			x += 50;
			tDenumire = new JTextField("");
			tDenumire.setBounds ( x, y, 200, 30 );
			tDenumire.setFont ( font2 );
			tDenumire.setText (denumire);

			tCategorie = new JTextField ("");
			tCategorie.setBounds ( x, y + 40, 200, 30 );
			tCategorie.setText ( categorie );
			tCategorie.setFont ( font2 );

			tTara = new JTextField ("");
			tTara.setBounds ( x, y + 80, 200, 30 );
			tTara.setText ( tara );
			tTara.setFont ( font2 );

			tPret = new JTextField ("");
			tPret.setBounds ( x, y + 120, 200, 30 );
			tPret.setText(pret);
			tPret.setFont ( font2 );
			
			add ( lDenumire );
			add ( tDenumire );

			add ( lCategorie );
			add ( tCategorie );

			add ( lTara );
			add ( tTara );

			add ( lPret );
			add ( tPret );

			editareButton.setBounds ( 95, 190, 150, 40 );
			editareButton.setFont ( font1 );
			editareButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					String ndenumire = tDenumire.getText();
					String ncategorie = tCategorie.getText();
					String ntara = tTara.getText();
					String npret = tPret.getText();
					if ( !( ndenumire.equals("") && ncategorie.equals("") && ntara.equals("") && npret.equals("") ) &&
						! ( denumire.equals(ndenumire) && categorie.equals(ncategorie) && tara.equals(ntara) && pret.equals(npret) ) )
					{
						// Sterg produsul vechi
						try
						{
							// Deschid fisierul ce contine produsele
							RandomAccessFile input = new RandomAccessFile ( gestiune.getFileProduse(), "r" );

							// Citesc prima linie
							String line = input.readLine();
							String s = "";

							while ( line != null )
							{
								// Extrag informatiile unui produs
								StringTokenizer st = new StringTokenizer ( line );
								String newLine = "";
								String fDenumire = st.nextToken();
								String fCategorie = st.nextToken();
								// Verific daca produsul selectat se afla pe linia curenta
								if ( fDenumire.equals ( denumire ) && fCategorie.equals ( categorie ) )
								{
									boolean sterge = true;
									newLine = newLine + fDenumire + " " + fCategorie;
									int i;
									// Daca da ii schimb pretul in -1
									for ( i = 0; i < gestiune.tari.size(); i++ )
									{
										String pret = st.nextToken();
										if ( gestiune.tari.get(i).equals(tara) )
											newLine = newLine + " " + "-1";
										else
										{
											if ( !pret.equals("-1") )
												sterge = false;
											newLine = newLine + " " + pret;
										}
									}
									// Daca produsul are pretul -1 in toate tarile, voi sterge linia respectiva din fisier
									if ( !sterge )
										s = s + newLine + "\r\n";
								}
								else
									s = s + line + "\r\n";
								// Citesc urmatoarea linie
								line = input.readLine();
							}

							// Actualizez gestiunea
							PrintStream output = new PrintStream ( gestiune.getFileProduse() );
							output.print ( s );
							gestiune.readDate();
							PrintStream output2 = new PrintStream ( gestiune.getFileOutput() );
							output2.print ( gestiune.magazine );

							output.close();
							output2.close();

						} catch (FileNotFoundException ex)
						{
							Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex)
						{
							Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
						}

						//Adaug produsul nou
						String denumire = tDenumire.getText();
						String categorie = tCategorie.getText();
						String tara = tTara.getText();
						String pret = tPret.getText();
						boolean exista = false;
						boolean add = true;

						try {
							// Deschid fisierul ce contine produsele
							RandomAccessFile input = new RandomAccessFile ( gestiune.getFileProduse(), "r" );
							
							// Citesc prima linie
							String line = input.readLine();
							String s = line + "\r\n";
							line = input.readLine();
							
							while ( line != null )
							{
								// Extrag informatiile unui produs
								StringTokenizer st = new StringTokenizer ( line );
								String newLine = "";
								String fDenumire = st.nextToken();
								String fCategorie = st.nextToken();
								// Verific daca produsul selectat se afla pe linia curenta
								if ( fDenumire.equals ( denumire ) && fCategorie.equals ( categorie ) )
								{
									newLine = newLine + fDenumire + " " + fCategorie;
									int i;
									// Daca da, verific daca se afla in tara dorita
									for ( i = 0; i < gestiune.tari.size(); i++ )
									{
										if ( gestiune.tari.get(i).equals(tara) )
										{
											add = false;
											// Verific daca produsul exista deja (daca pretul este diferit de -1)
											String pret2 = st.nextToken();
											if ( pret2.equals("-1") )
											{
												exista = false;
												pret2 = pret;
											}
											else
												exista = true;
											newLine = newLine + " " + pret2;
										}
										else
										{
											String pret2 = st.nextToken();
											newLine = newLine + " " + pret2;
										}
									}
									s = s + newLine + "\r\n";
								}
								else
									s = s + line + "\r\n";
								// Citesc urmatoarea linie
								line = input.readLine();
							}
							
							if ( add )
							{
								s = s + denumire + " " + categorie;
								for ( int i = 0; i < gestiune.tari.size(); i++ )
								{
									if ( gestiune.tari.get(i).equals(tara) )
										s = s + " " + pret;
									else
										s = s + " " + "-1";
								}
								
							}
							
							// Actualizez gestiunea
							PrintStream output = new PrintStream ( gestiune.getFileProduse() );
							output.print ( s );
							
							gestiune.readDate();
							PrintStream output2 = new PrintStream ( gestiune.getFileOutput() );
							output2.print ( gestiune.magazine );

							output.close();
							output2.close();
							
							// Actualizez tabelul
							gestiune.readDate();
							scroll.setVisible(false);
							scroll = createTable ( gestiune );
							panel.add ( scroll );
							
							if ( exista )
							{
								MsjAddProdus asd = new MsjAddProdus ( false, false );
							}
							else
							{
								MsjAddProdus asd = new MsjAddProdus ( true, true );
							}
							
						} catch (FileNotFoundException ex) {
							Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex)
						{
							Logger.getLogger(AfisareAdministrare.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
					dispose();
				}
			});
			
			cancelButton.setBounds ( 95, 235, 150, 40 );
			cancelButton.setFont ( font1 );
			cancelButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					dispose();
				}
			});
			
			add ( editareButton );
			add ( cancelButton );

			show();
			pack();
		}
	}
	
	Image background = new ImageIcon("src\\interfata\\resurse\\afisare_background.jpg").getImage();
	
	@Override
	public void paintComponent ( Graphics g )
	{
		g.drawImage ( background, 0, 0, 800, 600, this );
	}
}

class MsjAddProdus extends JFrame
{
	private JButton okButton = new JButton ("OK");
	private JLabel text = new JLabel ("<html><center>Produsul exista deja<br/>in baza de date<center/></html>");
	public MsjAddProdus ( boolean ex, boolean edit )
	{
		super ( "Succes" );
		
		if ( ex )
		{
			if ( !edit )
				text.setText("<html><center>Produsul a fost<br/>adaugat cu succes<center/></html>");
			else
				text.setText("<html><center>Produsul a fost<br/>editat cu succes<center/></html>");
		}
		
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