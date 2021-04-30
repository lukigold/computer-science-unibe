//Lukas Ingold 20-123-998
public class Factorial {

  public static long factorial(int n) {
    if(n == 0)
      return 1;
    else
      return n * factorial(n-1);
  }

  public static void main(String[] i) {
    int a = Integer.parseInt(i[0]);
    System.out.println(factorial(a));

  }
}
/*Die Rekursion in diesem Programm findet nur an einer Stelle Statt bein Fibonacci
muss die methode sich zweimal selber aufrufen und an verschiedenen Stellen sich selber
wieder aufrufen anstatt nur wie hier immer wieder an einer Stelle.*/
