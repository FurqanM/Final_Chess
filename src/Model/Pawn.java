package Model;

import Controller.Board;

/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should. All of the handling and checking for the movement is done within this class
 * (or Super class if validateMove method is invoked polymorphically.
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

			if(moveTwice(initialRank, initialFile, newRank, newFile, 2) && (initialFile == 1)) // if the white pawn is where it is supposed to be to move twice
			{
				return true;
			}
				

			else if (hasValidMove(initialRank, initialFile, newRank, newFile, 1, 0) || //light movement
			hasValidMove(initialRank, initialFile, newRank, newFile, 1, 1) || //if piece to north east kill it
			hasValidMove(initialRank, initialFile, newRank, newFile, 1, -1)) //if piece to north west kill it
			{
				return true;
			}
		}
		else
		{
			
			if(moveTwice(initialRank, initialFile, newRank, newFile, -2) && (initialFile == 6)) // if the white pawn is where it is supposed to be to move twice
			{
				return true;
			}
				
			
			else if (hasValidMove(initialRank, initialFile, newRank, newFile, -1, 0) || //dark movement
			hasValidMove(initialRank, initialFile, newRank, newFile, -1, -1) || //if piece to south west then kill it
			hasValidMove(initialRank, initialFile, newRank, newFile, -1, 1)) //if piece to south east then kill it
			{
				return true;
			}
				
		}

		
		return false;

	}

	private boolean moveTwice(int initialRank, int initialFile, int newRank, int newFile, int verticalMovement)
	{
		int north = initialFile;
		
		north += verticalMovement;
		//if the Pawn is in the starting position of the board, then allow it to move up twice
		if (newFile == north && (Board.getInstance().getPieceAt(newFile, newRank) == null))
		{
			//if the x-axis is the same, if the new Y position is equal to the initial Y position + 2 then it moved up twice
			//System.out.println("Moved a Pawn twice"); //TODO set a boolean flag setting that the pawn has moved for White and Black
			return true;

		}
		return false;
	}

	private boolean hasValidMove(int initialRank, int initialFile, int newRank, int newFile, int verticalMovement, int horizontalMovement)
	{

		int north;
		int east;
		north = initialFile;
		east = initialRank;

		north += verticalMovement;
		east += horizontalMovement;

		if(Math.abs(verticalMovement - horizontalMovement) == 0 || Math.abs(verticalMovement - horizontalMovement) == 2) //if the pawn is not moving diagnol then check for a piece
		{
			if (((newFile == north && newRank == east) ||(newFile == north && newFile == east)) && 
					Board.getInstance().getPieceAt(newFile, newRank) != null) //if moving cross and if there is a piece there
			{
				return true;
			}
				
		}
		
		// a = 0 white
		//if the x-axis is the same, if the new Y position is equal to the initial Y position + 1 then it moved up once
		else if ((newRank == east) && (newFile == north))
		{
			System.out.println("Moved a White Pawn");
			return true;
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
