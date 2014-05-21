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
		
		
		
		for(int i = initialFile; i < newFile + 1; i++)
			if(i == newFile && initialRank == newRank)
			{
				System.out.println("Moved Rook up");
				return true;
			}
		
		for(int i = initialFile; i > newFile - 1; i--)
			if(i == newFile && initialRank == newRank)
			{
				System.out.println("Moved Rook down");
				return true;
			}
		
		for(int i = initialRank; i < newRank + 1; i++)
			if(i == newRank && initialFile == newFile)
			{
				System.out.println("Moved Rook right");
				return true;
			}
		
		for(int i = initialRank; i > newRank - 1; i--)
			if(i == newRank && initialFile == newFile)
			{
				System.out.println("Moved Rook left");
				return true;
			}
		
		
				
		System.out.println("Not a valid movement, ignoring command.");
		return false;
	}
	
	//move (min)X to (max)X || (min)Y to (max)Y
	
}
