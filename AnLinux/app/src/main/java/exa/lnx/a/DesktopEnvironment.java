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

public class DesktopEnvironment extends Fragment {

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
        getActivity().setTitle(R.string.desktop_title);

        View view = inflater.inflate(R.layout.desktop_environment, container, false);

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

        textView2.setText(R.string.de_step2_choose_first);
        textView3.setText(R.string.de_step3_choose_first);
        textView4.setText(R.string.de_step4_choose_first);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyUserToChooseDistro();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyUserToChooseDesktop();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(distro.equals("Ubuntu") | distro.equals("Debian") | distro.equals("Kali") | distro.equals("Parrot") | distro.equals("BackBox")){
                    if(desktop.equals("Xfce4")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh --no-check-certificate && bash de-apt-xfce4.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(desktop.equals("Mate")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh --no-check-certificate && bash de-apt-mate.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(desktop.equals("LXQt")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh --no-check-certificate && bash de-apt-lxqt.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(desktop.equals("LXDE")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh --no-check-certificate && bash de-apt-lxde.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }else if(distro.equals("Fedora")){
                    if(s.contains("arm") && !s.equals("arm64-v8a")){
                        if(desktop.equals("Xfce4")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Xfce4/de-yum-xfce4.sh --no-check-certificate && bash de-yum-xfce4.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("Mate")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Mate/de-yum-mate.sh --no-check-certificate && bash de-yum-mate.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXQt")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXQt/de-yum-lxqt.sh --no-check-certificate && bash de-yum-lxqt.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXDE")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXDE/de-yum-lxde.sh --no-check-certificate && bash de-yum-lxde.sh");
                            clipboard.setPrimaryClip(clip);
                        }
                    }else{
                        if(desktop.equals("Xfce4")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Xfce4/de-yum-xfce4.sh --no-check-certificate && bash de-yum-xfce4.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("Mate")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Mate/de-yum-mate.sh --no-check-certificate && bash de-yum-mate.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXQt")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXQt/de-yum-lxqt.sh --no-check-certificate && bash de-yum-lxqt.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXDE")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXDE/de-yum-lxde.sh --no-check-certificate && bash de-yum-lxde.sh");
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                }else if(distro.equals("Arch")){
                    if(s.contains("arm")){
                        ClipData clip = ClipData.newPlainText("Command", "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh --no-check-certificate && bash de-pac.sh");
                        clipboard.setPrimaryClip(clip);
                    }else{
                        ClipData clip = ClipData.newPlainText("Command", "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh --no-check-certificate && bash de-pac.sh");
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
    public void notifyUserToChooseDistro(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.desktop_environment_chooser, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = view.findViewById(R.id.checkBox5);
        final CheckBox checkBox6 = view.findViewById(R.id.checkBox6);
        final CheckBox checkBox7 = view.findViewById(R.id.checkBox7);

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
        }else if(distro.equals("Arch")){
            checkBox7.setChecked(true);
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
            }
        });
        if(s.equals("i386")){
            checkBox6.setEnabled(false);
            checkBox6.setText(R.string.not_Supported);
            checkBox7.setEnabled(false);
            checkBox7.setText(R.string.not_Supported);
        }else if(s.contains("arm") && !s.contains("64")){
            checkBox6.setEnabled(false);
            checkBox6.setText(R.string.not_Supported);
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!distro.equals("Ubuntu")){
                        distro = "Ubuntu";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
                    }
                }else if(checkBox2.isChecked()){
                    if(!distro.equals("Debian")){
                        distro = "Debian";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
                    }
                }else if(checkBox3.isChecked()){
                    if(!distro.equals("Kali")){
                        distro = "Kali";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
                    }
                }else if(checkBox4.isChecked()){
                    if(!distro.equals("Parrot")){
                        distro = "Parrot";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("BackBox")){
                        distro = "BackBox";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("Fedora")){
                        distro = "Fedora";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
                    }
                }else if(checkBox7.isChecked()){
                    if(!distro.equals("Arch")){
                        distro = "Arch";
                        textView2.setText(R.string.de_step2_choose_first);
                        button2.setEnabled(true);
                        textView3.setText(R.string.de_step3_choose_first);
                        textView4.setText(R.string.de_step4_choose_first);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        desktop = "Nothing";
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
    public void notifyUserToChooseDesktop(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.desktop_environment_chooser2, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(desktop.equals("Xfce4")){
            if(!distro.equals("Arch")){
                checkBox.setChecked(true);
            }
        }else if(desktop.equals("Mate")){
            if(!distro.equals("Arch")){
                checkBox2.setChecked(true);
            }
        }else if(desktop.equals("LXQt")){
            if(!distro.equals("Arch")){
                checkBox3.setChecked(true);
            }
        }else if(desktop.equals("LXDE")){
            checkBox4.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
            }
        });
        if(distro.equals("Arch")){
            checkBox.setText(R.string.not_available);
            checkBox2.setText(R.string.not_available);
            checkBox3.setText(R.string.not_available);
            checkBox.setEnabled(false);
            checkBox2.setEnabled(false);
            checkBox3.setEnabled(false);
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!desktop.equals("Xfce4")){
                        desktop = "Xfce4";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox2.isChecked()){
                    if(!desktop.equals("Mate")){
                        desktop = "Mate";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox3.isChecked()){
                    if(!desktop.equals("LXQt")){
                        desktop = "LXQt";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }else if(checkBox4.isChecked()){
                    if(!desktop.equals("LXDE")){
                        desktop = "LXDE";
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                    }
                }
                if(distro.equals("Ubuntu")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh --no-check-certificate && bash de-apt-xfce4.sh", "Xfce"));
                        textView4.setText(getString(R.string.gui_step3, "./start-ubuntu.sh"));
                    }else if(desktop.equals("Mate")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh --no-check-certificate && bash de-apt-mate.sh", "Mate"));
                        textView4.setText(getString(R.string.gui_step3, "./start-ubuntu.sh"));
                    }else if(desktop.equals("LXQt")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh --no-check-certificate && bash de-apt-lxqt.sh", "LXQt"));
                        textView4.setText(getString(R.string.gui_step3, "./start-ubuntu.sh"));
                    }else if(desktop.equals("LXDE")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh --no-check-certificate && bash de-apt-lxde.sh", "LXDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-ubuntu.sh"));
                    };
                }else if(distro.equals("Debian")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh --no-check-certificate && bash de-apt-xfce4.sh", "Xfce"));
                        textView4.setText(getString(R.string.gui_step3, "./start-debian.sh"));
                    }else if(desktop.equals("Mate")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh --no-check-certificate && bash de-apt-mate.sh", "Mate"));
                        textView4.setText(getString(R.string.gui_step3, "./start-debian.sh"));
                    }else if(desktop.equals("LXQt")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh --no-check-certificate && bash de-apt-lxqt.sh", "LXQt"));
                        textView4.setText(getString(R.string.gui_step3, "./start-debian.sh"));
                    }else if(desktop.equals("LXDE")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh --no-check-certificate && bash de-apt-lxde.sh", "LXDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-debian.sh"));
                    }
                }else if(distro.equals("Kali")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh --no-check-certificate && bash de-apt-xfce4.sh", "Xfce"));
                        textView4.setText(getString(R.string.gui_step3, "./start-kali.sh"));
                    }else if(desktop.equals("Mate")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh --no-check-certificate && bash de-apt-mate.sh", "Mate"));
                        textView4.setText(getString(R.string.gui_step3, "./start-kali.sh"));
                    }else if(desktop.equals("LXQt")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh --no-check-certificate && bash de-apt-lxqt.sh", "LXQt"));
                        textView4.setText(getString(R.string.gui_step3, "./start-kali.sh"));
                    }else if(desktop.equals("LXDE")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh --no-check-certificate && bash de-apt-lxde.sh", "LXDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-kali.sh"));
                    }
                }else if(distro.equals("Parrot")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh --no-check-certificate && bash de-apt-xfce4.sh", "Xfce"));
                        textView4.setText(getString(R.string.gui_step3, "./start-parrot.sh"));
                    }else if(desktop.equals("Mate")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh --no-check-certificate && bash de-apt-mate.sh", "Mate"));
                        textView4.setText(getString(R.string.gui_step3, "./start-parrot.sh"));
                    }else if(desktop.equals("LXQt")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh --no-check-certificate && bash de-apt-lxqt.sh", "LXQt"));
                        textView4.setText(getString(R.string.gui_step3, "./start-parrot.sh"));
                    }else if(desktop.equals("LXDE")){
                        textView3.setText(getString(R.string.gui_step2, "apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh --no-check-certificate && bash de-apt-lxde.sh", "LXDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-parrot.sh"));
                    }
                }else if(distro.equals("Fedora")){
                    if(s.contains("arm") && !s.equals("arm64-v8a")){
                        if(desktop.equals("Xfce4")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Xfce4/de-yum-xfce4.sh --no-check-certificate && bash de-yum-xfce4.sh", "Xfce"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else if(desktop.equals("Mate")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Mate/de-yum-mate.sh --no-check-certificate && bash de-yum-mate.sh", "Mate"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else if(desktop.equals("LXQt")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXQt/de-yum-lxqt.sh --no-check-certificate && bash de-yum-lxqt.sh", "LXQt"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else if(desktop.equals("LXDE")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXDE/de-yum-lxde.sh --no-check-certificate && bash de-yum-lxde.sh", "LXDE"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }
                    }else{
                        if(desktop.equals("Xfce4")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Xfce4/de-yum-xfce4.sh --no-check-certificate && bash de-yum-xfce4.sh", "Xfce"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else if(desktop.equals("Mate")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Mate/de-yum-mate.sh --no-check-certificate && bash de-yum-mate.sh", "Mate"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else if(desktop.equals("LXQt")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXQt/de-yum-lxqt.sh --no-check-certificate && bash de-yum-lxqt.sh", "LXQt"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }else if(desktop.equals("LXDE")){
                            textView3.setText(getString(R.string.gui_step2, "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXDE/de-yum-lxde.sh --no-check-certificate && bash de-yum-lxde.sh", "LXDE"));
                            textView4.setText(getString(R.string.gui_step3, "./start-fedora.sh"));
                        }
                    }
                }else if(distro.equals("Arch")){
                    if(s.contains("arm")){
                        textView3.setText(getString(R.string.gui_step2, "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh --no-check-certificate && bash de-pac.sh", "LXDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-arch.sh"));
                    }else{
                        textView3.setText(getString(R.string.gui_step2, "pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh --no-check-certificate && bash de-pac.sh", "LXDE"));
                        textView4.setText(getString(R.string.gui_step3, "./start-arch.sh"));
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
