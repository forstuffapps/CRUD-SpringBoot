package q6;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> 
{


	public void deleteById(Long id);
	
	public Optional<User> findById(Long id);
	

	
}
