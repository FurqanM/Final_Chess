package Model;

import Controller.Board;

public class Piece
{
	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}
	
	public void movePiece(String position)
	{
		System.out.println("I MOVED BITCHASS!");
		Board.getInstance().movePiece(position);
	}

	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();

		return (this.isWhite == true) ? name.substring(6, 7).toUpperCase() : name.substring(6, 7).toLowerCase();
	}

}