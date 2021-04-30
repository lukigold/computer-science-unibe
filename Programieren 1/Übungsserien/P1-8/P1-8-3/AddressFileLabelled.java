import java.util.*;
import java.io.*;

public class AddressFileLabelled extends AddressFile
{
  public AddressFileLabelled(String filename)
  {
    super(filename);
  }

  private String toLine(Address addr)
  {
    return addr.toString();
  }

  private Address parseLine(String line) throws AddressFileException
  {
    try
    {
      Scanner scnr = new Scanner(line);

      scnr.findInLine("id"+"[\\s]*:[\\s]*([^;]*)");
      String input = scnr.match().group(1).trim();
      int id = Integer.parseInt(input);

      scnr.findInLine("name"+"[\\s]*:[\\s]*([^;]*)");
      String input2 = scnr.match().group(1).trim();
      String name = input2;

      scnr.findInLine("street"+"[\\s]*:[\\s]*([^;]*)");
      String input3 = scnr.match().group(1).trim();
      String street = input3;

      scnr.findInLine("zip"+"[\\s]*:[\\s]*([^;]*)");
      String input4 = scnr.match().group(1).trim();
      int zipcode = Integer.parseInt(input4);

      scnr.findInLine("city"+"[\\s]*:[\\s]*([^;]*)");
      String input5 = scnr.match().group(1).trim();
      String city = input5;

      return (new Address(id, name, street, zipcode, city));

    }
    catch(Exception e)
    {
      AddressFileException AddressFileException = new AddressFileException("Error in Address file.");
      throw AddressFileException;
    }
  }
}
