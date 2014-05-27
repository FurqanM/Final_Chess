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
	public boolean validateMovement(String position)
	{
		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - 49; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - 49;
		
		if (initialFile == newFile || initialRank == newRank)
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
