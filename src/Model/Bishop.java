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

		// a = 0
		//white
		if ((((initialFile + 1) == newFile && (initialRank + 1) == newRank) || ((initialFile + 1) == newFile && (initialRank - 1) == newRank)) || (((initialFile - 1) == newFile && (initialRank - 1) == newRank) || ((initialFile - 1) == newFile && (initialRank + 1) == newRank)))
		{
			System.out.println("Moved a Bishop");
			return true;
		}
		else
		{
			System.out.println("Not a valid movement, ignoring command.");
			return false;
		}

	}

	// move (x - 1 && y - 1) || (x + 1 && y + 1)
	// (x - 1 && y + 1) || (x + 1 && y - 1)

}
