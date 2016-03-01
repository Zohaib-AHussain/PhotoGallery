package zohaibhussain.com.photogallery;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by zohaibhussain on 2016-02-29.
 */
public class QueryPreferences {
    private static final String PREF_QUERY_SEARCH = "search-query";

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
}
