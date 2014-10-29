package net.sf.appcompv21;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

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
public class WidgetTintingActivity extends ActionBarActivity {

    private static final String TAG = WidgetTintingActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_tinting);

    }

}
