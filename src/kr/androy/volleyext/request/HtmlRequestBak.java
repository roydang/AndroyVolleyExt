package kr.androy.volleyext.request;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.navercorp.volleyextensions.request.AbstractConverterRequest;

public class HtmlRequestBak<T> extends AbstractConverterRequest<T> {

	
	
	public HtmlRequestBak(String url, Class<T> clazz, Listener<T> listener) {
		super(url, clazz, listener);
	}
	public HtmlRequestBak(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(url, clazz, listener, errorListener);
	}
	public HtmlRequestBak(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, clazz, listener, errorListener);
	}
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		
		String hrefPrefix = "/Images/Thumbnails/";
		String rootClassName = "gallery-item-group exitemrepeater";
		
		
		try {
			Source source = new Source(getBodyString(response));		
			
			List<Element> list = source.getAllElementsByClass(rootClassName);		
      
            for (int i = 0; i < list.size(); i++) {
            	Element element = list.get(i);            
            	System.out.println("Ele=>" + element);
            	
//            	List<Element> srcList = element.getAllElements("src",Pattern.compile("/Images/Thumbnails/[0-9]+/[0-9]+.jpg"));
//            	for (Element srcEle:srcList) {
//            		System.out.println("Ele srcEle=>" + srcEle);
//            	}
            	
            	List<Element> aList = element.getAllElements(HTMLElementName.A);
            	
            	for (Element aEle:aList) {
            		List<Element> childEle = aEle.getChildElements();
            		
            		if (childEle.size() > 0) {
            			Element imgEle = childEle.get(0);  
            			String srcStr = imgEle.getAttributeValue("src");
            			System.out.print("imgSrc=>" + srcStr);
            		} else {
            			TextExtractor ext = aEle.getTextExtractor();
            			System.out.println("ext=>" + ext);
            		}

            		
            	}
            }
			
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
		return null;
	}

//	@Override
//	protected Response<T> parseNetworkResponse(NetworkResponse response) {

//		String charset = HttpHeaderParser.parseCharset(response.headers);
//		Reader reader = null;
//		try {
//			reader = new InputStreamReader(new ByteArrayInputStream(response.data), charset);
//			T result = persister.read(getTargetClass(),	reader);
//			return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
//			
//		} catch (UnsupportedEncodingException e) {
//			return Response.error(new ParseError(e));
//		} catch (XmlPullParserException e) {
//			return Response.error(new ParseError(e));
//		} catch (ElementException e) {
//			return Response.error(new ParseError(e));
//		} catch (Exception e) {
//			return Response.error(new VolleyError(e));
//		} finally {
//			IoUtils.closeQuietly(reader);
//		}
//	}

}
