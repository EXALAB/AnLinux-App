This is repo for the main application. If you are looking for the images and scripts used in this app, please visit [here](https://github.com/EXALAB/AnLinux-Resources)

# AnLinux
Run Linux On Android Without Root Access, thanks for the Awesome [Termux](https://github.com/termux/termux-app) and [PRoot](https://github.com/proot-me/PRoot), which make this project possible.

You can find the application on Play Store [here](https://play.google.com/store/apps/details?id=exa.lnx.a), or download it from Github if you don't have access to Play Store.



## How it works

The bash script download image over internet, then decompress the image and then mount it using [PRoot](https://github.com/proot-me/PRoot).



## Desktop Environment

We currently support 4 Desktop Environments, and only [Ubuntu](https://www.ubuntu.com/), [Debian](https://www.debian.org/), [Kali](https://www.kali.org/), [Parrot Security OS](https://www.parrotsec.org/), [Fedora](https://getfedora.org/), [Arch Linux](https://www.archlinux.org/) distros are supported.

Supported Desktop Environments:

1. [Xfce4](https://xfce.org)
2. [Mate](https://mate-desktop.org)
3. [LXQt](https://lxqt.org)
4. [LXDE](https://lxde.org)

If you have any tweak, suggestion, recommendation, please open an issue on Github.



## Note

1. This app requires [Termux](https://github.com/termux/termux-app) to work, it can be install from Play Store or from [F-Droid](https://f-droid.org) if you don't have access to Play Store

2. About device requirements:

   Android Version : At least Android Lollipop

   Architecture : armv7, arm64, x86, x86_64

3. Currently supported distros:

   [Ubuntu](https://www.ubuntu.com/), [Debian](https://www.debian.org/), [Kali](https://www.kali.org/), [Parrot Security OS](https://www.parrotsec.org/), [Fedora](https://getfedora.org/), [CentOS](https://www.centos.org/), [openSUSE Leap](https://www.opensuse.org/), [openSUSE Tumbleweed](https://www.opensuse.org/), [Arch Linux](https://www.archlinux.org/), [BlackArch](https://blackarch.org/)

5. For any suggestion or issue, please open an issue on Github.



## Extra License

The author of application icon is [Alpár-Etele Méder](https://www.iconfinder.com/pocike)

Source of Ashmem library used in this app: [android-shmem](https://github.com/pelya/android-shmem)



## Todo

1. [FreeBSD](https://www.freebsd.org/), [Slackware](http://www.slackware.com/), [Manjaro](https://manjaro.org/) support



## Reference

1. [GNURootDebian](https://github.com/corbinlc/GNURootDebian)
2. [debian-noroot](https://github.com/pelya/debian-noroot)
3. [termux-ubuntu](https://github.com/Neo-Oli/termux-ubuntu)
