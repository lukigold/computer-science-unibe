//Lukas Ingold 20-123-998
public class Order{
	private static int id=0;
	private String customerName;
	private String customerAdress;
	private Book var1;
	private Book var2;
	private Book var3;
	private Book var4;
	private Book var5;
	private static int buchanz;

	public Order(){id++; buchanz = 0;}


	public String toString()
	{

	String b = "Order id: "+ id + ", Customer: "+ customerName + ", "+ customerAdress+"\n";
	if (buchanz > 0) {b += var1.toString()+ "\n";}
	if (buchanz > 1) {b += var2.toString()+ "\n";}
	if (buchanz > 2) {b += var3.toString()+ "\n";}
	if (buchanz > 3) {b += var4.toString()+ "\n";}
	if (buchanz > 4) {b += var5.toString();}

		return b;
	}

	public void setCustomerName( String m ){ customerName = m; }
	public void setCustomerAddress( String n ){ customerAdress = n; }


	public void addBook(Book b)
	{

		if (buchanz<5){
		if (buchanz==0){var1=b;buchanz++;}
		else if (buchanz==1){var2=b;buchanz++;}
		else if (buchanz==2){var3=b;buchanz++;}
		else if (buchanz==3){var4=b;buchanz++;}
		else if (buchanz==4){var5=b;buchanz++;}

	}}
}
