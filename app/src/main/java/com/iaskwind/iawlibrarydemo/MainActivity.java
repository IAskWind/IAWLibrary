package com.iaskwind.iawlibrarydemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iaskwind.iawlibrary.ProgressHUD.IAW_ProgressHUDTool;
import com.iaskwind.iawlibrary.retrofit.IAW_RetrofitServiceTool;
import com.iaskwind.iawlibrary.retrofit.IAW_RxJavaGeneralReqTool;
import com.iaskwind.iawlibrary.tools.IAW_ViewTool;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Observable;


public class MainActivity extends RxAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private IAW_ProgressHUDTool mSVProgressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar toolbar = IAW_ViewTool.findView(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        mSVProgressHUD=new IAW_ProgressHUDTool(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                ToastUtils.showLongToastSafe(MainActivity.this,"nihaodfdafdafdafdafdafdfdfdfd");
//                mSVProgressHUD.iawShowErrorInfo("nihaodafdafdfadf");
                    mSVProgressHUD.iawShow("正在加载中……");


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Observable<News> n = IAW_RetrofitServiceTool.createRetrofitService(NewService.class).getNews();
        IAW_RxJavaGeneralReqTool.generalReq(n, this, new IAW_RxJavaGeneralReqTool.ReqListener<News>() {
            @Override
            public void onSuccess(News entity) {
                Log.d("Test",entity.getResults().get(0).getDesc());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d("Test",throwable.getMessage());
            }
        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
