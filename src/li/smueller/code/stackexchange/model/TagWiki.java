package li.smueller.code.stackexchange.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TagWiki {

	@SerializedName("tag_wikis")
	private List<Wiki> wikis = new ArrayList<Wiki>();
	
	public boolean hasWiki() {
		return wikis.size() != 0;
	}
	
}