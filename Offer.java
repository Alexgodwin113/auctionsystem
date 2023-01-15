package coursework_question4;

public class Offer {
	private double value;
	private User buyer;
	
	
	public Offer(User buyer, double value) {
		this.buyer=buyer;
		this.value=value;
		
		if (buyer == null) {
			throw new IllegalArgumentException();
		}
		
		if (value <=0) {
			throw new IllegalArgumentException();
		}
	}


	public User getBuyer() {
		return buyer;
	}
	
	
	public void setValue(double value) {
		this.value = value;
	}


	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}


	public double getValue() {
		return value;
	}


	@Override
	public String toString() {
		return buyer+" offered £"+ String.format("%.2f", value);
	}

}
