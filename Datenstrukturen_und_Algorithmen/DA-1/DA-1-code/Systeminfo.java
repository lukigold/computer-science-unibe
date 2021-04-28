//Lukas Ingold 20-123-998

public class Systeminfo
{
  long kilobytes = 1024;
  long megabytes = kilobytes * 1024;
  long gigabytes = megabytes * 1024;


  public String getSystemInfo()
  {
    String name = "os.name";
    String os = System.getProperty(name);
    String mem = Runtime.getRuntime().totalMemory() / (float) megabytes +" mb";// Total memory currently available to the JVM
    String cores = Runtime.getRuntime().availableProcessors()+" ";

    String info = "OS: "+os+", Memory: "+mem+", CPU Cores: "+ cores;
    return info;
  }
}
