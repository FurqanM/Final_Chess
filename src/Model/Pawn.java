package Model;

import Controller.Board;

public class Pawn extends Piece
{

	public Pawn(boolean isWhite)
	{
		super(isWhite);
	}

	@Override
	public boolean validateMovement(String moveBoardPosition)
	{

		// c7 c5
		int initialRank = moveBoardPosition.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - 49; // 7
		int newRank = moveBoardPosition.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		int newFile = moveBoardPosition.substring(4).trim().charAt(0) - 49;

		if (isWhite)
		{
			// a = 0 white
			if ((newRank == initialRank) && (newFile - initialFile == 1))
			{
				System.out.println("Moved a White Pawn");
				return true;
			}
			else
			{
				System.out.println("Not a valid movement, ignoring command.");
				return false;
			}

		}
		else
		{
			// a = 0 black
			if ((newRank == initialRank) && (newFile - initialFile == -1))
			{
				System.out.println("Moved a Black Pawn");
				return true;
			}
			else
			{
				System.out.println("Not a valid movement, ignoring command.");
				return false;
			}

		}

	}



	// move y - 1 black Pawn
	// or ((x - 1) && (y - 1)) || ((x + 1) && (y - 1))
	// move one space down
	// if has not moved yet then move two spaces y = 7

	// white pawn
	// move y + 1
	// or ((x + 1) && (y + 1)) || ((x - 1) && (y + 1))
	// move one space up
	// if has not moved yet then move two spaces y = 2

}
