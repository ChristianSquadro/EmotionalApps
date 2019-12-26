package soluzione;
import java.util.Objects;

/**
*Le istanze della classe Evento contengono informazioni relative
*agli eventi.
*/
public class Evento {
	private boolean stato_registrazione, stato_utente;
	private String userId="";
	private double latitudine,longitudine;
	private StatoEmozione emozione;
	private PuntoDiInteresse POI;
	
	/**
	*Restituisce lo stato_registrazione dell'istanza Evento che esegue il metodo
	*@return Lo stato_registrazione
	*/
	public boolean getStato_registrazione ()
	{
		return stato_registrazione;				
	}
	
	/**
	*Modifica lo stato_registrazione dell'istanza di Evento che esegue il metodo
	*con lo stato_registrazione fornito come parametro.
	*Viene salvato come boolean TRUE se stato_registrazione = IN.
	*Viene salvato come boolean FALSE se stato_registrazione = OUT.
	*@param stato_reg Lo stato di registrazione nell'Evento.
	*@throws EventiException se stato_reg non è uguale a IN e OUT.
	*/
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
	
	
	/**
	*Restituisce lo stato_utente dell'istanza di Evento che esegue il metodo.
	*@return stato_utente Lo stato_utente.
	*/
	public boolean getStato_utente ()
	{
		return stato_utente;				
	}
	
	/**
	*Modifica lo stato_utente dell'istanza di Evento che esegue il metodo
	*con lo stato_utente fornito come parametro.
	*Viene salvato come boolean TRUE se stato_utente = LOGIN.
	*Viene salvato come boolean FALSE se stato_utente = LOGOUT.
	*@param stato_ut Lo stato_utente contenuto nell'Evento.
	*@throws EventiException se stato_ut è diverso da LOGIN e LOGOUT.
	*/
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
	
	/**
	*Restituisce lo UserId dell'istanza di Evento che esegue il metodo.
	*@return userId L'identificatore dell'utente.
	*/
	public String getUserId ()
	{
		return userId;				
	}
	
	/**
	*Modifica lo UserId dell'istanza di Evento che esegue il metodo
	*con lo UserId fornito come parametro.
	*@param Id L'identificatore dell'utente.
	*@throws EventiException se l'identificatore non è di 5 caratteri e se non è composto
	*da lettere dell'alfabeto o numeri.
	*/
	public void setUserId (String Id) throws EventiException
	{
		if (Id.matches("\\w+")&& Id.length()==5)//qualsiasi carattere che sia un numero o una lettera dell'alfabeto (che si ripete una o piu volte)
			userId=Id;
		else
			throw new EventiException("Formato UserId non inserito correttamente");
	}
	
	/**
	*Restituisce la latitudine dell'istanza di Evento che esegue il metodo.
	*@return latitudine La latitudine.
	*/
	public double getLatitudine ()
	{
		return latitudine;		
	}
	/**
	*Restituisce la longitudine dell'istanza di Evento che esegue il metodo.
	*@return longitudine La longitudine.
	*/
	public double getLongitudine ()
	{
		return longitudine;		
	}
	
	/**
	*Modifica le coordinate(latitudine e longitudine) dell'istanza di Evento
	*che esegue il metodo con le coordinate fornite come parametro.
	*@param coord Le coodinate composte da latitudine e longitudine separate da ','.
	*@throws EventiException se coord non è composto da numeri con parte intera di
	*1,2 o 3 cifre, un punto(.) e parte decimale di 3 cifre con latitudine e longitudine
	*separate da ','.
	*/
	public void setCoordinate (String coord) throws EventiException

	{
		String[] tmp;
		/**
		*Composizione stringa: numero di 1,2,3 cifre, un punto'.', numero di 3 cifre,
		*una virgola',', numero di 1,2,3 cifre, un punto'.', numero di 3 cifre.
		*/
		if (coord.matches("[0-9]{1,3}\\.[0-9]{3},[0-9]{1,3}\\.[0-9]{3}")) 
		{
			tmp=coord.split(",");
			latitudine=Double.parseDouble(tmp[0]);
			longitudine=Double.parseDouble(tmp[1]);
		}
		else
			throw new EventiException("Formato coordinate non inserito correttamente");
	}
	
	/**
	*Restituisce lo StatoEmozione dell'istanza che esegue il metodo.
	*@return emozione Lo StatoEmozione.
	*/
	public StatoEmozione getStatoEmozione ()
	{
		return emozione;				
	}
	
	/**
	*Modifica lo StatoEmozione dell'istanza di Evento che esegue il metodo
	*con l'emozione fornita come parametro.
	*@param emoz L'emozione
	*@throws EventiException se emoz non è una delle emozioni possibili A,F,S,T
	*o N.
	*/
	public void setStatoEmozione (String emoz) throws EventiException
	{
		boolean inserito=false; //true se trovo l'emozione corrispondente
		for(StatoEmozione statoEmozione: StatoEmozione.values())
		{
			emoz=emoz.toUpperCase().trim();//rimuovo gli spazi e rendo la stringa tutta maiuscola
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
	
	/**
	*Restituisce il PuntoDiInteresse(POI) dell'istanza di Evento che
	*esegue il metodo.
	*@return POI PuntoDiInteresse.
	*/
	public PuntoDiInteresse getPOI ()
	{
		return POI;
	}
	
	/**
	*Modifica il PuntoDiInteresse(POI) dell'istanza di Evento 
	*che esegue il metodo basandosi su latitudine e longitudine.
	*@throws EventiException se latitudine e longitudine non rientrano nel
	*range di nessun POI.
	*/
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
	
	/**
	*Costruisce un'istanza della classe Evento, ricevendo come argomenti stato_reg,
	*stato_ut, Id, coord, emoz.
	*@param stato_reg Lo stato di Registrazione di un Utente (IN/OUT) contenuto nell'evento.
	*@param stato_ut Lo stato dell'utente (LOGIN/LOGOUT) contenuto nell'evento.
	*@param Id L'identificatore dell'utente.
	*@param coord Le coordinate in cui l'Evento si è verificato.
	*@param emoz L'emozione associata all'Evento
	*@throws EventiException se stato_reg è diverso da IN o OUT, se stato_ut è diverso da LOGIN
	*o LOGOUT, se Id non è di 5 caratteri alfanumerici, se coord non è composto da parte intera
	*di 1 a 3 cifre e parte decimale di 3 cifre numeriche, se emoz non è uguale a A o F o S o T
	*o N, se l'Evento si è verificato fuori dal range di tutti i POI possibili.
	*/
	public Evento (String stato_reg,String stato_ut,String Id,String coord,String emoz) throws EventiException
	{
			setStato_registrazione(stato_reg);
			setStato_utente(stato_ut);
			setUserId(Id);
			setCoordinate(coord);
			setStatoEmozione(emoz);
			setPOI();
	}
	
	/**
	*Indica se l'Evento è uguale ad un oggeto passato come parametro.
	*@param obj L'oggetto con cui fare il confronto.
	*@return TRUE se obj è un'istanza della classe Evento con campi stato_registrazione,
	*stato_utente, userId, POI ed emozione hanno valore uguale;
	*FALSE negli altri casi.
	*/
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
	
	/**
	*Restituisce il valore dell'hash code dell'istanza di Evento
	*che esegue il metodo.
	*@return l'hash code dell'evento
	*/
	 public int hashCode() 
	 {
		 /**
		 *Object.hash calcola l'hash sui parametri dell'istanza di Evento che richiama il metodo hashCode
		 */
		 return Objects.hash(this.stato_registrazione,this.stato_utente,this.userId,this.POI,this.emozione);
	 }
}
