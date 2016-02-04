package zohaibhussain.com.photogallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoGalleryFragment extends Fragment {

    private static final int NUM_OF_COLUMNS = 3;

    @Bind(R.id.fragment_photo_gallery_recycler_view)
    protected RecyclerView mPhotoRecyclerView;

    public PhotoGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        ButterKnife.bind(this,v);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUM_OF_COLUMNS));
        return v;
    }

    public static Fragment newInstance() {
        return new PhotoGalleryFragment();
    }
}
