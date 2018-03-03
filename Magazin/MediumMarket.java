/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 *
 * @author andre
 */
public class MediumMarket extends Magazin
{
	private Vector<String> categorii;
	private String file_taxe = "src\\resurse\\taxe.txt";
	
	public MediumMarket ( String nume ) throws FileNotFoundException, IOException
	{
		super ( nume );
		categorii = new Vector (10);
		// Construiesc vectorul cu toate categoriile
		RandomAccessFile file = new RandomAccessFile ( file_taxe, "r" );
		
		// Nu am nevoie de prima linie
		file.readLine();
		
		String s = file.readLine();
		
		while ( s != null )
		{
			StringTokenizer st = new StringTokenizer (s);
			categorii.add ( st.nextToken() );
			s = file.readLine();
		}
	}
	
	@Override
	public void setFileTaxe ( String file_taxe )
	{
		this.file_taxe = file_taxe;
	}
	
	@Override
	public double calculScutiriTaxe ()
	{
		double scutire = 0;
		double total_vanzari = getTotalCuTaxe();
		for ( int i = 0; i < categorii.size(); i++ )
		{
			if ( getTotalCategorieCuTaxe( categorii.elementAt(i)) > total_vanzari / 2 )
			{
				scutire = 0.05;
				break;
			}
		}
		
		return scutire;
	}
}
