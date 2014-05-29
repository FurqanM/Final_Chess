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
	public boolean validateMovement(String position)
	{
		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - 49; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - 49;

		
		if(hasValidMove(initialFile, initialRank, newRank, newFile, 2, 1) || //up 2 right 1
				hasValidMove(initialFile, initialRank, newRank, newFile, -2, 1) || //down 2 right 1
				hasValidMove(initialFile, initialRank, newRank, newFile, 2, -1) || //up 2 left 1
				hasValidMove(initialFile, initialRank, newRank, newFile, -2, -1) || //down 2 left 1
				hasValidMove(initialFile, initialRank, newRank, newFile, 1, 2) || // right 2 up 1
				hasValidMove(initialFile, initialRank, newRank, newFile, -1, 2) || //right 2 down 1
				hasValidMove(initialFile, initialRank, newRank, newFile, 1, -2) || //left 2 up 1
				hasValidMove(initialFile, initialRank, newRank, newFile, -1, -2) // left 2 down 1
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
