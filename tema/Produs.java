/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

/**
 *
 * @author andre
 */
public class Produs
{
	private String denumire;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	public Produs ()
	{
		this ( null, null, null, 0 );
	}
	
	public Produs ( String denumire, String categorie, String taraOrigine, double pret )
	{
		this.denumire = denumire;
		this.categorie = categorie;
		this.taraOrigine = taraOrigine;
		this.pret = pret;
	}
	
	public void setDenumire ( String denumire )
	{
		this.denumire = denumire;
	}
	
	public String getDenumire ()
	{
		return denumire;
	}
	
	public void setCategorie ( String categorie )
	{
		this.categorie = categorie;
	}
	
	public String getCategorie ()
	{
		return categorie;
	}
	
	public void setTaraOrigine ( String taraOrigine )
	{
		this.taraOrigine = taraOrigine;
	}
	
	public String getTaraOrigine ()
	{
		return taraOrigine;
	}
	
	public void setPret ( double pret )
	{
		this.pret = pret;
	}
	
	public double getPret ()
	{
		return pret;
	}
	
	@Override
	public String toString ()
	{
		String s;
		s = denumire + " " + " " + categorie + " " + taraOrigine + " " + pret;
		return s;
	}
}
