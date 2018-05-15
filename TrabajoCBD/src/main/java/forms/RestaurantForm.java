package forms;

public class RestaurantForm {
	
	private String address;
	private String address2;
	private String name;
	private String outcode;
	private String postcode;
	private int rating;
	private String type_food;
	
	public RestaurantForm() {
		super();
	}
	
	public RestaurantForm(String address, String address2, String name,	String outcode, String postcode, int rating, String type_food) {
		this.setAddress(address);
		this.setAddress2(address2);
		this.setName(name);
		this.setOutcode(outcode);
		this.setPostcode(postcode);
		this.setRating(rating);
		this.setType_food(type_food);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOutcode() {
		return outcode;
	}

	public void setOutcode(String outcode) {
		this.outcode = outcode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getType_food() {
		return type_food;
	}

	public void setType_food(String type_food) {
		this.type_food = type_food;
	}

}
