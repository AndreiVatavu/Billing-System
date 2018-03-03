/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazin;

import java.util.Comparator;

/**
 *
 * @author andre
 */
public class ComparatorMagazine implements Comparator
{
	@Override
	public int compare ( Object o1, Object o2 )
	{
		Magazin m1 = (Magazin) o1;
		Magazin m2 = (Magazin) o2;
		
		return (int) (m1.getTotalFaraTaxe() - m2.getTotalFaraTaxe());
	}
}
