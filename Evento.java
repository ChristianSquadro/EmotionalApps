import java.util.Objects;

public class Evento {
	private boolean stato_registrazione, stato_utente;
	private String userId="";
	private double latitudine,longitudine;
	private StatoEmozione emozione;
	private PuntoDiInteresse POI;
	
	public boolean getStato_registrazione ()
	{
		return stato_registrazione;				
	}
	public void setStato_registrazione (String stato_reg) throws EventiException
	{
		if(stato_reg.contains("IN"))
		stato_registrazione=true;
		else
			if(stato_reg.contains("OUT"))
			{
				stato_registrazione=false;
				stato_utente=false;
			}
			else
				throw new EventiException("Formato stato registrazione non inserito correttamente");
	}
	
	public boolean getStato_utente ()
	{
		return stato_utente;				
	}
	public void setStato_utente (String stato_ut) throws EventiException
	{
		if(stato_ut.contains("LOGOUT"))
			stato_utente=false;
		else
		{			
			if(stato_registrazione)
			{
				if (stato_ut.contains("LOGIN"))
					stato_utente=true;
				else
					throw new EventiException("Formato stato utente non inserito correttamente");
			}		
			else
				throw new EventiException("Non puo effettuare un login se non sei registrato");
		}
	}
	
	public String getUserId ()
	{
		return userId;				
	}
	public void setUserId (String Id) throws EventiException
	{
		if (Id.matches("\\w+")&& Id.length()==5)//qualsiasi carattere che sia un numero o una lettera dell'alfabeto (che si ripete una o piu volte)
			userId=Id;
		else
			throw new EventiException("Formato UserId non inserito correttamente");//darai l'eccezione con la rispettiva linea dell'evento scorretto
	}
	
	public double getLatitudine ()
	{
		return latitudine;		
	}	
	public double getLongitudine ()
	{
		return longitudine;		
	}
	
	public void setCoordinate (String coord) throws EventiException

	{
		String[] tmp;
		if (coord.matches("[0-9]{1,3}\\.[0-9]{3},[0-9]{1,3}\\.[0-9]{3}")) 
		{
			tmp=coord.split(",");
			latitudine=Double.parseDouble(tmp[0]);
			longitudine=Double.parseDouble(tmp[1]);
		}
		else
			throw new EventiException("Formato coordinate non inserito correttamente");//darai l'eccezione con la rispettiva linea dell'evento scorretto
	}

	public StatoEmozione getStatoEmozione ()
	{
		return emozione;				
	}
	public void setStatoEmozione (String emoz) throws EventiException
	{
		boolean flag=true;
		for(StatoEmozione tmp: StatoEmozione.values())
		{
			if(emoz.toUpperCase().trim().equals(tmp.GetEmozione()))
			{
				emozione=tmp;
				flag=false;
				break;
			}
		}
		
		if(flag)
			throw new EventiException("Formato emozione non inserito correttamente");
	}
	
	public PuntoDiInteresse getPOI ()
	{
		return POI;
	}
	
	public void setPOI () throws EventiException
	{
		boolean flag=true;
		double approssLat,approssLon;
		double range=0.003;
		for(PuntoDiInteresse tmp: PuntoDiInteresse.values())
		{
			approssLat=Math.abs(this.latitudine-tmp.GetLatitudine());
			approssLon=Math.abs(this.longitudine-tmp.GetLongitudine());
			if((approssLat < range)&&(approssLon < range ))
			{
				POI=tmp;
				flag=false;
				break;
			}
		}
		if(flag)
			throw new EventiException("L'utente non è vicino ad uno dei POI");
	}
	
	
	public Evento (String stato_reg,String stato_ut,String Id,String coord,String emoz) throws EventiException
	{
		try {
			setStato_registrazione(stato_reg);
			setStato_utente(stato_ut);
			setUserId(Id);
			setCoordinate(coord);
			setStatoEmozione(emoz);
			setPOI();
		}
		catch (EventiException eventi)
		{
			throw eventi;//conterrà quale campo ha generato l'eccezione
		}
	}
	
	public boolean equals(Object obj) { 
		if (obj == this) 
			return true; 
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		
		Evento tmp = (Evento) obj;
		return((stato_registrazione==tmp.stato_registrazione)&&
		      (stato_utente==tmp.stato_utente)&&
		      (userId.equals(tmp.userId))&&
		      (POI==tmp.POI)&&
		      (emozione==tmp.emozione));
	}
	
	 public int hashCode() 
	 {
		 return Objects.hash(this.stato_registrazione,this.stato_utente,this.userId,this.POI,this.emozione);
	 }
}
