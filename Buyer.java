package coursework_question4;

public class Buyer extends User{
	private int age;
	
	public Buyer(String fullname, int age) {
		super(fullname);
		// TODO Auto-generated constructor stub
		if (age < 18) {
			throw new IllegalArgumentException();
		}
		this.age=age;
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
		String output1 = getFullname().substring(0,1);
		int index = getFullname().indexOf(" ");
		String output2 = getFullname().substring(index-1, index);
		String output3 = output1+"***"+ output2;
		return output3;
	}

	public int getAge() {
		return age;
	}
	
	





}
