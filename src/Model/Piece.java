package Model;

import Controller.Board;

public abstract class Piece
{
	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}
	
	public abstract boolean validateMovement(String position);
	
	public boolean validateColor()
	{
		if(isWhite)
			return true; //is White
		else
			return false; //is Black
	}

	public boolean isWhite()
	{
		return isWhite;
	}

	public void setWhite(boolean isWhite)
	{
		this.isWhite = isWhite;
	}

	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();

		return (this.isWhite == true) ? name.substring(6, 7).toUpperCase() : name.substring(6, 7).toLowerCase();
	}

}