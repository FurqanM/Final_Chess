package Model;


/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should.
 * All of the handling and checking for the movement is done within this class (or Super class if validateMove method is invoked polymorphically.
 */

public class Queen extends Piece
{

	public Queen(boolean isWhite)
	{
		super(isWhite);
	}

	@Override
	public boolean validateMovement(Position position)
	{
		// c7 c5
		
		if (position.getInitialFile() == position.getNewFile() || position.getInitialRank() == position.getNewRank())
		{
			return moveStraight(position);
		}
		else
		{
			return moveCross(position);
		}
	}

	//move Rook + Bishop

}
