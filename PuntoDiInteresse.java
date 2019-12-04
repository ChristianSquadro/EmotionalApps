
public enum PuntoDiInteresse {
	POI1(45.464,9.190),
	POI2(45.473,9.173),
	POI3(45.458,9.181);
	
	private final double latitudine,longitudine;
	
	private PuntoDiInteresse(double lat,double lon)
	{
		this.latitudine=lat;
		this.longitudine=lon;
	}
	
	public double GetLatitudine()
	{
		return latitudine;
	}
	
	public double GetLongitudine()
	{
		return longitudine;
	}
}
