package Model;

import Controller.Board;

public class Pawn extends Piece
{

	public Pawn(boolean isWhite)
	{
		super(isWhite);
	}
	
	@Override
	public boolean validateMovement()
	{
		if(isWhite)
			return true;
		else return false;		
	}
	//move y - 1
	//or ((x - 1) && (y - 1)) || ((x + 1) && (y - 1))
	//move one space down
	//if has not moved yet then move two spaces y = 7
	
	//white pawn
	//move y + 1
	//or ((x + 1) && (y + 1)) || ((x - 1) && (y + 1))
	//move one space up
	//if has not moved yet then move two spaces y = 2
	
}
