/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import Magazin.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Gestiune
{
	public ArrayList<Produs> produse = new ArrayList<> ();
	public MyArrayList<Magazin> magazine = new MyArrayList<> ();
	public TreeMap<String, TreeMap> dictionar = new TreeMap<> ();
	
	public Vector<String> tari;
	public Vector<String> categorii = new Vector<> ();

	
	// Fisierele
	private String file_produse = "src\\resurse\\produse.txt";
	private String file_facturi = "src\\resurse\\facturi.txt";
	private String file_taxe = "src\\resurse\\taxe.txt";
	private String file_out = "src\\resurse\\out.txt";

	
	public String getFileProduse()
	{
		return file_produse;
	}
	
	public void setFileProduse ( String file_produse )
	{
		this.file_produse = file_produse;
	}
	
	public void setFileFacturi ( String file_facturi )
	{
		this.file_facturi = file_facturi;
	}
	
	public void setFileTaxe ( String file_taxe )
	{
		this.file_taxe = file_taxe;
	}
	
	public void setFileOut ( String file_out )
	{
		this.file_out = file_out;
	}
	
	public String getFileOutput()
	{
		return file_out;
	}
	
	private static Gestiune singleton = new Gestiune();
	
	private Gestiune ()
	{
		readDate();
	}
	
	public void readDate ()
	{
		produse.clear();
		magazine.clear();
		dictionar.clear();
		addProduse();
		creareDictionar();
		addMagazine();
	}
	
	public static Gestiune getInstance ()
	{
		return singleton;
	}
	
	// Metoda ce cauta un produs dupa denumire si tara de origine
	public Produs cauta ( String nume, String tara )
	{
		for (Produs produs : produse)
			if ( produs.getDenumire().equals(nume) && produs.getTaraOrigine().equals(tara) )
				return produs;
		
		return null;
	}
	
	private void addProduse ()
	{
		try
		{
			// Adaug toate produsele in lista de produse
			
			// Deschid fisierul ce contine produsele
			RandomAccessFile file = new RandomAccessFile ( file_produse, "r" );
			// Citesc prima linie si elimin primele doua cuvinte deoarece nu ne trebuie
			
			String s = file.readLine();
			StringTokenizer st = new StringTokenizer ( s );
			st.nextToken();
			st.nextToken();
			
			// Creez un vector cu toate tarile
			tari = new Vector<> ();
			
			while ( st.hasMoreTokens() )
				tari.add ( st.nextToken() );
			
			s = file.readLine();
			while ( s != null )
			{   // Extrag fiecare produs din fisier si il adaug in lista
				st = new StringTokenizer (s);
				String denumire = st.nextToken();
				String categorie = st.nextToken();
				
				//Adaug categoria in vectorul de categorii
				if ( !categorii.contains(categorie) )
					categorii.add ( categorie );
				
				for (String tara : tari)
				{
					// pretul
					double pret = Double.parseDouble ( st.nextToken() );
					// Daca pretul este -1 inseamna ca produsul a fost sters
					if ( pret != -1 )
					{
						Produs produs = new Produs( denumire, categorie, tara, pret );
						produse.add(produs);
					}
				}
				s = file.readLine();
			}
		} catch (FileNotFoundException ex)
		{
			Logger.getLogger(Gestiune.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex)
		{
			Logger.getLogger(Gestiune.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void addMagazine ()
	{
		try
		{
			RandomAccessFile file = new RandomAccessFile ( file_facturi, "r" );
			
			String s = file.readLine();
			
			while ( s != null && s.contains( "Magazin" ) )
			{
				StringTokenizer st = new StringTokenizer ( s, ":" );
				st.nextToken();
				
				Magazin magazin = MagazinFactory.createMagazin ( st.nextToken(), st.nextToken() );
				magazin.setFileTaxe ( file_taxe );
				
				// Crearea facturilor
				// Elimin randul liber
				s = file.readLine();
				String tmp = s;
				
				s = file.readLine();
				while ( s != null && s.contains( "Factura" ) )
				{
					Factura factura = new Factura ( s );
					factura.setFileTaxe ( file_taxe );
					// Elimin randul liber
					file.readLine();
					
					s = file.readLine();
					while ( s != null && !s.equals ( tmp ) )
					{
						st = new StringTokenizer ( s );
						
						// Extrag elementele necesare
						// Caut produsul cu numele si tara corespunzatoare
						Produs prod = cauta ( st.nextToken(), st.nextToken() );
						if ( prod != null )
						{
							int cantitate = Integer.parseInt ( st.nextToken() );
							double taxa;
							// Taxa o aflu cu ajutorul dictionarului
							taxa = (double)dictionar.get ( prod.getTaraOrigine() ).get( prod.getCategorie() );

							ProdusComandat produs = new ProdusComandat ( prod, taxa, cantitate );
							factura.add(produs);
						}
						s = file.readLine();
					}
					magazin.add ( factura );
					s = file.readLine();
				}
				magazine.add( magazin );
			}
		} catch (FileNotFoundException ex)
		{
			Logger.getLogger(Gestiune.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex)
		{
			Logger.getLogger(Gestiune.class.getName()).log(Level.SEVERE, null, ex);
		}
		// Sortez magazinele in ordine crescatoare dupa totalul facturilor
		Collections.sort(magazine, new ComparatorMagazine() );
	}
	
	// Creare Dictionar
	private void creareDictionar ()
	{
		try
		{
			RandomAccessFile file = new RandomAccessFile ( file_taxe, "r" );
			
			// Citesc prima linie si elimin primul cuvant deoarece nu ne trebuie
			String s = file.readLine();
			StringTokenizer st = new StringTokenizer ( s );
			st.nextToken();
			
			// Creez o lista cu toate dictionarele pentru fiecare tara
			ArrayList< TreeMap<String, Double> > mini_dictionar = new ArrayList<> ();
			for ( int i = 0; i < st.countTokens(); i++ )
			{
				TreeMap<String, Double> dict = new TreeMap<> ();
				mini_dictionar.add(dict);
			}
			
			
			s = file.readLine();
			while ( s != null )
			{
				StringTokenizer token = new StringTokenizer (s);
				// Categoria
				String categorie = token.nextToken();
				
				int index = 0;
				while ( token.hasMoreTokens() )
				{
					// Taxa
					double taxa;
					taxa = Double.parseDouble ( token.nextToken() );
					// Adaug cele doua informatii in mini dictionar
					mini_dictionar.get(index).put(categorie, taxa );
					index++;
				}
				s = file.readLine();
			}
			
			// Adaug elementele in dictionar
			int i = 0;
			while ( st.hasMoreTokens() )
			{
				dictionar.put(st.nextToken(), mini_dictionar.get(i) );
				i++;
			}
			
		} catch (FileNotFoundException ex)
		{
			Logger.getLogger(Gestiune.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex)
		{
			Logger.getLogger(Gestiune.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	// Functie ce returneaza datele magazinului cu cele mai mari vanzari
	public String getTopMagazin()
	{
		String s = "";
		Magazin topMag = null;
		double max = 0;
		for ( Magazin mag : magazine )
		{
			if ( mag.getTotalCuTaxe() > max )
			{
				max = mag.getTotalCuTaxe();
				topMag = mag;
			}
		}
		
		if ( topMag == null )
			return s;
		
		DecimalFormat myFormatter = new DecimalFormat( "###.####" );
		
		s = s + "Nume: " + topMag.nume + "\n";
		s = s + "Total fara taxe: " + myFormatter.format ( topMag.getTotalFaraTaxe() ) + "\n";
		s = s + "Total cu taxe: " + myFormatter.format ( topMag.getTotalCuTaxe() ) + "\n";
		s = s + "Total cu taxe scutite: " + myFormatter.format ( topMag.getTotalCuTaxeScutite() );
		
		return s;
	}
	
	public String topMagTari()
	{
		String s = "";
		
		for ( String tara : tari )
		{
			Magazin topMag = null;
			double max = 0;
			for ( Magazin mag : magazine )
			{
				if ( mag.getTotalTaraCuTaxe(tara) > max )
				{
					max = mag.getTotalTaraCuTaxe(tara);
					topMag = mag;
				}
			}

			DecimalFormat myFormatter = new DecimalFormat( "###.####" );
			s = s + "Tara: " + tara + "\n";
			s = s + "Nume: " + topMag.nume + "\n";
			s = s + "Total fara taxe: " + myFormatter.format ( topMag.getTotalFaraTaxe() ) + "\n";
			s = s + "Total cu taxe: " + myFormatter.format ( topMag.getTotalCuTaxe() ) + "\n";
			s = s + "Total cu taxe scutite: " + myFormatter.format ( topMag.getTotalCuTaxeScutite() ) + "\n";
			s = s + "--------------------------------------------------------------" + "\n";
		}
		
		return s;
	}
	
	public String topMagCateg()
	{
		String s = "";
		
		for ( String categorie : categorii )
		{
			Magazin topMag = null;
			double max = 0;
			for ( Magazin mag : magazine )
			{
				if ( mag.getTotalCategorieCuTaxe(categorie) > max )
				{
					max = mag.getTotalCategorieCuTaxe(categorie);
					topMag = mag;
				}
			}

			DecimalFormat myFormatter = new DecimalFormat( "###.####" );
			s = s + "Tara: " + categorie + "\n";
			s = s + "Nume: " + topMag.nume + "\n";
			s = s + "Total fara taxe: " + myFormatter.format ( topMag.getTotalFaraTaxe() ) + "\n";
			s = s + "Total cu taxe: " + myFormatter.format ( topMag.getTotalCuTaxe() ) + "\n";
			s = s + "Total cu taxe scutite: " + myFormatter.format ( topMag.getTotalCuTaxeScutite() ) + "\n";
			s = s + "--------------------------------------------------------------" + "\n";
		}
		
		return s;
	}
	
	public String getTopFactura()
	{
		String s = "";
		String numeMagazin = "";
		Factura topFactura = null;
		double max = 0;
		for ( Magazin mag : magazine )
		{
			for ( Factura factura : mag.facturi )
			{
				if ( factura.getTotalFaraTaxe() > max )
				{
					numeMagazin = mag.nume;
					max = factura.getTotalFaraTaxe();
					topFactura = factura;
				}
			}
		}
		
		if ( topFactura == null )
			return s;
		
		DecimalFormat myFormatter = new DecimalFormat( "###.####" );
		
		s = s + "Nume magazin: " + numeMagazin + "\n";
		s = s + "Nume factura: " + topFactura.getDenumire() + "\n";
		s = s + "Total fara taxe: " + myFormatter.format ( topFactura.getTotalFaraTaxe() ) + "\n";
		s = s + "Total cu taxe: " + myFormatter.format ( topFactura.getTotalCuTaxe() );
		
		return s;
	}
}