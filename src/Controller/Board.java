package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import Model.Bishop;
import Model.King;
import Model.Knight;
import Model.Pawn;
import Model.Piece;
import Model.Queen;
import Model.Rook;

public class Board
{
	// this.color = (this.file + this.rank) % 2 == 0 ? ColorDefinition.DARK :
	// ColorDefinition.LIGHT;
	

	// regex pattern for reading chess input
	// ([BKNPQR][ld][a-h][1-8])|((([a-h][1-8][ \*]?){2})+)
	// ([BKNPQR][ld][a-h][1-8])|((?:(?:[a-h][1-8][ ]?){2})+)
	// ([BKNPQR]?)([a-hl]?)([0-9]?)([x=]?)([BKNPQR\*]|[a-h][1-8])([+#]?)
	// ([KQNBR]?([a-h]?[1-8]?x)?[a-h]([2-7]|[18](=[KQNBR])?)|0-0(-0)?)(\(ep\)|\+{1,2})?

	private static final String CHESS_PATTERN = "^(([a-h][1-8])\\s*([a-h][1-8])(\\*?))|([qkbprn][dl][a-h][1-8])$";
	// private final String FILE = "12345678";
	private final String FILE = "87654321";
	private final String RANK = "abcdefgh";
	private Piece[][] boardSetup;
	private final int BOARD_FILE = 8;
	private final int BOARD_RANK = 8;
	
	private MoveValidator moveValidator;

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
		moveValidator = new MoveValidator(this);
		
		this.boardSetup = new Piece[8][8];

