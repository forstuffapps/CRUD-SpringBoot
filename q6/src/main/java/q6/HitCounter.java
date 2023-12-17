package q6;


import lombok.Getter;

public class HitCounter 
{

	@Getter
	private Integer count;
	
	public HitCounter() 
	{
		this.count = 0;
	}
	
	public void add_Page_Hit() {
		this.count++;
	}
}
