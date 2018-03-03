/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazin;

import java.util.Comparator;
import tema.Factura;

/**
 *
 * @author andre
 */
public class ComparatorFacturi implements Comparator
{
	@Override
	public int compare ( Object o1, Object o2 )
	{
		Factura f1 = (Factura) o1;
		Factura f2 = (Factura) o2;
		
		return (int) (f1.getTotalFaraTaxe() - f2.getTotalFaraTaxe());
	}
}
