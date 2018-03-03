/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

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
public class Factura
{
	private String denumire;
	private Vector<ProdusComandat> list;
	private String file_taxe = "src\\resurse\\taxe.txt";
	
	public Factura ()
	{
		this ( null );
	}
	
	public Factura ( String denumire )
	{
		this.denumire = denumire;
		list = new Vector<> ( 10 );
	}
	
	public void setFileTaxe ( String file_taxe )
	{
		this.file_taxe = file_taxe;
	}
	
	public void setDenumire ( String denumire )
	{
		this.denumire = denumire;
	}
	
	public String getDenumire ()
	{
		return denumire;
	}
	
	public void add ( ProdusComandat produs )
	{
		list.add ( produs );
	}
	
	public double getTotalFaraTaxe ()
	{
		double total = 0;
		
		for (ProdusComandat produs : list)
			total += ( produs.getProdus().getPret() * produs.getCantitate());
			
		return total;
	}
	
	public double getTotalCuTaxe ()
	{
		double total = 0;
		
		for ( int i = 0 ; i < list.size(); i++ )
		{
			// Aflu pretul si taxa
			double pret = list.elementAt(i).getProdus().getPret();
			double taxa = list.elementAt(i).getTaxa() / 100;
			// Pretul cu taxa
			double pretCuTaxa = pret + pret * taxa;
			total += ( pretCuTaxa * list.elementAt(i).getCantitate() );
		}
		
		return total;
	}
	
	public double getTaxe ()
	{
		double total_taxe;
		total_taxe = getTotalCuTaxe() - getTotalFaraTaxe();
		
		return total_taxe;
	}
	
	public double getTotalTaraFaraTaxe ( String tara )
	{
		double total = 0;
		
		for ( ProdusComandat produs : list )
			// Verific daca tara de origine a produsului este aceeasi cu tara dorita
			if ( produs.getProdus().getTaraOrigine().equals( tara ) )
				total += ( produs.getProdus().getPret() * produs.getCantitate() );
		
		return total;
	}
	
	public double getTotalTaraCuTaxe ( String tara )
	{
		double total = 0;
		
		for ( ProdusComandat produs : list )
		{
			// Verific daca tara de origine a produsului este aceeasi cu tara dorita
			if ( produs.getProdus().getTaraOrigine().equals( tara ) )
			{
				// Aflu pretul si taxa
				double pret = produs.getProdus().getPret();
				double taxa = produs.getTaxa() / 100;
				// Pretul cu taxa
				double pretCuTaxa = pret + pret * taxa;
				total += ( pretCuTaxa * produs.getCantitate() );
			}
		}
		
		return total;
	}
	
	// Calculeaza pretul tuturor produselor dintr-o anumita categorie
	public double getTotalCategorieCuTaxe ( String categorie )
	{
		double total = 0;
		
		for ( ProdusComandat produs : list )
		{
			// Verific daca produsul face parte din categoria dorita
			if ( produs.getProdus().getCategorie().equals( categorie ) )
			{
				// Aflu pretul si taxa
				double pret = produs.getProdus().getPret();
				double taxa = produs.getTaxa() / 100;
				// Pretul cu taxa
				double pretCuTaxa = pret + pret * taxa;
				total += ( pretCuTaxa * produs.getCantitate() );
			}
		}
		
		return total;
	}
	
	public double getTaxeTara ( String tara )
	{
		double total_taxe;
		total_taxe = getTotalTaraCuTaxe ( tara ) - getTotalTaraFaraTaxe ( tara );
		
		return total_taxe;
	}
	
	public StringTokenizer getTari () throws FileNotFoundException, IOException
	{
		RandomAccessFile file = new RandomAccessFile ( file_taxe, "r" );
		StringTokenizer st = new StringTokenizer ( file.readLine() );
		st.nextToken();
		return st;
	}
	
	@Override
	public String toString ()
	{
		String s;
		DecimalFormat myFormatter = new DecimalFormat( "###.####" );
		s = denumire + "\n\n" + "Total ";
		s = s + myFormatter.format( getTotalFaraTaxe () ) + " ";
		s = s + myFormatter.format( getTotalCuTaxe () );
		s = s + "\n\n";
		
		s = s + "Tara\n";
		
		try
		{
			StringTokenizer st = getTari();
			ArrayList<String> tari = new ArrayList<> ();
			while ( st.hasMoreTokens() )
				tari.add( st.nextToken() );
			// Sortez tarile alfabetic
			Collections.sort(tari);
			
			for ( String tara: tari )
			{
				s = s + tara + " ";
				if ( getTotalTaraFaraTaxe(tara) == 0 )
					s = s + "0\n";
				else
				{
					s = s + myFormatter.format( getTotalTaraFaraTaxe(tara) ) + " ";
					s = s + myFormatter.format ( getTotalTaraCuTaxe(tara) ) + "\n";
				}
			}
		} catch (IOException ex)
		{
			Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return s;
	}
}
