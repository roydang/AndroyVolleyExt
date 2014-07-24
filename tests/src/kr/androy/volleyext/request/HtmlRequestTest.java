package kr.androy.volleyext.request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.android.volley.NetworkResponse;
import com.navercorp.volleyextensions.mock.ErrorResponseHoldListener;
import com.navercorp.volleyextensions.mock.ResponseHoldListener;
import com.navercorp.volleyextensions.request.SimpleXmlRequest;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class HtmlRequestTest {
	
	String url = "http://test";
	ResponseHoldListener<News> listener = new ResponseHoldListener<News>();
	ErrorResponseHoldListener errorListener = new ErrorResponseHoldListener();
	
	@Test
	public void testParseNetworkResponse() throws Exception {
		// Given
//		String content =
//		"<news>\n" + 
//		 "   <imageUrl>http://static.naver.com/volley-ext.jpg</imageUrl>\n" + 
//		 "   <title>Volley extention has released</title>\n" + 
//		 "</news>";
		File file = new File(".");
		System.out.println("File Path=" + file.getAbsolutePath());
		FileReader fr = new FileReader(new File("./test_res/gettyimagesgallery.html"));
		BufferedReader br = new BufferedReader(fr);
		
		StringBuilder sb = new StringBuilder();
		String tmp = null;
		while((tmp=br.readLine())!=null) {
			sb.append(tmp);
		}
		
		
//		HtmlRequestBakBak<News> request = nHtmlRequestBakestBak<News>(url, News.class,listener);
//		NetworkResponse networkResponse = new NetworkResponse(sb.toString().getBytes());
//		request.parseNetworkResponse(networkResponse);
		
		// When
//		Response<News> response = request.parseNetworkResponse(networkResponse);
//		// Then
//		News news = response.result;
//		assertThat(news.imageUrl, is("http://static.naver.com/volley-ext.jpg"));
//		assertThat(news.title, is("Volley extention has released"));
	}
	
	/** just for test */
	private static class News {
		public String imageUrl;
		public String title;		
	}
	
}
 