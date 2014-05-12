package Model;


public class Piece
{
	private String picture;
	private PieceType type;
	
	public Piece(PieceType type)
	{
		this.type = type;
	}
	
	
	
	
	public String getPicture()
	{
		return picture;
	}
	public void setPicture(String picture)
	{
		this.picture = picture;
	}
	public PieceType getType()
	{
		return type;
			
	}
	public void setType(PieceType type)
	{
		this.type = type;
		if(type == PieceType.NOPIECE)
		{
			this.picture = " ";
		}
	}
	public void setPiece(Piece piece)
	{
		this.type = piece.type;
		this.picture = piece.picture;
	}
}
