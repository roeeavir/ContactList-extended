import java.util.Comparator;

public class sortByName implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.compareTo(c2);
	}

}
