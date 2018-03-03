/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tema.*;

/**
 *
 * @author andre
 */
public abstract class Magazin implements IMagazin
{
	public String nume;
	public Vector<Factura> facturi;
	
	private String file_taxe = "src\\resurse\\taxe.txt";
	
	public Magazin ()
	{
		this ( null );
	}
	
	public Magazin ( String nume )
	{
		this.nume = nume;
		facturi = new Vector<> ( 10 );
	}
	
	public void setFileTaxe ( String file_taxe )
	{
		this.file_taxe = file_taxe;
	}
	
	public void add ( Factura factura )
	{
		facturi.add ( factura );
		Collections.sort(facturi, new ComparatorFacturi() );
	}
	
	@Override
	public double getTotalFaraTaxe ()
	{
		double total = 0;
		for ( int i = 0; i < facturi.size(); i++ )
			total += facturi.elementAt(i).getTotalFaraTaxe();
		
		return total;
	}
	
	@Override
	public double getTotalCuTaxe ()
	{
		double total = 0;
		for ( int i = 0; i < facturi.size(); i++ )
			total += facturi.elementAt(i).getTotalCuTaxe();
		
		return total;
	}
	
	@Override
	public double getTotalCuTaxeScutite ()
	{
		double total;
		total = getTotalCuTaxe() - getTotalCuTaxe() * calculScutiriTaxe();
		return total;
	}
	
	@Override
	public double getTotalTaraFaraTaxe ( String tara )
	{
		double total = 0;
		for ( int i = 0; i < facturi.size(); i++ )
			total += facturi.elementAt(i).getTotalTaraFaraTaxe ( tara );
		
		return total;
	}
	
	@Override
	public double getTotalTaraCuTaxe ( String tara )
	{
		double total = 0;
		for ( int i = 0; i < facturi.size(); i++ )
			total += facturi.elementAt(i).getTotalTaraCuTaxe ( tara );
		
		return total;
	}
	
	public double getTotalCategorieCuTaxe ( String categorie )
	{
		double total = 0;
		for ( int i = 0; i < facturi.size(); i++ )
			total += facturi.elementAt(i).getTotalCategorieCuTaxe ( categorie );
		
		return total;
	}
	
	@Override
	public double getTotalTaraCuTaxeScutite ( String tara )
	{
		double total;
		total = getTotalTaraCuTaxe( tara ) - getTotalTaraCuTaxe( tara ) * calculScutiriTaxe();
		return total;
	}
	
	@Override
	public abstract double calculScutiriTaxe ();
	
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
		s = nume + "\n\n" + "Total "; 
		s = s + myFormatter.format( getTotalFaraTaxe() ) + " ";
		s = s + myFormatter.format( getTotalCuTaxe() ) + " ";
		s = s + myFormatter.format( getTotalCuTaxeScutite () ) + "\n\n";
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
					s = s + myFormatter.format( getTotalTaraCuTaxe(tara) ) + " ";
					s = s + myFormatter.format( getTotalTaraCuTaxeScutite(tara) ) + "\n";
				}
			}
		} catch (IOException ex)
		{
			Logger.getLogger(Magazin.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		s = s + "\n";
		
		for ( Factura factura : facturi )
			s = s + factura.toString() + "\n";
		
		return s;
	}
}
