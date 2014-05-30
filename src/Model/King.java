package Model;

import Controller.Board;

/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should. All of the handling and checking for the movement is done within this class
 * (or Super class if validateMove method is invoked polymorphically.
 */

public class King extends Piece
{

	boolean inCheck = false;

	public King(boolean isWhite)
	{
		super(isWhite);
	}

	@Override
	public boolean validateMovement(Position position)
	{

		// c7 c5

//		if(Board.getInstance().isWhiteTurn() &&
//				(Board.getInstance().getLightKingPosition().equals(position.substring(3, 4).trim().toLowerCase().charAt(0) - 'a' + position.substring(4).trim().charAt(0) - '1'))
//				) //if it's white's turn and the White King's new position == Black king's current position
//			return false;
//		
//		else if(Board.getInstance().isWhiteTurn() &&
//				(Board.getInstance().getDarkKingPosition().equals(position.substring(3, 4).trim().toLowerCase().charAt(0) - 'a' + position.substring(4).trim().charAt(0) - '1'))
//				)
//			return false;
		
			if (hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 1, 0) || //move up
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -1, 0) || //move down
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 0, 1) || //move right
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 0, -1) || //move left
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 1, 1) || //move north east
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -1, -1) || //move south west
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), 1, -1) || //move north west
					hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), -1, 1)) //move south east
					{
						if(this.isWhite()) //TODO
							Board.getInstance().setLightKingPosition(position);
						else
							Board.getInstance().setDarkKingPosition(position);
						return true;
					}
						
					else
						return false;
		}
	

	private boolean hasValidMove(int initialRank, int initialFile, int newRank, int newFile, int vertMovement, int horizMovement)
	{
		int north;
		int east;
		north = initialFile;
		east = initialRank;
		north += vertMovement;
		east += horizMovement;

		if (newRank == east && newFile == north)
		{
//			System.out.println("Moved a King");
			return true;
		}
		else
		{
			//System.out.println("Invalid movement, ignoring command");
			return false;
		}
	}

	public boolean isInCheck()
	{
		return inCheck;
	}

	public void setInCheck(boolean inCheck)
	{
		this.inCheck = inCheck;
	}

	//move (y - 1) || (y + 1)
	//(x + 1) || (x - 1)
	//or ((x - 1) && (y - 1)) || (x + 1) && (y - 1)
	//or ((x + 1) && (y + 1)) || ( x - 1) && ( y + 1))

}
