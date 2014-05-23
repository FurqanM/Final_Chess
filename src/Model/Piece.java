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

	public boolean moveCross(String position)
	{

		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - 49; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - 49;

		final int END_BOARD = 8;
		final int BEGIN_BOARD = -1;

		int movement = Math.abs(newFile - initialFile);

		int north = initialFile;
		int east = initialRank;

		for (int i = 0; i < movement; i++)
		{
			north++;
			east++;
			if (Board.getInstance().getBoardSetup()[north][east] != null)
			{
				System.out.println("Piece is in the path movement.");
				return false;
			}
			else if (north == newFile && east == newRank)
			{
				System.out.println("Moved a Piece north-east");
				return true;
			}

		}

		//Takes the position that is larger and smaller between the 2 points and increments i by the difference
		//of the amount and then while it is iterating it is decrementing south and west
		//until it matches the matched position the user wants to go and then returns true if a valid move
		//or false if invalid
		north = initialFile;
		east = initialRank;

		for (int i = 0; i < movement; i++)
		{
			north--;
			east--;
			if ((north > BEGIN_BOARD && east > BEGIN_BOARD) && (north < END_BOARD && east < END_BOARD))
			{
				if (Board.getInstance().getBoardSetup()[north][east] != null)
				{
					System.out.println("Piece is in the path movement.");
					return false;
				}
				else if (north == newFile && east == newRank)
				{
					System.out.println("Moved a Piece south-west");
					return true;
				}
			}

		}
		north = initialFile;
		east = initialRank;

		for (int i = 0; i < movement; i++)
		{
			north++;
			east--;
			if ((north > BEGIN_BOARD && east > BEGIN_BOARD) && (north < END_BOARD && east < END_BOARD))
			{
				if (Board.getInstance().getBoardSetup()[north][east] != null)
				{
					System.out.println("Piece is in the path movement.");
					return false;
				}
				else if (north == newFile && east == newRank)
				{
					System.out.println("Moved a Piece north-west");
					return true;
				}
			}
		}

		north = initialFile;
		east = initialRank;

		for (int i = 0; i < movement; i++)
		{
			north--;
			east++;
			if ((north > BEGIN_BOARD && east > BEGIN_BOARD) && (north < END_BOARD && east < END_BOARD))
			{
				if (Board.getInstance().getBoardSetup()[north][east] != null)
				{
					System.out.println("Piece is in the path movement.");
					return false;
				}
				else if (north == newFile && east == newRank)
				{
					System.out.println("Moved a Piece south-east");
					return true;
				}
			}
		}

		System.out.println("Not a valid movement, ignoring command.");
		return false;
	}

	public boolean moveStraight(String position)
	{
		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - 49; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - 49;

		int movement = Math.abs(newFile - initialFile);

		int north = initialFile;
		int east = initialRank;

		// c7 c5
		initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		initialFile = position.substring(1, 2).trim().charAt(0) - 49; // 7
		newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		newFile = position.substring(4).trim().charAt(0) - 49;

		movement = Math.abs(newFile - initialFile);

		north = initialFile;
		east = initialRank;

		for (int i = initialFile; i < newFile + 1; i++)
		{
			if (i == newFile && initialRank == newRank)
			{
				System.out.println("Moved Piece up");
				return true;
			}
		}

		for (int i = initialFile; i > newFile - 1; i--)
		{
			if (i == newFile && initialRank == newRank)
			{
				System.out.println("Moved Piece down");
				return true;
			}
		}

		for (int i = initialRank; i < newRank + 1; i++)
		{
//			if (Board.getInstance().getBoardSetup()[north][east] != null) TODO
//			{
//				System.out.println("Piece is in the path movement.");
//				return false;
//			}
			if (i == newRank && initialFile == newFile)
			{
				System.out.println("Moved Piece right");
				return true;
			}
		}

		for (int i = initialRank; i > newRank - 1; i--)
		{
			if (i == newRank && initialFile == newFile)
			{
				System.out.println("Moved Piece left");
				return true;
			}
		}

		System.out.println("Not a valid movement, ignoring command.");
		return false;

	}

	public boolean validateColor()
	{
		if (isWhite)
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