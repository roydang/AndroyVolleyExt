package kr.androy.volleyext.adpter;

import kr.androy.volleyext.base.util.log.Logger;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.android.volley.RequestQueue;

public class VExtOnScrollListener implements OnScrollListener {

	private static Logger logger = Logger.getLogger(VExtOnScrollListener.class);
	
	private RequestQueue requestQueue;
	private final boolean pauseOnScroll;
	private final boolean pauseOnFling;
	
	public VExtOnScrollListener(RequestQueue requestQueue, boolean pauseOnScroll, boolean pauseOnFling) {
		this.requestQueue = requestQueue;
		this.pauseOnScroll = pauseOnScroll;
		this.pauseOnFling = pauseOnFling;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		logger.d("# onScrollStateChanged:%s", scrollState);
		

		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
		    requestQueue.start();
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			if (pauseOnScroll) {
				requestQueue.stop();
			}
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			if (pauseOnFling) {
				 requestQueue.stop();
			}
			break;
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// not implement
		
	}


}
