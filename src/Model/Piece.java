package Model;

public class Piece
{
	private String picture;
	private Piece type;

	public String getPicture()
	{
		return picture;
	}

	public void setPicture(String picture)
	{
		this.picture = picture;
	}

	public Piece getType()
	{
		return type;

	}

	public void setType(Piece type)
	{
		this.type = type;
		if (type.equals(" "))
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
