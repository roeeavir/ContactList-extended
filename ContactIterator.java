import java.util.Iterator;

public class ContactIterator implements Iterator<Contact>{

	private ContactList list;
	private int index;
	
	//Constructor
	public ContactIterator(ContactList cList) {
		this.list = cList;
		this.index = -1;
	}

	//Setters and getters
	public ContactList getList() {
		return list;
	}

	public void setList(ContactList list) {
		this.list = list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	//Implemented methods in Iterator interface.
	@Override
	public boolean hasNext() {
		return (index + 1) < list.size();
	}
	
	@Override
	public Contact next() {
		if (hasNext())
			return list.get(++index);
		return null;
	}
	
	public void remove() {
		if(index>=0 && index<list.size())
			list.remove(index);
	}

}
