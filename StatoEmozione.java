package soluzione;

/**
*L'enum StatoEmozione permette di rappresentare tutti gli
*stati emozionali all'interno del programma.
*/
public enum StatoEmozione {
	Arrabbiato ("A"),
	Felice ("F"),
	Sorpreso("S"),
	Triste("T"),
	Neutro("N");
	
	private final String emozione;
	
	/**
	*Costruisce un'istanza dell'enum StatoEmozione ricevendo come
	*argomento emozione.
	*@param emozione l'emozione che si vuole creare.
	*/
	private StatoEmozione (String emozione)
	{
		this.emozione=emozione;
	}
	
	/*
	*Restituisce l'emozione dell'istanza StatoEmozione che esegue il metodo.
	*@return L'emozione dell'istanza.
	*/
	public String GetEmozione ()
	{
		return emozione;
	}
}
