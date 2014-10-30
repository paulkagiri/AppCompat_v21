package net.sf.appcompv21;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

    private Toolbar toolbar;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_tinting);

        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        //icon
        toolbar.setNavigationIcon(R.drawable.ic_up);
        //title
        toolbar.setTitle("Tint");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                                                             R.array.planets_array,
                                                                             android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
