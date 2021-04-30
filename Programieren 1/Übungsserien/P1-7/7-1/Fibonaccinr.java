//Lukas Ingold 20-123-998
public class Fibonaccinr {

  public static long fib(int a){
    long x = 0;
    long y = 1;
    long n = 1;

    if(a==0)
      return 0;

    for (int i = 2; i <= a; i++)
    {
      n = x + y;
      x = y;
      y = n;
    }

    return n;


  }
  public static void main(String[] args) {

    Fibonaccinr Fi = new Fibonaccinr();

    for(int i = 1; i <= 50; i++)
    {
      System.out.println(Fi.fib(i));
    }
  }
}
