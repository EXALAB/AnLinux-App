package exa.lnx.a;

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
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Calendar;
import java.util.Date;

public class MainUI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    NavigationView navigationView;
    DrawerLayout drawer;
    private long lastPressedTime;
    private static final int PERIOD = 3000;
    private RewardedVideoAd mRewardedVideoAd;
    AdView mAdView;
    RelativeLayout relativeLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);

        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        relativeLayout = findViewById(R.id.fragmentHolder);

        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);
        editor = sharedPreferences.edit();

        mAdView = findViewById(R.id.adView);

        if(!donationInstalled() && !isVideoAdsWatched()){
            mAdView.loadAd(new AdRequest.Builder().build());
        }

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd("ca-app-pub-5748356089815497/5381178563", new AdRequest.Builder().build());

        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
            }
            @Override
            public void onRewardedVideoAdOpened() {
            }
            @Override
            public void onRewardedVideoStarted() {
            }
            @Override
            public void onRewardedVideoAdClosed() {
            }
            @Override
            public void onRewarded(RewardItem rewardItem) {
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
                    Toast.makeText(context, "Thanks for your support!!!", Toast.LENGTH_LONG).show();
                }else{
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    cal.setTime(date);
                    int a =  cal.get(Calendar.DAY_OF_MONTH);
                    int b = sharedPreferences.getInt("VideoAds", 0);
                    if(a != b){
                        editor.putInt("VideoAds", a);
                        editor.apply();
                        relativeLayout.removeView(mAdView);
                        Toast.makeText(context, "The ads are removed for today.", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onRewardedVideoAdLeftApplication() {
            }
            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
            }
            @Override
            public void onRewardedVideoCompleted() {
                mRewardedVideoAd.loadAd("ca-app-pub-5748356089815497/5381178563", new AdRequest.Builder().build());
            }
        });

        if(savedInstanceState == null){
            MenuItem selected = navigationView.getMenu().findItem(R.id.dashboard);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(0);
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
            }
        }
        return false;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.dashboard) {
            MenuItem selected = navigationView.getMenu().findItem(R.id.dashboard);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(0);
        }else if(id == R.id.about){
            MenuItem selected = navigationView.getMenu().findItem(R.id.about);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(1);
        }else if(id == R.id.support){
            if(donationInstalled()){
                notifyUserForSupportAfterDonation();
            }else{
                notifyUserForSupport();
            }
        }else if(id == R.id.report){
            notifyUserToReportError();
        }else if(id == R.id.ssh){
            MenuItem selected = navigationView.getMenu().findItem(R.id.ssh);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(4);
        }else if(id == R.id.gui){
            MenuItem selected = navigationView.getMenu().findItem(R.id.gui);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(2);
        }else if(id == R.id.uninstall){
            MenuItem selected = navigationView.getMenu().findItem(R.id.uninstall);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(3);
        }else if(id == R.id.patch){
            MenuItem selected = navigationView.getMenu().findItem(R.id.patch);
            selected.setCheckable(true);
            selected.setChecked(true);
            newFragment(5);
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
                fragment = new Uninstaller();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 4:
                fragment = new SSH();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case 5:
                fragment = new Patches();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
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
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }else{
                    Toast.makeText(context, "Currently no video ads available", Toast.LENGTH_SHORT).show();
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
        textView.setText("Thanks for using this app, do you want to support the developers?\n\n You can choose to watch an ad video, which will remove advertisement for a day, or purchase a Donation Package on Play Store which remove the ads forever.");
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
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }else{
                    Toast.makeText(context, "Currently no video ads available", Toast.LENGTH_SHORT).show();
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
        textView.setText("Thanks for purchasing Donation Package, you can still watch video ads to support the developers, or continue to enjoy this app.");
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
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bug Report");

                context.startActivity(Intent.createChooser(emailIntent, "Please choose an app"));
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
        textView.setText("If you encountered a bug, you can choose to open an issue on Github, or email us.");
    }
    private boolean donationInstalled() {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.checkSignatures(context.getPackageName(), "exa.lnx.d") == PackageManager.SIGNATURE_MATCH;
    }
    private boolean isVideoAdsWatched(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        cal.setTime(date);
        int a =  cal.get(Calendar.DAY_OF_MONTH);
        int b = sharedPreferences.getInt("VideoAds", 0);
        return a == b;
    }
}
