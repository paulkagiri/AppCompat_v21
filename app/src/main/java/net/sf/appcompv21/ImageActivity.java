package net.sf.appcompv21;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class ImageActivity extends ActionBarActivity {

    private static final String TAG = ImageActivity.class.getName();

    private Toolbar toolbar;

    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTransition();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);
        view = (ImageView) findViewById(R.id.image);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initTransition() {
        getWindow().setEnterTransition(null);
        getWindow().setSharedElementEnterTransition(ClippingActivity.buildTransitionSet());
        getWindow().setAllowEnterTransitionOverlap(true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
