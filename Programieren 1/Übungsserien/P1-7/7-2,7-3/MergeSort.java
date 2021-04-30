import java.util.ArrayList;
import java.util.List;

public class MergeSort{
  public static void sort(Comparable[] array){
    // Array wird sortiert und an den gefragten Array übbergeben

    Comparable[] sortedArray = MergeSort(array);

    for(int a=0;a<sortedArray.length; a++)
    {
      array[a]=sortedArray[a];
    }

  }
  public static Comparable[] MergeSort( Comparable[] array){
    //Array halbieren
    int arraylength = array.length;

    Comparable[] firstpart = new Comparable[(arraylength+1)/2];

    int firstpartlength = firstpart.length;

    Comparable[] seccondpart = new Comparable[arraylength-firstpartlength];



    //neue arrays mit den inhalten des gefraten füllen

    for(int i = 0; i < arraylength; i++)
    {
      if(i<firstpartlength)
        firstpart[i] = array[i];
      else
        seccondpart[i-firstpartlength] = array[i];
    }

    if(firstpartlength == 1) {return merge(firstpart, seccondpart);}

    return merge(MergeSort(firstpart), MergeSort(seccondpart));
  }


  public static Comparable[] merge(Comparable[] firstpart, Comparable[] seccondpart)
  {

    int arraylength = firstpart.length+seccondpart.length;

    Comparable[] merge = new Comparable[arraylength];


    int x = 0;
    int y = 0;


    for(int b=0; b<arraylength; b++)
    {
      if(firstpart.length<=x)
      {
        for(int c = y; c < seccondpart.length; c++)
        {
          merge[b] = seccondpart[c];
          b++;
        }
        return merge;
      }


      else if (seccondpart.length<=y)
      {
        for(int d = x; d < firstpart.length; d++)
        {
          merge[b] = firstpart[d];
          b++;
        }
        return merge;
      }


      if(firstpart[x].compareTo(seccondpart[y]) < 0)
      {
        merge[b] = firstpart[x];
        x++;
      }
      else { merge[b] = seccondpart[y]; y++;}
    }
    return merge;
  }
}
