import java.util.ArrayList;
import java.util.List;


public class TagResult {

	private int total;
	private List<Tags> tags = new ArrayList<Tags>();
	
	public List<Tags> getTags() {
		return tags;
	}
	
	public int getTotal() {
		return total;
	}
}
