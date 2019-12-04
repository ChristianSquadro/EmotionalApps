
public enum StatoEmozione {
	Arrabbiato ("A"),
	Felice ("F"),
	Sorpreso("S"),
	Triste("T"),
	Neutro("N");
	
	private final String emozione;
	
	private StatoEmozione (String emozione)
	{
		this.emozione=emozione;
	}
	
	public String GetEmozione ()
	{
		return emozione;
	}
}
