package zohaibhussain.com.photogallery;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by zohaibhussain on 2016-03-15.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Received results: " + intent.getAction());
        if (getResultCode() != Activity.RESULT_OK)
            return; //Broadcase was cancelled

        int requestCode = intent.getIntExtra(PollService.REQUEST_CODE,0);
        Notification notification = intent.getParcelableExtra(PollService.NOTIFICATION);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(requestCode, notification);
    }
}
