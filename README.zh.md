这里是主程序使用的库。如果你在找程序中使用的图片或脚本，请点击[这里](https://github.com/EXALAB/AnLinux-Resources)

# AnLinux
AnLinux允许你在安卓系统中免root运行Linux系统。感谢[Termux](https://github.com/termux/termux-app)和[PRoot](https://github.com/proot-me/PRoot)让这个项目成为可能。

在Google Play上下载

<a href='https://play.google.com/store/apps/details?id=exa.lnx.a'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="100"/></a>

（中国大陆不能使用）

或者

在Github上下载apk

[<img src="https://user-images.githubusercontent.com/663460/26973090-f8fdc986-4d14-11e7-995a-e7c5e79ed925.png" alt="Download APK from GitHub" height="100">](https://github.com/EXALAB/AnLinux-App/releases/latest) 

如果你不能访问Google Play。

注意：使用该软件需要连接到raw.github.com并下载文件。从Github上下载安装并不意味着你不需要梯子或其他手段就可以正常使用该软件。



## 它怎么工作？

bash脚本从网上下载系统镜像，然后解压并用[PRoot](https://github.com/proot-me/PRoot)挂载它。



## 桌面环境

我们目前支持5种桌面环境，只有部分发行版可用。

支持的桌面环境：

1. [Xfce4](https://xfce.org)
2. [Mate](https://mate-desktop.org)
3. [LXQt](https://lxqt.org)
4. [LXDE](https://lxde.org)
5. [KDE](https://kde.org)



## 窗口管理器

我们目前支持2种窗口管理器，而且只有[Ubuntu](https://www.ubuntu.com/)、[Debian](https://www.debian.org/)、[Kali](https://www.kali.org/)、[Parrot Security OS](https://www.parrotsec.org/)、[Fedora](https://getfedora.org/)的发行版受支持。

支持的窗口管理器：

1. [Awesome](https://awesomewm.org)
2. [IceWM](https://ice-wm.org/)



## 未来的目标：

1. KDE和GNOME的系统环境支持(也许还会测试其他不可用的桌面)。
2. 音频支持。
3. 修复PRoot的错误。
4. 可能考虑BSD发行版的支持。



如果你有任何改进方法，建议，推荐，请在Github上发一个issue。



## 注意

1. 这个应用需要[Termux](https://github.com/termux/termux-app)的支持，你可以从Google Play（中国大陆不能使用）或者[F-Droid](https://f-droid.org)下载它。

2. 设备需求：

   安卓5或以上

   系统架构：armv7, arm64, x86, x86_64

3. 目前支持的发行版：

   [Ubuntu](https://www.ubuntu.com/), [Debian](https://www.debian.org/), [Kali](https://www.kali.org/), [Kali Nethunter](https://www.kali.org/kali-linux-nethunter/), [Parrot Security OS](https://www.parrotsec.org/), [BackBox](https://www.backbox.org/), [Fedora](https://getfedora.org/), [CentOS](https://www.centos.org/), [openSUSE Leap](https://www.opensuse.org/), [openSUSE Tumbleweed](https://www.opensuse.org/), [Arch Linux](https://www.archlinux.org/), [BlackArch](https://blackarch.org/), [Alpine](https://alpinelinux.org/), [Void](https://voidlinux.org/)

4. 有任何问题或建议，请在Github上发issue。

5. 由于找不到/dev/tty0的错误，窗口管理器已经被移除。



## 其他许可

应用图标的作者是[Alpár-Etele Méder](https://www.iconfinder.com/pocike)

程序中用到的Ashmem库：[android-shmem](https://github.com/pelya/android-shmem)



## 参考

1. [GNURootDebian](https://github.com/corbinlc/GNURootDebian)
2. [debian-noroot](https://github.com/pelya/debian-noroot)
3. [termux-ubuntu](https://github.com/Neo-Oli/termux-ubuntu)
