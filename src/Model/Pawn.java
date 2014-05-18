package Model;

import Controller.Board;

public class Pawn extends Piece
{

	public Pawn(boolean isWhite)
	{
		super(isWhite);
	}
	
	
	@Override
	public boolean validatePieceColor()
	{				
				
		if(isWhite)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
					
	}
	
	
	
	
	
	
	public boolean validateWhite(String moveBoardPosition)
	{

		
		// c7 c5
		int initialRank = moveBoardPosition.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c

		int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - 49; // 7

		int newRank = moveBoardPosition.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c

		int newFile = moveBoardPosition.substring(4).trim().charAt(0) - 49; // 5 maybe *
		
		
		if((newRank == initialRank)&&(newFile - initialFile == 1)) //a = 0      //white
			return true;
		else
			System.out.println("Not a valid movement, ignoring command.");
			return false;
		
	}
	
	
	

	public boolean validateBlack(String moveBoardPosition)
	{

		
		// c7 c5
		int initialRank = moveBoardPosition.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c

		int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - 49; // 7

		int newRank = moveBoardPosition.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c

		int newFile = moveBoardPosition.substring(4).trim().charAt(0) - 49; // 5 maybe *
		
		
		
		if((newRank == initialRank)&&(newFile - initialFile == -1)) //a = 0      //black
			return true;
		else
		{
			System.out.println("Not a valid movement, ignoring command.");
			return false;
			
		}
			
	}


	
	
	//move y - 1      black Pawn
	//or ((x - 1) && (y - 1)) || ((x + 1) && (y - 1))
	//move one space down
	//if has not moved yet then move two spaces y = 7
	
	//white pawn
	//move y + 1
	//or ((x + 1) && (y + 1)) || ((x - 1) && (y + 1))
	//move one space up
	//if has not moved yet then move two spaces y = 2
	
}
