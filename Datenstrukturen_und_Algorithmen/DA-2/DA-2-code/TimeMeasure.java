//Lukas Ingold 20-123-998
import java.util.*;
import java.io.*;

public class TimeMeasure {

	public static void printArray(int[] array)
	{
		// Ausgabe des Arrays:
		for (int i=0; i<array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}

	public static int[] randomArray(int l)
	{
		Random rnd = new Random();

		int[] array = new int[5000 + (l*10000)];
		for( int i=0 ; i <= array.length-1 ; i++)
		{
			array[i] = rnd.nextInt(9999);
		}
		return array;
	}

	public static long TimetoSortInsertionSort(int[] array)
	{

		Timer Timer = new Timer();
		Sorting.insertionSort(array);
		long ms = Timer.timeElapsed();

		return ms;
	}

	public static long TimetoSortJavaSort(int[] array)
	{

		Timer Timer = new Timer();
		Arrays.sort(array);
		long ms = Timer.timeElapsed();

		return ms;
	}

	public static long TimetoSortMergeSort(int[] array)
	{

		Timer Timer = new Timer();
		Sorting.mergeSort(array, 0, array.length-1);
		long ms = Timer.timeElapsed();

		return ms;
	}

	public static void main(String[] args) throws FileNotFoundException {

	File file = new File ("Daten_TimeMeasure.txt");
	System.out.println("\nCreating Daten_TimeMeasure.txt please wait...");

	try
	{
			PrintWriter outputfile = new PrintWriter(file);

			Systeminfo thisSystem = new Systeminfo();

			outputfile.println(thisSystem.getSystemInfo());

			int numberOfTests = 50; //ATTENTION it may take a while depending on input! 100 ca. 1.5h

			//Insertion Sort LOOP
			System.out.println("Insertion Sort Test initialised may take a while with: "+numberOfTests +" Tests\n");
			outputfile.println("\n Insertion Sort Test: \n");
			outputfile.println("id\t\t"+"n\t\t"+"ms");

			long a = 0;
			long x = 0;
			for(int i=0;i<numberOfTests;i++)
			{
				int[] array = randomArray(i);

				long n = (long) array.length;
				long ms = TimetoSortInsertionSort(array);

				a += (ms);
				x += (n);
				//TODO: debug only System.out.println(a);


				outputfile.println(i+1+"\t\t"+ n+"\t\t"+ms);
			}
			System.out.println("InsertionSort done");


				//merge Sort LOOP
				System.out.println("\nMerge Sort Test initialised \n");
				outputfile.println("\n Merge Sort Test: \n");
				outputfile.println("id\t\t"+"n\t\t"+"ms");

				long b = 0;
				long y = 0;
				for(int m=0;m<numberOfTests;m++)
				{
					int[] array = randomArray(m);

					long n = (long) array.length;
					long ms = TimetoSortMergeSort(array);

					b += (ms);
					y += (n);
					//TODO: debug only System.out.println(b);

					outputfile.println(m+1+"\t\t"+ n+"\t\t"+ms);
				}
				System.out.println("MergeSort done\n");

				System.out.println("\nJava Sort Test initialised \n");
				outputfile.println("\n Java Sort Test: \n");
				outputfile.println("id\t\t"+"n\t\t"+"ms");

				long h = 0;
				long i = 0;
				for(int u=0;u<numberOfTests;u++)
				{
					int[] array = randomArray(u);

					long n = (long) array.length;
					long ms = TimetoSortJavaSort(array);

					h += (ms);
					i += (n);

					outputfile.println(u+1+"\t\t"+ n+"\t\t"+ms);
				}
				System.out.println("JavaSort done\n");


				long one = a/numberOfTests;
				long two = b/numberOfTests;
				long three = x/numberOfTests;
				long four = y/numberOfTests;
				long five = h/numberOfTests;
				long six = i/numberOfTests;

				outputfile.println("\naverage time and n for Insertion-Sort: \n\n\n\t"+one+" ms"+"\t\t"+three+"\n\n");
				outputfile.println("average time and n for Merge-Sort: \n\n\n\t"+two+" ms"+"\t\t"+four+"\n\n");
				outputfile.println("average time and n for Java-Sort: \n\n\n\t"+five+" ms"+"\t\t"+six+"\n\n");

			outputfile.close();
	}
	catch (FileNotFoundException e)
	{
			System.out.println("...File Output Error...");
	}

	}
}
