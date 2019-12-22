package soluzione;
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
				if (stato_ut.contains("LOGIN"))
					stato_utente=true;
				else
					throw new EventiException("Formato stato utente non inserito correttamente");
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
		boolean inserito=false;
		for(StatoEmozione statoEmozione: StatoEmozione.values())
		{
			emoz=emoz.toUpperCase().trim();//rimuovo gli spazi e metto tutto maiuscolo
			if(emoz.equals(statoEmozione.GetEmozione()))
			{
				emozione=statoEmozione;
				inserito=true;
				break;
			}
		}
		
		if(!inserito)
			throw new EventiException("Formato emozione non inserito correttamente");
	}
	
	public PuntoDiInteresse getPOI ()
	{
		return POI;
	}
	
	public void setPOI () throws EventiException
	{
		boolean inserito=false;
		double approssLat,approssLon;
		double range=0.003;
		for(PuntoDiInteresse puntoDiInteresse: PuntoDiInteresse.values())
		{
			approssLat=Math.abs(this.latitudine-puntoDiInteresse.GetLatitudine());
			approssLon=Math.abs(this.longitudine-puntoDiInteresse.GetLongitudine());
			if((approssLat < range)&&(approssLon < range ))
			{
				POI=puntoDiInteresse;
				inserito=true;
				break;
			}
		}
		if(!inserito)
			throw new EventiException("L'utente non è vicino ad uno dei POI");
	}
	
	
	public Evento (String stato_reg,String stato_ut,String Id,String coord,String emoz) throws EventiException
	{
			setStato_registrazione(stato_reg);
			setStato_utente(stato_ut);
			setUserId(Id);
			setCoordinate(coord);
			setStatoEmozione(emoz);
			setPOI();
	}
	
	public boolean equals(Object obj) { 
		
		if (obj == this) 
			return true; 
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		
		Evento evento = (Evento) obj;
		return((stato_registrazione==evento.stato_registrazione)&&
		      (stato_utente==evento.stato_utente)&&
		      (userId.equals(evento.userId))&&
		      (POI==evento.POI)&&
		      (emozione==evento.emozione));
	}
	
	 public int hashCode() 
	 {
		 return Objects.hash(this.stato_registrazione,this.stato_utente,this.userId,this.POI,this.emozione);
	 }
}
