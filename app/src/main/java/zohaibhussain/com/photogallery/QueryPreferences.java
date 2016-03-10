package zohaibhussain.com.photogallery;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by zohaibhussain on 2016-02-29.
 */
public class QueryPreferences {
    private static final String PREF_QUERY_SEARCH = "search-query";
    private static final String PREF_LAST_RESULT_ID = "lastResultId";

    public static String getStoredQuery(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_QUERY_SEARCH, null);
    }

    public static void setStoredQuery(Context context, String query){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_QUERY_SEARCH, query)
                .apply();
    }

    public static String getLastResultID(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setLastResultID(Context context, String lastResultID){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_LAST_RESULT_ID, lastResultID)
                .apply();
    }
}
