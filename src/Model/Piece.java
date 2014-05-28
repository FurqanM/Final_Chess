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

	//Movement across method takes care of moving cross for bishops and the queen
	//the movement method is invoked polymorphically for each class
	public boolean moveCross(String position)
	{

		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 'a'; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - '1'; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 'a'; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - '1';

		int movement = Math.abs(newFile - initialFile);

		//Takes the position that is larger and smaller between the 2 points and increments i by the difference
		//of the amount and then while it is iterating it is decrementing south and west
		//until it matches the matched position the user wants to go and then returns true if a valid move
		//or false if invalid

		if (hasValidMove(initialRank, initialFile, newRank, newFile, movement, 1, 1) || 
				hasValidMove(initialRank, initialFile, newRank, newFile, movement, -1, -1) || 
				hasValidMove(initialRank, initialFile, newRank, newFile, movement, 1, -1) || 
				hasValidMove(initialRank, initialFile, newRank, newFile, movement, -1, 1))
			return true;

		System.out.println("Not a valid movement, ignoring command.");
		return false;
	}

	private boolean hasValidMove(int initialRank, int initialFile, int newRank, int newFile, int movement, int verticalMovement, int horzMovement)
	{
		int north;
		int east;
		north = initialFile;
		east = initialRank;

		for (int i = 0; i < movement; i++)
		{
			north += verticalMovement;
			east += horzMovement;

			if (north == newFile && east == newRank)
			{
				System.out.println("Moved a Piece south-west");
				if (Board.getInstance().getBoardSetup()[north][east] != null)
				{
					System.out.println("Piece is in the path movement.");
					return false;
				}
				return true;
			}

		}

		return true;
	}

	//Movement across method takes care of moving cross for rook and the queen
	//the movement method is invoked polymorphically for each class
	public boolean moveStraight(String position)
	{
		//the initial file and rank classes are there to capture in memory each location of the piece and take apart
		//the string in order to move them correctly
		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 'a'; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - '1'; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 'a'; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - '1';

		int movement = Math.abs(newFile - initialFile);

		movement = Math.abs(newFile - initialFile);

		if (hasValidMove(initialRank, initialFile, newRank, newFile, movement, 1, 0) || //up
		hasValidMove(initialRank, initialFile, newRank, newFile, movement, -1, 0) || //down
		hasValidMove(initialRank, initialFile, newRank, newFile, movement, 0, 1) || //right
		hasValidMove(initialRank, initialFile, newRank, newFile, movement, 0, -1)) //left
			return true;

		System.out.println("Not a valid movement or piece blocking path, ignoring command.");
		return false;

	}

	public boolean isWhite()
	{
		return isWhite;
	}

	public void setWhite(boolean isWhite)
	{
		this.isWhite = isWhite;
	}

	//This method overrides the toString to concatonate the piece name to the display to print out what piece it is
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();

		return (this.isWhite)
				? name.substring(6, 7).toUpperCase()
				: name.substring(6, 7).toLowerCase();
	}

}