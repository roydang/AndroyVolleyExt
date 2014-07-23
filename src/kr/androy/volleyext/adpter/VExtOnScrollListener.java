package kr.androy.volleyext.adpter;

import com.android.volley.RequestQueue;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class VExtOnScrollListener implements OnScrollListener {

	private RequestQueue requestQueue;
	public VExtOnScrollListener(RequestQueue requestQueue) {
		this.requestQueue = requestQueue;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		  if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
			  requestQueue.stop();
	      } else {
	          requestQueue.start();
	      }
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

}
