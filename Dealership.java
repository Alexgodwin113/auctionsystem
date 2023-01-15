package coursework_question4;

import java.util.Map;



public abstract class Dealership {
	private String name;
	Map<Advert,Seller> carsForSale;
	Map<Advert,Buyer> soldCars;
	Map<Advert,Seller> unsoldCars;
	
	public Dealership() {
		
	}
	
	public abstract String displaySoldCars();
	
	public abstract  String displayStatistics();
	
	public abstract String displayUnsoldCars();
	
	public abstract boolean placeOffer(Advert carAdvert, User user, double value);
	
	public abstract void registerCar(Advert carAdvert,User user,String colour, CarType type,CarBody body, int noOfSeats);

	public abstract String getName();
	

	
}
