package soluzione;

@SuppressWarnings("serial")

/**
*La classe EventiException è una estensione della classe Throwable che
* indica errori che si verificano nella classe Evento.
*/

public class EventiException extends Throwable
{
	/**
	*Costruisco un'istanza della classe EventiException che indica
	*un'eccezione controllata della classe Eventi.
	*@param ErrorException il messaggio di errore.
	*/
	public EventiException (String ErrorExcetpion)
	{
		super(ErrorExcetpion);
	}
}
