package Model;

public class King extends Piece
{

	public King(boolean isWhite)
	{
		super(isWhite);
	}
	
	//move (y - 1) || (y + 1)
	//(x + 1) || (x - 1)
	//or ((x - 1) && (y - 1)) || (x + 1) && (y - 1)
	//or ((x + 1) && (y + 1)) || ( x - 1) && ( y + 1))

}
