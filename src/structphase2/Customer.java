package structphase2;

public class Customer {
	private String name;
	private int mobileNo;

	public Customer(String name, int mobileNo) {
		this.name = name;
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
}