		for (int y = 0; y < BOARD_FILE; y++)
			for (int x = 0; x < BOARD_RANK; x++)
				boardSetup[y][x] = null;

	}

	public void draw()
	{
		int j = BOARD_FILE;
		System.out.println("    A   B   C   D   E   F   G   H ");
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for (int y = 0; y < BOARD_RANK; y++)
		{
			j--;
			System.out.print(j + 1 + " |");

			for (int x = 0; x < BOARD_RANK; x++)
			{
				if (boardSetup[y][x] == null)
				{
					System.out.printf("%3s|", " ");
				}
				else
				{
					System.out.printf(" %-2s|", boardSetup[y][x].toString());
				} 

			}
			System.out.println();
			System.out.println("  +---+---+---+---+---+---+---+---+");
		}
		System.out.println("    A   B   C   D   E   F   G   H ");
	}

	public void movePiece(String moveBoardPosition)
	{

		// c7 c5
		int initialRank = RANK.indexOf(moveBoardPosition.substring(0, 1).trim()); // c
		int initialFile = FILE.indexOf(moveBoardPosition.substring(1, 2).trim()); // 7

		int newRank = RANK.indexOf(moveBoardPosition.substring(3, 4).trim()); // c
		int newFile = FILE.indexOf(moveBoardPosition.substring(4).trim()); // 5 maybe *

		//this.boardSetup[initnewFile][newRank] = null;
		this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
		this.boardSetup[initialFile][initialRank] = null;
		
		System.out.println("\t\t\t\t\t\t\t" + " Moves piece from " + moveBoardPosition.substring(0, 2).trim() + " to " + moveBoardPosition.substring(3, 5).trim());
		
		//this.boardSetup[newFile][newRank].setPiece(this.boardSetup[initialFile][initialRank]);
		
		// this.boardSetup[newFile][newRank].setPiece(this.boardSetup[initialFile][initialRank]);
		// this.boardSetup[initialFile][initialRank].setType(PieceType.NOPIECE);

	}

	public void placePiece(String position)
	{
		// ple4

		String piece = position.substring(0, 2).trim().toLowerCase(); // pl

		int initialRank = RANK.indexOf(position.substring(2, 3).trim()); // e
		int initialFile = FILE.indexOf(position.substring(3).trim()); // 4

		boolean isWhite;
		if (piece.substring(1, 2).toLowerCase().equals("l"))
		{
			isWhite = true;
		}
		else
		{
			isWhite = false;
		}

		switch (piece.substring(0, 1).toLowerCase())
		{
		case "q":
			this.boardSetup[initialFile][initialRank] = new Queen(isWhite);
			System.out.println("\t\t\t\t\t\t\tPlaced a " + ((isWhite == true) ? "Light Queen" : "Dark Queen") + " on "  + position.substring(2, 3).trim() + position.substring(3).trim());
			break;
		case "k":
			this.boardSetup[initialFile][initialRank] = new King(isWhite);
			System.out.println("\t\t\t\t\t\t\tPlaced a " + ((isWhite == true) ? "Light King" : "Dark King") + " on "  + position.substring(2, 3).trim() + position.substring(3).trim());
			break;
		case "r":
			this.boardSetup[initialFile][initialRank] = new Rook(isWhite);
			System.out.println("\t\t\t\t\t\t\tPlaced a " + ((isWhite == true) ? "Light Rook" : "Dark Rook") + " on "  + position.substring(2, 3).trim() + position.substring(3).trim());
			break;
		case "b":
			this.boardSetup[initialFile][initialRank] = new Bishop(isWhite);
			System.out.println("\t\t\t\t\t\t\tPlaced a " + ((isWhite == true) ? "Light Bishop" : "Dark Bishop") + " on "  + position.substring(2, 3).trim() + position.substring(3).trim());
			break;
		case "p":
			this.boardSetup[initialFile][initialRank] = new Pawn(isWhite);
			System.out.println("\t\t\t\t\t\t\tPlaced a " + ((isWhite == true) ? "Light Pawn" : "Dark Pawn") + " on "  + position.substring(2, 3).trim() + position.substring(3).trim());
			break;
		case "n":
			this.boardSetup[initialFile][initialRank] = new Knight(isWhite);
			System.out.println("\t\t\t\t\t\t\tPlaced a " + ((isWhite == true) ? "Light Knight" : "Dark Knight") + " on "  + position.substring(2, 3).trim() + position.substring(3).trim());
		default:

			break;
		}

	}

	public void loadFile(String filename)
	{

		FileInputStream fstream = null;
		try
		{
			fstream = new FileInputStream(new File(filename));
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		/*
		 * Move a single piece on the board (ex: d8 h4 – moves the piece at D8
		 * to the square at H4, c4 d6* - moves the piece at C4 to D6 and
		 * captures the piece at D6). Move two pieces in a single turn (ex: e1
		 * g1 h1 f1 – moves the king from E1 to G1 and moves the rook from H1 to
		 * F1. This is called a “king-side castle”).
		 */

		try
		{
			while ((strLine = br.readLine()) != null)
			{
				if (Pattern.matches(CHESS_PATTERN, strLine))
				{
					// e1 e4
					// c4 d6*
					// e1 g1 h1 f1
					if(strLine.length() == 4)
					{
						placePiece(strLine);
						Board.getInstance().draw();
					}
					else if (strLine.length() == 5)
					{
						movePiece(strLine);
						Board.getInstance().draw();
					}
					else if(strLine.length() == 6)
					{
						killPiece(strLine); // c4 d6*
						Board.getInstance().draw();
					}
					else if (strLine.length() == 10)
					{
						//moveTwoPieces();
					}

				}
				else
				{
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private void killPiece(String moveBoardPosition)
	{
		// c7 c5
		int initialRank = RANK.indexOf(moveBoardPosition.substring(0, 1).trim()); // c
		int initialFile = FILE.indexOf(moveBoardPosition.substring(1, 2).trim()); // 7

		int newRank = RANK.indexOf(moveBoardPosition.substring(3, 4).trim()); // c
		int newFile = FILE.indexOf(moveBoardPosition.substring(4, 5).trim()); // 5 maybe *

		//this.boardSetup[initnewFile][newRank] = null;
		this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
		System.out.println("\t\t\t\t\t\t\t" + moveBoardPosition.substring(0, 2).trim() + " captures piece at " + moveBoardPosition.substring(3, 5).trim());
		this.boardSetup[initialFile][initialRank] = null;
		
		
	}

	public Piece[][] getBoardSetup()
	{
		return boardSetup;
	}

	public void setBoardSetup(Piece[][] boardSetup)
	{
		this.boardSetup = boardSetup;
	}

}
