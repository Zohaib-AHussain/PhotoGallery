package zohaibhussain.com.photogallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zohaibhussain on 2016-03-15.
 */
public abstract class VisibleFragment extends Fragment {
    private static final String TAG = "VisibleFragment";
    public static final String CUSTOM_PERMISSION = "zohaibhussain.com.photogallery.permission.custom";


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(PollService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mOnShowNotification, intentFilter, CUSTOM_PERMISSION, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mOnShowNotification);
    }

    private BroadcastReceiver mOnShowNotification = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Cancelling notification");
            setResultCode(Activity.RESULT_CANCELED);
        }
    };
}
