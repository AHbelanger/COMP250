
public class Fruit extends MarketProduct{
	private double weight;
	private int pricePerKg;
	
	public Fruit(String name, double weight, int price) {
		super(name);
		this.weight = weight;
		this.pricePerKg = price;
	}
	
	public int getCost() {
		return (int) (this.weight * this.pricePerKg);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Fruit) {
			if (((Fruit) obj).getName().equals(this.getName())) {
				if (((Fruit) obj).getCost() == this.getCost()) {
					if (((Fruit) obj).weight == this.weight)
						return true;
				}
			}
		}
		return false;
	}
	
}
