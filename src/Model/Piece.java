package Model;

public class Piece
{
	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}
	
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();
		
		return (this.isWhite == true) ? name.substring(6, 7).toUpperCase() : name.substring(6, 7).toLowerCase();
	}
	
}