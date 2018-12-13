package exa.lnx.a;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;
import java.util.Date;

public class Uninstaller extends Fragment{

    Context context;
    Button button;
    Button button2;
    Button button3;
    TextView textView2;
    TextView textView3;
    String distro;
    String s;
    SharedPreferences sharedPreferences;
    boolean shouldShowAds;
    InterstitialAd mInterstitialAd;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        getActivity().setTitle(R.string.uninstall);

        context = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.uninstaller, container, false);

        distro = "Nothing";

        s = Build.SUPPORTED_ABIS[0];

        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);

        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-5748356089815497/3581271493");

        if(!donationInstalled()){
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }

        button = view.findViewById(R.id.button);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);

        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);

        textView2.setText("Step 2 : Please choose a distro first");
        textView3.setText("Step 3 : Please choose a distro first");
        button2.setEnabled(false);
        button3.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.equals("arm64-v8a") | !s.contains("arm")){
                    notifyUserToChooseDistro();
                }else{
                    notifyUserToChooseDistroARM();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(distro.equals("Ubuntu")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Ubuntu/UNI-ubuntu.sh && bash UNI-ubuntu.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Debian")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Debian/UNI-debian.sh && bash UNI-debian.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Kali")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Kali/UNI-kali.sh && bash UNI-kali.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Parrot")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Parrot/UNI-parrot.sh && bash UNI-parrot.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Fedora")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Fedora/UNI-fedora.sh && bash UNI-fedora.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("CentOS")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/CentOS/UNI-centos.sh && bash UNI-centos.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Leap")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/Leap/UNI-opensuse-leap.sh && bash UNI-opensuse-leap.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Tumbleweed")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/Tumbleweed/UNI-opensuse-tumbleweed.sh && bash UNI-opensuse-tumbleweed.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("openSUSE")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/armhf/UNI-opensuse.sh && bash UNI-opensuse.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Arch")){
                    if(s.equals("x86_64")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Arch/UNI-arch.sh && bash UNI-arch.sh");
                        clipboard.setPrimaryClip(clip);
                    }else{
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Arch/UNI-arch.sh && bash UNI-arch.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }
                if(mInterstitialAd != null && mInterstitialAd.isLoaded() && shouldShowAds){
                    if(!donationInstalled() && !isVideoAdsWatched()){
                        mInterstitialAd.show();
                    }
                    shouldShowAds = false;
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.termux");
                if(isPackageInstalled("com.termux", context.getPackageManager())){
                    startActivity(intent);
                }else{
                    notifyUserForInstallTerminal();
                }
            }
        });
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        return view;
    }
    public void notifyUserToChooseDistro(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.distro_chooser, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = view.findViewById(R.id.checkBox5);
        final CheckBox checkBox6 = view.findViewById(R.id.checkBox6);
        final CheckBox checkBox7 = view.findViewById(R.id.checkBox7);
        final CheckBox checkBox8 = view.findViewById(R.id.checkBox8);
        final CheckBox checkBox9 = view.findViewById(R.id.checkBox9);
        final CheckBox checkBox10 = view.findViewById(R.id.checkBox10);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(distro.equals("Ubuntu")){
            checkBox.setChecked(true);
        }else if(distro.equals("Debian")){
            checkBox2.setChecked(true);
        }else if(distro.equals("Kali")){
            checkBox3.setChecked(true);
        }else if(distro.equals("Parrot")){
            checkBox4.setChecked(true);
        }else if(distro.equals("Fedora")){
            checkBox5.setChecked(true);
        }else if(distro.equals("CentOS")){
            checkBox6.setChecked(true);
        }else if(distro.equals("Leap")){
            checkBox7.setChecked(true);
        }else if(distro.equals("Tumbleweed")){
            checkBox8.setChecked(true);
        }else if(distro.equals("Arch")){
            checkBox9.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox8.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox9.setChecked(false);
            }
        });
        checkBox9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        if(s.equals("i386")){
            checkBox5.setEnabled(false);
            checkBox7.setEnabled(false);
            checkBox8.setEnabled(false);
            checkBox9.setEnabled(false);
            checkBox10.setEnabled(false);
            checkBox5.setText("Not supported");
            checkBox7.setText("Not supported");
            checkBox8.setText("Not supported");
            checkBox9.setText("Not supported");
            checkBox10.setText("Not supported");
        }else{
            checkBox10.setEnabled(false);
            checkBox10.setText("Same as Arch Linux");
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!distro.equals("Ubuntu")){
                        shouldShowAds = true;
                        distro = "Ubuntu";
                    }
                }else if(checkBox2.isChecked()){
                    if(!distro.equals("Debian")){
                        shouldShowAds = true;
                        distro = "Debian";
                    }
                }else if(checkBox3.isChecked()){
                    if(!distro.equals("Kali")){
                        shouldShowAds = true;
                        distro = "Kali";
                    }
                }else if(checkBox4.isChecked()){
                    if(!distro.equals("Parrot")){
                        shouldShowAds = true;
                        distro = "Parrot";
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("Fedora")){
                        shouldShowAds = true;
                        distro = "Fedora";
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("CentOS")){
                        shouldShowAds = true;
                        distro = "CentOS";
                    }
                }else if(checkBox7.isChecked()){
                    if(!distro.equals("Leap")){
                        shouldShowAds = true;
                        distro = "Leap";
                    }
                }else if(checkBox8.isChecked()){
                    if(!distro.equals("Tumbleweed")){
                        shouldShowAds = true;
                        distro = "Tumbleweed";
                    }
                }else if(checkBox9.isChecked()){
                    if(!distro.equals("Arch")){
                        shouldShowAds = true;
                        distro = "Arch";
                    }
                }
                if(distro.equals("Ubuntu")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Ubuntu/UNI-ubuntu.sh && bash UNI-ubuntu.sh \n\n This should fully remove Ubuntu from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Debian")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Debian/UNI-debian.sh && bash UNI-debian.sh \n\n This should fully remove Debian from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Kali")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Kali/UNI-kali.sh && bash UNI-kali.sh \n\n This should fully remove Kali from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Parrot")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Parrot/UNI-parrot.sh && bash UNI-parrot.sh \n\n This should fully remove Parrot Security OS from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Fedora")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Fedora/UNI-fedora.sh && bash UNI-fedora.sh \n\n This should fully remove Fedora from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Remember: you will need to run ./start-fedora.sh to run the command line.");
                }else if(distro.equals("CentOS")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/CentOS/UNI-centos.sh && bash UNI-centos.sh \n\n This should fully remove CentOS from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Leap")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/Leap/UNI-opensuse-leap.sh && bash UNI-opensuse-leap.sh \n\n This should fully remove openSUSE Leap from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Tumbleweed")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/Tumbleweed/UNI-opensuse-tumbleweed.sh && bash UNI-opensuse-tumbleweed.sh \n\n This should fully remove openSUSE Tumbleweed from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Arch")){
                    if(s.equals("x86_64")){
                        textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Arch/UNI-arch.sh && bash UNI-arch.sh \n\n This should fully remove Arch Linux from your system.");
                        textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                    }else{
                        textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Arch/UNI-arch.sh && bash UNI-arch.sh \n\n This should fully remove Arch Linux from your system.");
                        textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                    }
                }
                button2.setEnabled(true);
                button3.setEnabled(true);
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void notifyUserToChooseDistroARM(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.distro_chooser_arm, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = view.findViewById(R.id.checkBox5);
        final CheckBox checkBox6 = view.findViewById(R.id.checkBox6);
        final CheckBox checkBox7 = view.findViewById(R.id.checkBox7);
        final CheckBox checkBox8 = view.findViewById(R.id.checkBox8);
        final CheckBox checkBox9 = view.findViewById(R.id.checkBox9);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(distro.equals("Ubuntu")){
            checkBox.setChecked(true);
        }else if(distro.equals("Debian")){
            checkBox2.setChecked(true);
        }else if(distro.equals("Kali")){
            checkBox3.setChecked(true);
        }else if(distro.equals("Parrot")){
            checkBox4.setChecked(true);
        }else if(distro.equals("Fedora")){
            checkBox5.setChecked(true);
        }else if(distro.equals("CentOS")){
            checkBox6.setChecked(true);
        }else if(distro.equals("openSUSE")){
            checkBox7.setChecked(true);
        }else if(distro.equals("Arch")){
            checkBox8.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox8.setChecked(false);
            }
        });
        checkBox8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
            }
        });
        if(s.equals("i386")){
            checkBox5.setEnabled(false);
            checkBox7.setEnabled(false);
            checkBox8.setEnabled(false);
            checkBox9.setEnabled(false);
            checkBox5.setText("Not supported");
            checkBox7.setText("Not supported");
            checkBox8.setText("Not supported");
            checkBox9.setText("Not supported");
        }else{
            checkBox9.setEnabled(false);
            checkBox9.setText("Same as Arch Linux");
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!distro.equals("Ubuntu")){
                        shouldShowAds = true;
                        distro = "Ubuntu";
                    }
                }else if(checkBox2.isChecked()){
                    if(!distro.equals("Debian")){
                        shouldShowAds = true;
                        distro = "Debian";
                    }
                }else if(checkBox3.isChecked()){
                    if(!distro.equals("Kali")){
                        shouldShowAds = true;
                        distro = "Kali";
                    }
                }else if(checkBox4.isChecked()){
                    if(!distro.equals("Parrot")){
                        shouldShowAds = true;
                        distro = "Parrot";
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("Fedora")){
                        shouldShowAds = true;
                        distro = "Fedora";
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("CentOS")){
                        shouldShowAds = true;
                        distro = "CentOS";
                    }
                }else if(checkBox7.isChecked()){
                    if(!distro.equals("openSUSE")){
                        shouldShowAds = true;
                        distro = "openSUSE";
                    }
                }else if(checkBox8.isChecked()){
                    if(!distro.equals("Arch")){
                        shouldShowAds = true;
                        distro = "Arch";
                    }
                }
                if(distro.equals("Ubuntu")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Ubuntu/UNI-ubuntu.sh && bash UNI-ubuntu.sh \n\n This should fully remove Ubuntu from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Debian")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Debian/UNI-debian.sh && bash UNI-debian.sh \n\n This should fully remove Debian from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Kali")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Kali/UNI-kali.sh && bash UNI-kali.sh \n\n This should fully remove Kali from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Parrot")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Parrot/UNI-parrot.sh && bash UNI-parrot.sh \n\n This should fully remove Parrot Security OS from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Fedora")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Fedora/UNI-fedora.sh && bash UNI-fedora.sh \n\n This should fully remove Fedora from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("CentOS")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/CentOS/UNI-centos.sh && bash UNI-centos.sh \n\n This should fully remove CentOS from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("openSUSE")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/armhf/UNI-opensuse.sh && bash UNI-opensuse.sh \n\n This should fully remove openSUSE from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }else if(distro.equals("Arch")){
                    textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Arch/UNI-arch.sh && bash UNI-arch.sh \n\n This should fully remove Arch Linux from your system.");
                    textView3.setText("Step 3 : Start Termux, paste and enter the command to uninstall distro. Important: you will need to run this command inside Termux shell and NOT IN Linux Shell.");
                }
                button2.setEnabled(true);
                button3.setEnabled(true);
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    public void notifyUserForInstallTerminal(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=com.termux");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(Build.VERSION.SDK_INT >= 21){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                }
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.termux")));
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
        textView.setText("Termux is not installed, do you want to install it now ?");
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
