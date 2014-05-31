package Model;

import Controller.Board;


/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should.
 * All of the handling and checking for the movement is done within this class (or Super class if validateMove method is invoked polymorphically.
 */

public class Knight extends Piece
{

	public Knight(boolean isWhite)
	{
		super(isWhite);
	}
	
	
	
	@Override
	public boolean validateMovement(Position position)
	{
		// c7 c5

		
		if(hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), 2, 1) || //up 2 right 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), -2, 1) || //down 2 right 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), 2, -1) || //up 2 left 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), -2, -1) || //down 2 left 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), 1, 2) || // right 2 up 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), -1, 2) || //right 2 down 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), 1, -2) || //left 2 up 1
				hasValidMove(position.getInitialFile(), position.getInitialRank(), position.getNewRank(), position.getNewFile(), -1, -2) // left 2 down 1
				)
		{	
			return true;
		}
				
		
		
		return false;
	}



	private boolean hasValidMove(int initialFile, int initialRank, int newRank, int newFile, int verticalMovement, int horizontalMovement)
	{
		
		int north = initialFile;
		int east = initialRank;
		
		north += verticalMovement;
		east += horizontalMovement;
		
		//movement logic for moving up 2 right 1
		if(newFile == north && newRank == east)
		{
			System.out.println("Moved a Knight");
			return true;
		}
		
		return false;
	}
	
	
	
	
	/*
	//vertical
	(y + 2) && (x + 1)
	(y + 2) && (x - 1)
	
	(y - 2) && (x + 1)
	(y - 2) && (x - 1)
	
	
	//horizontal
	(x + 2) && (y + 1)
	(x + 2) && (y - 1)
	
	(x - 2) && (y + 1)
	(x - 2) && (y - 1)
	*/
	
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();
		
		return (super.isWhite) ? name.substring(7, 8).toUpperCase() : name.substring(7, 8).toLowerCase();
	}
	
}
