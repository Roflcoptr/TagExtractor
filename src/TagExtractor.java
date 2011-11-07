import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

public class TagExtractor {

	private static int totalCount;
	
	public static void checkPage(String url) {

		HttpPost postRequest = new HttpPost(url);
		postRequest.setHeader("Accept-Encoding", "GZIP");
				
		HttpClient client = new DefaultHttpClient();
		
		try {
			HttpResponse resp = client.execute(postRequest);
			HttpEntity entity = resp.getEntity();
			
			if (entity != null){
				InputStream is = entity.getContent();
				String response = convertStreamToString(new GZIPInputStream(is));
				//System.out.println(response);
				is.close();
				
				Gson gson = new Gson();
				gson.toJson(response);
				
				TagResult total = gson.fromJson(response, TagResult.class);
				totalCount = total.getTotal();
				
				List<Tags> result = total.getTags();
				for (Tags tag : result) {
					checkTag(tag);
				}
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
		Date start = new Date();
		String checkURL = "http://api.travel.stackexchange.com/1.1/tags?page=";
		System.out.println("These are the tags with no wiki excerpt:");
		for (int i = 1; i < 10; i++) {
			String checkURL1 = checkURL + i + "&pagesize=100";
			checkPage(checkURL1);
		}
		Date end = new Date();
		System.out.println("Checked " + totalCount + " tags in " + ((end.getTime() - start.getTime()) / 1000) + " seconds.");
	
	}
	
	private static void checkTag(Tags tag) {
		
		String tagURL = "http://api.travel.stackexchange.com/1.1/tags/" + tag.getName() + "/wikis";
		
		HttpPost postRequest = new HttpPost(tagURL);
		postRequest.setHeader("Accept-Encoding", "GZIP");
				
		HttpClient client = new DefaultHttpClient();
		
		try {
			HttpResponse resp = client.execute(postRequest);
			HttpEntity entity = resp.getEntity();
			
			if (entity != null){
				InputStream is = entity.getContent();
				String response = convertStreamToString(new GZIPInputStream(is));
				//System.out.println(response);
				is.close();
				
				Gson gson = new Gson();
				gson.toJson(response);
				
				TagWiki result = gson.fromJson(response, TagWiki.class);
				
				if (!result.hasWiki()) {
					System.out.println(tag.toString());
				}
			}
		} catch (Exception e) {
			
		}
				
	}

	public static String convertStreamToString(GZIPInputStream is) throws IOException  {
		if (is != null) {
			Reader decoder = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(decoder);
			
			String result = "";
			String buffer;
			while((buffer = bufferedReader.readLine())!= null) {
				result = result + buffer;
			}
			return result;
		}
		return null;
	}
}
