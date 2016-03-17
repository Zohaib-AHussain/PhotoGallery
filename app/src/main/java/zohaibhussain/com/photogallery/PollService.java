package zohaibhussain.com.photogallery;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.List;

/**
 * Created by zohaibhussain on 2016-03-06.
 */
public class PollService extends IntentService {

    private static final long POLL_INTERVAL = AlarmManager.INTERVAL_FIFTEEN_MINUTES; //60 seconds
    private static final String TAG = "POLL_SERVICE";
    public static final String ACTION_SHOW_NOTIFICATION = "zohaibhussain.com.photogallery.show_notification";
    public static final String CUSTOM_PERMISSION = "zohaibhussain.com.photogallery.permission.custom";
    public static final String REQUEST_CODE = "REQUEST_CODE";
    public static final String NOTIFICATION = "NOTIFICATION";

    public PollService() {
        super(TAG);
    }

    public static Intent newIntent(Context context){
        return new Intent(context, PollService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (!isNetworkAvailableAndConnected())
            return;

        String query = QueryPreferences.getStoredQuery(this);
        String lastResultID = QueryPreferences.getLastResultID(this);
        List<GalleryItem> items;

        if (query == null)
            items = new FlickrFetchr().fetchRecentPhotos();
        else
            items = new FlickrFetchr().searchPhotos(query);

        if (items.size() == 0)
            return;

        String resultID = items.get(0).getID();
        if (resultID.equals(lastResultID))
            Log.i(TAG, "Got an old result: " + resultID);
        else {
            Log.i(TAG, "Got a new result: " + resultID);

            Resources resources = getResources();
            Intent i = PhotoGalleryActivity.newIntent(this);
            PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
            Notification notification = new NotificationCompat.Builder(this)
                    .setTicker(resources.getString(R.string.new_pictures_title))
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle(resources.getString(R.string.new_pictures_title))
                    .setContentText(resources.getString(R.string.new_pictures_text))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

            /*NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, notification);

            sendBroadcast(new Intent(ACTION_SHOW_NOTIFICATION), CUSTOM_PERMISSION);*/

            showBackgroundNotification(0, notification);
        }
        QueryPreferences.setLastResultID(this, resultID);
    }

    private void showBackgroundNotification(int requestCode, Notification notification){
        Intent i = new Intent(ACTION_SHOW_NOTIFICATION);
        i.putExtra(REQUEST_CODE, requestCode);
        i.putExtra(NOTIFICATION, notification);
        sendOrderedBroadcast(i,     CUSTOM_PERMISSION, null, null, Activity.RESULT_OK, null, null);
    }


    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();
        return  isNetworkConnected;
    }

    public static void setServiceAlarm(Context context, boolean isOn){

        Intent i = PollService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (isOn)
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), POLL_INTERVAL, pi);
        else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
        QueryPreferences.setAlarmOn(context, isOn);
    }

    public static boolean isServiceAlarmOn(Context context){
        Intent i = PollService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }



}
