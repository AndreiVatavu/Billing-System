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
public class MiniMarket extends Magazin
{
	private Vector<String> tari;
	
	public MiniMarket ( String nume ) throws FileNotFoundException, IOException
	{
		super ( nume );
		tari = new Vector (10);
		// Extrag tarile si le pun in vectorul de tari
		StringTokenizer st = getTari();
		
		while ( st.hasMoreTokens() )
			tari.add ( st.nextToken() );
	}
	
	@Override
	public double calculScutiriTaxe ()
	{
		double scutire = 0;
		double total_vanzari = getTotalCuTaxe();
		for ( int i = 0; i < tari.size(); i++ )
		{
			if ( getTotalTaraCuTaxe( tari.elementAt(i)) > total_vanzari / 2 )
			{
				scutire = 0.1;
				break;
			}
		}
		
		return scutire;
	}
}
