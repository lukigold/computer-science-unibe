/* ************************************************************************** *\
*                     Programmierung 1 HS 2020                                *
*                            Aufgabe 7-2                                     *
\* ************************************************************************** */


import java.util.ArrayList;
import java.util.List;

public class SortTest
{
	public static void main ( String[] args )
	{
		//sort an array of strings
		String[] wordlist = {"Z", "C", "B", "A", "X", "M", "F"};
		System.out.println( "Word list before sorting: " );
		for ( int i = 0; i < wordlist.length; i++ )
			System.out.println( wordlist[ i ] );
		MergeSort.sort( wordlist );
		System.out.println( "Word list after sorting: " );
		for ( int i = 0; i < wordlist.length; i++ )
			System.out.println( wordlist[ i ] );

		//sort an array of rectangles
		Rectangle[] rectangles = {new Rectangle( 100, 55 ),
		                          new Rectangle( 3, 4 ),
		                          new Rectangle( 30, 24 ),
		                          new Rectangle( 14, 9 ),
		                          new Rectangle( 90, 3 ),
		                          new Rectangle( 34, 12 ),
		                          new Rectangle( 1, 2 ),
		                          new Rectangle( 31, 24 ) };
		System.out.println( "\nRectangles before sorting:" );
		for ( int i = 0; i < rectangles.length; i++ )
			System.out.println( rectangles[ i ] );
		MergeSort.sort( rectangles );
		System.out.println( "\nRectangles after sorting:" );
		for ( int i = 0; i < rectangles.length; i++ )
			System.out.println( rectangles[ i ] );

			//sortiert array von Planeten
			Planet[] planets = {new Planet(63, 597),
													new Planet(33, 639),
													new Planet(253, 8681),
													new Planet(246, 10240),
													new Planet(582, 56830),
													new Planet(11, 1) };
			System.out.println( "\nPlanets before sorting:" );
													for ( int i = 0; i < planets.length; i++ )
														System.out.println( planets[ i ] );
													MergeSort.sort( planets );
													System.out.println( "\nPlanets after sorting:" );
													for ( int i = 0; i < planets.length; i++ )
														System.out.println( planets[ i ] );
	}
}
