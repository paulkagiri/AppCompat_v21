package net.sf.appcompv21;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

public class FabActivity extends ActionBarActivity {

    private static final String TAG = FabActivity.class.getName();

    private View fab;

    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};

    private ImageView iconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        fab = findViewById(R.id.add_schedule_button);
        iconView = (ImageView) fab.findViewById(R.id.add_schedule_icon);
        //changes icon color to white
        iconView.setColorFilter(Color.WHITE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reveal effect, see AddToScheduleFABFragmetnLayout.java
                Drawable drawable = iconView.getDrawable();
//                if (!(drawable instanceof AnimatedStateListDrawable)) {
//                    drawable = getResources().getDrawable(R.drawable.add_schedule_fab_icon_anim);
//                    iconView.setImageDrawable(drawable);
//                }
                if (isL()) {
                    iconView.setImageState(STATE_UNCHECKED, false);
                    drawable.jumpToCurrentState();
                    iconView.setImageState(STATE_CHECKED, false);
                } else {
                    //animates fade in/out
                }

            }
        });

    }

    private static boolean isL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


}
