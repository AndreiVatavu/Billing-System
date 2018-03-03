/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazin;

/**
 *
 * @author andre
 */
public class HyperMarket extends Magazin
{
	public HyperMarket ( String nume )
	{
		super ( nume );
	}
	
	@Override
	public double calculScutiriTaxe ()
	{
		double scutire = 0;
		double total_vanzari = getTotalCuTaxe();
		for ( int i = 0; i < facturi.size(); i++ )
		{
			if ( facturi.elementAt(i).getTotalCuTaxe() > total_vanzari / 10 )
			{
				scutire = 0.01;
				break;
			}
		}
		
		return scutire;
	}
}
