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
import Model.Pawn;
import Model.Piece;
import Model.Queen;
import Model.Rook;

public class Board
{
	//this.color = (this.file + this.rank) % 2 == 0 ? ColorDefinition.DARK : ColorDefinition.LIGHT;
	private static final File file = new File("src\\moves.txt");
	private static final String CHESS_PATTERN = "(([bknpqr]?)([a-hl]?)([0-9]?)([x=]?)([bknpqr\\*]|[a-h][1-8])([+#]?))";
	private final String FILE = "12345678";
	private final String RANK = "abcdefgh";
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
		
		for (int y = 0; y < BOARD_FILE; y++)
			for (int x = 0; x < BOARD_RANK; x++)
				boardSetup[y][x] = null;
				
	}
	


	public void draw()
	{
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for (int y = 0; y < BOARD_RANK; y++)
		{
			System.out.print(y + " |");
			for (int x = 0; x < BOARD_RANK; x++)
			{
				if(boardSetup[y][x] == null)
				{
					System.out.printf("%3s|", " ");
				}
				else
				{
					System.out.printf("%3s|", boardSetup[y][x].toString());
				}
				
			}
			System.out.println();
			System.out.println("  +---+---+---+---+---+---+---+---+");
		}
		System.out.println("  + A + B + C + D + E + F + G + H +");
	}

	public void movePiece(String boardPosition, String newPosition)
	{		
		int initialRank = RANK.indexOf(boardPosition.substring(0, 1).trim());
		int initialFile = FILE.indexOf(boardPosition.substring(1).trim());
		
		int newRank = RANK.indexOf(newPosition.substring(0, 1).trim());
		int newFile = FILE.indexOf(newPosition.substring(1).trim());
		
		
		//this.boardSetup[newFile][newRank].setPiece(this.boardSetup[initialFile][initialRank]);
		//this.boardSetup[initialFile][initialRank].setType(PieceType.NOPIECE);
				
	}
	
	public void placePiece(String position)
	{
		//pda7
		
		String piece = position.substring(0, 2).trim().toLowerCase(); //pd
		
		
		
		int initialRank = RANK.indexOf(position.substring(2, 3).trim()); //a
		int initialFile = FILE.indexOf(position.substring(3).trim()); //7
		
		boolean isWhite;
		if(piece.substring(1,2).toLowerCase().equals("l"))
		{
			isWhite = true;
		}
		else
		{
			isWhite = false;
		}
		
		
		switch(piece.substring(0,1).toLowerCase())
		{
		case "q":
			this.boardSetup[initialFile][initialRank] = new Queen(isWhite);
			break;
		case "k":
			this.boardSetup[initialFile][initialRank] = new King(isWhite);
			break;
		case "r":
			this.boardSetup[initialFile][initialRank] = new Rook(isWhite);
			break;
		case "b":
			this.boardSetup[initialFile][initialRank] = new Bishop(isWhite);
			break;
		case "p":
			this.boardSetup[initialFile][initialRank] = new Pawn(isWhite);
			break;
		default:
			
			break;
		}
	}

	// regex pattern for reading chess input
	// ([BKNPQR][ld][a-h][1-8])|((([a-h][1-8][ \*]?){2})+)
	// ([BKNPQR][ld][a-h][1-8])|((?:(?:[a-h][1-8][ ]?){2})+) THIS WORKS
	// ([BKNPQR]?)([a-hl]?)([0-9]?)([x=]?)([BKNPQR\*]|[a-h][1-8])([+#]?)
	// ([KQNBR]?([a-h]?[1-8]?x)?[a-h]([2-7]|[18](=[KQNBR])?)|0-0(-0)?)(\(ep\)|\+{1,2})?

	public void loadFile()
	{

		FileInputStream fstream = null;
		try
		{
			fstream = new FileInputStream(file);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		try
		{
			while ((strLine = br.readLine()) != null)
			{
				if(Pattern.matches(CHESS_PATTERN, strLine))
				{
					placePiece(strLine);		
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
	
	
	
	

}
