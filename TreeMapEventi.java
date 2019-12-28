package soluzione;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;

@SuppressWarnings("serial")

/**
*TreeMapEventi estende la classe TreeMap che conterrà
*istanze della classe Evento.
*I suoi elementi sono ordinati per Data in base a quando l'evento si è verificato e ogni elemento della TreeMap contiene un HashSet di Eventi.
*/
public class TreeMapEventi extends TreeMap<LocalDate,HashSet<Evento>> {

	/**
	*Costruisce un istanza della classe TreeMapEventi richiamando il
	*costruttore della classe TreeMap.
	*/
	public TreeMapEventi ()
	{
		super();
	}
	
	/**
	*Inserisce un nuovo HashSet all'interno dell'istanza di TreeMapEventi
	*che esegue il metodo passando come parametri timestamp e evento.
	*@param timestamp La data.
	*@param evento L'evento.
	*/
	public void put(LocalDate timestamp,Evento evento)
	{
		/**imposto l'initial factory a 50 perche superiore ai 30 eventi massimi previsti al giorno diviso 
		*il load factor(30/0,75=40).Cosi facendo non ci dovrebbero essere più rehash causati dal ridimensionamento dell'HashSet
		*/
		HashSet<Evento> hashSetTmp=new HashSet<Evento>(50);
		hashSetTmp.add(evento);
		super.put(timestamp, hashSetTmp);
	}
	
	/**
	*Controlla se all'interno del nodo dell'istanza di TreeMapEventi che
	*esegue il metodo è presente un HashSet con lo stesso evento passando
	*come parametro timestamp e evento
	*@param timestamp La data.
	*@param evento L'evento.
	*@return resultModify TRUE se la modifica è avvenuta o FALSE se non è
	*avvenuta.
	*/
	public boolean modify (LocalDate timestamp,Evento evento)
	{
		boolean resultModify;
		HashSet<Evento> hashSetTmp=get(timestamp);
		resultModify=hashSetTmp.add(evento);
		super.replace(timestamp, hashSetTmp);
		return resultModify;
	}
	
	/**
	*Controlla se l'istanza di TreeMapEventi che esegue il metodo
	*contiene la chiave passata come parametro con timestamp.
	*@param timestamp La data.
	*@return TRUE se l'istanza di TreeMapEventi che esegue il metodo contiene
	*la chiave; FALSE se l'istanza di TreeMapEventi che esegue il metodo non
	*contiene la chiave.
	*/
	public boolean containsKey(LocalDate timestamp)
	{
		return super.containsKey(timestamp);
	}
	
	/**
	*Restituisce una porzione di mappa dell'istanza di TreeMap che esegue
	*il metodo ricevendo come parametro dataInizio e dataFine.
	*@param dataInizio La data minima per estrarre la porzione di mappa.
	*@param dataFine La data massima per estrarre la porzione di mappa.
	*@return tmpSubMap La porzione di mappa.
	*@throws ComandiException se dataInizio è minore di dataFine.
	*/
	public SortedMap<LocalDate,HashSet<Evento>> SubMap(LocalDate dataInizio,LocalDate dataFine) throws ComandiException
	{	
		SortedMap<LocalDate,HashSet<Evento>> tmpSubMap;
		
		try
		{
			tmpSubMap=super.subMap(dataInizio,true,dataFine,true);
			return tmpSubMap;
		}
		catch(IllegalArgumentException e)
		{
			throw new ComandiException("Data iniziale superiore a data finale");
		}
	}
	
	/**
	*Restituisce l'HashSet contenuta nel nodo che ha come chiave la data
	*passata come parametro con timestamp.
	*@param timestamp La data.
	*@return super.get(timestamp) L'HashSet contenuta nel nodo
	*/
	private HashSet<Evento> get(LocalDate timestamp)
	{
		return super.get(timestamp);
	}

}
