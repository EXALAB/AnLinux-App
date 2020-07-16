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
import android.widget.Toast;

public class SSH extends Fragment {

    Context context;
    SharedPreferences sharedPreferences;
    Button button;
    Button button2;
    Button button3;
    TextView textView2;
    TextView textView3;
    String distro;
    String s;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle(R.string.ssh_title);

        View view = inflater.inflate(R.layout.ssh, container, false);

        context = getActivity().getApplicationContext();

        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);

        distro = "Nothing";

        s = Build.SUPPORTED_ABIS[0];

        button = view.findViewById(R.id.button);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);

        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);

        textView2.setText(R.string.step2_cd);
        textView3.setText(R.string.step3_cd);
        button2.setEnabled(false);
        button3.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyUserToChooseDistro();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(distro.equals("Ubuntu")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Debian")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Kali")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Parrot")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("BackBox")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Fedora")){
                    if(s.contains("arm") && !s.equals("arm64-v8a")){
                        ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/ssh-yum.sh && bash ssh-yum.sh");
                        clipboard.setPrimaryClip(clip);
                    }else{
                        ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/ssh-yum.sh && bash ssh-yum.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }else if(distro.equals("CentOS")){
                    ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/ssh-yum.sh && bash ssh-yum.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Arch")){
                    if(s.contains("arm")){
                        ClipData clip = ClipData.newPlainText("Command", "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Pacman/ssh-pac.sh && bash ssh-pac.sh");
                        clipboard.setPrimaryClip(clip);
                    }else{
                        ClipData clip = ClipData.newPlainText("Command", "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Pacman/ssh-pac.sh && bash ssh-pac.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }
                Toast.makeText(context, getString(R.string.command_copied), Toast.LENGTH_SHORT).show();
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

        return view;
    }
    public void notifyUserToChooseDistro(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.ssh_chooser, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = view.findViewById(R.id.checkBox5);
        final CheckBox checkBox6 = view.findViewById(R.id.checkBox6);
        final CheckBox checkBox7 = view.findViewById(R.id.checkBox7);
        final CheckBox checkBox8 = view.findViewById(R.id.checkBox8);

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
        }else if(distro.equals("BackBox")){
            checkBox5.setChecked(true);
        }else if(distro.equals("Fedora")){
            checkBox6.setChecked(true);
        }else if(distro.equals("CentOS")){
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
            checkBox6.setEnabled(false);
            checkBox6.setText(R.string.not_Supported);
            checkBox8.setEnabled(false);
            checkBox8.setText(R.string.not_Supported);
        }else if(s.contains("arm")){
            checkBox4.setEnabled(false);
            checkBox4.setText(R.string.not_Supported);
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!distro.equals("Ubuntu")){
                        distro = "Ubuntu";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox2.isChecked()){
                    if(!distro.equals("Debian")){
                        distro = "Debian";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox3.isChecked()){
                    if(!distro.equals("Kali")){
                        distro = "Kali";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox4.isChecked()){
                    if(!distro.equals("Parrot")){
                        distro = "Parrot";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("BackBox")){
                        distro = "BackBox";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("Fedora")){
                        distro = "Fedora";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox7.isChecked()){
                    if(!distro.equals("CentOS")){
                        distro = "CentOS";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox8.isChecked()){
                    if(!distro.equals("Arch")){
                        distro = "Arch";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }
                if(distro.equals("Ubuntu")){
                    textView2.setText(getString(R.string.ssh_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh"));
                    textView3.setText(getString(R.string.ssh_step3, "./start-ubuntu.sh"));
                }else if(distro.equals("Debian")){
                    textView2.setText(getString(R.string.ssh_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh"));
                    textView3.setText(getString(R.string.ssh_step3, "./start-debian.sh"));
                }else if(distro.equals("Kali")){
                    textView2.setText(getString(R.string.ssh_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh"));
                    textView3.setText(getString(R.string.ssh_step3, "./start-kali.sh"));
                }else if(distro.equals("Parrot")){
                    textView2.setText(getString(R.string.ssh_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh"));
                    textView3.setText(getString(R.string.ssh_step3, "./start-parrot.sh"));
                }else if(distro.equals("BackBox")){
                    textView2.setText(getString(R.string.ssh_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh"));
                    textView3.setText(getString(R.string.ssh_step3, "./start-backbox.sh"));
                }else if(distro.equals("Fedora")){
                    if(s.contains("arm") && !s.equals("arm64-v8a")){
                        textView2.setText(getString(R.string.ssh_step2, "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/arm/ssh-yum.sh && bash ssh-yum.sh"));
                        textView3.setText(getString(R.string.ssh_step3, "./start-fedora.sh"));
                    }else{
                        textView2.setText(getString(R.string.ssh_step2, "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/ssh-yum.sh && bash ssh-yum.sh"));
                        textView3.setText(getString(R.string.ssh_step3, "./start-fedora.sh"));
                    }
                }else if(distro.equals("CentOS")){
                    textView2.setText(getString(R.string.ssh_step2, "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/ssh-yum.sh && bash ssh-yum.sh"));
                    textView3.setText(getString(R.string.ssh_step3, "./start-centos.sh"));
                }else if(distro.equals("Arch")){
                    if(s.contains("arm")){
                        textView2.setText(getString(R.string.ssh_step2, "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Pacman/ssh-pac.sh && bash ssh-pac.sh"));
                        textView3.setText(getString(R.string.ssh_step3, "./start-arch.sh"));
                    }else{
                        textView2.setText(getString(R.string.ssh_step2, "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Pacman/ssh-pac.sh && bash ssh-pac.sh"));
                        textView3.setText(getString(R.string.ssh_step3, "./start-arch.sh"));
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
