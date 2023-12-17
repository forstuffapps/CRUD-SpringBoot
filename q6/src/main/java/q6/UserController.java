package q6;



import java.util.List;
import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController 
{
	
	@Autowired
	private UserRepository User_DB;
	
	@Autowired
	private HitCounter hitCounter;

	@GetMapping("/")
	public String userForm(Model model) {
		hitCounter.add_Page_Hit();
		model.addAttribute("user", new User());
		model.addAttribute("pagecount", hitCounter.getCount());
		return "home";
	}
	
	@PostMapping("/createuser")
	public String createUser(@Validated User toSave, BindingResult bindingResult) {
		hitCounter.add_Page_Hit();
		if(bindingResult.hasErrors()) {
			return "home";
		} 
		else 
		{
			
			this.User_DB.save(toSave);
			System.out.println(this.User_DB.findAll());
			System.out.println("inside the else case");
			return "saved";
		}
	}
	
	
	@GetMapping("/allusers")
	public String listAllUsers(Model model) {
		hitCounter.add_Page_Hit();
		model.addAttribute("pagecount", hitCounter.getCount());
		model.addAttribute("users", User_DB.findAll());
		return "view";
	}
	
	
	@GetMapping("/sortage")
	public String listAllUsersbyAge(Model model) {
		//hitCounter.add_Page_Hit();
		List<User> temp = (List<User>) User_DB.findAll();
		//temp = [User(1, "vishalreddy12x@gmail.com", "q1", 41), User(2, "qwe@gmail.com", "q2", 18)]
		/*temp = Arrays.asList(
	            new User(1, "vishalreddy12x@gmail.com", "q1", 41), 
	            new User(2, "qwe@gmail.com", "q2", 18));
		*/
		temp = temp.stream()
		.sorted((o1, o2) -> o1.getAge() - o2.getAge())
		.collect(Collectors.toList());
		System.out.print("printing the temp data : ");
		System.out.println(temp);
		model.addAttribute("users", temp);
		return "view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteuserbyid(@PathVariable Long id, Model model) {
		//hitCounter.add_Page_Hit();
		System.out.print("id is : ");
		System.out.println(id);
		System.out.println(this.User_DB.findAll());
		User_DB.deleteById(id);
		List<User> temp = (List<User>) User_DB.findAll();
		System.out.print("After deleting : ");
		System.out.println(temp);
		model.addAttribute("users", temp);
		return "view";
	}
	
	
	
	
	@GetMapping("/getHitCount")
	public ResponseEntity<Integer> getHitCount() {
		return ResponseEntity.ok(hitCounter.getCount());
	}
}
