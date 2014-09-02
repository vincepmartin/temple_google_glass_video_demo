package org.temple.glassuielements;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

public class AppService extends Service {
    private static final String TAG = "AppService";
    private static final String LIVE_CARD_ID = "HelloGlass";
    
    private AppDrawer mCallback;
    private LiveCard mLiveCard;

    @Override
    public void onCreate() {
    	Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
    	Log.e(TAG, "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	createGlassStyledCard(); 
    	return START_STICKY; 
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "OnDestroy()");
    	
        if (mLiveCard != null && mLiveCard.isPublished()) {
            Log.e(TAG, "OnDestroy: true");
            if (mCallback != null) {
                mLiveCard.getSurfaceHolder().removeCallback(mCallback);
            }
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        else {
            Log.e(TAG, "OnDestroy: false");
        }
        super.onDestroy();
    }

    private void createGlassStyledCard() {
    	Intent intent = new Intent( this, GlassStyledCardActivity.class); 
    
    	intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK); 
    	startActivity( intent);

    }
   
}