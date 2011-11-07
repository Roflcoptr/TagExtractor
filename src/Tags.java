
public class Tags {

	private String name;
	private int count;
	
	@Override
	public String toString() {
		return name + " (" + count + ")"; 
	}
	
	public int getCount() {
		return count;
	}

	public String getName() {
		return name;
	}
}
