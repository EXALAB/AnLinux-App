package exa.lnx.a;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class HeavyDE extends Fragment {

    Context context;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    String distro;
    String desktop;
    String s;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle(R.string.heavy_de);

        View view = inflater.inflate(R.layout.heavyde, container, false);

        context = getActivity().getApplicationContext();

        distro = "Nothing";
        desktop = "Nothing";

        s = Build.SUPPORTED_ABIS[0];

        button = view.findViewById(R.id.button);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);

        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);

        textView2.setText(R.string.heavy_de_step2_choose_first);
        textView3.setText(R.string.heavy_de_step3_choose_first);
        textView4.setText(R.string.heavy_de_step4_choose_first);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyUserToChooseDesktop();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyUserToChooseDistro();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(desktop.equals("KDE")){
                    if(distro.equals("Ubuntu") | distro.equals("BackBox")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Ubuntu/de-ubuntu-kde.sh && bash de-ubuntu-kde.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(distro.equals("Fedora")){
                        if(s.contains("arm") && !s.equals("arm64-v8a")){
                            ClipData clip = ClipData.newPlainText("Command", "wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Yum/arm/de-yum-kde.sh && bash de-yum-kde.sh");
                            clipboard.setPrimaryClip(clip);
                        }else{
                            ClipData clip = ClipData.newPlainText("Command", "wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Yum/de-yum-kde.sh && bash de-yum-kde.sh");
                            clipboard.setPrimaryClip(clip);
                        }
                    } else{
                        ClipData clip = ClipData.newPlainText("Command", "wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Apt/de-apt-kde.sh && bash de-apt-kde.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }
                Toast.makeText(context, getString(R.string.command_copied), Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.termux");
                if(isPackageInstalled("com.termux", context.getPackageManager()) && termuxVersionCode() >= 118){
                    startActivity(intent);
                }else{
                    notifyUserForInstallTerminal();
                }
            }
        });

        return view;
    }
    public void notifyUserToChooseDesktop(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.heavyde_chooser, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(desktop.equals("KDE")){
            checkBox.setChecked(true);
        }

        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!desktop.equals("KDE")){
                        desktop = "KDE";
                        textView2.setText(R.string.heavy_de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.heavy_de_step3_choose_first);
                        textView4.setText(R.string.heavy_de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        distro = "Nothing";
                    }
                }else{
                    if(desktop.equals("KDE")){
                        desktop = "nothing";
                    }
                }
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
    public void notifyUserToChooseDistro(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.heavyde_chooser2, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = view.findViewById(R.id.checkBox5);
        final CheckBox checkBox6 = view.findViewById(R.id.checkBox6);

        if(distro.equals("Ubuntu")){
            checkBox.setChecked(true);
        }else if(distro.equals("Debian")){
            checkBox2.setChecked(true);
        }else if(distro.equals("Kali")){
            checkBox3.setChecked(true);
        }else if(distro.equals("Parrot")){
            checkBox4.setChecked(true);
        }else if(distro.equals("BackBox")){
            checkBox5.setChecked(true);
        }else if(distro.equals("Fedora")){
            checkBox6.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
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
            }
        });

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!distro.equals("Ubuntu")){
                        distro = "Ubuntu";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox2.isChecked()){
                    if(!distro.equals("Debian")){
                        distro = "Debian";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox3.isChecked()){
                    if(!distro.equals("Kali")){
                        distro = "Kali";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox4.isChecked()){
                    if(!distro.equals("Parrot")){
                        distro = "Parrot";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("BackBox")){
                        distro = "BackBox";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("Fedora")){
                        distro = "Fedora";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }
                if(desktop.equals("KDE")){
                    if(distro.equals("Ubuntu")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Ubuntu/de-ubuntu-kde.sh && bash de-ubuntu-kde.sh", "KDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-ubuntu.sh"));
                    }else if(distro.equals("Debian")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Apt/de-apt-kde.sh && bash de-apt-kde.sh", "KDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-debian.sh"));
                    }else if(distro.equals("Kali")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Apt/de-apt-kde.sh && bash de-apt-kde.sh", "KDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-kali.sh"));
                    }else if(distro.equals("Parrot")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Apt/de-apt-kde.sh && bash de-apt-kde.sh", "KDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-parrot.sh"));
                    }else if(distro.equals("BackBox")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Ubuntu/de-ubuntu-kde.sh && bash de-ubuntu-kde.sh", "KDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-backbox.sh"));
                    }else if(distro.equals("Fedora")){
                        if(s.contains("arm") && !s.equals("arm64-v8a")){
                            textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Yum/arm/de-yum-kde.sh && bash de-yum-kde.sh", "KDE"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else{
                            textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Yum/de-yum-kde.sh && bash de-yum-kde.sh", "KDE"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }
                    }
                }
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
                Uri uri = Uri.parse("https://f-droid.org/en/packages/com.termux/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(Build.VERSION.SDK_INT >= 21){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                }
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://f-droid.org/en/packages/com.termux/")));
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
        if(termuxVersionCode() == 0){
            textView.setText(R.string.termux_not_Installed);
        }else if(termuxVersionCode() < 118){
            textView.setText(R.string.termux_not_fdroid);
        }
    }
    private int termuxVersionCode(){
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo("com.termux", 0);
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("error", "Package not found");
            return 0;
        }
    }
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}