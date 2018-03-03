/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazin;

import java.io.IOException;

/**
 *
 * @author andre
 */
public class MagazinFactory
{
	public static Magazin createMagazin( String tip_magazin, String nume ) throws IOException
	{
		if ( tip_magazin.equals( "MiniMarket" ) )
			return new MiniMarket( nume );
		
		if ( tip_magazin.equals ( "MediumMarket" ) )
			return new MediumMarket( nume );
		
		if ( tip_magazin.equals( "HyperMarket" ) )
			return new HyperMarket( nume );
		
		return null;
	}
}
