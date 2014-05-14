package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import Model.Piece;
import Model.PieceType;
import Model.Rook;

public class Board
{
	private File file = new File("Chess.txt");
	private static final String CHESS_PATTERN = "([KQNBR][ld][a-h][1-8])|([a-h][1-8]\\s[a-h][1-8]\\*?)";
	private final String FILE = "012345678";
	private final String RANK = "ABCDEFGH";
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
		boardSetup[0][0] = new Piece(PieceType.WROOK);
		boardSetup[0][1] = new Piece(PieceType.WKNIGHT);
		boardSetup[0][2] = new Piece(PieceType.WBISHOP);
		boardSetup[0][3] = new Piece(PieceType.WQUEEN);
		boardSetup[0][4] = new Piece(PieceType.WKING);
		boardSetup[0][5] = new Piece(PieceType.WBISHOP);
		boardSetup[0][6] = new Piece(PieceType.WKNIGHT);
		boardSetup[0][7] = new Piece(PieceType.WROOK);
		boardSetup[1][0] = new Piece(PieceType.WPAWN);
		boardSetup[1][1] = new Piece(PieceType.WPAWN);
		boardSetup[1][2] = new Piece(PieceType.WPAWN);
		boardSetup[1][3] = new Piece(PieceType.WPAWN);
		boardSetup[1][4] = new Piece(PieceType.WPAWN);
		boardSetup[1][5] = new Piece(PieceType.WPAWN);
		boardSetup[1][6] = new Piece(PieceType.WPAWN);
		boardSetup[1][7] = new Piece(PieceType.WPAWN);

		for (int y = 2; y <= 5; y++)
			for (int x = 0; x < BOARD_RANK; x++)
				boardSetup[y][x] = new Piece(PieceType.NOPIECE);

		boardSetup[7][0] = new Piece(PieceType.BROOK);
		boardSetup[7][1] = new Piece(PieceType.BKNIGHT);
		boardSetup[7][2] = new Piece(PieceType.BBISHOP);
		boardSetup[7][3] = new Piece(PieceType.BQUEEN);
		boardSetup[7][4] = new Piece(PieceType.BKING);
		boardSetup[7][5] = new Piece(PieceType.BBISHOP);
		boardSetup[7][6] = new Piece(PieceType.BKNIGHT);
		boardSetup[7][7] = new Piece(PieceType.BROOK);
		boardSetup[6][0] = new Piece(PieceType.BPAWN);
		boardSetup[6][1] = new Piece(PieceType.BPAWN);
		boardSetup[6][2] = new Piece(PieceType.BPAWN);
		boardSetup[6][3] = new Piece(PieceType.BPAWN);
		boardSetup[6][4] = new Piece(PieceType.BPAWN);
		boardSetup[6][5] = new Piece(PieceType.BPAWN);
		boardSetup[6][6] = new Piece(PieceType.BPAWN);
		boardSetup[6][7] = new Piece(PieceType.BPAWN);

		for (int y = 0; y < BOARD_FILE; y++)
			for (int x = 0; x < BOARD_RANK; x++)
			{
				if (boardSetup[y][x].getType() == PieceType.WPAWN)
					boardSetup[y][x].setPicture("PL");
				else if (boardSetup[y][x].getType() == PieceType.WROOK)
					boardSetup[y][x].setPicture("RL");
				else if (boardSetup[y][x].getType() == PieceType.WBISHOP)
					boardSetup[y][x].setPicture("BL");
				else if (boardSetup[y][x].getType() == PieceType.WKNIGHT)
					boardSetup[y][x].setPicture("KL");
				else if (boardSetup[y][x].getType() == PieceType.WQUEEN)
					boardSetup[y][x].setPicture("QL");
				else if (boardSetup[y][x].getType() == PieceType.WKING)
					boardSetup[y][x].setPicture("KL");
				else if (boardSetup[y][x].getType() == PieceType.BPAWN)
					boardSetup[y][x].setPicture("PD");
				else if (boardSetup[y][x].getType() == PieceType.BROOK)
					boardSetup[y][x].setPicture("RD");
				else if (boardSetup[y][x].getType() == PieceType.BBISHOP)
					boardSetup[y][x].setPicture("BD");
				else if (boardSetup[y][x].getType() == PieceType.BKNIGHT)
					boardSetup[y][x].setPicture("KD");
				else if (boardSetup[y][x].getType() == PieceType.BQUEEN)
					boardSetup[y][x].setPicture("QD");
				else if (boardSetup[y][x].getType() == PieceType.BKING)
					boardSetup[y][x].setPicture("KD");
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
		int initialRank = RANK.indexOf(boardPosition.substring(0, 1).trim());
		int initialFile = FILE.indexOf(boardPosition.substring(1).trim());
		
		int newRank = RANK.indexOf(newPosition.substring(0, 1).trim());
		int newFile = FILE.indexOf(newPosition.substring(1).trim());
		
		
		this.boardSetup[newFile][newRank].setPiece(this.boardSetup[initialFile][initialRank]);
		this.boardSetup[initialFile][initialRank].setType(PieceType.NOPIECE);
				
	}
	
	public void placePiece(String position)
	{
		//Qld8
		
		String piece = position.substring(0, 2).trim().toLowerCase(); //Ql
		int boardPosition = FILE.indexOf(position.substring(2).trim()); //d8
		
		switch(piece)
		{
		case "ql":
			//piece = PieceType.WQUEEN;
			break;
		}
		
		int initialRank = RANK.indexOf(position.substring(2, 3).trim()); //d
		int initialFile = FILE.indexOf(position.substring(3).trim()); //8
		
		
		
	//	this.boardSetup[initialFile][initialRank].setType(boardPiece);
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
