//Lukas Ingold 20-123-998
import java.util.*;
import java.io.*;

public class AddressFile
{
  private String filename;

  public AddressFile(String filename)
  {
    this.filename = filename;
  }

  private String toLine(Address address)
  {
    return address.toString();
  }

  private Address parseLine(String line) throws AddressFileException
  {
    try
    {
      Scanner scan = new Scanner(line);
      scan.useDelimiter(",");

      int id = Integer.parseInt(scan.next().trim());
      String name = scan.next().trim();
      String strasse = scan.next().trim();
      int zipcode = Integer.parseInt(scan.next().trim());
      String city = scan.next().trim();

      return (new Address(id, name, strasse, zipcode, city));
    }
    catch(Exception e)
    {
      AddressFileException AddressFileException = new AddressFileException("Error in Address File.");
      throw AddressFileException;
    }
  }

  public void save(ArrayList<Address> addresses) throws FileNotFoundException
  {
    PrintWriter outPutFile = new PrintWriter(""+filename+".txt");
    for (Address address : addresses)
    {
      outPutFile.println(toLine(address));
    }
    outPutFile.close();
  }

  public ArrayList<Address> load() throws AddressFileException
  {
    try
    {
    Scanner scnr = new Scanner(new File(filename));
    ArrayList addresses = new ArrayList<Address>();

    while(scnr.hasNext())
    {
      String nextLine = scnr.nextLine();
      addresses.add(parseLine(nextLine));
    }
    return addresses;
    }
    catch(Exception e)
    {
      AddressFileException AddressFileException = new AddressFileException("Error in Address file.");
      throw AddressFileException;
    }
  }
}

class AddressFileException extends Exception
{
  AddressFileException(String message)
  {
    super(message);
  }
}
