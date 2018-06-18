package com.grupo7.hci.smarthome.smarthome;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static Integer hola = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);

        final Switch switchOn = findViewById(R.id.switch_ac_on);
        final RadioGroup radioGroupMode = findViewById(R.id.radioGroup_ac_mode);
        final RadioGroup radioGroupVSwing = findViewById(R.id.radioGroup_vswing);
        final RadioGroup radioGroupHSwing = findViewById(R.id.radioGroup_hswing);
        final RadioGroup radioGroupFanSpeed = findViewById(R.id.radioGroup_fanspeed);

        //JSONObject apiResponse = getDeviceStatus(deviceId);
        if(hola >= 1) {
            switchOn.setChecked(true);
            switchOn.setText(R.string.on);
        } else {
            switchOn.setChecked(false);
        }

        RadioButton[] selectedButtons = new RadioButton[4];
        int rb0 = getResources().getIdentifier("radioButton_ac_cool", "id", getPackageName());
        int rb1 = getResources().getIdentifier("radioButton_ac_67vswing", "id", getPackageName());
        int rb2 = getResources().getIdentifier("radioButton_ac_autohswing", "id", getPackageName());
        int rb3 = getResources().getIdentifier("radioButton_ac_25fsp", "id", getPackageName());


        selectedButtons[0] = findViewById(rb0);
        selectedButtons[1] = findViewById(rb1);
        selectedButtons[2] = findViewById(rb2);
        selectedButtons[3] = findViewById(rb3);
        for(RadioButton rb : selectedButtons)
            rb.setChecked(true);

        switchOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //setDeviceStatus(deviceId, "on-status", !isChecked);
                if(isChecked)
                    switchOn.setText(R.string.on);
                else
                    switchOn.setText(R.string.off);
            }
        });

        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), String.valueOf(checkedId),Toast.LENGTH_LONG).show();
                //setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupVSwing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), String.valueOf(checkedId),Toast.LENGTH_LONG).show();
                //setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupHSwing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), String.valueOf(checkedId),Toast.LENGTH_LONG).show();
                //setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupFanSpeed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), String.valueOf(checkedId),Toast.LENGTH_LONG).show();
                //setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    HomeTab homeTab = new HomeTab();
                    return homeTab;
                case 1:
                    DevicesTab devicesTab = new DevicesTab();
                    return devicesTab;
                case 2:
                    RoutinesTab routinesTab = new RoutinesTab();
                    return routinesTab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
