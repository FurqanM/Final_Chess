package Model;


public enum PieceType
{
	
	BPAWN, BBISHOP, BROOK, BKNIGHT, BQUEEN, BKING,
	WPAWN, WBISHOP, WROOK, WKNIGHT, WQUEEN, WKING,
	NOPIECE;
	
	
	public void movePath(Piece piece)
	{
		//Moves pieces
		if(this.equals(BPAWN))
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
		
		else if(this.equals(WPAWN))
		{
			//move y + 1
			//or ((x + 1) && (y + 1)) || ((x - 1) && (y + 1))
			//move one space up
			//if has not moved yet then move two spaces y = 2
		}
		
		else if(this.equals(BBISHOP) || this.equals(WBISHOP))
		{
			//move (x - 1 && y - 1) || (x + 1 && y + 1)
			//(x - 1 && y + 1) || (x + 1 && y - 1)
		}
		
		else if(this.equals(BROOK) || this.equals(WROOK))
		{
			//move (min)X to (max)X || (min)Y to (max)Y
		}
		
		else if(this.equals(BKNIGHT) || this.equals(WKNIGHT))
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
		
		else if(this.equals(BQUEEN) || this.equals(WQUEEN))
		{
			//move Rook + Bishop
		}
		
		else if(this.equals(BKING) || this.equals(WKING))
		{
			//move (y - 1) || (y + 1)
			//(x + 1) || (x - 1)
			//or ((x - 1) && (y - 1)) || (x + 1) && (y - 1)
			//or ((x + 1) && (y + 1)) || ( x - 1) && ( y + 1))
		}
	}
	
	
}

