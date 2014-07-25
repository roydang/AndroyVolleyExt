package kr.androy.volleyext.base.util.display;

import kr.androy.volleyext.base.AndroyApplication;
import android.content.Context;
import android.graphics.Point;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public final class DisplayUtility {
    private static final String TAG = DisplayUtility.class.getName();
	
	public static final int LIMIT_MINIMUM_Y = 50;
	public static final int LIMIT_MAXIMUM_Y = 150;

	private static int limitMiniumYPixel = Integer.MIN_VALUE;
	private static int limitMaximumYPixel = Integer.MIN_VALUE;
	
	public static final String RES_HDPI = "hdpi";
	public static final String RES_XHDPI = "xhdpi";
	
	public static String getScreenDPI() {
		float density = AndroyApplication.getApplication().getResources().getDisplayMetrics().density;
		if(density > DisplayMetrics.DENSITY_HIGH) {
			return RES_XHDPI;
		} else {
			return RES_HDPI;
		}
	}
	public static String getDensityName(Context context) {
	    float density = context.getResources().getDisplayMetrics().density;
	    if (density >= 4.0) {
	        return "xxxhdpi";
	    }
	    if (density >= 3.0) {
	        return "xxhdpi";
	    }
	    if (density >= 2.0) {
	        return "xhdpi";
	    }
	    if (density >= 1.5) {
	        return "hdpi";
	    }
	    if (density >= 1.0) {
	        return "mdpi";
	    }
	    return "ldpi";
	}
	public static float getDPFromPixel(float pixel) {
		return getDPFromPixel(AndroyApplication.getApplication().getResources().getDisplayMetrics().densityDpi, pixel);
	}

	public static float getDPFromPixel(int densityDpi, float pixel) {
		return (float) (pixel * (160.0 / densityDpi));
	}

	public static float getPixelFromDP(float dp) {
		if (AndroyApplication.getApplication() == null) {
			return 0;
		}
		return getPixelFromDP(AndroyApplication.getApplication().getResources().getDisplayMetrics().densityDpi, dp);
	}
	
	public static float getPixelFromDP(int densityDpi, float dp) {
		return (float) (dp * (densityDpi / 160.0));
	}

    // added
    public static float getPixelFromDpUnit(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
    }

    private static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) AndroyApplication.getApplication().getSystemService("window")).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

	public static int getLimitMiniumYPixel() {
		if (limitMiniumYPixel < 0) {
			limitMiniumYPixel = (int)getPixelFromDP(LIMIT_MINIMUM_Y);
		}

		return limitMiniumYPixel;
	}

	public static int getLimitMaximumYPixel() {
		if (limitMaximumYPixel < 0) {
			limitMaximumYPixel = (int)getPixelFromDP(LIMIT_MAXIMUM_Y);
		}

		return limitMaximumYPixel;
	}
	
	/**
	 * getDisplaySize(720, 1184) :  VEGA R3
	 * getDisplaySize(720, 1280) : Galaxy Note2
	 * getDisplaySize(800, 1280) : Galaxy Note
	 * @return
	 */
	private static Point displaySize = null;
	public static Point getDisplaySize() {
		if (displaySize != null) {
			return displaySize;
		}
		
		try {
			DisplayMetrics metrics = new DisplayMetrics();
			WindowManager wm = (WindowManager) AndroyApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
			wm.getDefaultDisplay().getMetrics(metrics);
			
			displaySize = new Point();
			displaySize.x = metrics.widthPixels;
			displaySize.y = (int) (metrics.heightPixels - getPixelFromDP(25)); // status bar 영역 제거
			
			return displaySize;
		} catch (Exception e) {
			
		}
		
		return new Point(480, 800);
	}
	
	/**
	 * 화면 인자 timeout 시간동안 켰다가 다시 끄기
	 * @param context
	 * @param timeout
	 */
	public static void turnScreenOn(Context context, long timeout) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "POKETROKET_WAKEUP"); 
		try {
			wl.acquire(timeout);  //create the wake lock and release the lock after the give timeout in milliseconds.
		} catch (Exception e) {
			e.printStackTrace();
			wl.release();
		}
	}
	
}
