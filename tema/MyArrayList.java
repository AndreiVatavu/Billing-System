/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.util.*;

/**
 *
 * @author andre
 */
public class MyArrayList<E> extends ArrayList<E>
{
	@Override
	public String toString ()
	{
		String s = "";
		Vector<String> tip_magazin = new Vector<> (3);
		tip_magazin.add ( "Magazin.MiniMarket" );
		tip_magazin.add ( "Magazin.MediumMarket" );
		tip_magazin.add ( "Magazin.HyperMarket" );
		for ( String tip : tip_magazin )
		{
			s = s + tip.substring(8) + "\n";
			for (E element : this)
			{
				if ( element.getClass().getName().equals( tip ) )
					s = s + element.toString();
			}
		}
		s = s.substring(0, s.length() - 2);
		return s;
	}
}
