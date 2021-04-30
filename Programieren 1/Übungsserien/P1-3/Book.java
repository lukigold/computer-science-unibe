/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 3-1                         *
\* ************************************************************************* */

//Lukas Ingold 20-123-998

import java.util.Date;
import java.util.Scanner;
import java.text.*;

public class Book
{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	//--- constructor ---

	public Book(int idU, String titleU, String authorU, Date dateOfPublicationU){
								id = idU;
								title = titleU;
								author = authorU;
								dateOfPublication = dateOfPublicationU;
	}


	/** Returns the age of the book in days since publication */
	public int age()
	{
		// TODO: Insert your code here!

			long a = System.currentTimeMillis()/86400000;
	    long b = dateOfPublication.getTime()/86400000;

	    long daysSincePublication = a-b;
	    return (int) daysSincePublication;

	}

	/** Returns a String representation of the book */
	public String toString()
	{
		String u = "" + id + ", " + title + ", " + author + ", " + dateToString(dateOfPublication);
		return u;
	}

	/** Reads all book data from user input */

	public void input()
	{
		Scanner scn = new Scanner( System.in );
		System.out.print( "Please enter id: " );

				id = scn.nextInt();
				System.out.println();

				System.out.print( "Please enter title: " );
				title = scn.nextLine();
				System.out.println();

				System.out.print( "Please enter author: " );
				author = scn.nextLine();
				System.out.println();

				System.out.print( "Please enter dateOfPublication: " );
				dateOfPublication = stringToDate(scn.nextLine());
				System.out.println();

	}

	//--- Get-methods ---
	public int getId(){return id;}
	public String getTitle(){return title;}
	public String getAuthor(){return author;}
	public Date getDateOfPublication() {return dateOfPublication;}

	//--- Set-methods ---

	public void setId(int n){id=n;}
	public void setTitle(String n){title=n;}
	public void setAuthor(String n){author=n;}
	public void setDateOfPublication(String n){dateOfPublication = stringToDate(n);}



	//--- helper methods -- DO NOT CHANGE ------------------------------------
	/** Converts the Date object d into a String object */
	public static String dateToString( Date d )
	{
		SimpleDateFormat fmt = new SimpleDateFormat( DATE_FORMAT );
		return fmt.format( d );
	}

	/** Converts the String object s into a Date object */
	public static Date stringToDate( String s )
	{
		Date r = null;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat( DATE_FORMAT );
			r = fmt.parse( s );
		} catch ( ParseException e ){
			System.err.println( e );
			System.exit(1);
		}
		return r;
	}
}
