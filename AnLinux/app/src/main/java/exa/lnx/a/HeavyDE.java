package exa.lnx.a;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
                    if(distro.equals("Ubuntu")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Ubuntu/de-ubuntu-kde.sh && bash de-ubuntu-kde.sh");
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
                if(isPackageInstalled("com.termux", context.getPackageManager())){
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
                }else{
                    if(distro.equals("Ubuntu")){
                        distro = "nothing";
                    }
                }
                if(desktop.equals("KDE")){
                    if(distro.equals("Ubuntu")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://github.com/EXALAB/Anlinux-Resources/raw/master/Scripts/DesktopEnvironment/Heavy/KDE/Ubuntu/de-ubuntu-kde.sh && bash de-ubuntu-kde.sh", "KDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-ubuntu.sh"));
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
        textView.setText(R.string.termux_not_Installed);
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
