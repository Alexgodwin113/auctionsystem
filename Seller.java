package coursework_question4;

public class Seller extends User {
	private  int sales;
	
	public Seller(String fullname) {
		super(fullname);
	}
	
	@Override
	public String getName() {
		  int index = getFullname().lastIndexOf(" ");
	        if (index > -1) {
	            return getFullname().substring(0, index);
	        }
	      return getFullname();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		int index = getFullname().indexOf(" ");
		String output1= getFullname().substring(0, index);
		String output2= getFullname().substring(index+1, index+2);
		return output1+" "+output2+". (Sales: "+ sales + ", Rating: "+ identifyRating()+")";
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String identifyRating() {
		String level="";
		if (this.sales == 0) {
			level = "Level 0";
		} else if (this.sales <= 5) {
			level = "Level 1";
		} else if (this.sales <= 10) {
			level = "Level 2";
		} else {
			level = "Level 3";
		}
		return level;
	}
	
}
