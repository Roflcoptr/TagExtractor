package li.smueller.code.stackexchange.model;

import com.google.gson.annotations.SerializedName;

public class Wiki {

	@SerializedName("wiki_excerpt")
	private String excerpt;
	
	public String getWikiExcerpt() {
		return excerpt;
	}
	
}