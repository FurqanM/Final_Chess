package Model;


/*
 * This class will be responsible for this piece movement and overriding its methods to move and behave like how it should.
 * All of the handling and checking for the movement is done within this class (or Super class if validateMove method is invoked polymorphically.
 */

public class Knight extends Piece
{

	public Knight(boolean isWhite)
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

		
		//movement logic for moving up 2 right 1
		if(newFile == north+2 && newRank == east+1)
		{
			System.out.println("Moved a Knight up 2 right 1");
			return true;
		}
		
		//movement logic for moving up 2 and left 1
		else if(newFile == north+2 && newRank == east-1)
		{
			System.out.println("Moved a Knight up 2 left 1");
			return true;
		}
		//movement logic for moving down 2 right 1
		else if(newFile == north-2 && newRank == east+1)
		{
			System.out.println("Moved a Knight down 2 right 1");
			return true;
		}
		//movement down 2 left 1
		else if(newFile == north-2 && newRank == east-1)
		{
			System.out.println("Moved a Knight down 2 left 1");
			return true;
		}
		
		//movement for right 2 up 1
		else if(newRank == east+2 && newFile == north+1)
		{
			System.out.println("Moved a Knight right 2 up 1");
			return true;
		}
		//movement for right 2 down 1
		else if(newRank == east+2 && newFile == north-1)
		{
			System.out.println("Moved a Knight right 2 down 1");
			return true;
		}
		//movement for left 2 up 1
		else if(newRank == east-2 && newFile == north+1)
		{
			System.out.println("Moved a Knight left 2 up 1");
			return true;
		}
		//movement for left 2 down 1
		else if(newRank == east-2 && newFile == north-1)
		{
			System.out.println("Moved a Knight left 2 up 1");
			return true;
		}
		else
		{
			System.out.println("Invalid movement, ignoring command");
			return false;
		}
	}
	
	
	
	
	/*
	//vertical
	(y + 2) && (x + 1)
	(y + 2) && (x - 1)
	
	(y - 2) && (x + 1)
	(y - 2) && (x - 1)
	
	
	//horizontal
	(x + 2) && (y + 1)
	(x + 2) && (y - 1)
	
	(x - 2) && (y + 1)
	(x - 2) && (y - 1)
	*/
	
	@Override
	public String toString()
	{
		String name = "" + this.getClass().getCanonicalName();
		
		return (super.isWhite) ? name.substring(7, 8).toUpperCase() : name.substring(7, 8).toLowerCase();
	}
	
}
