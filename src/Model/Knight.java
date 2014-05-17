package Model;

public class Knight extends Piece
{

	public Knight(boolean isWhite)
	{
		super(isWhite);
	}
	
	/*
	//vertical
	(y + 2) && (x + 1)
	(y + 2) && (x - 1)
	
	(y - 2) && (x + 1)
	(y - 2) && (x - 1)
	
	
	//horizontal
	(x + 2) && (y + 1)
	(x + 2) && (y - 1)
	
	(x - 2) && (y + 1)
	(x - 2) && (y - 1)
	*/
	
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();
		
		return (super.isWhite) ? name.substring(7, 8).toUpperCase() : name.substring(7, 8).toLowerCase();
	}
	
}
