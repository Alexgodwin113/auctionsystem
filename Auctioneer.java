package coursework_question4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Auctioneer extends Dealership {
	private String name;
	Map<Advert,Seller> carsForSale;
	Map<Advert,Buyer> soldCars;
	Map<Advert,Seller> unsoldCars;
	Map<Seller,Integer> sales;
	private Seller topSeller;
	
	
	
	public Auctioneer(String name) {
		this.name=name;
		this.carsForSale = new HashMap<Advert, Seller>();
		this.soldCars = new HashMap<Advert, Buyer>();
		this.unsoldCars = new HashMap<Advert, Seller>();
		this.sales = new HashMap<Seller, Integer>();
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
		boolean validOffer;
		if(carAdvert == null || user == null || value <0) {
			throw new IllegalArgumentException();
		}
		else{
			validOffer = true;
		}
	
		if (value >= carAdvert.getCar().getPrice()) {
			carAdvert.placeOffer(user, value);
			unsoldCars.remove(carAdvert, unsoldCars.get(carAdvert));
			soldCars.put(carAdvert, (Buyer) user);
		} 
		
		return validOffer;
	}
	
	public void endSale(Advert advert) {
		if (advert != null) {
			
			carsForSale.get(advert).setSales(carsForSale.get(advert).getSales()+1);
			
			if (sales.containsKey(carsForSale.get(advert)) == true) {
				sales.remove(carsForSale.get(advert));
				sales.put(carsForSale.get(advert), carsForSale.get(advert).getSales());
			} else {
				sales.put(carsForSale.get(advert), carsForSale.get(advert).getSales());
			}
			
			carsForSale.remove(advert);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
	private Seller getTopSeller() {
		
		for(Seller sell:sales.keySet()) {
			if (this.topSeller != null) {
				if (this.topSeller.getSales()<sell.getSales()) {
					topSeller = sell;
				}
			} else {
				topSeller =sell;
			}
		}
		return topSeller;
	}
	
	public String displaySoldCars() {
		
		String output1="SOLD CARS:"+"\n";
		for (Advert key:soldCars.keySet()) {
			output1 += key.getCar().getID()+" - "+"Purchased by "+ 
					key.getHighestOffer().getBuyer().getName().substring(0,1)+"***"+
					key.getHighestOffer().getBuyer().getName().substring(key.getHighestOffer().getBuyer().getName().length()-1)
					+" with a successful £"+ String.format("%.2f",key.getHighestOffer().getValue()) + " bid."+"\n";
		}
		System.out.println(output1);
		return output1;
	}
	
	
	
	public String displayUnsoldCars() {
		
		String output2="UNSOLD CARS:"+"\n";
		for (Advert key:unsoldCars.keySet()) {
			output2 += "Ad: "+key.getCar().getID()+" - "+key.getCar().getName()+
					   " (£"+String.format("%.2f",key.getCar().getPrice())+")"+"\n"+
					"\t"+"Type: "+key.getCar().getGearbox()+"\n"+
					"\t"+"Style: "+key.getCar().getBodyStyle()+"\n"+
					"\t"+"Colour: "+key.getCar().getColour()+"\n"+
					"\t"+"No. of Seats: "+key.getCar().getNumberOfSeats()+"\n"+
					"\t"+"Condition: "+key.getCar().getCondition()+"\n";		
		}
		System.out.println(output2);
		return output2;
	}
	
	
	
	public String displayStatistics() {
		int totalauctionsales = 0;
		double automaticcars =0;
		double manualcars=0;
		
		for (Advert key:soldCars.keySet()) {
			totalauctionsales += 1;
		}
		
		for (Advert key:soldCars.keySet()) {
			if (key.getCar().getGearbox() == CarType.AUTOMATIC) {
				automaticcars +=1;
			}
		}
		
		for (Advert key:soldCars.keySet()) {
			if (key.getCar().getGearbox() == CarType.MANUAL) {
				manualcars +=1;
			}
		}
		
		double totalcars = manualcars + automaticcars;
		double manualpercent = (manualcars/totalcars) *100;
		double automaticpercent = (automaticcars/totalcars)*100;
		
		saveInFile(totalauctionsales,automaticpercent,manualpercent);
		
		return 	"** Auctioneer - "+name+"**"+"\n"+
				"Total Auction Sales: "+totalauctionsales+"\n"+
				"Automatic Cars: "+ automaticpercent +"%"+"\n"+
				"Manual Cars: "+manualpercent+"%"+"\n"+
				"Top Seller: "+getTopSeller().toString();
		
	}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void updateStatistics(Seller seller) {
			this.topSeller=seller;
		}
	
		private void saveInFile(int noOfSales, double percentageOfUsed, double percentageOfNew) {
		
		
		String output = "Total Auction Sales: "+noOfSales+"\n"+
						"Automatic Cars: "+ percentageOfUsed +"%"+"\n"+
						"Manual Cars: "+ percentageOfNew +"%"+"\n"+
						"Top Seller: "+getTopSeller().toString();
		
		File file = new File("auction_statistics.txt");
		FileWriter fw;
		try {
			
			fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(output);
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
