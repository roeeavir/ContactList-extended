import java.util.Comparator;

public class sortByHomeNumber implements Comparator<Contact>{

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.gethPhone().compareTo(c2.gethPhone());
	}
	
}
