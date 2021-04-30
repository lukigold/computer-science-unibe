//Lukas Ingold 20-123-998
import java.util.*;

public class Order{
	private static int id=0;
	private String customerName;
	private String customerAdress;
  public ArrayList<IArticle> articles = new ArrayList<IArticle>();
  private static int nO;

	public Order()
  {
    id++; nO = 0;
  }


	public String toString()
	{

	String a = "Order id: "+ id + ", Customer: "+ customerName + ", "+ customerAdress+"\n";
  for ( IArticle article : articles)
    {
		    a += article.toString();
	  }
  return a;
  }

//get und set methoden
	public void setCustomerName( String name ){ customerName = name; }
	public void setCustomerAddress( String adress ){ customerAdress = adress; }

  public String getCustomerName(){return customerName;}
  public String getCustomerAddress(){return customerAdress;}

  public int getTotalPrice()
  {
    int total = 0;
    for( IArticle article : articles )
    {
      total+= article.getPrice();
    }
    return total;
  }

  public Iterable<IArticle> getOrderedArticles()
  {
    return articles;
  }

	public void add(IArticle article)
	{
    articles.add(article);
  }
  public int getId()
  {
    return id;
  }
}
