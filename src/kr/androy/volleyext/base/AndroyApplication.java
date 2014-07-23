package kr.androy.volleyext.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public abstract class AndroyApplication extends Application {
	
	private static AndroyApplication _internalInstance;
	public static String BACKGROUND_HANDLER_THREAD = "AndroyBackgroundHandlerThread";
	
	private ExecutorService workExecutor;
	private Handler uiHandler;
	private Handler backgroundHandler;
	private HandlerThread backgroundHandlerThread;
	
	private static RequestQueue requestQueue;
	private static ImageLoader imageLoader;
	
	@Override
	public void onCreate() {
		_internalInstance = this;
		
		//http://code.google.com/p/android/issues/detail?id=20915
		try {
			Class.forName("android.os.AsyncTask");
		} catch (ClassNotFoundException e) {
		}
		
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false");
		}
		super.onCreate();
		
		init();
		volleyInit();
	}
	private void init() {
		workExecutor = Executors.newCachedThreadPool();
		uiHandler = new Handler(Looper.getMainLooper());
		
		backgroundHandlerThread = new HandlerThread(BACKGROUND_HANDLER_THREAD);
		backgroundHandlerThread.start();
		
		backgroundHandler = new Handler(backgroundHandlerThread.getLooper());
	}
	public static AndroyApplication getApplication() {
		return _internalInstance;
	}
	public static Handler getUiHandler() {
		return getApplication().uiHandler;
	}
	public static Handler getBackgroundHandler() {
		return getApplication().backgroundHandler;
	}	

	public void addWorker(Runnable runnable) {
		if (runnable == null) {
			return;
		}
		workExecutor.execute(runnable);
	}
	public void volleyInit() {
		Cache diskCache = getDefaultDiskCache(_internalInstance);
		ImageCache memoryCache = getDefaultImageCache(_internalInstance);
		requestQueue = new RequestQueue(diskCache, getDefaultNetwork());
		imageLoader = new ImageLoader(requestQueue, memoryCache);
		requestQueue.start();
	}
	public static RequestQueue getRequestQueue() {
		if (requestQueue == null)
			throw new IllegalStateException("RequestQueue is not initialized.");
		return requestQueue;
	}

	public static ImageLoader getImageLoader() {
		if (imageLoader == null)
			throw new IllegalStateException("ImageLoader is not initialized.");
		return imageLoader;
	}
	protected abstract ImageCache getDefaultImageCache(Context context);

	protected abstract Cache getDefaultDiskCache(Context context);
	
	protected abstract Network getDefaultNetwork();
	
	protected abstract String getUserAgent();
}
