package Classes;

import java.util.Comparator;

import People.User;

public class NameSorter implements Comparator<User>{
	@Override
	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		if( o1.getName().compareTo(o2.getName()) == 0) {
			return o1.getLastName().compareTo(o2.getLastName());
		}
		else return o1.getName().compareTo(o2.getName());
	}

}
