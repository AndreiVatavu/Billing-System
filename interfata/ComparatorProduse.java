/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfata;

import java.util.Comparator;
import tema.Produs;

/**
 *
 * @author andre
 */
public class ComparatorProduse implements Comparator
{
	@Override
	public int compare ( Object o1, Object o2 )
	{
		Produs p1 = (Produs) o1;
		Produs p2 = (Produs) o2;
		
		if ( p1.getDenumire().equals(p2.getDenumire()) )
			return (int) ( p1.getTaraOrigine().compareTo(p2.getTaraOrigine()) );
		
		return (int) ( p1.getDenumire().compareTo(p2.getDenumire()) );
	}
}
