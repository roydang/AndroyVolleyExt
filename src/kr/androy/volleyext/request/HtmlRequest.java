package kr.androy.volleyext.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.navercorp.volleyextensions.request.AbstractConverterRequest;

public class HtmlRequest<T> extends AbstractConverterRequest<T> {

	public HtmlRequest(String url, Class<T> clazz, Listener<T> listener) {
		super(url, clazz, listener);
	}
	public HtmlRequest(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(url, clazz, listener, errorListener);
	}
	public HtmlRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, clazz, listener, errorListener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {

		return null;
	}

}
