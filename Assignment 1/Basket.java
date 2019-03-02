
public class Basket {
	private MarketProduct[] list;
	
	public Basket() {
		list = new MarketProduct[0];
	}
	
	public MarketProduct[] getProducts() {
		MarketProduct[] copy = new MarketProduct[this.list.length];
		for (int i = 0; i < this.list.length; i++)
			copy[i] = this.list[i];
		return copy;
	}
	
	public void add(MarketProduct item) {
		MarketProduct[] temp = new MarketProduct[this.list.length + 1];
		System.arraycopy(this.list, 0, temp, 0, this.list.length);
		temp[temp.length - 1] = item; //
		this.list = temp;
	}
	
	public boolean remove(MarketProduct item) {
		for (int i = 0; i < this.list.length; i++) {
			if (item.equals(list[i])){
					for (int j = i + 1; j < this.list.length; j++) {
						list[i] = list[j];
					}
					MarketProduct[] temp = new MarketProduct[list.length - 1];
					System.arraycopy(list, 0, temp, 0, list.length - 1);
					list = temp;
					return true;
				}
		}	
		return false;
	}
	
	public void clear() {
		list = new MarketProduct[0];
	}
	
	public int getNumOfProducts() {
		return this.list.length;
	}
	
	public int getSubTotal() {
		int subTotal = 0;
		for (int i = 0; i < this.list.length; i++) {
			subTotal += this.list[i].getCost(); //Check how to downcast
		}
		return subTotal;
	}
	
	public int getTotalTax() {
		double totalTax = 0;
		for (int i = 0; i < this.list.length; i++) {
			if (this.list[i] instanceof Jam) 
				totalTax += 0.15 * this.list[i].getCost();	
		}
		return (int) totalTax;
	}
	
	public int getTotalCost() {
		return this.getSubTotal() + this.getTotalTax();
	}
	
	public static String format(int amount) {
		if (amount <= 0)
			return "-";
		else
			return String.format("%.2f", (amount / 100.0));
	}
	
	public String toString() {
		String receipt = "\n";
		for (int i = 0; i < this.list.length; i++) {
			receipt += this.list[i].getName() + "  " + Basket.format(this.list[i].getCost()) + "\n";
		}
		receipt += "\nSubtotal   " + Basket.format(this.getSubTotal()) + "\nTotal tax   " + Basket.format(this.getTotalTax()) + "\nTotal cost  ";
		receipt += Basket.format(this.getTotalCost()) + "\n\n";
		return receipt;
	}
}
