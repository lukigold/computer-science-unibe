
import java.util.Arrays;

public class Transpose {

	public static int[][] A = {{1,2,3},{4,5,6},{7,8,9}};

	public static int m = A.length;
  public static int n = A[0].length;
  public static int[][] B = new int[m][n];

	public void TransposeMatrix()
    {
        for(int i = 0; i < m ; i++)
        {
            for(int j = 0; j<n; j++)
            {
                B[i][j]=A[j][i];
            }
        }
    }

    public void PrintMatrixA()
    {
        System.out.println("Original Matrix:");
        System.out.println();

        for(int u = 0; u < m ; u++)
         {
          for(int v = 0; v<n ; v++)
            {
              System.out.print(A[u][v]+" ");
            }
          System.out.println();
         }
        System.out.println();
    }
    public void PrintMatrixB()
    {
        System.out.println("Transposed Matrix:");
        System.out.println();

         for(int x = 0; x < m ; x++)
          {
             for(int y = 0; y<n ; y++)
               {
                   System.out.print(B[x][y]+" ");
                }
            System.out.println();
          }
        System.out.println();
    }

    public static void main(String[] args)
    {
    	Transpose Matrix = new Transpose();
    	Matrix.TransposeMatrix();
    	Matrix.PrintMatrixA();
    	Matrix.PrintMatrixB();
    }
}
