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
	public boolean validateMovement(Position position)
	{

		// c7 c5

		if (isWhite)
		{

			if(moveTwice(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 2) && (position.getInitialFile() == 1)) // if the white pawn is where it is supposed to be to move twice
			{
				return true;
			}
				

			else if (hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 1, 0) || //light movement
			hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 1, 1) || //if piece to north east kill it
			hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 1, -1)) //if piece to north west kill it
			{
				return true;
			}
		}
		else
		{
			
			if(moveTwice(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -2) && (position.getInitialFile()== 6)) // if the white pawn is where it is supposed to be to move twice
			{
				return true;
			}
				
			
			else if (hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -1, 0) || //dark movement
			hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -1, -1) || //if piece to south west then kill it
			hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -1, 1)) //if piece to south east then kill it
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
