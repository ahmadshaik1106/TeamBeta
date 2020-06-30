package com.example.newsapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {

    public static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ffeead")),
                    new ColorDrawable(Color.parseColor("#93cfb3")),
                    new ColorDrawable(Color.parseColor("#fd7a7a")),
                    new ColorDrawable(Color.parseColor("#faca5f")),
                    new ColorDrawable(Color.parseColor("#1ba798")),
                    new ColorDrawable(Color.parseColor("#6aa9ae")),
                    new ColorDrawable(Color.parseColor("#ffbf27")),
                    new ColorDrawable(Color.parseColor("#d93947"))
            };

    public static ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }

    public static String DateToTimeFormat(String oldstringDate) {
        PrettyTime p = new PrettyTime(new Locale(getCountry()));
        String isTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.ENGLISH);
            Date date = sdf.parse(oldstringDate);
            isTime = p.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isTime;
    }

    public static String DateFormat(String oldstringDate) {
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy", new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }

    public static String getCountry() {
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static String getLanguage() {
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getLanguage());
        return country.toLowerCase();
    }
}









//package com.example.newsapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestOptions;
//import com.google.android.material.appbar.AppBarLayout;
//import com.google.android.material.appbar.CollapsingToolbarLayout;
//
//public class NewsDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
//
//    private ImageView imageView;
//    private TextView appbar_title, appbar_subtitle, date, time, title;
//    private boolean isHideToolbarView = false;
//    private FrameLayout date_behavior;
//    private LinearLayout titleAppbar;
//    private AppBarLayout appBarLayout;
//    private Toolbar toolbar;
//    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news_detail);
//
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("");
//
//        appBarLayout = findViewById(R.id.appbar);
//        appBarLayout.addOnOffsetChangedListener(this);
//        date_behavior = findViewById(R.id.date_behavior);
//        titleAppbar = findViewById(R.id.title_appbar);
//        imageView = findViewById(R.id.backdrop);
//        appbar_title = findViewById(R.id.title_on_appbar);
//        appbar_subtitle = findViewById(R.id.subtitle_on_appbar);
//        date = findViewById(R.id.date);
//        time = findViewById(R.id.time);
//        title = findViewById(R.id.title);
//
//        Intent intent = getIntent();
//        mUrl = intent.getStringExtra("url");
//        mImg = intent.getStringExtra("img");
//        mTitle = intent.getStringExtra("title");
//        mDate = intent.getStringExtra("date");
//        mSource = intent.getStringExtra("source");
//        mAuthor = intent.getStringExtra("author");
//
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.error(Utils.getRandomDrawbleColor());
//
//        Glide.with(this)
//                .load(mImg)
//                .apply(requestOptions)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(imageView);
//
//        appbar_title.setText(mSource);
//        appbar_subtitle.setText(mUrl);
//        date.setText(Utils.DateFormat(mDate));
//        title.setText(mTitle);
//
//        String author;
//        if (mAuthor != null){
//            author = " \u2022 " + mAuthor;
//        } else {
//            author = "";
//        }
//
//        time.setText(mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate));
//
//        initWebView(mUrl);
//
//    }
//
//    private void initWebView(String url){
//        WebView webView = findViewById(R.id.webView);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(url);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        supportFinishAfterTransition();
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//
//    @Override
//    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        int maxScroll = appBarLayout.getTotalScrollRange();
//        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
//
//        if (percentage == 1f && isHideToolbarView) {
//            date_behavior.setVisibility(View.GONE);
//            titleAppbar.setVisibility(View.VISIBLE);
//            isHideToolbarView = !isHideToolbarView;
//
//        } else if (percentage < 1f && !isHideToolbarView) {
//            date_behavior.setVisibility(View.VISIBLE);
//            titleAppbar.setVisibility(View.GONE);
//            isHideToolbarView = !isHideToolbarView;
//        }
//
//    }
//
//}












