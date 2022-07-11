This is repo for the main application. If you are looking for the images and scripts used in this app, please visit [here](https://github.com/EXALAB/AnLinux-Resources)

# AnLinux
Run Linux On Android Without Root Access, thanks for the Awesome [Termux](https://github.com/termux/termux-app) and [PRoot](https://github.com/proot-me/PRoot), which make this project possible.

<a href='https://play.google.com/store/apps/details?id=exa.lnx.a'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="100"/></a>

Or 

[<img src="https://user-images.githubusercontent.com/663460/26973090-f8fdc986-4d14-11e7-995a-e7c5e79ed925.png" alt="Download APK from GitHub" height="100">](https://github.com/EXALAB/AnLinux-App/releases/latest) 

if you don't have access to Play Store.



## How it works

The bash script download image over internet, then decompress the image and then mount it using [PRoot](https://github.com/proot-me/PRoot).



## Desktop Environment

We currently support 5 Desktop Environments, and only some distros are supported.

Supported Desktop Environments:

1. [Xfce4](https://xfce.org)
2. [Mate](https://mate-desktop.org)
3. [LXQt](https://lxqt.org)
4. [LXDE](https://lxde.org)
5. [KDE](https://kde.org)



### Window Manager

We currently support 2 Desktop Environments, and only [Ubuntu](https://www.ubuntu.com/), [Debian](https://www.debian.org/), [Kali](https://www.kali.org/), [Parrot Security OS](https://www.parrotsec.org/), [Fedora](https://getfedora.org/) distros are supported.

Supported Window Manager:

1. [Awesome](https://awesomewm.org)
2. [IceWM](https://ice-wm.org/)



## Future target and todo:

1. KDE and GNOME DE support. (Might retest other blacklisted DE too)
2. Audio support.
3. Fix PRoot error.
4. Might consider support BSD distro.



If you have any tweak, suggestion, recommendation, please open an issue on Github.



## Note

1. This app requires [Termux](https://github.com/termux/termux-app) to work, it can be install from Play Store or from [F-Droid](https://f-droid.org) if you don't have access to Play Store

2. About device requirements:

   Android Version : At least Android Lollipop

   Architecture : armv7, arm64, x86, x86_64

3. Currently supported distros:

   [Ubuntu](https://www.ubuntu.com/), [Debian](https://www.debian.org/), [Kali](https://www.kali.org/), [Kali Nethunter](https://www.kali.org/kali-linux-nethunter/), [Parrot Security OS](https://www.parrotsec.org/), [BackBox](https://www.backbox.org/), [Fedora](https://getfedora.org/), [CentOS](https://www.centos.org/), [openSUSE Leap](https://www.opensuse.org/), [openSUSE Tumbleweed](https://www.opensuse.org/), [Arch Linux](https://www.archlinux.org/), [BlackArch](https://blackarch.org/), [Alpine](https://alpinelinux.org/), [Void](https://voidlinux.org/)

4. For any suggestion or issue, please open an issue on Github.

5. The support of Windows Manager has been removed, because of /dev/tty0 could not be found error.



## Extra License

The author of application icon is [Alpár-Etele Méder](https://www.iconfinder.com/pocike)

Source of Ashmem library used in this app: [android-shmem](https://github.com/pelya/android-shmem)



## Reference

1. [GNURootDebian](https://github.com/corbinlc/GNURootDebian)
2. [debian-noroot](https://github.com/pelya/debian-noroot)
3. [termux-ubuntu](https://github.com/Neo-Oli/termux-ubuntu)
