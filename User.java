package coursework_question4;

public abstract class User {
	private String fullname;
	
	
	public User(String fullname) {
		this.setFullname(fullname);
		String regExp = "[A-Z]{1}[a-z]*\\s[A-Z]{1}[a-z]*";	
		if (fullname.matches(regExp) == false) {
			throw new IllegalArgumentException();
		}
	}
	
	public abstract String getName();
	
	public abstract String toString();

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
