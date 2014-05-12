package Controller;

import java.lang.reflect.Array;

import Model.Piece;
import Model.PieceType;

public class Board
{
	private final String FILE = "ABCDEFGH";
	private final String RANK = "123456789";
	private Piece[][] boardSetup;
	private final int BOARD_FILE = 8;
	private final int BOARD_RANK = 8;

	private static Board instance = null;

	public static Board getInstance()
	{
		if (instance == null)
		{
			instance = new Board();
		}
		return instance;
	}

	public Board()
	{
		this.boardSetup = new Piece[8][8];
		boardSetup[0][0] = new Piece(PieceType.ROOK);
		boardSetup[0][1] = new Piece(PieceType.KNIGHT);
		boardSetup[0][2] = new Piece(PieceType.BISHOP);
		boardSetup[0][3] = new Piece(PieceType.QUEEN);
		boardSetup[0][4] = new Piece(PieceType.KING);
		boardSetup[0][5] = new Piece(PieceType.BISHOP);
		boardSetup[0][6] = new Piece(PieceType.KNIGHT);
		boardSetup[0][7] = new Piece(PieceType.ROOK);
		boardSetup[1][0] = new Piece(PieceType.PAWN);
		boardSetup[1][1] = new Piece(PieceType.PAWN);
		boardSetup[1][2] = new Piece(PieceType.PAWN);
		boardSetup[1][3] = new Piece(PieceType.PAWN);
		boardSetup[1][4] = new Piece(PieceType.PAWN);
		boardSetup[1][5] = new Piece(PieceType.PAWN);
		boardSetup[1][6] = new Piece(PieceType.PAWN);
		boardSetup[1][7] = new Piece(PieceType.PAWN);

		for (int y = 2; y <= 5; y++)
			for (int x = 0; x < BOARD_RANK; x++)
				boardSetup[y][x] = new Piece(PieceType.NOPIECE);

		boardSetup[7][0] = new Piece(PieceType.ROOK);
		boardSetup[7][1] = new Piece(PieceType.KNIGHT);
		boardSetup[7][2] = new Piece(PieceType.BISHOP);
		boardSetup[7][3] = new Piece(PieceType.QUEEN);
		boardSetup[7][4] = new Piece(PieceType.KING);
		boardSetup[7][5] = new Piece(PieceType.BISHOP);
		boardSetup[7][6] = new Piece(PieceType.KNIGHT);
		boardSetup[7][7] = new Piece(PieceType.ROOK);
		boardSetup[6][0] = new Piece(PieceType.PAWN);
		boardSetup[6][1] = new Piece(PieceType.PAWN);
		boardSetup[6][2] = new Piece(PieceType.PAWN);
		boardSetup[6][3] = new Piece(PieceType.PAWN);
		boardSetup[6][4] = new Piece(PieceType.PAWN);
		boardSetup[6][5] = new Piece(PieceType.PAWN);
		boardSetup[6][6] = new Piece(PieceType.PAWN);
		boardSetup[6][7] = new Piece(PieceType.PAWN);

		for (int y = 0; y < BOARD_FILE; y++)
			for (int x = 0; x < BOARD_RANK; x++)
			{
				if (boardSetup[y][x].getType() == PieceType.PAWN)
					boardSetup[y][x].setPicture("WPN");
				else if (boardSetup[y][x].getType() == PieceType.ROOK)
					boardSetup[y][x].setPicture("WRK");
				else if (boardSetup[y][x].getType() == PieceType.BISHOP)
					boardSetup[y][x].setPicture("WBP");
				else if (boardSetup[y][x].getType() == PieceType.KNIGHT)
					boardSetup[y][x].setPicture("WKT");
				else if (boardSetup[y][x].getType() == PieceType.QUEEN)
					boardSetup[y][x].setPicture("WQN");
				else if (boardSetup[y][x].getType() == PieceType.KING)
					boardSetup[y][x].setPicture("WKG");
				else if (boardSetup[y][x].getType() == PieceType.PAWN)
					boardSetup[y][x].setPicture("BPN");
				else if (boardSetup[y][x].getType() == PieceType.ROOK)
					boardSetup[y][x].setPicture("BRK");
				else if (boardSetup[y][x].getType() == PieceType.BISHOP)
					boardSetup[y][x].setPicture("BBP");
				else if (boardSetup[y][x].getType() == PieceType.KNIGHT)
					boardSetup[y][x].setPicture("BKT");
				else if (boardSetup[y][x].getType() == PieceType.QUEEN)
					boardSetup[y][x].setPicture("BQN");
				else if (boardSetup[y][x].getType() == PieceType.KING)
					boardSetup[y][x].setPicture("BKG");
				else
					boardSetup[y][x].setPicture(" ");
			}
	}

	public void draw()
	{
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for (int y = 0; y < BOARD_RANK; y++)
		{
			System.out.print(y + " |");
			for (int x = 0; x < BOARD_RANK; x++)
			{
				System.out.printf("%3s|", boardSetup[y][x].getPicture());
			}
			System.out.println();
			System.out.println("  +---+---+---+---+---+---+---+---+");
		}
		System.out.println("  + A + B + C + D + E + F + G + H +");
	}

	public void movePiece(String boardPosition, String newPosition)
	{
		
		// with letterSideInitial and letterSideNew the input should be something like (a7) 
		// so splitting it into the letter and into the number, then converting them all to
		// int to be able to manipulate the position on the 2d array
		this.boardSetup[][]
		
		// these 2 lines actually change the position to move to to the old piece
		// and the original position with a NOPIECE
		
				this.boardSetup[newY][newX].setPiece(this.boardSetup[initialY][initialX]);
				this.boardSetup[initialY][initialX].setType(PieceType.NOPIECE);
				
		
		//test and modulate the castling and isolation
		//of the checkmate and check application between methods
	}

}
