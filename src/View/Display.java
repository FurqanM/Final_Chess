package View;

import java.util.Scanner;

import Controller.Board;
import Model.Piece;
import Model.PieceType;

public class Display
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Board board = new Board();
		String boardPosition;
		String newPosition;
		boolean game = false;
		
		
		System.out.println("Which mode do you want to play?");
		System.out.println("\n1) Load game from file.\n2) Play new game.");
		String option = input.nextLine().trim();
		
		if(option.equals("1"))
		{
			
		}
		else if(option.equals("2"))
		{
			do
			{
				board.draw();

				System.out.println("Choose the piece you'd like to move example: (A7)");

				boardPosition = input.nextLine().toUpperCase();

				System.out.println("Choose where to go: (A7)");

				newPosition = input.nextLine().toUpperCase();

				board.movePiece(boardPosition, newPosition);

				board.draw();
				
			}  while(!game);
		}
	}
}
