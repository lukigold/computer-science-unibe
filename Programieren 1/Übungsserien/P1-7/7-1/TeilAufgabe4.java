//Lukas Ingold 20-123-998
public class TeilAufgabe4 {

  public static void iterative()
  {
    for(int i=0; i<=30; i+=3)
    {
      System.out.println(i);
    }
  }
  public static void recursive(int a){
    if(a > 1)
    {
      recursive(a-3);
    }
    System.out.println(a);
  }
  public static void main(String args[]) {

     recursive(30);
     iterative();

      }
}
