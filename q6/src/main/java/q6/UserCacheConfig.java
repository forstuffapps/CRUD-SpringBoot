package q6;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCacheConfig 
{

	@Bean
	public UserCache getUserCache() 
	{
		return new UserCache();
	}
}

