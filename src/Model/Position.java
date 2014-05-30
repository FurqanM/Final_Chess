package Model;

import java.util.regex.Pattern;

import Controller.Board;

public class Position
{
	private int initialFile;
	private int initialRank;
	private int newFile;
	private int newRank;
	private String piece;
	private String originalString;
	
	
	public Position(String string)
	{
//		String piece = position.substring(0, 2).trim().toLowerCase(); // pl
//
//		int initialRank = position.substring(2, 3).trim().toLowerCase().charAt(0) - 'a'; // e
//		int initialFile = position.substring(3).trim().toLowerCase().charAt(0) - '1'; // 4
//
//		boolean isWhite = (piece.substring(1, 2).toLowerCase().equals("l"));
		
		if (Pattern.matches(Board.getInstance().getCHESS_PATTERN(), string))
		{
			if(string.length() == 4)
			{
				// ple4

				String piece = string.substring(0, 2).trim().toLowerCase(); // pl

				int initialRank = string.substring(2, 3).trim().toLowerCase().charAt(0) - 97; // e
				int initialFile = string.substring(3).trim().toLowerCase().charAt(0) - 49; // 4
				
				
				this.setOriginalString((string.substring(0, 2).trim().toLowerCase()) + //pl
						(string.substring(2, 3).trim().toLowerCase().charAt(0) - 'a') + //e
						(string.substring(3).trim().toLowerCase().charAt(0) - '1')); //4
			}
			else
			{
				// c7 c5
				this.setInitialRank(string.substring(0, 1).trim().toLowerCase().charAt(0) - 'a'); // c

				this.setInitialFile(string.substring(1, 2).trim().charAt(0) - '1'); // 7

				this.setNewRank(string.substring(3, 4).trim().toLowerCase().charAt(0) - 'a'); // c

				this.setNewFile(string.substring(4).trim().charAt(0) - '1'); // 5 maybe *
				
				this.setOriginalString((string.substring(0, 1).trim().toLowerCase().charAt(0) - 'a') +
						(string.substring(1, 2).trim().charAt(0) - '1') + " " +
						(string.substring(3, 4).trim().toLowerCase().charAt(0) - 'a') +
						(string.substring(4).trim().charAt(0) - '1')); //*
			}
			
			
					
		}
		else{}
		
	}
	
	
	
	
	


	public int getInitialFile()
	{
		return initialFile;
	}


	public void setInitialFile(int initialFile)
	{
		this.initialFile = initialFile;
	}


	public int getInitialRank()
	{
		return initialRank;
	}


	public void setInitialRank(int initialRank)
	{
		this.initialRank = initialRank;
	}


	public int getNewFile()
	{
		return newFile;
	}


	public void setNewFile(int newFile)
	{
		this.newFile = newFile;
	}


	public int getNewRank()
	{
		return newRank;
	}


	public void setNewRank(int newRank)
	{
		this.newRank = newRank;
	}





	public boolean killPiece(String position)
	{
		if(position.length() == 5)
			return false;
		else
			return false;
	}

	public boolean placePiece(String position)
	{
		if(position.length() == 4)
			return false;
		else
			return false;
		
	}







	public String getPiece()
	{
		return piece;
	}







	public void setPiece(String piece)
	{
		this.piece = piece;
	}
	
	@Override
	public String toString()
	{
		return this.getOriginalString();
	}







	public String getOriginalString()
	{
		return this.originalString;
	}







	public void setOriginalString(String originalString)
	{
		this.originalString = originalString;
	}








	

}
