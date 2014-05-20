package Model;

import Controller.Board;

public class Rook extends Piece
{

	public Rook(boolean isWhite)
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
		
		int numberOfSquaresFile = Math.abs(newFile - initialFile);
		int numberOfSquaresRank = Math.abs(newRank - initialRank);
		
		
		
		if(numberOfSquaresFile != 0)
		{
			
			
		}
		
		else if(numberOfSquaresRank != 0)
		{
			
		}
				
		System.out.println("Not a valid movement, ignoring command.");
		return false;
	}
	
	//move (min)X to (max)X || (min)Y to (max)Y
	
}
