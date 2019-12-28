package soluzione;

@SuppressWarnings("serial")

/**
*La classe EventiException è una estensione della classe Throwable che viene sollevata 
* quando si verificano errori di input nella classe Evento.
*/

public class EventiException extends Throwable
{
	/**
	*Costruisco un'istanza della classe EventiException che è
	*un'eccezione controllata sollevata della classe Eventi.
	*@param ErrorException il messaggio di errore.
	*/
	public EventiException (String ErrorExcetpion)
	{
		super(ErrorExcetpion);
	}
}
