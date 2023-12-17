package q6;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.eq;

@WebMvcTest(UserController.class)
public class User_Controller_Tests 
{

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository User_DB;
	
	@MockBean
	private UserCache userCache;
	
	@MockBean
	private HitCounter hitCounter;
	
	@Test
	public void CreateUserTest() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().is(200))
		.andExpect(view().name("home"))
		.andExpect(model().attribute("user", new User()));
	}
	
	@Test
	public void createUserPostTest() throws Exception {
		User expectedUser = new User(1, "vishalreddy12x@gmail.com", "vishal", 23, "Student"); 
		mockMvc.perform(post("/createuser")
				.param("id", "1").param("name", "vishal").param("email", "vishalreddy12x@gmail.com")
				.param("age", "23").param("occupation", "Student"))
		.andExpect(status().is(200))
		.andExpect(view().name("saved"))
		.andExpect(model().attribute("user", expectedUser));
		
	}
	
	@Test
	public void createUserPostTest_invalidUser() throws Exception {
		User expectedUser = new User(1, "vishalreddy12x@gmail.com", "vishal", 15, "Student"); 
		mockMvc.perform(post("/createuser")
				.param("id", "1").param("name", "vishal").param("email", "vishalreddy12x@gmail.com")
				.param("age", "15").param("occupation", "Student"))
		.andExpect(status().is(200))
		.andExpect(view().name("home"))
		.andExpect(model().attribute("user", expectedUser));
		
		Mockito.verifyNoInteractions(userCache);
	}
	
	@Test
	public void listUserTest() throws Exception {
		List<User> expectedList = new ArrayList<>();
		expectedList.add(new User(1, "vishalreddy12x@gmail.com", "vishal", 23, "Student"));
		mockMvc.perform(post("/createuser")
				.param("id", "1").param("name", "vishal").param("email", "vishalreddy12x@gmail.com")
				.param("age", "15").param("occupation", "Student"));
		Mockito.when(userCache.getAllUsers()).thenReturn(expectedList);
		
		
		mockMvc.perform(get("/allusers"))
		.andExpect(status().is(200))
		.andExpect(view().name("view"));
	}
}
