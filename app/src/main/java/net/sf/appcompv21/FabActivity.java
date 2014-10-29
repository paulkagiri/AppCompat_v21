package net.sf.appcompv21;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

public class FabActivity extends ActionBarActivity {

    private static final String TAG = FabActivity.class.getName();

    private View fab;

    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};

    private static final int[] STATE_UNCHECKED = new int[]{};

    private ImageView iconView;

    private View revealView;

    private boolean selected = false;

    private Drawable uncheckedDrawable;

    private Drawable checkedDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        uncheckedDrawable = getResources().getDrawable(R.drawable.reveal_unchecked);
        checkedDrawable = getResources().getDrawable(R.drawable.reveal_checked);

        fab = findViewById(R.id.add_schedule_button);

        iconView = (ImageView) fab.findViewById(R.id.add_schedule_icon);
        //changes icon color to white
        iconView.setColorFilter(Color.WHITE);

        revealView = findViewById(R.id.add_schedule_reveal);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = !selected;

                iconView.setColorFilter(selected ? Color.BLUE : Color.WHITE);

                Drawable drawable = iconView.getDrawable();
//                if (!(drawable instanceof AnimatedStateListDrawable)) {
//                    drawable = getResources().getDrawable(R.drawable.add_schedule_fab_icon_anim);
//                    iconView.setImageDrawable(drawable);
//                }
                if (isL()) {

                    iconView.setImageState(selected ? STATE_CHECKED : STATE_UNCHECKED, false);
                    drawable.jumpToCurrentState();
                    iconView.setImageState(selected ? STATE_UNCHECKED : STATE_CHECKED, false);
                } else {
                    //animates fade in/out
                }

                reveal(true);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            private void reveal(boolean anim) {

                if (anim) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(
                            revealView,
                            (int) fab.getWidth() / 2, (int) fab.getHeight() / 2, 0,
                            fab.getWidth() / 2);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            reveal(false);
                        }
                    });

                    animator.start();

                    revealView.setVisibility(View.VISIBLE);
                    revealView.setBackground(selected ? checkedDrawable : uncheckedDrawable);
                } else {
                    revealView.setVisibility(View.GONE);

                    RippleDrawable newBackground = (RippleDrawable) getResources()
                            .getDrawable(selected
                                                 ? R.drawable.add_schedule_fab_ripple_background_on
                                                 : R.drawable.add_schedule_fab_ripple_background_off);
                    fab.setBackground(newBackground);
                }

            }
        });

    }

    private static boolean isL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
