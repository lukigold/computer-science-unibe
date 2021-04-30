/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         *
\* ************************************************************************* */
//Lukas Ingold 20-123-998

/** A very stupid computer player */
public class ComputerPlayer implements IPlayer
{
	private Token token;

	//public int COLS = 7;
//	public int ROWS = 6;
//	public Token[][] board;
	/**
	 * Strategy is to chose a random column and select
	 * the next valid column to the right (the chosen included)
	 */

	public int getNextColumn(Token[][] board )
	{
		int col = 0;
		java.util.Random generator = new java.util.Random();

		if(!isColFull(OptimalPosition(board),board))
			{
				col = OptimalPosition(board);
				return col;
			}
		else
			{
				col = generator.nextInt( board.length );
				while ( isColFull( col, board ) )
					{
						col = ( col + 1 ) % board.length;
					}
			}
		return col;
	}

	private int OptimalPosition(Token[][] board )
	{
		int opt = 0;
		opt = board.length/2;
		return opt;
	}
/* Methoden Funktionieren nochnicht
	private int threeinaRow(int col,int row)
	{
		int pos = 0;
		boolean not = false;
		Token now = board[col][row];
		int a = 1;
		int tokenInRow = 1;

		while(!not && tokenInRow<2 )
		{
			while (col + a<COLS && this.board[col + a][row] == now)
			{
				a++;
				tokenInRow++;
			}
			if(tokenInRow==3 && this.board[col+a+1][row] == Token.empty)
				{
					return col+3;
				}
			else if (this.board[col-1][row] == Token.empty)
				{
					return col-1;
				}
			else
				{
					not = true;
				}
		}
		return pos;
	}

	public int whereisplayerat()
	{
		boolean emp = true;
		int r = 0;

		for(int c = 0;c < board.length-1; c++)
			{
				while(this.board[c][r] != Token.empty && r<this.board[ 0 ].length-1)
					{
						r++;
					}
				if(this.board[c][r] == Token.player1)
					{
						return c;
					}

			}
			return r;
	}



/*
	 * @return true if the column col is already full and false otherwise.
	 */
	private boolean isColFull( int col, Token[][] board )
	{
		int topRow = board[ col ].length - 1;
		return ( board[ col ][ topRow ] != Token.empty );
	}


	public void setToken( Token token )
	{
		this.token = token;
	}

	public Token getToken()
	{
		return this.token;
	}

	public String getPlayersName()
	{
		return "Random Player";
	}
}
