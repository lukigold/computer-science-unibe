public class Planet implements Comparable<Planet>{
  private int radius;
  private int mass;

	public Planet(int radius, int mass){
		this.radius = radius;
    this.mass = mass;
	}

	public int getVolume(){
		 int volume = (int)(4 / 3) *(int)Math.PI *(int) Math.pow(radius, 3);
     return volume;
	}

  public int getSurfaceArea(){
    int SurfaceArea = (int)4 * (int)Math.PI * (int)Math.pow(radius, 2);
    return SurfaceArea;
  }

  /*public long getG(){
    long g =  Long.parseLong("667430000000");
    long u =  g * (int)(mass/Math.pow(radius, 2));
    int m = (int) u;
  return m;
}*/

	public String toString(){
		return "The Planets Surface Area is: "+this.getSurfaceArea()+"0 Mm^2";
	}

	public int compareTo(Planet other){
		int a = (int) this.getSurfaceArea() - (int)other.getSurfaceArea();
    return a;
	}
}