//=====================================
//<?xml version="1.0" encoding="utf-8"?>
//<androidx.coordinatorlayout.widget.CoordinatorLayout
//    xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:fitsSystemWindows="true"
//    android:background="@color/colorBackground"
//    tools:context=".NewsDetailActivity">
//
//    <com.google.android.material.appbar.AppBarLayout
//        android:id="@+id/appbar"
//        android:layout_width="match_parent"
//        android:layout_height="250dp"
//        android:fitsSystemWindows="true"
//        android:theme="@style/MyAppBarLayoutTheme">
//
//        <com.google.android.material.appbar.CollapsingToolbarLayout
//            android:id="@+id/collapsing_toolbar"
//            android:layout_width="match_parent"
//            app:titleEnabled="false"
//            android:layout_height="match_parent"
//            android:fitsSystemWindows="true"
//            app:contentScrim="?attr/colorPrimary"
//            app:layout_scrollFlags="scroll|exitUntilCollapsed">
//
//            <ImageView
//                android:id="@+id/backdrop"
//                android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                android:fitsSystemWindows="true"
//                android:scaleType="centerCrop"
//                app:layout_collapseMode="parallax"
//                android:transitionName="img"
//                tools:ignore="UnusedAttribute" />
//
//            <RelativeLayout
//                android:id="@+id/headerContent"
//                app:layout_collapseMode="pin"
//                android:fitsSystemWindows="true"
//                android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                android:layout_centerInParent="true"
//                android:orientation="vertical">
//
//                <ImageView
//                    android:id="@+id/img"
//                    android:src="@drawable/top_shadow"
//                    android:scaleType="centerCrop"
//                    android:layout_width="match_parent"
//                    android:layout_height="80dp" />
//
//                <ImageView
//                    android:layout_alignParentBottom="true"
//                    android:src="@drawable/bottom_shadow"
//                    android:id="@+id/img2"
//                    android:layout_alignBottom="@id/img"
//                    android:scaleType="centerCrop"
//                    android:layout_width="match_parent"
//                    android:layout_height="80dp" />
//
//            </RelativeLayout>
//
//            <androidx.appcompat.widget.Toolbar
//                android:id="@+id/toolbar"
//                android:layout_width="match_parent"
//                android:layout_height="?attr/actionBarSize"
//                android:contentInsetStart="0dp"
//                android:contentInsetLeft="0dp"
//                app:contentInsetLeft="0dp"
//                app:contentInsetStart="0dp"
//                app:layout_collapseMode="pin"
//                app:popupTheme="@style/ThemeOverlay.AppCompat.Light">
//
//                <LinearLayout
//                    android:id="@+id/title_appbar"
//                    android:clickable="false"
//                    android:layout_width="wrap_content"
//                    android:orientation="vertical"
//                    android:layout_height="wrap_content">
//
//                    <TextView
//                        android:id="@+id/title_on_appbar"
//                        style="@style/Base.TextAppearance.AppCompat.Widget.ActionBar.Title"
//                        android:text="News for you"
//                        android:textSize="18dp"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:maxLines="1"
//                        android:drawablePadding="10dp"
//                        android:singleLine="true"
//                        android:ellipsize="end"/>
//
//                    <TextView
//                        android:id="@+id/subtitle_on_appbar"
//                        style="@style/Base.TextAppearance.AppCompat.Widget.ActionBar.Subtitle"
//                        android:text="Subtitle"
//                        android:textSize="12dp"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:maxLines="1"
//                        android:drawablePadding="10dp"
//                        android:singleLine="true"
//                        android:ellipsize="end"/>
//
//                </LinearLayout>
//
//            </androidx.appcompat.widget.Toolbar>
//
//        </com.google.android.material.appbar.CollapsingToolbarLayout>
//
//    </com.google.android.material.appbar.AppBarLayout>
//
//    <androidx.core.widget.NestedScrollView
//        app:layout_behavior="@string/appbar_scrolling_view_behavior"
//        android:fitsSystemWindows="true"
//        android:layout_width="match_parent"
//        android:background="@color/colorBackground"
//        android:layout_height="match_parent">
//        <LinearLayout
//            android:orientation="vertical"
//            android:layout_width="match_parent"
//            android:layout_height="wrap_content">
//
//            <androidx.cardview.widget.CardView
//                app:layout_behavior="@string/appbar_scrolling_view_behavior"
//                android:layout_width="match_parent"
//                app:cardBackgroundColor="@color/colorPrimary"
//                app:cardCornerRadius="0dp"
//                app:cardElevation="@dimen/cardview_default_elevation"
//                android:layout_height="wrap_content">
//                <RelativeLayout
//                    android:layout_marginBottom="20dp"
//                    android:layout_marginTop="20dp"
//                    android:paddingLeft="16dp"
//                    android:paddingRight="16dp"
//                    android:layout_width="match_parent"
//                    android:layout_height="wrap_content">
//                    <TextView
//                        android:id="@+id/title"
//                        android:textColor="@color/colorTextTitle"
//                        android:textStyle="bold"
//                        android:fontFamily="sans-serif-light"
//                        android:textSize="19sp"
//                        android:text="Title of News"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        />
//
//                    <TextView
//                        android:id="@+id/time"
//                        android:layout_width="wrap_content"
//                        android:layout_height="20dp"
//                        android:layout_below="@id/title"
//                        android:layout_marginTop="10dp"
//                        android:maxLines="1"
//                        android:drawablePadding="10dp"
//                        android:singleLine="true"
//                        android:ellipsize="end"
//                        android:text="a time ago" />
//
//                </RelativeLayout>
//            </androidx.cardview.widget.CardView>
//
//            <androidx.cardview.widget.CardView
//                app:layout_behavior="@string/appbar_scrolling_view_behavior"
//                android:layout_marginTop="12dp"
//                android:layout_marginBottom="20dp"
//                android:layout_width="match_parent"
//                android:layout_height="wrap_content"
//                app:cardElevation="@dimen/cardview_default_elevation"
//                app:cardCornerRadius="0dp">
//
//                <RelativeLayout
//                    android:layout_width="match_parent"
//                    android:layout_height="match_parent">
//
//                    <ProgressBar
//                        app:layout_behavior="@string/appbar_scrolling_view_behavior"
//                        android:id="@+id/progress_bar"
//                        android:layout_marginTop="50dp"
//                        android:layout_marginBottom="70dp"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content" />
//
//                    <WebView
//                        app:layout_behavior="@string/appbar_scrolling_view_behavior"
//                        android:id="@+id/webView"
//                        android:layout_width="match_parent"
//                        android:layout_height="match_parent"/>
//
//
//                </RelativeLayout>
//
//            </androidx.cardview.widget.CardView>
//
//        </LinearLayout>
//
//    </androidx.core.widget.NestedScrollView>
//
//    <FrameLayout
//        android:id="@+id/date_behavior"
//        app:layout_anchor="@+id/appbar"
//        app:behavior_autoHide="true"
//        android:adjustViewBounds="true"
//        app:layout_anchorGravity="right|end|bottom"
//        android:clickable="false"
//        android:layout_below="@id/img"
//        android:background="@drawable/round_white"
//        android:layout_width="wrap_content"
//        android:padding="5dp"
//        android:layout_alignParentBottom="true"
//        android:layout_alignParentRight="true"
//        android:layout_marginRight="20dp"
//        android:layout_marginBottom="410dp"
//        android:layout_height="wrap_content"
//        tools:ignore="UnusedAttribute">
//        <ImageView
//            android:src="@drawable/ic_date"
//            android:layout_width="18dp"
//            android:layout_height="18dp"
//            android:layout_marginLeft="5dp"
//            android:layout_marginRight="5dp"/>
//        <TextView
//            android:textColor="#606060"
//            android:id="@+id/date"
//            android:layout_marginLeft="27dp"
//            android:layout_marginRight="10dp"
//            android:text="01 January 1990"
//            android:layout_width="wrap_content"
//            android:layout_height="wrap_content" />
//    </FrameLayout>
//
//</androidx.coordinatorlayout.widget.CoordinatorLayout>