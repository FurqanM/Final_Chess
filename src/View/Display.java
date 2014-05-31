package View;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import Controller.Board;
import Model.Piece;
import Model.Position;

public class Display
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String moveCommand = null;
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
					System.out.println("It is " + ((Board.getInstance().isWhiteTurn())
							? "Light's "
							: "Dark's ") + "turn");

					moveCommand = input.nextLine().toLowerCase();
					
					Position position = new Position(moveCommand);
					
					playGame(position);
					// gameEnd = true;
				}
			}
		}
	}

	public static void playGame(Position moveCommand)
	{
		// e1 e4
		// c4 d6*
		// e1 g1 h1 f1

		Board.getInstance().movePiece(moveCommand);

	}

}
