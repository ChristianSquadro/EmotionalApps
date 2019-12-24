package soluzione;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;

@SuppressWarnings("serial")

/**
*Le istanze della classe TreeMapEventi estendono la classe TreeMap che conterrà
*istanze della classe Evento.
*I nodi sono organizzati per Data e contengono un HashSet di Evento.
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
	*@param timestamp Il giorno.
	*@param evento L'evento.
	*/
	public void put(LocalDate timestamp,Evento evento)
	{
		HashSet<Evento> hashSetTmp=new HashSet<Evento>(50);//initial capacity partendo da un massimo di eventi di 50 al giorno diviso il load factor non ci dovrebbero essere più rehash
		hashSetTmp.add(evento);
		super.put(timestamp, hashSetTmp);
	}
	
	/**
	*Controlla se all'interno del nodo dell'istanza di TreeMapEventi che
	*esegue il metodo è presente un HashSet con lo stesso evento passando
	*come parametro timestamp e evento
	*@param timestamp Il giorno.
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
	*@param timestamp Il giorno.
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
	*@param timestamp Il giorno.
	*@return super.get(timestamp) L'HashSet contenuta nel nodo
	*/
	private HashSet<Evento> get(LocalDate timestamp)
	{
		return super.get(timestamp);
	}

}
