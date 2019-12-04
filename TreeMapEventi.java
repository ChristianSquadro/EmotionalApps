import java.time.LocalDate;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class TreeMapEventi extends TreeMap<LocalDate,HashSet<Evento>> {

	public TreeMapEventi ()
	{
		super();
	}
	
	public void put(LocalDate timestamp,Evento evento)
	{
		HashSet<Evento> hashSetTmp=new HashSet<Evento>();
		hashSetTmp.add(evento);
		super.put(timestamp, hashSetTmp);
	}
	
	public boolean modify (LocalDate timestamp,Evento evento)
	{
		boolean resultModify;
		HashSet<Evento> hashSetTmp=get(timestamp);
		resultModify=hashSetTmp.add(evento);
		super.replace(timestamp, hashSetTmp);
		return resultModify;
	}
	
	public boolean containsKey(LocalDate timestamp)
	{
		return super.containsKey(timestamp);
	}
	
	public SortedMap<LocalDate,HashSet<Evento>> SubMap(LocalDate datainizio,LocalDate datafine)
	{	
		return super.subMap(datainizio,true,datafine,true);
	}
	
	private HashSet<Evento> get(LocalDate timestamp)
	{
		return super.get(timestamp);
	}

}
