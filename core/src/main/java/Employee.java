
public class Employee {
	 String name;
	 String title;
	 String designation;
	 String country;
	 String image;
	 String email;

	 public Employee() {
		 
	 }
	 
	public Employee(String name, String title, String designation, String country, String image, String email) {
		super();
		this.name = name;
		this.title = title;
		this.designation = designation;
		this.country = country;
		this.image = image;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", title=" + title + ", designation=" + designation + ", country=" + country
				+ ", image=" + image + ", email=" + email + "]";
	}
	
}
