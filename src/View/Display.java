package View;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import Controller.Board;
import Model.Piece;

public class Display
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String moveCommand = null;
		String newPosition;
		boolean gameEnd = false;

		if (args.length == 1)
		{
			try
			{
				Board.getInstance().setUpdateTimer(1000);
				Board.getInstance().loadFile(args[0]);
			}
			catch (FileNotFoundException e)
			{
				Board.getInstance().setUpdateTimer(0);
				System.out.println("File not found. Resorting to User Play.");
				Board.getInstance().defaultSetup();
				
				Board.getInstance().draw();
				
				while (!gameEnd)
				{

					System.out.println("Choose the piece you'd like to move example: (B2 B3)");
					System.out.println(" or (B2 C3*) to capture piece.");
					System.out.println(" or (E1 G1 H1 F1 ) to castle");

					moveCommand = input.nextLine().toLowerCase();
					if(moveCommand.length() == 4)
						System.out.println("Cannot place pieces in the middle of the game. Ignoring command\n");
					
					playGame(moveCommand);
					// gameEnd = true;
				}
			}
		}
	}
	
	
	public static void playGame(String moveCommand)
	{
		if (Pattern.matches(Board.CHESS_PATTERN, moveCommand))
		{
			// e1 e4
			// c4 d6*
			// e1 g1 h1 f1
			if (moveCommand.length() == 4)
			{
				Board.getInstance().placePiece(moveCommand);
			}
			else if (moveCommand.length() == 5)
			{
				Board.getInstance().movePiece(moveCommand);
				Board.getInstance().draw();
			}
			else if (moveCommand.length() == 6)
			{
				Board.getInstance().killPiece(moveCommand); // c4 d6*
				Board.getInstance().draw();
			}
			else if (moveCommand.length() == 10)
			{
				// moveTwoPieces();
			}

		}
		else
		{
		}
	}

}
