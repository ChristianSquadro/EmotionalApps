import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Operazioni {
	
	static void Import (String filename) throws IOException
	{
		File file = new File(filename); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		int riga=1;
		String tmp="";
		
		while ((tmp=br.readLine())!= null)
		{
			String[] campi;
			campi=tmp.split(" ");
			
			try
			{
			  LocalDate timestamp=convertToLocalDate(campi[2]);
			  Evento evento=new Evento(campi[0],campi[1],campi[3],campi[4],campi[5]);
			  if (EmotionalMapsVers2.TreeEventi.containsKey(timestamp))
			  {
				  if(!EmotionalMapsVers2.TreeEventi.modify(timestamp,evento))
					  throw new EventiException("Evento gia' presente");
			  }
			  else
				  EmotionalMapsVers2.TreeEventi.put(timestamp,evento);
			}
			catch (EventiException e)
			{
				System.out.println(e.getMessage() + " alla riga:" + riga);
			}
			
			riga++;
		}
		
		br.close();
	}
	
	static ArrayList<int [][]> CreateMap (String datainizio,String datafine) throws EventiException
	{
		int NumPOI=PuntoDiInteresse.values().length,NumStatiEmozione=StatoEmozione.values().length;	
		SortedMap<LocalDate,HashSet<Evento>> subMap=EmotionalMapsVers2.TreeEventi.SubMap(convertToLocalDate(datainizio),convertToLocalDate(datafine));
		ArrayList<int[][]> map=new ArrayList<int[][]>();
		int[][] Utenti_attivi=new int[NumPOI][NumStatiEmozione],AllUtenti=new int[NumPOI][NumStatiEmozione];
		int[] NumEventiPOIUtenti_attivi=new int[NumPOI],NumEventiPOIAllUtenti=new int[NumPOI];
		
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
		for (HashSet<Evento> eventi : subMap.values())
		{
			for(Evento evento : eventi)
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
		map.add(Utenti_attivi);
		map.add(AllUtenti);

		return map;
	}
	
	private static LocalDate convertToLocalDate(String timestamp) throws EventiException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		try 
		{
			LocalDate tmp=null;
            return tmp=LocalDate.parse(timestamp, formatter);
		} 
		catch (DateTimeException e) 
		{ 
			throw new EventiException("Formato data non inserito correttamente");
		}
	}
}
