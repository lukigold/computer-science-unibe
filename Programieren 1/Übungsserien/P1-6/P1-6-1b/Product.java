
import java.util.Arrays;

public class Product{

  public static final String a = "A";
  public static final String b = "B";
  public static final String c = "Product";

  public static int[][] calculateProduct(int[][] A, int[][] B, int rowsA, int columnsB, int columnsA)
    {

      int[][] C = new int[rowsA][columnsB];
      for(int i = 0; i < rowsA; i++)
      {
           for (int j = 0; j < columnsB; j++)
           {
               for (int k = 0; k < columnsA; k++)
               {
                   C[i][j] += A[i][k] * B[k][j];
               }
           }
      }

       return C;
     }

  public static void PrintMatrix(int[][] U, String name)
  {
    System.out.println("Matrix "+ name +":");
    System.out.println();
    int m = U.length;
    int n= U[0].length;

    for(int u = 0; u < m ; u++)
    {
      for(int v = 0; v<n ; v++)
      {
        System.out.print(U[u][v]+" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String args[])
  {
    int[][] A = {{1, 2}, {3, 4}, {5, 6}};
    int[][] B = {{1, 2, 3}, {4, 5, 6}};

    int rowsA = A.length;
    int columnsA = A[0].length;
    int columnsB = B[0].length;

    int[][] C = calculateProduct(A, B, rowsA, columnsB, columnsA);

    PrintMatrix(A, a);
    PrintMatrix(B, b);
    PrintMatrix(C, c);

  }
}
