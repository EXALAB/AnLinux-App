package exa.lnx.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Date;

public class MainUI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    NavigationView navigationView;
    DrawerLayout drawer;
    private long lastPressedTime;
    private static final int PERIOD = 3000;
    private RewardedAd rewardedAd;
    InterstitialAd mInterstitialAd;
    AdView mAdView;
    FrameLayout frameLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int i = 0;
    boolean shouldShowAds = false;
    boolean isOreoNotified;
    boolean isFirstBugNotified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);

        context = getApplicationContext();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);
        editor = sharedPreferences.edit();

        frameLayout = findViewById(R.id.ad_view_container);

        mAdView = new AdView(this);
        mAdView.setAdUnitId("ca-app-pub-5748356089815497/7765835183");
        frameLayout.addView(mAdView);

        AdSize adSize = getAdSize();
        mAdView.setAdSize(adSize);

        if(!donationInstalled() && !isVideoAdsWatched()){
            mAdView.loadAd(new AdRequest.Builder().build());
            shouldShowAds = true;
        }else{
            mAdView.pause();
            mAdView.destroy();
            mAdView.setVisibility(View.GONE);
            frameLayout.removeView(mAdView);
            shouldShowAds = false;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        isOreoNotified = sharedPreferences.getBoolean("IsOreoNotified", false);
        isFirstBugNotified = sharedPreferences.getBoolean("IsFirstBugNotified", false);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if(donationInstalled()){
            if(savedInstanceState == null) {
                MenuItem selected = navigationView.getMenu().findItem(R.id.dashboard);
                selected.setCheckable(true);
                selected.setChecked(true);
                newFragment(0);
            }
        }else{
            if(!drawer.isDrawerOpen(GravityCompat.START)){
                drawer.openDrawer(GravityCompat.START);
            }
        }
        if(!isOreoNotified){
            showFirstDialog();
        }
    }
    @Override
    public void onResume() {
        // Start or resume the game.
        super.onResume();
        if (mInterstitialAd == null) {
            loadAd();
        }
        if(rewardedAd == null){
            loadRewardedAd();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        Fragment fragment = this.getFragmentManager().findFragmentById(R.id.fragmentHolder);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if(fragment instanceof DashBoard){
                if(drawer.isDrawerOpen(GravityCompat.START)){
                    switch(event.getAction()){
                        case KeyEvent.ACTION_DOWN:
                            if(event.getDownTime() - lastPressedTime < PERIOD){
                                finish();
                            }else{
                                Toast.makeText(context, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show();
                                lastPressedTime = event.getEventTime();
                            }
                            return true;
                    }
                }else if(!drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.openDrawer(GravityCompat.START);
                }
            }else if(fragment instanceof About){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof DesktopEnvironment){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof HeavyDE){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof WindowManager){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof SSH){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof Uninstaller){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof Patches){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof SU){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof RootfsDownload){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof Wiki){
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
        return false;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = this.getFragmentManager().findFragmentById(R.id.fragmentHolder);

        if (id == R.id.dashboard) {
            MenuItem selected = navigationView.getMenu().findItem(R.id.dashboard);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof DashBoard)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(0);
            }
        }else if(id == R.id.about){
            MenuItem selected = navigationView.getMenu().findItem(R.id.about);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(1);
        }else if(id == R.id.support){
            if(!donationInstalled()){
                notifyUserForSupport();
            }else{
                notifyUserForSupportAfterDonation();
            }
        }else if(id == R.id.report){
            if(isFirstBugNotified){
                notifyUserToReportError();
            }else{
                showFirstReportBugDialog();
            }
        }else if(id == R.id.gui){
            MenuItem selected = navigationView.getMenu().findItem(R.id.gui);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof DesktopEnvironment)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(2);
            }
        }else if(id == R.id.hgui){
            MenuItem selected = navigationView.getMenu().findItem(R.id.hgui);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof HeavyDE)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(9);
            }
            //Temporary Code, will be back later if any error in the future
            //notifyUserForTemporary();
        }else if(id == R.id.wm){
            MenuItem selected = navigationView.getMenu().findItem(R.id.wm);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof WindowManager)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(3);
            }
        }else if(id == R.id.uninstall){
            MenuItem selected = navigationView.getMenu().findItem(R.id.uninstall);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof Uninstaller)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(4);
            }
        }else if(id == R.id.ssh){
            MenuItem selected = navigationView.getMenu().findItem(R.id.ssh);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof SSH)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(5);
            }
        }else if(id == R.id.patch){
            MenuItem selected = navigationView.getMenu().findItem(R.id.patch);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof Patches)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(6);
            }
        }else if(id == R.id.documentation){
            notifyUserForDocumentation();
        }else if(id == R.id.su){
            MenuItem selected = navigationView.getMenu().findItem(R.id.su);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof SU)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(7);
            }
        }else if(id == R.id.rootfs_download){
            MenuItem selected = navigationView.getMenu().findItem(R.id.rootfs_download);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof Patches)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(8);
            }
        }else if(id == R.id.wiki){
            MenuItem selected = navigationView.getMenu().findItem(R.id.wiki);
            selected.setCheckable(true);
            selected.setChecked(true);
            if(!(fragment instanceof Patches)){
                if (i == 0) {
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 1;
                }else if(i == 1){
                    i = 2;
                }else if(i == 2){
                    if(mInterstitialAd != null && shouldShowAds){
                        mInterstitialAd.show(MainUI.this);
                    }
                    i = 0;
                }
                newFragment(10);
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void newFragment(int position){

        Fragment fragment;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch(position){

            case 0:
                fragment = new DashBoard();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 1:
                fragment = new About();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 2:
                fragment = new DesktopEnvironment();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 3:
                fragment = new WindowManager();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 4:
                fragment = new Uninstaller();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 5:
                fragment = new SSH();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 6:
                fragment = new Patches();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 7:
                fragment = new SU();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 8:
                fragment = new RootfsDownload();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 9:
                fragment = new HeavyDE();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 10:
                fragment = new Wiki();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }
    public void notifyUserForDocumentation(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("https://github.com/EXALAB/AnLinux-App/wiki");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(Build.VERSION.SDK_INT >= 21){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                }
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/EXALAB/AnLinux-App/wiki")));
                }
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText(R.string.documentation_prompt);
    }
    public void notifyUserForSupport(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.donation, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=exa.lnx.d");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(Build.VERSION.SDK_INT >= 21){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                }
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=exa.lnx.d")));
                }
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.watch_ads, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (rewardedAd != null) {
                    rewardedAd.show(MainUI.this, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            if(donationInstalled()){
                                Calendar cal = Calendar.getInstance();
                                Date date = cal.getTime();
                                cal.setTime(date);
                                int a =  cal.get(Calendar.DAY_OF_MONTH);
                                int b = sharedPreferences.getInt("VideoAds", 0);
                                if(a != b){
                                    editor.putInt("VideoAds", a);
                                    editor.apply();
                                }
                                Toast.makeText(context, R.string.thanks_for_support, Toast.LENGTH_LONG).show();
                            }else{
                                Calendar cal = Calendar.getInstance();
                                Date date = cal.getTime();
                                cal.setTime(date);
                                int a =  cal.get(Calendar.DAY_OF_MONTH);
                                int b = sharedPreferences.getInt("VideoAds", 0);
                                if(a != b){
                                    if(!donationInstalled() && !isVideoAdsWatched()){
                                        mAdView.pause();
                                        mAdView.destroy();
                                        mAdView.setVisibility(View.GONE);
                                        frameLayout.removeView(mAdView);
                                    }
                                    editor.putInt("VideoAds", a);
                                    editor.apply();
                                    Toast.makeText(context, R.string.ads_removed_temp, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(context, R.string.no_video_ads, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        alertDialog.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText(R.string.ask_for_donation);
    }
    public void notifyUserForSupportAfterDonation(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.watch_ads, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (rewardedAd != null) {
                    rewardedAd.show(MainUI.this, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            if(donationInstalled()){
                                Calendar cal = Calendar.getInstance();
                                Date date = cal.getTime();
                                cal.setTime(date);
                                int a =  cal.get(Calendar.DAY_OF_MONTH);
                                int b = sharedPreferences.getInt("VideoAds", 0);
                                if(a != b){
                                    editor.putInt("VideoAds", a);
                                    editor.apply();
                                }
                                Toast.makeText(context, R.string.thanks_for_support, Toast.LENGTH_LONG).show();
                            }else{
                                Calendar cal = Calendar.getInstance();
                                Date date = cal.getTime();
                                cal.setTime(date);
                                int a =  cal.get(Calendar.DAY_OF_MONTH);
                                int b = sharedPreferences.getInt("VideoAds", 0);
                                if(a != b){
                                    if(!donationInstalled() && !isVideoAdsWatched()){
                                        mAdView.pause();
                                        mAdView.destroy();
                                        mAdView.setVisibility(View.GONE);
                                        frameLayout.removeView(mAdView);
                                    }
                                    editor.putInt("VideoAds", a);
                                    editor.apply();
                                    Toast.makeText(context, R.string.ads_removed_temp, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(context, R.string.no_video_ads, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string._continue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText(R.string.support_after_donation);
    }
    public void notifyUserToReportError(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Email", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"exalabdevelopers@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, R.string.bug_report1);

                startActivity(Intent.createChooser(emailIntent, getString(R.string.bug_report2)));
            }
        });
        alertDialog.setNegativeButton("Github", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/EXALAB/AnLinux-App/issues"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText(R.string.bug_encounter);
    }
    private boolean donationInstalled() {
        PackageManager packageManager = getPackageManager();
        try {
            packageManager.getPackageInfo("exa.lnx.d", 0);
            return true;
        }catch(PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private boolean isVideoAdsWatched(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        cal.setTime(date);
        int a =  cal.get(Calendar.DAY_OF_MONTH);
        int b = sharedPreferences.getInt("VideoAds", 0);
        return a == b;
    }
    protected void showFirstDialog(){

        final ViewGroup nullParent = null;

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.first_warning, nullParent);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsOreoNotified", true);
                editor.apply();
                isOreoNotified = sharedPreferences.getBoolean("IsOreoNotified", false);
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }else{
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });
    }
    protected void showFirstReportBugDialog(){

        final ViewGroup nullParent = null;

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.first_reportbug, nullParent);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.i_agree, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsFirstBugNotified", true);
                editor.apply();
                isOreoNotified = sharedPreferences.getBoolean("IsFirstBugNotified", false);
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
                }else{
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(true);
                }
            }
        });
    }
    public void loadAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-5748356089815497/3581271493", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                MainUI.this.mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                    }
                });
            }
        });
    }
    public void loadRewardedAd(){
        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
                // Code to be invoked when the ad showed full screen content.
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                rewardedAd = null;
                // Code to be invoked when the ad dismissed full screen content.
            }
        };
        RewardedAd.load(this, "ca-app-pub-5748356089815497/2390763032", new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(RewardedAd ad) {
                rewardedAd = ad;
                rewardedAd.setFullScreenContentCallback(fullScreenContentCallback);
            }
        });
    }
    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
    //Temporary Code, will be back later if any error in the future
    /*public void notifyUserForTemporary(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainUI.this);
        LayoutInflater layoutInflater = LayoutInflater.from(MainUI.this);
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/EXALAB/AnLinux-App/issues/252"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText("This feature originally enable user to install KDE on Ubuntu, which work perfectly on Ubuntu 18 (Bionic), however the same code failed for Ubuntu 20 (Focal), if you have any code that work correctly on Ubuntu Focal to contribute or want to know more about this, please go to the Github issue page.\n\nDo you want to go there now?");
    }*/
}
