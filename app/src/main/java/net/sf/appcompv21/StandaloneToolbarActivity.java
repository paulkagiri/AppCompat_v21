package net.sf.appcompv21;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class StandaloneToolbarActivity extends ActionBarActivity implements ObservableScrollView.Callbacks {

    private static final String TAG = StandaloneToolbarActivity.class.getName();

    private Toolbar toolbar;

    private ObservableScrollView scrollView;

    private View headerView;

    private TextView contentText;

    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;

    private int titleHeight;

    private int toolbarHeight;

    private int maxHeaderElevation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

        scrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
        scrollView.addCallbacks(this);

        headerView = findViewById(R.id.standalone_header);
        contentText = (TextView) findViewById(R.id.standalone_content);

        toolbar = (Toolbar) findViewById(R.id.standalone_toolbar);
        //icon
        toolbar.setNavigationIcon(R.drawable.ic_up);
        //title
        toolbar.setTitle("Standalone");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_standalone);

        ViewTreeObserver viewTreeObserver = scrollView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    titleHeight = headerView.getHeight();
                    toolbarHeight = toolbar.getHeight();
                    //toolbar margin is set in onscrollchanged()

                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) contentText
                            .getLayoutParams();
                    layoutParams.topMargin = titleHeight + toolbarHeight;
                    contentText.setLayoutParams(layoutParams);

                    onScrollChanged(0, 0);
                }
            };
            viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener);
        }

        maxHeaderElevation = getResources().getDimensionPixelSize(
                R.dimen.session_detail_max_header_elevation);

    }

    @Override
    protected void onDestroy() {
        if (scrollView != null) {
            ViewTreeObserver observer = scrollView.getViewTreeObserver();
            if (observer.isAlive()) {
                observer.removeGlobalOnLayoutListener(globalLayoutListener);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {
        // Reposition the header bar -- it's normally anchored to the top of the content,
        // but locks to the top of the screen on scroll
        int scrollY = scrollView.getScrollY();

        float newTop = Math.max(titleHeight, scrollY);
        toolbar.setTranslationY(newTop);

//        Log.d(TAG, "new top: " + newTop);

        float gapFillProgress = 1;
        if (titleHeight != 0) {
            gapFillProgress = Math.min(Math.max(getProgress(scrollY,
                                                            0,
                                                            titleHeight), 0), 1);
        }

        ViewCompat.setElevation(toolbar, gapFillProgress * maxHeaderElevation);
//        ViewCompat.setElevation(mAddScheduleButton, gapFillProgress * mMaxHeaderElevation
//                + mFABElevation);

        // Move background photo (parallax effect)
        headerView.setTranslationY(scrollY * 0.5f);
    }

    public static float getProgress(int value, int min, int max) {
        if (min == max) {
            throw new IllegalArgumentException("Max (" + max + ") cannot equal min (" + min + ")");
        }

        return (value - min) / (float) (max - min);
    }

}
