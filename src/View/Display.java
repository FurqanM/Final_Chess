package View;

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
		String boardPosition;
		String newPosition;
		boolean end = false;
		boolean gameEnd = false;



		int option = 0;

		while (!end)
		{
			input = new Scanner(System.in);
			System.out.println("Which mode do you want to play?");
			System.out.println("\n1) Load game from file.\n2) Play new game.");
			try
			{
				
				option = input.nextInt();
				end = true;
			}
			catch (Exception e)
			{
				System.out.println("Bad input, try again.\n");
			}

			if (option == 1)
			{
				Board.getInstance().loadFile(args[0]);
			}
			else if (option == 2)
			{
				while(!gameEnd)
				{
					Board.getInstance().draw();

					System.out.println("Choose the piece you'd like to move example: (A7)");

					boardPosition = input.nextLine().toLowerCase();

					System.out.println("Choose where to go: (A7)");

					newPosition = input.nextLine().toLowerCase();

					//Board.getInstance().movePiece(boardPosition, newPosition);

					Board.getInstance().draw();
					//game = true;
				}
				
			}

		}

	}
}
