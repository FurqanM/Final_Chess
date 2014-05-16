package Controller;

import Model.Piece;

public class MoveValidator
{
	private Piece sourcePiece;
	private Piece targetPiece;

	public MoveValidator(Board board)
	{
		// TODO Auto-generated constructor stub
	}

	public Piece getSourcePiece()
	{
		return sourcePiece;
	}

	public void setSourcePiece(Piece sourcePiece)
	{
		this.sourcePiece = sourcePiece;
	}

	public Piece getTargetPiece()
	{
		return targetPiece;
	}

	public void setTargetPiece(Piece targetPiece)
	{
		this.targetPiece = targetPiece;
	}
}
