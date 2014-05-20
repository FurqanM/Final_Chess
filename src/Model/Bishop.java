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
			String undatedPosition = (position.substring(3, 4).trim().toLowerCase() + position.substring(4).trim()); //c5
			
			char[] charSequence = {(char) ((newRank + 1) + 97) , (char) ((newFile + 1) + 49)}; // d6
			
			String charString = String.copyValueOf(charSequence); // d6
			
			String updatedPosition = undatedPosition + " " + charString;
			
			if(updatedPosition.matches("(([a-h][1-8])\\s*([a-h][1-8]))"))
			{
				validateMovement(updatedPosition);
				System.out.println("Moved a Bishop");
				return true;
			}
			else
			{}
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
