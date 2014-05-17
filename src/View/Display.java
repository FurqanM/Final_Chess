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
		String moveCommand;
		String newPosition;
		boolean gameEnd = false;

		if (args.length == 1)
		{
			try
			{
				Board.getInstance().loadFile(args[0]);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File not found. Resorting to User Play.");
				Board.getInstance().defaultSetup();
				
				while (!gameEnd)
				{
					Board.getInstance().draw();

					System.out.println("Choose the piece you'd like to move example: (B2 B3)");

					moveCommand = input.nextLine().toLowerCase();
					
					Board.getInstance().movePiece(moveCommand);
					// gameEnd = true;
				}
			}
		}
	}

}
