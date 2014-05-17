package Model;

import Controller.Board;

public class Piece
{
	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}
	
	public boolean validateMovement()
	{
		System.out.println("This is invoking the Piece classs");
		return isWhite;
		
	}

	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();

		return (this.isWhite == true) ? name.substring(6, 7).toUpperCase() : name.substring(6, 7).toLowerCase();
	}

}