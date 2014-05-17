package Model;

public class Bishop extends Piece
{

	public Bishop(boolean isWhite)
	{
		super(isWhite);
	}
	
	//move (x - 1 && y - 1) || (x + 1 && y + 1)
	//(x - 1 && y + 1) || (x + 1 && y - 1)

}
