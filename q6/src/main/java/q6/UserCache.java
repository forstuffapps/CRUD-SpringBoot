package q6;

import java.util.ArrayList;
import java.util.List;

public class UserCache 
{

	private List<User> users_list;
	
	public UserCache() 
	{
		users_list = new ArrayList<>();
	}
	
	public void storeUser(User u) 
	{
		this.users_list.add(u);
	}
	
	public List<User> getAllUsers() 
	{
		return this.users_list;
	}
}
