//Lukas Ingold 20-123-998
import java.util.*;

public class Dictionary
{
  private List<String> translations;
  HashMap<String, String[]> HashMap = new HashMap<String, String[]>();


  public void addTranslations(String German, String[] English)
  {
    //String English = String.join(", ", EnglishA);
    HashMap.put(German, English);
  }



  public List<String> translate(String word) throws WordNotFoundException
  {
    WordNotFoundException WordNotFoundException = new WordNotFoundException("Word not in dictionary.");

    Set<String> keySet = HashMap.keySet();
    boolean exception = true;

    for(String key : keySet)
    {
      if(word.equals(key))
          exception = false;
    }
    if (exception)
        throw WordNotFoundException;

    String[] translations = HashMap.get(word);
    List translationsasaList = Arrays.asList(translations);

    return translationsasaList;
  }
}
class WordNotFoundException extends Exception
{
  WordNotFoundException(String message)
  {
    super(message);
  }
}
