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

	// regex pattern for reading chess input, international as well and other notations
	// ([BKNPQR][ld][a-h][1-8])|((([a-h][1-8][ \*]?){2})+)
	// ([BKNPQR][ld][a-h][1-8])|((?:(?:[a-h][1-8][ ]?){2})+)
	// ([BKNPQR]?)([a-hl]?)([0-9]?)([x=]?)([BKNPQR\*]|[a-h][1-8])([+#]?)
	// ([KQNBR]?([a-h]?[1-8]?x)?[a-h]([2-7]|[18](=[KQNBR])?)|0-0(-0)?)(\(ep\)|\+{1,2})?
	
	//^(([a-h][1-8])\\s*([a-h][1-8])(\\*?))|([qkbprn][dl][a-h][1-8])$
	//^([qkbprn][dl][a-h][1-8])|((([a-h][1-8])\s*([a-h][1-8])(\*?))|([a-h][1-8])\s*([a-h][1-8])(\*?)\s*([a-h][1-8])\s*([a-h][1-8]))$ complete Castling OR

	private static final String CHESS_PATTERN = "^([qkbprn][dl][a-h][1-8])|((([a-h][1-8])\\s*([a-h][1-8])(\\*?))|([a-h][1-8])\\s*([a-h][1-8])(\\*?)\\s*([a-h][1-8])\\s*([a-h][1-8]))$";
	private final long updateTimer = 1000;
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
		for (int y = 7; y > 0; y--)
		{

			System.out.print(y + " |");

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
		System.out.println("================================================");
		try
		{
			Thread.sleep(updateTimer);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void movePiece(String moveBoardPosition)
	{

		// c7 c5
		int initialRank = moveBoardPosition.substring(0, 1).trim().charAt(0) - 97; //c
		
		int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - 49; // 7

		int newRank = moveBoardPosition.substring(3, 4).trim().charAt(0) - 97; // c
		
		int newFile = moveBoardPosition.substring(4).trim().charAt(0) - 49; //5 maybe *

		this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
		this.boardSetup[initialFile][initialRank] = null;

		System.out.println("\t\t\t\t\t\t\t" + " Moves piece from " + moveBoardPosition.substring(0, 2).trim() + " to " + moveBoardPosition.substring(3, 5).trim());

	}

	public void placePiece(String position)
	{
		// ple4

		String piece = position.substring(0, 2).trim().toLowerCase(); // pl

		int initialRank = position.substring(2, 3).trim().charAt(0) - 97; // e
		int initialFile = position.substring(3).trim().charAt(0) - 49; // 4

		boolean isWhite = (piece.substring(1, 2).toLowerCase().equals("l"));

		Piece newPiece = null;
		switch (piece.substring(0, 1).toLowerCase())
		{
		case "q":
			newPiece = new Queen(isWhite);

			break;
		case "k":
			newPiece = new King(isWhite);

			break;
		case "r":
			newPiece = new Rook(isWhite);

			break;
		case "b":
			newPiece = new Bishop(isWhite);

			break;
		case "p":
			newPiece = new Pawn(isWhite);

			break;
		case "n":
			newPiece = new Knight(isWhite);

		default:

			break;
		}
		this.boardSetup[initialFile][initialRank] = newPiece;
		System.out.println("\t\t\t\t\t\t\tPlaced a " + newPiece + " on " + position.substring(2, 3).trim() + position.substring(3).trim());
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
		 * Move a single piece on the board (ex: d8 h4 � moves the piece at D8
		 * to the square at H4, c4 d6* - moves the piece at C4 to D6 and
		 * captures the piece at D6). Move two pieces in a single turn (ex: e1
		 * g1 h1 f1 � moves the king from E1 to G1 and moves the rook from H1
		 * to F1. This is called a �king-side castle�).
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
					if (strLine.length() == 4)
					{
						placePiece(strLine);
					}
					else if (strLine.length() == 5)
					{
						movePiece(strLine);
						Board.getInstance().draw();
					}
					else if (strLine.length() == 6)
					{
						killPiece(strLine); // c4 d6*
						Board.getInstance().draw();
					}
					else if (strLine.length() == 10)
					{
						// moveTwoPieces();
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
		// int initialRank = RANK.indexOf(moveBoardPosition.substring(0,
		// 1).trim()); // c
		int initialRank = moveBoardPosition.substring(0, 1).trim().charAt(0) - 97;
		// int initialFile = FILE.indexOf(moveBoardPosition.substring(1,
		// 2).trim()); // 7
		int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - 49;

		// int newRank = RANK.indexOf(moveBoardPosition.substring(3, 4).trim());
		// // c
		int newRank = moveBoardPosition.substring(3, 4).trim().charAt(0) - 97;
		// int newFile = FILE.indexOf(moveBoardPosition.substring(4, 5).trim());
		// // 5 maybe *
		int newFile = moveBoardPosition.substring(4, 5).trim().charAt(0) - 49;

		// this.boardSetup[initnewFile][newRank] = null;
		this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
		System.out.println("\t\t\t\t\t\t\t" + moveBoardPosition.substring(0, 2).trim() + " captures piece at " + moveBoardPosition.substring(3, 5).trim());
		this.boardSetup[initialFile][initialRank] = null;

	}

}
