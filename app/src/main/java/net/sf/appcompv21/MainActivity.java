package net.sf.appcompv21;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    private TextView standaloneText;

    private TextView fabText;

    private View rippleText;

    private View tintText;

    private View clippingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        Button next = (Button) findViewById(R.id.main_next);
        next.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StandaloneToolbarActivity.class)/*,
                              ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle()*/);
            }
        });

        standaloneText = (TextView) findViewById(R.id.nav_drawer_standalone_toolbar);
        standaloneText
                .setOnClickListener(new NavItemOnClickListener(StandaloneToolbarActivity.class));
        fabText = (TextView) findViewById(R.id.nav_drawer_fab);
        fabText.setOnClickListener(new NavItemOnClickListener(FabActivity.class));
        rippleText = findViewById(R.id.nav_drawer_ripple);
        rippleText.setOnClickListener(new NavItemOnClickListener(RippleRevealActivity.class));

        tintText = findViewById(R.id.nav_drawer_tint);
        tintText.setOnClickListener(new NavItemOnClickListener(WidgetTintingActivity.class));

        clippingText = findViewById(R.id.nav_drawer_clipping);
        clippingText.setOnClickListener(new NavItemOnClickListener(ClippingActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class NavItemOnClickListener implements View.OnClickListener {
        private Class<? extends ActionBarActivity> activityClass;

        public NavItemOnClickListener(
                Class<? extends ActionBarActivity> activityClass) {

            this.activityClass = activityClass;
        }

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, activityClass));
        }
    }
}
