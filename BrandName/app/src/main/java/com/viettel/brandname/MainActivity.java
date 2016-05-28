package com.viettel.brandname;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.viettel.brandname.activity.BaseSuperActivity;
import com.viettel.brandname.model.LoginModel;

public class MainActivity extends BaseSuperActivity implements View.OnClickListener {

    private TextView txtUserName, txtListProgram, txtSendMessage, txtAddCash, txtLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtUserName = (TextView) findViewById(R.id.txt_user_name);
        txtListProgram = (TextView) findViewById(R.id.txt_list_program);
        txtSendMessage = (TextView) findViewById(R.id.txt_send_message);
        txtAddCash = (TextView) findViewById(R.id.txt_add_cash);
        txtLogout = (TextView) findViewById(R.id.txt_logout);

        txtUserName.setOnClickListener(this);
        txtListProgram.setOnClickListener(this);
        txtSendMessage.setOnClickListener(this);
        txtAddCash.setOnClickListener(this);
        txtLogout.setOnClickListener(this);

        initActionBar();
        setTitleBar(getResources().getString(R.string.app_name));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                txtUserName.setText(LoginModel.getUserName(MainActivity.this));
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new_message) {
            goToSendSMS();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_list_program:
                break;
            case R.id.txt_send_message:
                goToSendSMS();
                break;
            case R.id.txt_add_cash:
                goToAddCash();
                break;
            case R.id.txt_logout:

                break;
            default:
                break;
        }

    }
}