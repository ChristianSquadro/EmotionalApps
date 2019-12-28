package soluzione;

@SuppressWarnings("serial")

/**
*La classe ComandiException è una estensione di Throwable che
*viene sollevata dalla classe Comandi quando si hanno errori di input .
*/

public class ComandiException  extends Throwable
{
	/**
	*Instanzia un'oggetto di ComandiException. E' un'eccezione
	*controllata e permette di inserire un messaggio
	*che riguarda il motivo dell'eccezione.
	*@param message il messaggio di errore
	*/
	public ComandiException (String message)
	{
		super(message);
	}
}
