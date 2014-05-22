package Model;

public class King extends Piece
{

	public King(boolean isWhite)
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

		
		int north = initialFile;
		int east = initialRank;

		
		//movement logic for moving in N E S W
		if(initialRank == newRank && initialFile == north++)
		{
			System.out.println("Moved a King up");
			return true;
		}
		
		else if(initialRank == newRank && initialFile == north--)
		{
			System.out.println("Moved a King down");
			return true;
		}
		else if(initialFile == newFile && initialRank == east++)
		{
			System.out.println("Moved a King right");
			return true;
		}
		else if(initialFile == newFile && initialRank == east--)
		{
			System.out.println("Moved a King left");
			return true;
		}
		
		//Movement logic for movement in NE SE SW NW
		if(initialFile == north++ && initialRank == east++)
		{
			System.out.println("Moved a King north east");
			return true;
		}
		else if(initialFile == north-- && initialRank == east++)
		{
			System.out.println("Moved a King south east");
			return true;
		}
		else if(initialFile == north-- && initialRank == east--)
		{
			System.out.println("Moved a King south west");
			return true;
		}
		else if(initialFile == north++ && initialRank == east--)
		{
			System.out.println("Moved a King north west");
			return true;
		}
		else
		{
			System.out.println("Invalid movement, ignoring command");
			return false;
		}

		
	}
	
	
	
	//move (y - 1) || (y + 1)
	//(x + 1) || (x - 1)
	//or ((x - 1) && (y - 1)) || (x + 1) && (y - 1)
	//or ((x + 1) && (y + 1)) || ( x - 1) && ( y + 1))

}
