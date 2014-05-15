package Model;

public class Piece
{
	private String picture;
	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}
	
	public String getPicture()
	{
		return picture;
	}

	public void setPicture(String picture)
	{
		this.picture = picture;
	}
	
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();
		return name.substring(6, 7);
	}
	
}