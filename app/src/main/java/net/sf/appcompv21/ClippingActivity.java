package net.sf.appcompv21;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

/**
 * http://android-developers.blogspot.com/2014/10/appcompat-v21-material-design-for-pre.html
 * <p/>
 * <p/>
 * AppCompat provides similar behaviour on earlier versions of Android for a subset of UI widgets:
 * Everything provided by AppCompat’s toolbar (action modes, etc)
 * EditText
 * Spinner
 * CheckBox
 * RadioButton
 * Switch (use the new android.support.v7.widget.SwitchCompat)
 * CheckedTextView
 */
public class ClippingActivity extends ActionBarActivity {

    private static final String TAG = ClippingActivity.class.getName();

    private Toolbar toolbar;

    private View view;

    private ImageView vector;

    private View heartText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        initTransition();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clipping);
        view = findViewById(R.id.clipping_img);
        heartText = findViewById(R.id.clipping_heart_text);
        vector = (ImageView) findViewById(R.id.clipping_heart);
        vector.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(ClippingActivity.this, ImageActivity.class);
//                startActivity(intent);
                //the 'sharedElementName' has to match in the called activity
//                ActivityOptions activityOptions = ActivityOptions
//                        .makeSceneTransitionAnimation(ClippingActivity.this, v, "lollipop");

                ActivityOptions activityOptions = ActivityOptions
                        .makeSceneTransitionAnimation(ClippingActivity.this,
                                                      new Pair<View, String>(v, "heart"),
                                                      new Pair<View, String>(heartText, "text"));
                startActivity(intent,
                              activityOptions.toBundle());

            }
        });

        addClipping();

//        vector = (ImageView) findViewById(R.id.clipping_heart);
//        Drawable drawable = vector.getDrawable();
//        Log.d(TAG, drawable.getClass().getName());
//        if (drawable instanceof Animatable) {
//            ((Animatable)drawable).start();
//        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initTransition() {
//        TransitionSet set = buildTransitionSet();
//        getWindow().setSharedElementExitTransition(set);
        getWindow().setExitTransition(null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static TransitionSet buildTransitionSet() {
        TransitionSet set = new TransitionSet();
        set.addTransition(new ChangeImageTransform());
        set.addTransition(new ChangeBounds());
        set.addTransition(new Fade());
        return set;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addClipping() {
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int radius = Math.min(view.getWidth(), view.getHeight());
//                int radius = 300;
                outline.setOval(0, 0, radius, radius);
            }
        };
        view.setOutlineProvider(viewOutlineProvider);
        view.setClipToOutline(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
