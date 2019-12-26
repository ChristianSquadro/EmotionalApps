package soluzione;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
*Classe principale dell'applicazione EmotionalMap.
* @author Christian Squadrito 736926 
* @author Andrea Fedeli 736839 
* @version 1.0
*/
public class EmotionalMaps {
	
	/**
	*La TreeMap contenente gli Eventi.
	*/
	static TreeMapEventi treeEventi;
	/**
	*La lista di mappe calcolate.
	*/
	static List<int[][]> emotionalMap;
	
	/**
	*Esegue l'applicazione.
	*@throws IOException se il file Comandi.txt non viene trovato
	*/
	public static void main(String[] args) throws IOException
	{
		treeEventi=new TreeMapEventi();
		emotionalMap=new ArrayList<int[][]>();
		//Il percorso del file Comandi.txt
		String path="";
		//Uso il BufferedReader per leggere il percorso
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Il file Comandi.txt
		File file;
		
		System.out.println("Specifica il percorso del file comandi da inserire!\n");
		try
		{
			path=br.readLine();
			if (path == "")
				System.out.println("Inserire un percorso valido per la lettura del file!\n");
			file = new File(path);
			//leggo il file Comandi.txt
			br = new BufferedReader(new FileReader(file));
			//Il comando che ho letto dalla linea del file Comandi.txt
			String comando="";
			
			//Finchè ci sono righe nel file
			while (br.ready())
			{
				comando=br.readLine();
				//L'argomento del comando letto
				String comandoArgomento=comando.substring(comando.indexOf('(')+1, comando.indexOf(')'));
				//Se il comando letto è import
				if(comando.matches("import(.+)"))
				{
					path=path.replace("Comandi.txt", "");//per togliere comandi.txt e far rimanere solo il percorso
					Operazioni.Import(path+comandoArgomento);
				}
				else
				{
					//Se il comando letto è create_map
					if(comando.matches("create_map(.+)") && comandoArgomento.contains("-"))
					{
						String[] intervalloData=comandoArgomento.split("-");
						emotionalMap=Operazioni.CreateMap(intervalloData[0],intervalloData[1]);
						StampaMap();
					}
					else
						//Il comando nella riga appena letta è sconosciuto o errato
						throw new ComandiException("Il comando inserito non è corretto!\n");
				}
			} 
		}
		catch (Throwable e)
		{
			System.out.println("Errore:"+e.getMessage()+"\n");
		}
		finally
		{
			br.close();
		}
	}
	
	/**
	*Stampa a video le mappe calcolate
	*/
	static private void StampaMap ()
	{
		//Stringa della Riga della mappa da costruire
		String tmpRigaMappa="";
		//Per far si che tutti i titoli delle mappe vengano visualizzate
		int indexMap=1;
		//Numero totale di POI e StatiEmozione
		int NumPOI=PuntoDiInteresse.values().length,NumStatiEmozione=StatoEmozione.values().length;
		//Per ogni mappa calcolata
		for(int[][] Arraymap : emotionalMap)
		{
			//Per stampare titolo mappa
			switch(indexMap)
			{
				case 1:
					tmpRigaMappa="\nUtenti attivi: \n";
					break;
				case 2:
					tmpRigaMappa="\nTotale utenti: \n";
					break;
				default:
					break;
			}
			//Per ogni POI
			for (int i=0;i<NumPOI;i++)
			{
				tmpRigaMappa=tmpRigaMappa+"POI"+(i+1)+" - ";
				//Per ogni StatoEmozione
				for(int j=0;j<NumStatiEmozione;j++)
				{
					tmpRigaMappa=tmpRigaMappa+Arraymap[i][j]+"% "+StatoEmozione.values()[j].GetEmozione()+", ";
				}
				tmpRigaMappa=tmpRigaMappa.substring(0,tmpRigaMappa.lastIndexOf(','));
				tmpRigaMappa=tmpRigaMappa+"\n";
			}
			System.out.println(tmpRigaMappa);
			indexMap++;
		}
	}
}
