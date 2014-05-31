package Model;


/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should.
 * All of the handling and checking for the movement is done within this class (or Super class if validateMove method is invoked polymorphically.
 */

public class Rook extends Piece
{

	public Rook(boolean isWhite)
	{
		super(isWhite);
	}

	@Override
	public boolean validateMovement(Position position)
	{
		return moveStraight(position);
	}


	//move (min)X to (max)X || (min)Y to (max)Y

}
