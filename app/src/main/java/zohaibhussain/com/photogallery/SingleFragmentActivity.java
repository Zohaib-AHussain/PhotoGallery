package zohaibhussain.com.photogallery;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by zohaibhussain on 2015-12-15.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment==null) {
            fm.beginTransaction()
                    .add(R.id.fragment_container, createFragment())
                    .commit();
        }
    }

    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResID(){
        return R.layout.activity_fragment;
    }
}
