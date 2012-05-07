package li.smueller.code.stackexchange.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TagResult {

	@SerializedName("total")
	private int total;
	
	@SerializedName("tags")
	private List<Tags> tags = new ArrayList<Tags>();
	
	public List<Tags> getTags() {
		return tags;
	}
	
	public int getTotal() {
		return total;
	}

}