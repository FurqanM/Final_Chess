package View;

import java.util.Scanner;

import Controller.Board;
import Model.Piece;

public class Display
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String boardPosition;
		String newPosition;
		boolean game = false;
		
		
		System.out.println("Which mode do you want to play?");
		System.out.println("\n1) Load game from file.\n2) Play new game.");
		String option = input.nextLine().trim();
		
		if(option.equals("1"))
		{
			Board.getInstance().loadFile();
			Board.getInstance().draw();
		}
		else if(option.equals("2"))
		{
			do
			{
				Board.getInstance().draw();

				System.out.println("Choose the piece you'd like to move example: (A7)");

				boardPosition = input.nextLine().toLowerCase();

				System.out.println("Choose where to go: (A7)");

				newPosition = input.nextLine().toLowerCase();

				Board.getInstance().movePiece(boardPosition, newPosition);

				Board.getInstance().draw();
				
			}  while(!game);
		}
	}
}
