
public class Customer {
	private String name;
	private int balance;
	private Basket basket;
	
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.basket = new Basket();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public Basket getBasket() {
		return this.basket;
	}
	
	public int addFunds(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Wrong Argument");
		else {
			this.balance += amount;
			return this.balance;
		}
	}
	
	public void addToBasket(MarketProduct item) {
		this.basket.add(item);
	}
	
	public boolean removeFromBasket(MarketProduct item) {
		return this.basket.remove(item);
	}
	
	public String checkOut() {
		if (this.balance < this.basket.getTotalCost()) {
			throw new IllegalStateException("Wrong Argument");
		}
		else {
			this.balance -= this.basket.getTotalCost();
			String receipt = this.basket.toString();
			this.basket.clear();
			return receipt;
			
			
		}
	}
}
