package soluzione;

@SuppressWarnings("serial")

/**
*La classe ComandiException è una estensione di Throwable che
*indica errori sollevati dalla classe Comandi.
*/

public class ComandiException  extends Throwable
{
	/**
	*Costruisco un'istanza di ComandiException che indica un'eccezione
	*non controllata della classe Comandi aggiungendo un messaggio
	*personalizzato.
	*@param message il messaggio di errore
	*/
	public ComandiException (String message)
	{
		super(message);
	}
}
