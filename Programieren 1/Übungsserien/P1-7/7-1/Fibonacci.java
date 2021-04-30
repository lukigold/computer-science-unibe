//Lukas Ingold 20-123-998
public class Fibonacci {

    public static long fib(int i) {
        if(i <= 2)
            return 1;
        return fib(i - 1) + fib(i - 2);
    }

    public static void main(String[] args) {

      Fibonacci Fib = new Fibonacci();


        for(int i = 1; i <= 50; i++){
          System.out.println(Fib.fib(i));
        }
    }
}
/*Dieses Programm wird sehr schnell sehr langsam da sich die methode immer
selber aufruft*/
