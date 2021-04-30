//Lukas Ingold 20-123-998
public class DVD implements IArticle
{
	private int id;
	private String title;
	private int year;
	private int price; // CHF

	/** constructor */
	public DVD( int id, String title, int year, int price )
	{
		this.id = id;
		this.title = title;
		this.year = year;
		this.price = price;
	}

	public String getDescription()
	{
		return id + " (DVD) " + title + ", " + year + ", " + price + " CHF";
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}
}
