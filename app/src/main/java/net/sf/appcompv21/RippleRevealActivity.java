package net.sf.appcompv21;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

public class RippleRevealActivity extends ActionBarActivity {

    private static final String TAG = RippleRevealActivity.class.getName();

    private Button normalBtn;

    private Button rippleBtn;

    private View image;

    private boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_reveal);
        normalBtn = (Button) findViewById(R.id.ripple_normal_btn);
        rippleBtn = (Button) findViewById(R.id.ripple_ripple_btn);

        image = findViewById(R.id.ripple_img);
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setVisibility(View.VISIBLE);
                reveal(image);

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            private void reveal(final View v) {

                // get the center for the clipping circle
                int cx = (v.getLeft() + v.getRight()) / 2;
                int cy = (v.getTop() + v.getBottom()) / 2;

                // get the final radius for the clipping circle
                int finalRadius = v.getWidth();

                // create and start the animator for this view
                // (the start radius is zero)
                int start = visible ? finalRadius : 0;
                int end = visible ? 0 : finalRadius;
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(v, cx, cy, start, end);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        visible = !visible;
                        if (!visible) {
                            v.setVisibility(View.INVISIBLE);
                        } else {
                            v.setVisibility(View.VISIBLE);
                        }

                    }
                });
                anim.start();
            }
        });
    }

}
