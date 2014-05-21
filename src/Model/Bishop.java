package Model;

public class Bishop extends Piece
{

	public Bishop(boolean isWhite)
	{
		super(isWhite);
	}

	@Override
	public boolean validateMovement(String position)
	{
		// c7 c5
		int initialRank = position.substring(0, 1).trim().toLowerCase().charAt(0) - 97; // c
		int initialFile = position.substring(1, 2).trim().charAt(0) - 49; // 7
		int newRank = position.substring(3, 4).trim().toLowerCase().charAt(0) - 97; // c
		// 5 maybe *
		int newFile = position.substring(4).trim().charAt(0) - 49;
		
		int movement = Math.abs(newFile - initialFile);

		// a = 0
		//white
		//if ((((initialFile + 1) == newFile && (initialRank + 1) == newRank) || ((initialFile + 1) == newFile && (initialRank - 1) == newRank)) || (((initialFile - 1) == newFile && (initialRank - 1) == newRank) || ((initialFile - 1) == newFile && (initialRank + 1) == newRank)))
		/*
		String undatedPosition = (position.substring(3, 4).trim().toLowerCase() + position.substring(4).trim()); //c5
		
		char[] charSequence = {(char) ((newRank + 1) + 97) , (char) ((newFile + 1) + 49)}; // d6
		
		String charString = String.copyValueOf(charSequence); // d6
		
		String updatedPosition = undatedPosition + " " + charString;
		*/
		/*
		int greaterSquare = 0;
		int smallerSquare = 0;
		
		if(initialFile < newFile)
		{
			greaterSquare = newFile;
			smallerSquare = initialFile;
		}
			
		
		else 
		{
			greaterSquare = initialFile;
			smallerSquare = newFile;
		}
		*/
			
		
		int north = initialFile;
		int east = initialRank;
		int south = initialFile;
		int west = initialRank;
		
		
		

		//Takes the position that is larger and smaller between the 2 points and increments i by the difference
		//of the amount and then while it is iterating it is incrementing north and east
		//until it matches the matched position the user wants to go and then returns true if a valid move
		//or false if invalid
		for(int i = 0; i < movement; i++)
		{
			north++;
			east++;
			if(north == newFile && east == newRank)
			{
				System.out.println("Moved a Bishop north-east");
				return true;
			}
		}
		
		//Takes the position that is larger and smaller between the 2 points and increments i by the difference
		//of the amount and then while it is iterating it is decrementing south and west
		//until it matches the matched position the user wants to go and then returns true if a valid move
		//or false if invalid
		north = initialFile;
		east = initialRank;
		south = initialFile;
		west = initialRank;
		for(int i = 0; i < movement; i++)
		{
			south--;
			west--;
			if(south == newFile && west == newRank)
			{
				System.out.println("Moved a Bishop south-west");
				return true;
			}
		}
		north = initialFile;
		east = initialRank;
		south = initialFile;
		west = initialRank;
		
		for(int i = 0; i < movement; i++)
		{
			north++;
			west--;
			if(north == newFile && west == newRank)
			{
				System.out.println("Moved a Bishop north-west");
				return true;
			}
		}
		
		north = initialFile;
		east = initialRank;
		south = initialFile;
		west = initialRank;
		
		for(int i = 0; i < movement; i++)
		{
			south--;
			east++;
			if(north == newFile && west == newRank)
			{
				System.out.println("Moved a Bishop south-east");
				return true;
			}
		}
		
	
		System.out.println("Not a valid movement, ignoring command.");
		return false;
	}

	// move (x - 1 && y - 1) || (x + 1 && y + 1)
	// (x - 1 && y + 1) || (x + 1 && y - 1)

}
