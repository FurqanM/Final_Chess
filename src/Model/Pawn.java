package Model;

import Controller.Board;

/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should.
 * All of the handling and checking for the movement is done within this class (or Super class if validateMove method is invoked polymorphically.
 */

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
		int initialRank = moveBoardPosition.substring(0, 1).trim().toLowerCase().charAt(0) - 'a'; // c
		int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - '1'; // 7
		int newRank = moveBoardPosition.substring(3, 4).trim().toLowerCase().charAt(0) - 'a'; // c
		// 5 maybe *
		int newFile = moveBoardPosition.substring(4).trim().charAt(0) - '1';

		if (isWhite)
		{
			// a = 0 white
			//if the x-axis is the same, if the new Y position is equal to the initial Y position + 1 then it moved up once
			if ((newRank == initialRank) && (newFile == initialFile + 1)) 
			{
				System.out.println("Moved a White Pawn");
				return true;
			}
			//if the Pawn is in the starting position of the board, then allow it to move up twice
			else if(initialFile == 1)
			{
				//if the x-axis is the same, if the new Y position is equal to the initial Y position + 2 then it moved up twice
				if ((newRank == initialRank) && (newFile == initialFile + 2))
				{
					System.out.println("Moved a White Pawn up 2"); //TODO set a boolean flag setting that the pawn has moved for White and Black
					return true;
				}
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
			else if(initialFile == 6)
			{
				if ((newRank == initialRank) && (newFile == initialFile - 2))
				{
					System.out.println("Moved a White Pawn up 2");
					return true;
				}
			}
			else
			{
				System.out.println("Not a valid movement, ignoring command.");
				return false;
			}

		}
		return false;

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
