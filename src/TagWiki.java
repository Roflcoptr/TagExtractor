import java.util.ArrayList;
import java.util.List;


public class TagWiki {

	private List<Wiki> tag_wikis = new ArrayList<Wiki>();
	
	public boolean hasWiki() {
		return tag_wikis.size() != 0;
	}
}
