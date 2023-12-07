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

public class DashBoard extends Fragment {

    Context context;
    Button button;
    Button button2;
    Button button3;
    TextView textView2;
    TextView textView3;
    String distro;
    String s;
    boolean isNethunterNotified;
    SharedPreferences sharedPreferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        getActivity().setTitle(R.string.dashboard);

        View view = inflater.inflate(R.layout.dashboard, container, false);

        context = getActivity().getApplicationContext();
        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);
        isNethunterNotified = sharedPreferences.getBoolean("IsNethunterNotified", false);

        distro = "Nothing";

        s = Build.SUPPORTED_ABIS[0];

        if(s.equals("mips") | s.equals("mips64")){
            Toast.makeText(context, "Your device is not supported", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }

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
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Ubuntu/ubuntu.sh && bash ubuntu.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Debian")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Debian/debian.sh && bash debian.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Kali")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Kali/kali.sh && bash kali.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Nethunter")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Nethunter/nethunter.sh && bash nethunter.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Parrot")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Parrot/parrot.sh && bash parrot.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("BackBox")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/BackBox/backbox.sh && bash backbox.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Fedora")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Fedora/fedora.sh && bash fedora.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("CentOS Stream")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/CentOS_Stream/centos_stream.sh && bash centos_stream.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Leap")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/openSUSE/Leap/opensuse-leap.sh && bash opensuse-leap.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Tumbleweed")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/openSUSE/Tumbleweed/opensuse-tumbleweed.sh && bash opensuse-tumbleweed.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("openSUSE")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/openSUSE/armhf/opensuse.sh && bash opensuse.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Arch")){
                    if(s.equals("x86_64")){
                        ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Arch/amd64/arch.sh && bash arch.sh");
                        clipboard.setPrimaryClip(clip);
                    }else{
                        ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Arch/armhf/arch.sh && bash arch.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }else if(distro.equals("BlackArch")){
                    ClipData clip = ClipData.newPlainText("Command", "pacman-key --init && pacman-key --populate archlinuxarm && pacman -Sy --noconfirm curl && curl -O https://blackarch.org/strap.sh && chmod +x strap.sh && ./strap.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Alpine")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Alpine/alpine.sh && bash alpine.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Void")){
                    ClipData clip = ClipData.newPlainText("Command", "pkg install wget openssl-tool proot tar -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Void/void.sh && bash void.sh");
                    clipboard.setPrimaryClip(clip);
                }
                Toast.makeText(context, getString(R.string.command_copied), Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
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
        final CheckBox checkBox11 = view.findViewById(R.id.checkBox11);
        final CheckBox checkBox12 = view.findViewById(R.id.checkBox12);
        final CheckBox checkBox13 = view.findViewById(R.id.checkBox13);
        final CheckBox checkBox14 = view.findViewById(R.id.checkBox14);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(distro.equals("Ubuntu")){
            checkBox.setChecked(true);
        }else if(distro.equals("Debian")){
            checkBox2.setChecked(true);
        }else if(distro.equals("Kali")){
            checkBox3.setChecked(true);
        }else if(distro.equals("Nethunter")){
            checkBox4.setChecked(true);
        }else if(distro.equals("Parrot")){
            checkBox5.setChecked(true);
        }else if(distro.equals("BackBox")){
            checkBox6.setChecked(true);
        }else if(distro.equals("Fedora")){
            checkBox7.setChecked(true);
        }else if(distro.equals("CentOS Stream")){
            checkBox8.setChecked(true);
        }else if(distro.equals("Leap")){
            checkBox9.setChecked(true);
        }else if(distro.equals("Tumbleweed")){
            checkBox10.setChecked(true);
        }else if(distro.equals("Arch")){
            checkBox11.setChecked(true);
        }else if(distro.equals("BlackArch")){
            checkBox12.setChecked(true);
        }else if(distro.equals("Alpine")){
            checkBox13.setChecked(true);
        }else if(distro.equals("Void")){
            checkBox14.setChecked(true);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
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
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
            }
        });
        checkBox10.setOnClickListener(new View.OnClickListener() {
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
                checkBox9.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
            }
        });
        checkBox11.setOnClickListener(new View.OnClickListener() {
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
                checkBox9.setChecked(false);
                checkBox10.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
            }
        });
        checkBox12.setOnClickListener(new View.OnClickListener() {
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
                checkBox9.setChecked(false);
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox13.setChecked(false);
                checkBox14.setChecked(false);
            }
        });
        checkBox13.setOnClickListener(new View.OnClickListener() {
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
                checkBox9.setChecked(false);
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox14.setChecked(false);
            }
        });
        checkBox14.setOnClickListener(new View.OnClickListener() {
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
                checkBox9.setChecked(false);
                checkBox10.setChecked(false);
                checkBox11.setChecked(false);
                checkBox12.setChecked(false);
                checkBox13.setChecked(false);
            }
        });
        if(s.equals("i386")){
            checkBox7.setEnabled(false);
            checkBox8.setEnabled(false);
            checkBox9.setEnabled(false);
            checkBox11.setEnabled(false);
            checkBox12.setEnabled(false);
            checkBox7.setText(R.string.not_Supported);
            checkBox8.setText(R.string.not_Supported);
            checkBox9.setText(R.string.not_Supported);
            checkBox11.setText(R.string.not_Supported);
            checkBox12.setText(R.string.not_Supported);
        }else if(s.contains("arm") && !s.contains("64")){
            checkBox7.setEnabled(false);
            checkBox8.setEnabled(false);
            checkBox7.setText(R.string.not_Supported);
            checkBox8.setText(R.string.not_Supported);
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
                    if(!distro.equals("Nethunter")){
                        distro = "Nethunter";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                        if(!isNethunterNotified){
                            notifyUserForNethunter();
                        }
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("Parrot")){
                        distro = "Parrot";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("BackBox")){
                        distro = "BackBox";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox7.isChecked()){
                    if(!distro.equals("Fedora")){
                        distro = "Fedora";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox8.isChecked()){
                    if(!distro.equals("CentOS Strean")){
                        distro = "CentOS Stream";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox9.isChecked()){
                    if(!distro.equals("Leap")){
                        distro = "Leap";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox10.isChecked()){
                    if(!distro.equals("Tumbleweed")){
                        distro = "Tumbleweed";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox11.isChecked()){
                    if(!distro.equals("Arch")){
                        distro = "Arch";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox12.isChecked()){
                    if(!distro.equals("BlackArch")){
                        distro = "BlackArch";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox13.isChecked()){
                    if(!distro.equals("Alpine")){
                        distro = "Alpine";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }else if(checkBox14.isChecked()){
                    if(!distro.equals("Void")){
                        distro = "Void";
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                    }
                }
                if(distro.equals("Ubuntu")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Ubuntu/ubuntu.sh && bash ubuntu.sh", "Ubuntu", "./start-ubuntu.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-ubuntu.sh"));
                }else if(distro.equals("Debian")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Debian/debian.sh && bash debian.sh", "Debian", "./start-debian.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-debian.sh"));
                }else if(distro.equals("Kali")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Kali/kali.sh && bash kali.sh", "Debian", "./start-kali.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-kali.sh"));
                }else if(distro.equals("Nethunter")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Nethunter/nethunter.sh && bash nethunter.sh", "Kali Nethunter", "./start-nethunter.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-nethunter.sh"));
                }else if(distro.equals("Parrot")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Parrot/parrot.sh && bash parrot.sh", "Parrot Security OS", "./start-parrot.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-parrot.sh"));
                }else if(distro.equals("BackBox")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/BackBox/backbox.sh && bash backbox.sh", "BackBox", "./start-backbox.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-backbox.sh"));
                }else if(distro.equals("Fedora")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Fedora/fedora.sh && bash fedora.sh", "Fedora", "./start-fedora.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-fedora.sh"));
                }else if(distro.equals("CentOS Stream")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/CentOS_Stream/centos_stream.sh && bash centos_stream.sh", "CentOS Stream", "./start-centos_stream.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-centos_stream.sh"));
                }else if(distro.equals("Leap")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/openSUSE/Leap/opensuse-leap.sh && bash opensuse-leap.sh", "openSUSE Leap", "./start-leap.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-leap.sh"));
                }else if(distro.equals("Tumbleweed")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/openSUSE/Tumbleweed/opensuse-tumbleweed.sh && bash opensuse-tumbleweed.sh", "openSUSE Tumbleweed", "./start-tumbleweed.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-tumbleweed.sh"));
                }else if(distro.equals("Arch")){
                    if(s.equals("x86_64")){
                        textView2.setText(getString(R.string.dashboard_step2_arch, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Arch/amd64/arch.sh && bash arch.sh", "Arch Linux", "./start-arch.sh"));
                        textView3.setText(R.string.dashboard_step3_arch);
                    }else{
                        textView2.setText(getString(R.string.dashboard_step2_arch, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Arch/armhf/arch.sh && bash arch.sh", "Arch Linux", "./start-arch.sh"));
                        textView3.setText(R.string.dashboard_step3_arch);
                    }
                    notifyUserForArchLinux();
                }else if(distro.equals("BlackArch")){
                    textView2.setText(getString(R.string.dashboard_step2_blackarch,"pacman-key --init && pacman-key --populate archlinuxarm && pacman -Sy --noconfirm curl && curl -O https://blackarch.org/strap.sh && chmod +x strap.sh && ./strap.sh"));
                    textView3.setText(R.string.dashboard_step3_blackarch);
                }else if(distro.equals("Alpine")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Alpine/alpine.sh && bash alpine.sh", "Alpine", "./start-alpine.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-alpine.sh"));
                }else if(distro.equals("Void")){
                    textView2.setText(getString(R.string.dashboard_step2, "pkg install wget openssl-tool proot -y && hash -r && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Installer/Void/void.sh && bash void.sh", "Void", "./start-void.sh"));
                    textView3.setText(getString(R.string.dashboard_step3, "./start-void.sh"));
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
    public void notifyUserForArchLinux(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText(R.string.arch_linux_warning_content);
    }
    public void notifyUserForNethunter(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsNethunterNotified", true);
                editor.apply();
                isNethunterNotified = sharedPreferences.getBoolean("IsNethunterNotified", false);
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText(R.string.nethunter_warning_content);
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
