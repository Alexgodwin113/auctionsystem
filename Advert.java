package coursework_question4;

import java.util.ArrayList;
import java.util.List;

public class Advert {
	private Car car;
	private List<Offer> offers;
	
	

	public Advert(Car car) {
		this.car=car;
		this.offers = new ArrayList<Offer>();
	}
	
	public Offer getHighestOffer() {
		Offer highestOffer = null;
		for (Offer each:offers) {
			if (highestOffer != null) {
				if (each.getValue() > highestOffer.getValue()) {
					highestOffer = each;
				} 
			} else {
				highestOffer = each;
			}
		}
		return highestOffer;
	}
	
	public boolean placeOffer(User buyer, double value) {
		boolean offerplaced;
		if(buyer != null && value>=0) {
			Offer offer = new Offer(buyer, value);
			offers.add(offer);
			offerplaced = true;
		} else {
			offerplaced = false;
			throw new IllegalArgumentException();
		}
		return offerplaced;
	}


	public List<Offer> getOffers() {
		return offers;
	}

	@Override
	public String toString() {
		return "Ad: "+ car.getID() + " - " + car.getName() + " (£" + String.format("%.2f", car.getPrice()) + ")\n"+
		"\t" + "Style: "+ car.getBodyStyle() + "\n" +
		"\t" + "Colour: "+ car.getColour() + "\n" +
		"\t" + "No. of Seats: "+ car.getNumberOfSeats()+ "\n" +
		"\t" + "Condition: "+ car.getCondition() + "\n";
	}
	
	public Car getCar() {
		return car;
	}
}
