package coursework_question4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trader extends Dealership {
	private String name;
	Map<Advert,Seller> carsForSale;
	Map<Advert,Buyer> soldCars;
	Map<Advert,Seller> unsoldCars;
	List<Seller> sellers;
	
	public Trader(String name) {
		this.name=name;
		this.carsForSale = new HashMap<Advert, Seller>();
		this.soldCars = new HashMap<Advert, Buyer>();
		this.unsoldCars = new HashMap<Advert, Seller>();
		this.sellers = new ArrayList<Seller>();
	}

	private boolean checkExistence(Car car) {
		boolean existence = false;
		for(Advert key:carsForSale.keySet()) {
			if (key.getCar() == car) {
				existence = true;
			} 
		}
		return existence;
	}
	
	public void registerCar(Advert carAdvert,User user,String colour, CarType type,
			CarBody body, int noOfSeats) {
		
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException();
		}
		
		if (checkExistence(carAdvert.getCar()) == false) {
			carAdvert.getCar().setColour(colour);
			carAdvert.getCar().setBody(body);
			carAdvert.getCar().setGearbox(type);
			carAdvert.getCar().setNumberOfSeats(noOfSeats);
			
			carsForSale.put(carAdvert, (Seller) user);
			unsoldCars.put(carAdvert, (Seller) user);
		}
		
	}
	
	public boolean placeOffer(Advert carAdvert, User user, double value) {
		boolean validOffer= false;
		
		if(carAdvert == null || user == null || value <0) {
			throw new IllegalArgumentException();
		}
	
		if (value >= carAdvert.getCar().getPrice()) {
			validOffer = true;
			carAdvert.placeOffer(user, value);
//			carsForSale.remove(carAdvert, carsForSale.get(carAdvert));
			unsoldCars.remove(carAdvert, unsoldCars.get(carAdvert));
			soldCars.put(carAdvert, (Buyer) user);
			endSale(carAdvert);
		} 
		
		return validOffer;
	}
	
	private void endSale(Advert advert) {
		if (advert != null) {
			
			carsForSale.get(advert).setSales(carsForSale.get(advert).getSales()+1);
			
			
			
			if (sellers.contains((carsForSale.get(advert)))  == true) {
				sellers.remove(carsForSale.get(advert));
				sellers.add(carsForSale.get(advert));
			} else {
				sellers.add(carsForSale.get(advert));
			}
			
			carsForSale.remove(advert);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String displaySoldCars() {
		
		String output1="SOLD CARS:"+"\n";
		for (Advert key:soldCars.keySet()) {
			output1 += key.getCar().getID()+" - "+"Purchased by "+ 
					key.getHighestOffer().getBuyer().getName().substring(0,1)+
					"***"+
					key.getHighestOffer().getBuyer().getName().substring(key.getHighestOffer().getBuyer().getName().length()-1)
					+" with a successful £"+ String.format("%.2f",key.getHighestOffer().getValue()) + " bid."+"\n";
		}
		System.out.println(output1);
		return output1;
	}
	
	
	public String displayUnsoldCars() {
		
		String output2="UNSOLD CARS:"+"\n";
		for (Advert key:unsoldCars.keySet()) {
			output2 += "Ad: "+key.getCar().getID()+" - "+key.getCar().getName()+" (£"+String.format("%.2f",key.getCar().getPrice())
			+")"+"\n"+"\t"+"Type: "+key.getCar().getGearbox()+"\n"+
			"\t"+"Style: "+key.getCar().getBodyStyle()+"\n"+
			"\t"+"Colour: "+key.getCar().getColour()+"\n"+
			"\t"+"No. of Seats: "+key.getCar().getNumberOfSeats()+"\n"+
			"\t"+"Condition: "+key.getCar().getCondition()+"\n";		
		}
		System.out.println(output2);
		return output2;
	}
	
	public String displayStatistics() {
		int totalsales=0;
		for (Seller each:sellers) {
			totalsales += each.getSales();
		}
		
		String output="";
		for (Seller each:sellers) {
			output+="\t"+ each.toString()+"\n";
		}
		
		try {
			saveInFile(totalsales);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "** Trader - "+name+"**"+"\n"+
			"Total Sales: "+totalsales+"\n"+
			"All Sellers:"+"\n"+output.substring(0, output.length()-1); 
			
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void updateStatistics(Seller seller) {
		// TODO Auto-generated method stub
	}
	
	private void saveInFile(int noOfSales) throws IOException {
		
		String output="";
		for (Seller each:sellers) {
			output+="\t"+ each.toString()+"\n";
		}
		
		String printout = "Total Sales: "+noOfSales+"\n"+
						"All Sellers:"+"\n"+output.substring(0, output.length()-1); 
		

		File file = new File("trade_statistics.txt");
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println(printout);
		
		pw.close();
	}
	
}
