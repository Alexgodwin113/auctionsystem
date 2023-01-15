package coursework_question4;

public class Car {
	private int id;
	private String name;
	private String colour;
	private double reservedPrice;
	private CarType gearbox;
	private CarBody body;
	private int numberOfSeats;
	private Condition condition;
	private SaleType type;
	


	public Car(int id, String name, double reservedPrice, Condition condition, SaleType type) {
		this.id=id;
		this.name=name;
		this.reservedPrice=reservedPrice;
		this.condition=condition;
		this.type=type;
		
		if (name==null) {
			throw new IllegalArgumentException();
		}
		
		if (reservedPrice < 0) {
			throw new IllegalArgumentException();
		}
		
		if (id <0) {
			throw new IllegalArgumentException();
		}
		
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return reservedPrice;
	}

	public Condition getCondition() {
		return condition;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public CarType getGearbox() {
		return gearbox;
	}

	public void setGearbox(CarType gearbox) {
		this.gearbox = gearbox;
	}

	public CarBody getBodyStyle() {
		return body;
	}

	public void setBody(CarBody body) {
		this.body = body;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String displayCarSpecification() {
		return id + " - " + this.getName() +" (£"+  String.format("%.2f",getPrice()) +")"+"\n"+"\t"+"Type: "
				+ getGearbox()+"\n"+"\t"+"Style: "+ getBodyStyle()
				+"\n"+"\t"+"Colour: "+ getColour()+"\n"+"\t"+"No. of Seats: "
				+getNumberOfSeats()+"\n"+"\t"+"Condition: "+getCondition();
	}
	
	public String toString() {
		return id + " - "+ getName();
	}
	
	public SaleType getType() {
		return type;
	}
	
}
