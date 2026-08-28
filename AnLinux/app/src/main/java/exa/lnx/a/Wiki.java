package exa.lnx.a;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.Calendar;
import java.util.Date;

public class Wiki extends Fragment {

    Context context;
    SharedPreferences sharedPreferences;
    Button button;
    RelativeLayout.LayoutParams relativeLayoutParam;
    ScrollView scrollView;
    int leftMargin;
    int rightMargin;
    int topMargin;
    int bottomMargin;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.wiki);
        View view = inflater.inflate(R.layout.wiki, container, false);

        context = getActivity().getApplicationContext();
        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);

        scrollView = view.findViewById(R.id.scrollView);
        relativeLayoutParam = (RelativeLayout.LayoutParams)scrollView.getLayoutParams();
        leftMargin = relativeLayoutParam.leftMargin;
        rightMargin = relativeLayoutParam.rightMargin;
        topMargin = relativeLayoutParam.topMargin;
        bottomMargin = 0;
        if(donationInstalled() || isVideoAdsWatched()){
            relativeLayoutParam.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        }

        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/EXALAB/AnLinux-App/wiki"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
    private boolean donationInstalled() {
        PackageManager packageManager = context.getPackageManager();
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
    public void removeAdView() {
        if (donationInstalled() || isVideoAdsWatched()) {
            relativeLayoutParam.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            scrollView.setLayoutParams(relativeLayoutParam);
            scrollView.requestLayout();
        }
    }
}