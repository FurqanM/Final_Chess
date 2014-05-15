package Model;

public class Knight extends Piece
{

	public Knight(boolean isWhite)
	{
		super(isWhite);
	}
	
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();
		
		
		return (super.isWhite == true) ? name.substring(7, 8).toUpperCase() : name.substring(7, 8).toLowerCase();
	}
	
}
