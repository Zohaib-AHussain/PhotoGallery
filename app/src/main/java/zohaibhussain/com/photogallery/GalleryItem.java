package zohaibhussain.com.photogallery;

import android.net.Uri;

/**
 * Created by zohaibhussain on 2016-02-04.
 */
public class GalleryItem {

    private String mCaption;
    private String mID;
    private String mUrl;
    private Uri mUri;

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

}
