package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Controller.TeamPlayer;
import Model.Bishop;
import Model.King;
import Model.Knight;
import Model.Pawn;
import Model.Piece;
import Model.Position;
import Model.Queen;
import Model.Rook;

public class Board
{
	// this.color = (this.file + this.rank) % 2 == 0 ? ColorDefinition.DARK :
	// ColorDefinition.LIGHT;

	// regex pattern for reading chess input, international as well and other
	// notations
	// ([BKNPQR][ld][a-h][1-8])|((([a-h][1-8][ \*]?){2})+)
	// ([BKNPQR][ld][a-h][1-8])|((?:(?:[a-h][1-8][ ]?){2})+)
	// ([BKNPQR]?)([a-hl]?)([0-9]?)([x=]?)([BKNPQR\*]|[a-h][1-8])([+#]?)
	// ([KQNBR]?([a-h]?[1-8]?x)?[a-h]([2-7]|[18](=[KQNBR])?)|0-0(-0)?)(\(ep\)|\+{1,2})?

	// ^(([a-h][1-8])\\s*([a-h][1-8])(\\*?))|([qkbprn][dl][a-h][1-8])$
	// ^([qkbprn][dl][a-h][1-8])|((([a-h][1-8])\s*([a-h][1-8])(\*?))|([a-h][1-8])\s*([a-h][1-8])(\*?)\s*([a-h][1-8])\s*([a-h][1-8]))$
	// complete Castling OR

	public final String CHESS_PATTERN = "^([qkbprn][dl][a-h][1-8])|((([a-h][1-8])\\s*([a-h][1-8])(\\*?))|([a-h][1-8])\\s*([a-h][1-8])(\\*?)\\s*([a-h][1-8])\\s*([a-h][1-8]))$";

	public String getCHESS_PATTERN()
	{
		return CHESS_PATTERN;
	}

	public int getBOARD_FILE()
	{
		return BOARD_FILE;
	}

	public int getBOARD_RANK()
	{
		return BOARD_RANK;
	}

	private long updateTimer;
	private Piece[][] boardSetup;
	private final int BOARD_FILE = 8;
	private final int BOARD_RANK = 8;
	private boolean isWhiteTurn = true; //who's turn is it
	private int LightKingFile, lightKingRank, darkKingFile, darkKingRank;
	
