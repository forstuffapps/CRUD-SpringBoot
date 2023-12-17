package q6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HitCounterConfig {

	@Bean
	public HitCounter get_Hit_Counter() 
	{
		return new HitCounter();
	}
}
