import java.io.Serializable;
import java.util.regex.Pattern;

/*Name: Roee Aviran
ID: 316492644 */

public class Contact implements Comparable<Contact>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables
	private String fName;
	private String lName;
	private String hPhone;
	private String mPhone;

	public Contact(String fName, String lName, String hPhone, String mPhone) throws Exception {

		try {
			setfName(fName);
			setlName(lName);
			sethPhone(hPhone);
			setmPhone(mPhone);
			hasPhone();
		} catch (Exception e) {
			System.err.println(e.getMessage() + "\n");
			throw new Exception();
		}
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) throws Exception {
		if (fName == null)
			throw new Exception("First Name Cannot Be Null!");
		else if (fName.length() < 3)
			throw new Exception("First Name's Size Cannot Be Shorter Than 3!");
		fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) throws Exception {
		if (lName == null)
			throw new Exception("Last Name Cannot Be Null!");
		else if (lName.length() < 3)
			throw new Exception("Last Name's Size Cannot Be Shorter Than 3!");
		lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);
		this.lName = lName;
	}

	public String gethPhone() {
		return hPhone;
	}

	//Checks for validation of the home phone number.
	public void sethPhone(String hPhone) throws Exception {
		if (hPhone.isEmpty())
			this.hPhone = hPhone;
		else if (Pattern.matches("[0-9]{2}-?[0-9]{7}", hPhone)) {
			this.hPhone = hPhone;
		}
		else 
			throw new Exception("Invalid Home Phone Number!");
		
	}
	

	public String getmPhone() {
		return mPhone;
	}

	//Checks for validation of the mobile phone number.
	public void setmPhone(String mPhone) throws Exception {
		if (mPhone.isEmpty())
			this.mPhone = mPhone;
		else if (Pattern.matches("[0-9]{3}-?[0-9]{7}", mPhone)) {
			this.mPhone = mPhone;
		}
		else 
			throw new Exception("Invalid Mobile Phone Number!");
	}
	
	//Checks if the user inserted 1 of the two phone numbers.
	private void hasPhone() throws Exception {
		if (mPhone.isEmpty() && hPhone.isEmpty())
			throw new Exception("At Least One Kind Of Phone Number Must Be Entered!");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (!fName.equals(other.fName) || !lName.equals(other.lName))
			return false;
		return true;
	}

	@Override
	public int compareTo(Contact c) {
		if (fName.toUpperCase().compareTo(c.fName.toUpperCase()) == 0) {
			if (lName.toUpperCase().compareTo(c.lName.toUpperCase()) == 0) 
				return 0;
			return lName.toUpperCase().compareTo(c.lName.toUpperCase());
		}
		else 
			return fName.toUpperCase().compareTo(c.fName.toUpperCase());
	}


	@Override
	public String toString() {
		return "\t" + String.format("%-12s %-12s %-12s %-12s", fName, lName, hPhone, mPhone);
	}

}
