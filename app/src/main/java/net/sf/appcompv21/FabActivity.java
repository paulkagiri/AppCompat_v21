package net.sf.appcompv21;

import android.graphics.Color;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

public class FabActivity extends ActionBarActivity {

    private static final String TAG = FabActivity.class.getName();

    private Toolbar toolbar;

    private ObservableScrollView scrollView;

    private View headerView;

    private TextView contentText;

    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;

    private int titleHeight;

    private int toolbarHeight;

    private int maxHeaderElevation;

    private View fab;

    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        fab = findViewById(R.id.add_schedule_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iconView = (ImageView) fab.findViewById(R.id.add_schedule_icon);
                Drawable drawable = iconView.getDrawable();
                if (!(drawable instanceof AnimatedStateListDrawable)) {
                    drawable = getResources().getDrawable(R.drawable.add_schedule_fab_icon_anim);
                    iconView.setImageDrawable(drawable);
                }
                iconView.setColorFilter(Color.WHITE);
                iconView.setImageState(STATE_UNCHECKED,false);
                drawable.jumpToCurrentState();
                iconView.setImageState(STATE_CHECKED,false);
            }
        });

    }

}
