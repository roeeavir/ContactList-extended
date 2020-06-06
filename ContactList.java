import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/*Name: Roee Aviran
ID: 316492644 */

public class ContactList extends LinkedList<Contact> implements Iterable<Contact> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Comparator<Contact> comparator = new sortByName(); //Default comparator is by name.

	// Setter and getters
	public ContactList() {
		super();
	}

	public void sort() {
		Collections.sort(this, comparator);
	}

	public Comparator<Contact> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Contact> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public Iterator<Contact> iterator() {
		return new ContactIterator(this);
	}

	//A method for adding/updating a contact in the list.
	@Override
	public boolean add(Contact c) {
		if (c == null)
		{
			System.out.println("Contact Cannot Be NULL!\n");
			return false;
		}
		Collections.sort(this, new sortByName());
		int index = Collections.binarySearch(this, c, new sortByName());
		if (index >= 0) {
			super.set(index, c);
			System.out.println("Contact Updated!");
		} else {
			super.add(Math.abs(index + 1), c);
			System.out.println("Contact Added!");
		}
		sort();
		return true;
	}

	//A method for removing a contact for the list and returning said contact.
	@Override
	public Contact remove(int index) {
		if (index >= 0 && index < size())
			return super.remove(index);
		return null;
	}

	@Override
	public String toString() {
		if (size() > 0) {
			String str = "Contacts:\n";
			String ch = "";
			for (int i = 0; i < size(); i++) {
				if (comparator instanceof sortByName && !get(i).getfName().toUpperCase().substring(0, 1).equals(ch)) {
					ch = get(i).getfName().toUpperCase().substring(0, 1);
					str += ch.toUpperCase() + ":\n";
				}
				str += get(i) + "\n";
			}
			return str;
		}
		return "List is Empty";
	}

	//A method for saving the list into a file.
	public void saveToFile() {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("contacts.obj")))) {
			if (size() == 0) {
				System.out.println("List Is Empty");
			} else {
				out.writeInt(size());
				for (int i = 0; i < size(); i++) {
					out.writeObject(get(i));
				}
				System.out.printf("Contacts Saved To File: %d\n", size());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Save Exception: File contacts.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Save Exception: " + e.getMessage());
		}
	}

	//A method for loading the list out of the file into the list.
	public void loadFromFile() {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("contacts.obj")))) {
			int index = 0;
			int temp = size();
			int size = in.readInt();
			if (size == 0) {
				throw new Exception();
			} else {
				while (index < size) {
					add((Contact) in.readObject());
					index++;
				}
				System.out.printf("Contacts Loaded From File: Added: %d Updated: %d\n", size() - temp,
						size - (size() - temp));
			}
		} catch (IOException e) {
			System.out.println("File Is Empty.");
		} catch (ClassNotFoundException e) {
			System.out.println("Load Exception: File contacts.obj Contains Something That Is Not A Contact Data!");
		} catch (Exception e) {
			System.out.println("Load Exception: " + e.getMessage());
		}
	}

	//A method that returns a contact.
	public Contact get(int index) {
		if (index >= 0 && size() > 0 && size() > index)
			return super.get(index);
		System.out.println("Contact Not Found!");
		return null;
	}
}
