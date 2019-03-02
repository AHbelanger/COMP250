
public class Egg extends MarketProduct{
	private int numberOfEggs;
	private int pricePerDozen;
	
	
	public Egg(String name, int number, int price) {
		super(name);
		this.numberOfEggs = number;
		this.pricePerDozen = price;
	}
	
	public int getCost() {
		return (int) (this.numberOfEggs * (this.pricePerDozen / 12.0));
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Egg) {
			if (((Egg) obj).getName().equals(this.getName())) {
				if (((Egg) obj).getCost() == this.getCost()) {
					if (((Egg) obj).numberOfEggs == this.numberOfEggs)
						return true;
				}
			}
		}
		return false;
	}
	
}
