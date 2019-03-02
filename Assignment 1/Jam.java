
public class Jam extends MarketProduct{
	private int numberOfJars;
	private int pricePerJar;
	
	public Jam(String name, int number, int price) {
		super(name);
		this.numberOfJars = number;
		this.pricePerJar = price;
	}
	
	public int getCost() {
		return this.numberOfJars * this.pricePerJar;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Jam) {
			if (((Jam) obj).getName().equals(this.getName())) {
				if (((Jam) obj).getCost() == this.getCost()) {
					if (((Jam) obj).numberOfJars == this.numberOfJars)
						return true;
				}
			}
		}
		return false;
	}
}