	TeamPlayer tp = new TeamPlayer();

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
		System.out.println("    A   B   C   D   E   F   G   H ");
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for (int y = 7; y >= 0; y--)
		{

			System.out.print(y + 1 + " |");

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
			Thread.sleep(getUpdateTimer());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void toggleGameState()
	{
		this.setWhiteTurn(!this.isWhiteTurn());
	}

	public void placePiece(Position position)
	{
		// ple4
		boolean isWhite = (position.getOriginalString().substring(1, 2).toLowerCase().equals("l"));

		Piece newPiece = null;
		switch (position.getOriginalString().substring(0, 1).toLowerCase())
		{
		case "q":
			newPiece = new Queen(isWhite);
			break;
		case "k":
			newPiece = new King(isWhite); //position.getOriginalString().substring(3, 4).trim().toLowerCase()); //this is setting the position of the DarkKing to track
			if (newPiece.isWhite())
			{
				this.setLightKingFile(position.getInitialFile());
				this.setLightKingRank(position.getInitialRank());
			}
			else
			{
				this.setDarkKingFile(position.getInitialFile());
				this.setDarkKingRank(position.getInitialRank());
			}
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
		this.boardSetup[position.getInitialFile()][position.getInitialRank()] = newPiece;
		System.out.println("\t\t\t\t\t\t\tPlaced a " + newPiece + " on " + position.getOriginalString().substring(2, 3).trim() + position.getOriginalString().substring(3).trim());
//		this.draw();
	}

	public void loadFile(String filename) throws FileNotFoundException
	{

		FileInputStream fstream = null;
		try
		{
			fstream = new FileInputStream(new File(filename));
		}
		catch (FileNotFoundException e1)
		{
			throw new FileNotFoundException("File not found");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		/*
		 * Move a single piece on the board (ex: d8 h4 - moves the piece at D8 to the square at H4, c4 d6* - moves the piece at C4 to D6 and captures the piece at D6). Move two pieces in a single turn
		 * (ex: e1 g1 h1 f1 � moves the king from E1 to G1 and moves the rook from H1 to F1. This is called a - king-side castle).
		 */
		try
		{
			while ((strLine = br.readLine()) != null)
			{
				Position position = new Position(strLine);
					// e1 e4
					// c4 d6*
					// e1 g1 h1 f1
				
					placePiece(position);
					this.draw();
				
					// moveTwoPieces();
					//returns the CastlingType
					//Throws IllegalMoveException if castling move is not valid.

					//castling rules: TODO
					//The king and the chosen rook are on the player's first rank.
					//Neither the king nor the chosen rook have previously moved.
					//There are no pieces between the king and the chosen rook.
					//The king is not currently in check.
					//The king does not pass through a square that is attacked by
					//an enemy piece.
					//The king does not end up in check. (True of any legal move.)
					

				
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		//this.draw();
	}

	public void movePiece(Position position)
	{
			// c7 c5


			if (this.boardSetup[position.getNewFile()][position.getNewRank()] == null) //if there is no piece at this location
			{
				//				try
				//				{
				if (this.boardSetup[position.getInitialFile()][position.getInitialRank()].validateMovement(position))
				{
					//if the piece at initialFile and initialRank is white and if it's white's turn then allow the player to move
					//or if the piece at that position is black and if it's black's turn then allow the player to move
					if ((this.boardSetup[position.getInitialFile()][position.getInitialRank()].isWhite() && this.isWhiteTurn()) || (!this.boardSetup[position.getInitialFile()][position.getInitialRank()].isWhite() && !this.isWhiteTurn()))
					{

//						for (int i = 0; i < BOARD_FILE; i++)
//						{
//							for (int j = 0; j < BOARD_RANK; j++)
//							{
//								String iString = Character.toString((char) (i + 'a'));
//								String jString = Character.toString((char) (j + '1'));
//								
//								//concatenated King position
//								String kingString = (this.isWhiteTurn())
//										? iString.trim() + jString.trim() + " " + this.getDarkKingPosition() //newFile and newRank
//										: iString.trim() + jString.trim() + " " + this.getLightKingPosition(); //newFile and newRank
//										
//								if (this.getPieceAt(i, j) != null)
//								{
//									if (this.getPieceAt(i, j).validateMovement(kingString))
//									{
//										System.out.println(this.getPieceAt(i, j) + " can put");
//										System.out.println(((this.isWhiteTurn())
//												? "Dark King"
//												: "Light King") + " in check");
//									}
//									else
//									{
//									}
//								}
//								else
//								{
//								}
//
//							}
//						}

						if((this.boardSetup[position.getInitialFile()][position.getInitialRank()].isWhite()) && this.boardSetup[position.getInitialFile()][position.getInitialRank()].toString().equalsIgnoreCase("k"))
						{
							this.setLightKingFile(position.getNewFile());
							this.setLightKingRank(position.getNewRank());
						}
						else if(!(this.boardSetup[position.getInitialFile()][position.getInitialRank()].isWhite()) && this.boardSetup[position.getInitialFile()][position.getInitialRank()].toString().equalsIgnoreCase("k"))
						{
							this.setDarkKingFile(position.getNewFile());
							this.setDarkKingRank(position.getNewRank());
						}
						
						System.out.println(this.getLightKingFile());
						System.out.println(this.getLightKingRank());
						
						System.out.println(this.getDarkKingFile());
						System.out.println(this.getDarkKingRank());
						
						toggleGameState();
						this.boardSetup[position.getNewFile()][position.getNewRank()] = (this.boardSetup[position.getInitialFile()][position.getInitialRank()]);
						this.boardSetup[position.getInitialFile()][position.getInitialRank()] = null;
						System.out.println("\t\t\t\t\t\t\t" + " Moves piece from " + position.getOriginalString().substring(0, 2).trim() + " to " + position.getOriginalString().substring(3, 5).trim());
						this.draw();

					}
					else
					{
						System.out.println("Nice try! It's not your turn!");
					}

				}
			
			//				catch (NullPointerException e)
			//				{
			//					System.out.println("No piece there to move.");
			//				}
		}
			else if (this.boardSetup[position.getNewFile()][position.getNewRank()] != null) //there is a piece here
				{
				try
				{
					if (this.boardSetup[position.getInitialFile()][position.getInitialRank()].validateMovement(position))
					{
						//if the piece at initialFile and initialRank is white and if it's white's turn then allow the player to move
						//or if the piece at that position is black and if it's black's turn then allow the player to move
						if ((this.boardSetup[position.getInitialFile()][position.getInitialRank()].isWhite() && this.isWhiteTurn()) || (!this.boardSetup[position.getInitialFile()][position.getInitialRank()].isWhite() && !this.isWhiteTurn()))
						{
//							if (checkForCheck(initialFile, initialRank, newFile, newRank, kingString))
//							{
//								System.out.println(((this.isWhiteTurn())
//										? "Dark King"
//										: "Light King") + " is in check");
//							}
							
							toggleGameState();
							this.boardSetup[position.getNewFile()][position.getNewRank()] = (this.boardSetup[position.getInitialFile()][position.getInitialRank()]);
							this.boardSetup[position.getInitialFile()][position.getInitialRank()] = null;
							System.out.println("\t\t\t\t\t\t\t" + position.getOriginalString().substring(0, 2).trim() + " captures piece at " + position.getOriginalString().substring(3, 5).trim());
						}
						else
						{
							System.out.println("Nice try! It's not your turn!");
						}
	
					}
				}
				catch(NullPointerException e)
				{
					System.out.println("No piece there");
			
				}
					
	
					//				if (this.boardSetup[initialFile][initialRank].validateMovement(moveBoardPosition))
					//				{
					//					//if the piece at initialFile and initialRank is white and if it's white's turn then allow the player to move
					//					//or if the piece at that position is black and if it's black's turn then allow the player to move
					//					if ((this.boardSetup[initialFile][initialRank].isWhite() && this.isWhiteTurn()) || (!this.boardSetup[initialFile][initialRank].isWhite() && !this.isWhiteTurn()))
					//					{
					//						toggleGameState();
					//						this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
					//						System.out.println("\t\t\t\t\t\t\t" + moveBoardPosition.substring(0, 2).trim() + " captures piece at " + moveBoardPosition.substring(3, 5).trim());
					//						this.boardSetup[initialFile][initialRank] = null;
					//					}
					//				}
				}
	}

//	public void killPiece(String moveBoardPosition)
//	{
//		if (moveBoardPosition.length() == 6)
//		{
//
//			// c7 c5*
//			int initialRank = moveBoardPosition.substring(0, 1).trim().charAt(0) - 'a';
//
//			int initialFile = moveBoardPosition.substring(1, 2).trim().charAt(0) - '1';
//
//			// c
//			int newRank = moveBoardPosition.substring(3, 4).trim().charAt(0) - 'a';
//
//			// 5*
//			int newFile = moveBoardPosition.substring(4, 5).trim().charAt(0) - '1';
//
//			//concatenated King position
//			String kingString = (this.isWhiteTurn())
//					? moveBoardPosition.substring(3, 5).trim().toLowerCase() + " " + this.getDarkKingPosition() //newFile and newRank
//					: moveBoardPosition.substring(3, 5).trim().toLowerCase() + " " + this.getLightKingPosition(); //newFile and newRank
//
//			if (this.boardSetup[newFile][newRank] != null) //there is a piece here
//			{
//				if (this.boardSetup[initialFile][initialRank].validateMovement(moveBoardPosition))
//				{
//					//if the piece at initialFile and initialRank is white and if it's white's turn then allow the player to move
//					//or if the piece at that position is black and if it's black's turn then allow the player to move
//					if ((this.boardSetup[initialFile][initialRank].isWhite() && this.isWhiteTurn()) || (!this.boardSetup[initialFile][initialRank].isWhite() && !this.isWhiteTurn()))
//					{
//						if (checkForCheck(initialFile, initialRank, newFile, newRank, kingString))
//						{
//							System.out.println(((this.isWhiteTurn())
//									? "Dark King"
//									: "Light King") + " is in check");
//						}
//						toggleGameState();
//						this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
//						this.boardSetup[initialFile][initialRank] = null;
//						System.out.println("\t\t\t\t\t\t\t" + moveBoardPosition.substring(0, 2).trim() + " captures piece at " + moveBoardPosition.substring(3, 5).trim());
//					}
//					else
//					{
//						System.out.println("Nice try! It's not your turn!");
//					}
//
//				}
//
//				//				if (this.boardSetup[initialFile][initialRank].validateMovement(moveBoardPosition))
//				//				{
//				//					//if the piece at initialFile and initialRank is white and if it's white's turn then allow the player to move
//				//					//or if the piece at that position is black and if it's black's turn then allow the player to move
//				//					if ((this.boardSetup[initialFile][initialRank].isWhite() && this.isWhiteTurn()) || (!this.boardSetup[initialFile][initialRank].isWhite() && !this.isWhiteTurn()))
//				//					{
//				//						toggleGameState();
//				//						this.boardSetup[newFile][newRank] = (this.boardSetup[initialFile][initialRank]);
//				//						System.out.println("\t\t\t\t\t\t\t" + moveBoardPosition.substring(0, 2).trim() + " captures piece at " + moveBoardPosition.substring(3, 5).trim());
//				//						this.boardSetup[initialFile][initialRank] = null;
//				//					}
//				//				}
//			}
//
//		}
//
//	}

	public boolean isWhiteTurn()
	{
		return isWhiteTurn;
	}

	public void setWhiteTurn(boolean isWhiteTurn)
	{
		this.isWhiteTurn = isWhiteTurn;
	}

	// TODO add a create new .txt file and writes all
	// of the default piece arrangements to it and
	// load that txt and append to it after every
	// move
	public void defaultSetup()
	{
		Position position;
		String[] defaultPieceArrangement =
		{ "pda7", "pdb7", "pdc7", "pdd7", "pde7", "pdf7", "pdg7", "pdh7", "rda8", "rdh8", "ndb8", "ndg8",
				// "bdc8"
		"bdf8", //"qdd8",
		// "kde8"
		"pla2", "plb2", "plc2", "pld2", "ple2", "plf2", "plg2", "plh2", "rla1", "rlh1", "nlb1", "nlg1", "blc1", "blf1",
				// "qld1"
				// "kle1"
		"rdh6", "kle4", "kde6", "rla5", };
		

		for (int i = 0; i < defaultPieceArrangement.length; i++)
		{
			placePiece(new Position(defaultPieceArrangement[i]));
		}
	}

	public Piece getPieceAt(int file, int rank)
	{
		return this.boardSetup[file][rank];
	}

//	private boolean checkForCheck(int initialFile, int initialRank, int newFile, int newRank, String kingPosition)
//	{
//		if (this.boardSetup[initialFile][initialRank].validateMovement(kingPosition))
//			return true;
//
//		return false;
//
//	}

	public int getLightKingFile()
	{
		return LightKingFile;
	}

	public void setLightKingFile(int lightKingFile)
	{
		LightKingFile = lightKingFile;
	}

	public int getLightKingRank()
	{
		return lightKingRank;
	}

	public void setLightKingRank(int lightKingRank)
	{
		this.lightKingRank = lightKingRank;
	}

	public int getDarkKingFile()
	{
		return darkKingFile;
	}

	public void setDarkKingFile(int darkKingFile)
	{
		this.darkKingFile = darkKingFile;
	}

	public int getDarkKingRank()
	{
		return darkKingRank;
	}

	public void setDarkKingRank(int darkKingRank)
	{
		this.darkKingRank = darkKingRank;
	}

	public long getUpdateTimer()
	{
		return updateTimer;
	}

	public void setUpdateTimer(long updateTimer)
	{
		this.updateTimer = updateTimer;
	}

}
