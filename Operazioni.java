package soluzione;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
*Le istanze della classe Operazioni modellano le attività da svolgere quando
*si legge il Comandi.txt.
*/
public class Operazioni {
	
	/**
	*Apre e legge il file contenente gli Eventi riga per riga ricevendo 
	*come parametro filename creando una nuova istanza di Evento ad 
	*ogni riga del file,controllando se l'evento è già presente
	*nell'istanza di TreeMapEventi modificandolo oppure inserendolo se non è presente.
	*@param filename Il nome del file.
	*@throws IOException se il file non viene trovato.
	*@throws EventiException se gli input degli eventi non sono validi.
	*/
	static void Import (String filename) throws IOException
	{
		File file = new File(filename); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		int riga=1;
		String tmpEvento="";
		
		while ((tmpEvento=br.readLine())!= null)
		{
			String[] campiEvento;
			campiEvento=tmpEvento.split(" ");
			
			try
			{
			  LocalDate timestamp=convertToLocalDate(campiEvento[2]);
			  Evento evento=new Evento(campiEvento[0],campiEvento[1],campiEvento[3],campiEvento[4],campiEvento[5]);
			  if (EmotionalMaps.treeEventi.containsKey(timestamp))
			  {
				  if(!EmotionalMaps.treeEventi.modify(timestamp,evento))
					  throw new EventiException("Evento gia' presente");
			  }
			  else
				  EmotionalMaps.treeEventi.put(timestamp,evento);
			}
			catch (Throwable e)
			{
				System.out.println(e.getMessage() + " alla riga:" + riga);
			}
			
			riga++;
		}
		
		br.close();
	}
	
	/**
	*Crea le mappe relative agli utenti attivi e a tutti gli utenti
	*ricevendo come parametro dataInizo e dataFine.
	*@param dataInizio Data minima per l'estrazione della sottomappa.
	*@param dataFine Data massima per l'estrazione della sottomappa.
	*@return tmpEmotionalMap ArrayList contenente le 2 mappe.
	*@throws Throwable se EmotionalMaps.treeEventi.SubMap contiene dataInizio maggiore di dataFine
	*o se il formato della data inserita è scorretto.
	*/
	static ArrayList<int [][]> CreateMap (String dataInizio,String dataFine) throws Throwable
	{
		int NumPOI=PuntoDiInteresse.values().length,NumStatiEmozione=StatoEmozione.values().length;	
		SortedMap<LocalDate,HashSet<Evento>> subMap;
		ArrayList<int[][]> tmpEmotionalMap=new ArrayList<int[][]>();
		int[][] Utenti_attivi=new int[NumPOI][NumStatiEmozione],AllUtenti=new int[NumPOI][NumStatiEmozione];
		int[] NumEventiPOIUtenti_attivi=new int[NumPOI],NumEventiPOIAllUtenti=new int[NumPOI];
		
		
		//Estraggo la sottomappa dalla TreeMap
		subMap=EmotionalMaps.treeEventi.SubMap(convertToLocalDate(dataInizio),convertToLocalDate(dataFine));
		
		//inizializzo
		for (int i=0;i<NumPOI;i++)
		{
			NumEventiPOIAllUtenti[i]=0;
			NumEventiPOIUtenti_attivi[i]=0;
			for(int j=0;j<NumStatiEmozione;j++)
				{
					Utenti_attivi[i][j]=0;
					AllUtenti[i][j]=0;
				}
		}
		
		//carico
		for (HashSet<Evento> hashsetEventi : subMap.values())
		{
			for(Evento evento : hashsetEventi)
			{
				int posPuntoInteresse=evento.getPOI().ordinal();
				int posEmozione=evento.getStatoEmozione().ordinal();
				
				if(evento.getStato_utente())
				{
					Utenti_attivi[posPuntoInteresse][posEmozione]++;//utenti attivi
					NumEventiPOIUtenti_attivi[posPuntoInteresse]++;
				}
				
				AllUtenti[posPuntoInteresse][posEmozione]++;
				NumEventiPOIAllUtenti[posPuntoInteresse]++;
			}
		}
				
		//creo le percentuali
		for(int i=0;i<NumPOI;i++)
			for(int j=0;j<NumStatiEmozione;j++)
			{
				double calcolo;
				if (NumEventiPOIUtenti_attivi[i]!=0)
				{
					calcolo=((double)Utenti_attivi[i][j]/NumEventiPOIUtenti_attivi[i])*100;
					Utenti_attivi[i][j]=(int)Math.round(calcolo);
				}
				
				if (NumEventiPOIAllUtenti[i]!=0)
				{
					calcolo=((double)AllUtenti[i][j]/NumEventiPOIAllUtenti[i])*100;
					AllUtenti[i][j]=(int)Math.round(calcolo);
				}
			}
		
		//inserisco nella lista
		tmpEmotionalMap.add(Utenti_attivi);
		tmpEmotionalMap.add(AllUtenti);

		return tmpEmotionalMap;
	}
	
	/**
	*Converte la  stringa timestamp fornita come parametro
	*in un tipo LocalDate per poter essere utilizzato all'interno
	*della TreeMap.
	*@return LocalDate.parse L'oggetto convertito in LocalDate.
	*@throws Throwable se la data non ha il formato corretto.
	*/
	private static LocalDate convertToLocalDate(String timestamp) throws Throwable
	{
		//formattazione della data
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		try 
		{
            		return LocalDate.parse(timestamp, formatter);
		} 
		catch (DateTimeException e) 
		{ 
			throw new Throwable("Formato data non inserito correttamente");
		}
	}
}
