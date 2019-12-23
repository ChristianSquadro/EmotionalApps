package soluzione;

@SuppressWarnings("serial")

/**
*La classe EventiException è una forma di Throwable che
* indica errori che si verificano nella classe Evento.
*/

public class EventiException extends Throwable
{
	/**
	*Costruisco un'istanza della classe EventiException che indica
	*un'eccezione non controllata della classe Eventi aggiungendo 
	*un messaggio personalizzato.
	*@param ErrorException il messaggio di errore.
	*/
	public EventiException (String ErrorExcetpion)
	{
		super(ErrorExcetpion);
	}
}
