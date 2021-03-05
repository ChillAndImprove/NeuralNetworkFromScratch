public interface Error
{
	// Fehlerfkt.
	public double error(double e, double soll);

	// Fehlerfkt. Ableitung
	public double errorA(double x, double soll);
}
