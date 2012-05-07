package li.smueller.code.stackexchange.model;

import com.google.gson.annotations.SerializedName;

public class Tags {

	@SerializedName("name")
	private String name;
	
	@SerializedName("count")
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