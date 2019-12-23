package soluzione;

/*
*L'enum PuntoDiInteresse permette di rappresentare
*i 3 Punti di Interesse(POI) all'interno
*del programma.
*/
public enum PuntoDiInteresse {
	POI1(45.464,9.190),
	POI2(45.473,9.173),
	POI3(45.458,9.181);
	
	private final double latitudine,longitudine;
	
	/**
	*Costruisce un'istanza dell'enum PuntoDiInteresse 
	*ricevendo come argomento latitudine e longitudine.
	*@param lat La latitudine in cui si è verificato l'Evento.
	*@param lon La longitudine in cui si è verificato l'Evento.
	*/
	private PuntoDiInteresse(double lat,double lon)
	{
		this.latitudine=lat;
		this.longitudine=lon;
	}
	
	/**
	*Restituisce la latitudine dell'istanza PuntoDiInteresse
	*che esegue il metodo.
	*@return La latitudine dell'istanza.
	*/
	public double GetLatitudine()
	{
		return latitudine;
	}
	
	/**
	*Restituisce la longitudine dell'istanza PuntoDiInteresse
	*che esegue il metodo.
	*@return La longitudine dell'istanza.
	*/
	public double GetLongitudine()
	{
		return longitudine;
	}
}
