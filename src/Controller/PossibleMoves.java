package Controller;

import Model.PieceType;

public class PossibleMoves
{
	
	

	public void movePath(PieceType piece)
	{
		//Moves pieces
		if(this.equals(piece.PAWN))
		{
		//	if(Board[x][y].hasPiece(NOPIECE))
					{
		//				PieceType.BPAWN[][].
					}
			//move y - 1
			//or ((x - 1) && (y - 1)) || ((x + 1) && (y - 1))
			//move one space down
			//if has not moved yet then move two spaces y = 7
		}
		
		else if(this.equals(piece.PAWN))
		{
			//move y + 1
			//or ((x + 1) && (y + 1)) || ((x - 1) && (y + 1))
			//move one space up
			//if has not moved yet then move two spaces y = 2
		}
		
		else if(this.equals(piece.BISHOP))
		{
			//move (x - 1 && y - 1) || (x + 1 && y + 1)
			//(x - 1 && y + 1) || (x + 1 && y - 1)
		}
		
		else if(this.equals(piece.ROOK))
		{
			//move (min)X to (max)X || (min)Y to (max)Y
		}
		
		else if(this.equals(piece.KNIGHT))
		{
			
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
		}
		
		else if(this.equals(piece.QUEEN))
		{
			//move Rook + Bishop
		}
		
		else if(this.equals(piece.KING))
		{
			//move (y - 1) || (y + 1)
			//(x + 1) || (x - 1)
			//or ((x - 1) && (y - 1)) || (x + 1) && (y - 1)
			//or ((x + 1) && (y + 1)) || ( x - 1) && ( y + 1))
		}
	}
	
	
}
