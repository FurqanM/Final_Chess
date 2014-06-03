package Model;

import java.util.regex.Pattern;

import Controller.Board;

public class Position
{
	public static int whiteKingFile;
	public static int whiteKingRank;
	public static int blackKingFile;
	public static int blackKingRank;
	private int initialFile;
	private int initialRank;
	private int newFile;
	private int newRank;
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
			if (string.length() == 4)
			{
				// ple4

				string.substring(0, 2).trim().toLowerCase();

				int initialRank = string.substring(2, 3).trim().toLowerCase().charAt(0) - 'a'; // e
				int initialFile = string.substring(3).trim().toLowerCase().charAt(0) - '1'; // 4

				this.initialFile = initialFile;
				this.initialRank = initialRank;

				this.originalString = (string.substring(0, 2).trim().toLowerCase()) + //pl
				(string.substring(2, 3).trim().toLowerCase()) + //e
				(string.substring(3).trim().toLowerCase()); //4
			}
			else
			{
				// c7 c5
				int initialRank = (string.substring(0, 1).trim().toLowerCase().charAt(0) - 'a'); // c
				int initialFile = (string.substring(1, 2).trim().charAt(0) - '1'); // 7

				int newRank = (string.substring(3, 4).trim().toLowerCase().charAt(0) - 'a'); // c
				int newFile = (string.substring(4).trim().charAt(0) - '1'); // 5 maybe *
				
				this.initialRank = initialRank;
				this.initialFile = initialFile;
				this.newRank = newRank;
				this.newFile = newFile;
				

				this.originalString = ((string.substring(0, 1).trim().toLowerCase()) + (string.substring(1, 2).trim()) + " " + (string.substring(3, 4).trim().toLowerCase()) + (string.substring(4).trim())); //*
			}

		}
		else
		{
		}

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

	public String getOriginalString()
	{
		return this.originalString;
	}

	public void setOriginalString(String originalString)
	{
		this.originalString = originalString;
	}

}
