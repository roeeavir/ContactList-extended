import java.util.Collections;
import java.util.Scanner;

/*Name: Roee Aviran
ID: 316492644 */

public class Main {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		ContactList cList = new ContactList();
		try {
			loadMenu(cList, s);
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println("Exiting...");
		s.close();

	}

	//Menu.
	public static void loadMenu(ContactList cList, Scanner s) throws Exception {
		boolean isFinished = false;
		while (!isFinished) {
			try {
				System.out.println("- - - - - - - - - - - - - - -\r\n" + "- - - Contact Creator : - - -\r\n"
						+ "- - - - - - - - - - - - - - -\r\n" + "1. Add/Update Contact\r\n" + "2. Remove Contact\r\n"
						+ "3. Save Contacts To File\r\n" + "4. Load Contacts From File\r\n" + "5. Sort Contacts\r\n"
						+ "6. Search Contact\r\n" + "7. Print All Contacts\r\n" + "- - - - - - - - - - - - - - -\r\n"
						+ "Choose your option or any other key to EXIT.\r\n" + "Your Option:");

				char i = s.next().charAt(0);
				s.nextLine();
				switch (i) {
				case '1':
					System.out.println("ADD/UPDATE CONTACT:");
					add(cList, s);
					break;
				case '2':
					System.out.println("REMOVE CONTACT:");
					remove(cList, s);
					break;
				case '3':
					cList.saveToFile();
					break;
				case '4':
					cList.loadFromFile();
					break;
				case '5':
					System.out.println("SORT CONTACTS:");
					sort(cList, s);
					break;
				case '6':
					System.out.println("SEARCH CONTACT:");
					search(cList, s);
					break;
				case '7':
					System.out.println(cList);
					break;

				default:
					System.out.println("No option was chosen\n");
					isFinished = true;
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	public static String getFirstName(Scanner s) {
		System.out.println("Enter First Name:");
		return s.nextLine();
	}

	public static String getLastName(Scanner s) {
		System.out.println("Enter Last Name:");
		return s.nextLine();
	}

	public static String getHomePhone(Scanner s) {
		System.out.println("Enter Home Phone:");
		return s.nextLine();
	}

	public static String getMobilePhone(Scanner s) {
		System.out.println("Enter Mobile Phone:");
		return s.nextLine();
	}

	//Adds/Updates a contact in the contact list.
	public static void add(ContactList cList, Scanner s) throws Exception {
		cList.add(new Contact(getFirstName(s), getLastName(s), getHomePhone(s), getMobilePhone(s)));
	}

	//Removes a contact in the contact list, By name or phone numbers.
	public static void remove(ContactList cList, Scanner s) throws Exception {
		char op = option(s);
		Contact c = null;
		int index = -1;
		switch (op) {// option for removal.
		case '1':
			c = new Contact(getFirstName(s), getLastName(s), "123123123", "");
			Collections.sort(cList, new sortByName());
			index = Collections.binarySearch(cList, c, new sortByName());
			c = cList.get(index);
			break;
		case '2':
			c = new Contact("...", "...", getHomePhone(s), "");
			Collections.sort(cList, new sortByHomeNumber());
			index = Collections.binarySearch(cList, c, new sortByHomeNumber());
			c = cList.get(index);
			break;
		case '3':
			c = new Contact("...", "...", "", getMobilePhone(s));
			Collections.sort(cList, new sortByMobileNumber());
			index = Collections.binarySearch(cList, c, new sortByMobileNumber());
			c = cList.get(index);
			break;
		default:
			break;
		}
		if (c != null) {
			System.out.println("Result:" + c);
			System.out.println("Are You Sure? y/n");
			op = s.nextLine().charAt(0);
			if (op == 'y')
			{
				cList.remove(index);
				System.out.println("Contact Removed!");
			}
			else
				System.out.println("Operation Canceled!");
		} else
			System.out.println("Contact Not Found!");
		cList.sort();
	}

	//Changes the sorting method of the contact list (name, home phone number, mobile phone number).
	public static void sort(ContactList cList, Scanner s) {
		char op = option(s);
		switch (op) {// option for sorting.
		case '1':
			cList.setComparator(new sortByName());
			break;
		case '2':
			cList.setComparator(new sortByHomeNumber());
			break;
		case '3':
			cList.setComparator(new sortByMobileNumber());
			break;
		default:
			break;
		}
		cList.sort();
		System.out.println(cList);
	}

	//Searching for a contact by name or phone numbers.
	public static void search(ContactList cList, Scanner s) throws Exception {
		char op = option(s);
		Contact c = null;
		int index = -1;
		switch (op) {// option for searching.
		case '1':
			c = new Contact(getFirstName(s), getLastName(s), "123123123", "");
			Collections.sort(cList, new sortByName());
			index = Collections.binarySearch(cList, c, new sortByName());
			c = cList.get(index);
			break;
		case '2':
			c = new Contact("...", "...", getHomePhone(s), "");
			Collections.sort(cList, new sortByHomeNumber());
			index = Collections.binarySearch(cList, c, new sortByHomeNumber());
			c = cList.get(index);
			break;
		case '3':
			c = new Contact("...", "...", "", getMobilePhone(s));
			Collections.sort(cList, new sortByMobileNumber());
			index = Collections.binarySearch(cList, c, new sortByMobileNumber());
			c = cList.get(index);
			break;
		default:
			break;
		}
		if (c != null)
			System.out.println(c);
	}

	//Prints options for the user to choose for.
	public static char option(Scanner s) {
		System.out.println("1. By Name\r\n" + "2. By Home\r\n" + "3. By Mobile\r\n" + "Your Option: ");
		return s.nextLine().charAt(0);
	}
}
