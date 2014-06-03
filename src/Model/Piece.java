package Model;

import Controller.Board;

public abstract class Piece
{
	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}

	public abstract boolean validateMovement(Position position);

	//Movement across method takes care of moving cross for bishops and the queen
	//the movement method is invoked polymorphically for each class
	public boolean moveCross(Position position)
	{

		// c7 c5


		int movement = Math.abs(position.getNewFile() - position.getInitialFile());

		//Takes the position that is larger and smaller between the 2 points and increments i by the difference
		//of the amount and then while it is iterating it is decrementing south and west
		//until it matches the matched position the user wants to go and then returns true if a valid move
		//or false if invalid

		if (hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, 1, 1) || //moving north east
				hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, -1, -1) || //moving south west
				hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, 1, -1) ||  //north west
				hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, -1, 1)) //south east
		{
			return true;
		}
			
		

		//System.out.println("Not a valid movement or piece blocking path, ignoring command.");
		return false;
	}

	private boolean hasValidMove(int initialRank, int initialFile, int newRank, int newFile, int movement, int verticalMovement, int horzMovement)
	{
		int north;
		int east;
		north = initialFile;
		east = initialRank;

		for (int i = 0; i <= movement; i++)
		{
			north += verticalMovement;
			east += horzMovement;

			if ((north > -1 && north < 8) && (east > -1) && (east < 8)) //if the move is in the boundaries of the board
			{
				if (Board.getInstance().getPieceAt(north, east) != null) //if there is a piece here
				{
					if(((Board.getInstance().getPieceAt(north, east).isWhite() != this.isWhite())) && (newFile == north && newRank == east))
					{
						//System.out.println("Killed a piece");
						return true;
					}
					else
					{
						//System.out.println("Can't kill your own piece traitor!");
						return false;
					}
						
					
				}
				else if ((north == newFile && east == newRank) && Board.getInstance().getPieceAt(north, east) == null)
				{
					//System.out.println("Moved a Piece");
					return true;
				}
			}

		}

		return false;
	}

	//Movement across method takes care of moving cross for rook and the queen
	//the movement method is invoked polymorphically for each class
	public boolean moveStraight(Position position)
	{
		//the initial file and rank classes are there to capture in memory each location of the piece and take apart
		//the string in order to move them correctly
		// c7 c5

		int movement = 0;
		int movementVert = Math.abs(position.getNewFile() - position.getInitialFile());
		int movementHoriz = Math.abs(position.getNewRank() - position.getInitialRank());

		//if the movement of the piece on the X-axis is the same then that means that it moved
		//along the Y-axis or else it moved along the X-axis
		if(position.getNewFile() == position.getInitialFile())
			movement = movementHoriz;
		else
			movement = movementVert;

		if (hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, 1, 0) || //up
		hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, -1, 0) || //down
		hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, 0, 1) || //right
		hasValidMove(position.getInitialRank(), position.getInitialFile(), position.getNewRank(), position.getNewFile(), movement, 0, -1)) //left
		{
			return true;
		}
		

		//System.out.println("Not a valid movement or piece blocking path, ignoring command.");
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

	//This method overrides the toString to concatenate the piece name to the display to print out what piece it is
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();

		return (this.isWhite)
				? name.substring(6, 7).toUpperCase()
				: name.substring(6, 7).toLowerCase();
	}

}