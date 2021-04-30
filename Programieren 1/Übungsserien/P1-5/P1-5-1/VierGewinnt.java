/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         *
\* ************************************************************************* */

//LUKAS INGOLD 20-123-998

import java.util.Arrays;
import java.util.Scanner;


public class VierGewinnt
{

	public static final int COLS = 7;
	public static final int ROWS = 6;

	private Token[][] board = new Token[ COLS ][ ROWS ]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[ 2 ]; // two players

	/** initialize board and players and start the game */
	public void play()
	{
		// initialize the board
		for ( Token[] column : this.board ) {
			Arrays.fill( column, Token.empty );
		}

		/* initialize players */
		players[ 0 ] = new HumanPlayer();
		System.out.print( "Play against a human opponent? (y / n) " );
		String opponent = new Scanner( System.in ).nextLine().toLowerCase();
		while ( ( 1 != opponent.length() ) || ( -1 == ( "yn".indexOf ( opponent ) ) ) ) {
			System.out.print( "Can't understand your answer. Play against a human opponent? (y / n) " );
			opponent = new Scanner( System.in ).nextLine().toLowerCase();
		}
		if ( opponent.equals( "y" ) ) {
			players[ 1 ] = new HumanPlayer();
		} else {
			players[ 1 ] = new ComputerPlayer();
		}
		players[ 0 ].setToken( Token.player1 );
		players[ 1 ].setToken( Token.player2 );

		/* play... */
		boolean solved = false;
		int currentPlayer = ( new java.util.Random() ).nextInt( 2 );  //choose randomly who begins
		System.out.println( "current player: " + currentPlayer );
		int insertCol, insertRow; // starting from 0
		while ( !solved && !this.isBoardFull() ) {
			// get player's next "move"
			// note that we pass only a copy of the board as an argument,
			// otherwise the player would be able to manipulate the board and cheat!
			insertCol = players[ currentPlayer ].getNextColumn( getCopyOfBoard() );
			// insert the token and get the row where it landed
			insertRow = this.insertToken( insertCol, players[ currentPlayer ].getToken() );
			// check if the game is over
			solved = this.checkVierGewinnt( insertCol, insertRow );
			//switch to other player
			if ( !solved )
				currentPlayer = ( currentPlayer + 1 ) % 2;
		}
		System.out.println( displayBoard( this.board ) );
		if ( solved )
			System.out.println( "Player " + players[ currentPlayer ].getToken() + " wins!" );
		else
			System.out.println( "Draw! Game over." );
	}


	/**
	 * Inserts the token at the specified column (if possible)
	 * @param column the column to insert the token
	 * @param token the players token
	 * @return the row where the token landed
	 */
	private int insertToken( int column, Token tok )
	{
		if (column > COLS - 1 || column < 0 )
		{
			System.exit(1);
		}

				int row = 0;

				for(int rowCheck = 0; rowCheck < ROWS; rowCheck++)
				{
					row = rowCheck;
					if (board[column][rowCheck] == Token.empty)
					{
						board[column][row] = tok;
						break;
					}
				}
		return row;
	}


	/**
	 * Checks if every position is occupied
	 * @returns true, iff the board is full.
	 */
	private boolean isBoardFull()
	{
		int topRow = board[ 0 ].length-1;

		for(int i = 0; i < board.length-1; i++)
		{
			if(board[i][ topRow ] == Token.empty)
			{
				return false;
			}
		}
		return true;
	}


	/**
	 * Checks for at least four equal tokens in a row in
	 * either direction, starting from the given position.
	 */
	private boolean checkVierGewinnt( int col, int row )
	{
		int x1=1,x2=1,y1=1,y2=1,z1=1,z2=1,i1=1,i2=1;
		Token currentToken = board[col][row];
		int tokenInRow = 0;
		boolean noWin = false;

		//HORIZONTAL
		while(!noWin && tokenInRow<2 )
		{
			while (col + x1<COLS && board[col + x1][row] == currentToken)
			{
				x1++;
				tokenInRow++;
			}
			while (col-x2>=0&& board[col-x2][row]==currentToken)
			{
				x2++;
				tokenInRow++;
			}
			if(tokenInRow>2)
				{

				return true;
				}
			else
				{
					noWin=true;
				}
		}



		//VERTIKAL
		noWin = false;
		tokenInRow = 0;
		while(!noWin && tokenInRow<2 )
		{
			while (row + y1<ROWS && board[col][row + y1] == currentToken)
			{
				y1++;
				tokenInRow++;
			}
			while (row-y2 >=0 && board[col][row-y2] == currentToken)
			{
				y2++;
				tokenInRow++;
			}
			if(tokenInRow>2)
				{

				return true;
				}
			else
				{
					noWin=true;
				}
		}

	//Diagonal "\"
 	//gegen oben links
	noWin=false;
	tokenInRow = 0;
	while (!noWin && tokenInRow < 2)
	{//gegen oben links
		while ( col-z1>=0 && row +z1 <ROWS && board[col-z1][row+z1] == currentToken)
		{
			z1++;
			tokenInRow++;
		}//Gegen unten rechts
		while( col+z2<COLS && row-z2>=0 && board[col+z2][row-z2] == currentToken)
		{
			z2++;
			tokenInRow++;
		}
		if(tokenInRow>2)
			{
			return true;
			}
		else
			{
			noWin=true;
			}
	}


	//Diagonal "/"
 	//gegen oben rechts
	noWin=false;
	tokenInRow = 0;
	while (!noWin && tokenInRow < 2)
	{//gegen oben rechts
		while ( col+i1<COLS && row +i1 <ROWS && board[col+i1][row+i1] == currentToken)
		{
			i1++;
			tokenInRow++;
		}//Gegen unten links
		while( col-i2>=0 && row-i2>=0 && board[col-i2][row-i2] == currentToken)
		{
			i2++;
			tokenInRow++;
		}
		if(tokenInRow>2)
			{
			return true;
			}
		else
			{
			noWin=true;
			}
	}
 return false;
}


	/** Returns a (deep) copy of the board array */
	private Token[][] getCopyOfBoard()
	{
		Token[][] copiedBoard = new Token[ COLS ][ ROWS ];
		for ( int i = 0; i < copiedBoard.length; i++ ) {
			for ( int j = 0; j < copiedBoard[ i ].length; j++ ) {
				copiedBoard[ i ][ j ] = this.board[ i ][ j ];
			}
		}
		return copiedBoard;
	}


	/** returns a graphical representation of the board */
	public static String displayBoard( Token[][] myBoard )
	{
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for ( int col = 0; col < myBoard.length; col++ ) {
			rowDelimiter += "---+";
			rowNumbering += " " + ( col + 1 ) + "  ";
		}
		rowDelimiter += "\n";

		String rowStr;
		String presentation = rowDelimiter;
		for ( int row = myBoard[ 0 ].length - 1; row >= 0; row-- ) {
			rowStr = "| ";
			for ( int col = 0; col < myBoard.length; col++ ) {
				rowStr += myBoard[ col ][ row ].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}



	/** main method, starts the program */
	public static void main( String args[] )
	{
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}
