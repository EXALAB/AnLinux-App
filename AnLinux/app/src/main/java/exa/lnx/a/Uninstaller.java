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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        getActivity().setTitle(R.string.uninstall);

        context = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.uninstaller, container, false);

        distro = "Nothing";

        s = Build.SUPPORTED_ABIS[0];

        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);

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
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Ubuntu/UNI-ubuntu.sh && bash UNI-ubuntu.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Debian")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Debian/UNI-debian.sh && bash UNI-debian.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Kali")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Kali/UNI-kali.sh && bash UNI-kali.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Nethunter")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Nethunter/UNI-nethunter.sh && bash UNI-nethunter.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Parrot")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Parrot/UNI-parrot.sh && bash UNI-parrot.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("BackBox")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/BackBox/UNI-backbox.sh && bash UNI-backbox.sh");
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
                }else if(distro.equals("Alpine")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Alpine/UNI-alpine.sh && bash UNI-alpine.sh");
                    clipboard.setPrimaryClip(clip);
                }else if(distro.equals("Void")){
                    ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Void/UNI-void.sh && bash UNI-void.sh");
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
        }else if(distro.equals("CentOS")){
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
            checkBox9.setEnabled(false);
            checkBox11.setEnabled(false);
            checkBox12.setEnabled(false);
            checkBox7.setText(R.string.not_Supported);
            checkBox9.setText(R.string.not_Supported);
            checkBox11.setText(R.string.not_Supported);
            checkBox12.setText(R.string.not_Supported);
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
                    if(!distro.equals("CentOS")){
                        distro = "CentOS";
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
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Ubuntu/UNI-ubuntu.sh && bash UNI-ubuntu.sh", "Ubuntu"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Debian")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Debian/UNI-debian.sh && bash UNI-debian.sh", "Debian"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Kali")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Kali/UNI-kali.sh && bash UNI-kali.sh", "Kali"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Nethunter")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Nethunter/UNI-nethunter.sh && bash UNI-nethunter.sh", "Kali Nethunter"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Parrot")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Parrot/UNI-parrot.sh && bash UNI-parrot.sh", "Parrot Security OS"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("BackBox")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/BackBox/UNI-backbox.sh && bash UNI-backbox.sh", "BackBox"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Fedora")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Fedora/UNI-fedora.sh && bash UNI-fedora.sh", "Fedora"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("CentOS")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/CentOS/UNI-centos.sh && bash UNI-centos.sh", "CentOS"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Leap")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/Leap/UNI-opensuse-leap.sh && bash UNI-opensuse-leap.sh", "openSUSE Leap"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Tumbleweed")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/openSUSE/Tumbleweed/UNI-opensuse-tumbleweed.sh && bash UNI-opensuse-tumbleweed.sh", "openSUSE Tumbleweed"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Arch")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Arch/UNI-arch.sh && bash UNI-arch.sh", "Arch Linux"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Alpine")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Alpine/UNI-alpine.sh && bash UNI-alpine.sh", "Alpine"));
                    textView3.setText(R.string.uninstall_step3);
                }else if(distro.equals("Void")){
                    textView2.setText(getString(R.string.uninstall_step2, "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/Uninstaller/Void/UNI-void.sh && bash UNI-void.sh", "Void Linux"));
                    textView3.setText(R.string.uninstall_step3);
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
}
