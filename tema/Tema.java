/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import Magazin.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.*;

/**
 *
 * @author andre
 */
public class Tema
{

	/**
	 * @param args the command line arguments
	 * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Gestiune gestiune = Gestiune.getInstance();
		
		PrintStream fileStream = new PrintStream("src\\Resurse\\out.txt");
		gestiune.setFileProduse( "src\\resurse\\produse.txt" );
		gestiune.setFileFacturi ( "src\\resurse\\facturi.txt" );
		gestiune.setFileTaxe ( "src\\resurse\\taxe.txt" );
		gestiune.readDate();
		fileStream.print ( gestiune.magazine );		
	}
}
