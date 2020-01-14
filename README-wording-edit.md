<div class="Box-body">
    <article class="markdown-body entry-content p-5" itemprop="text"><p>This is repo for the main application. If you are looking for the images and scripts used in this app, please visit <a href="https://github.com/EXALAB/AnLinux-Resources">here</a></p>
<h1><a id="user-content-anlinux" class="anchor" aria-hidden="true" href="#anlinux"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>AnLinux</h1>
<p>Run Linux On Android Without Root Access, thanks for the Awesome <a href="https://github.com/termux/termux-app">Termux</a> and <a href="https://github.com/proot-me/PRoot">PRoot</a>, which make this project possible.</p>
<p><a href="https://play.google.com/store/apps/details?id=exa.lnx.a" rel="nofollow"><img alt="Get it on Google Play" src="https://camo.githubusercontent.com/59c5c810fc8363f8488c3a36fc78f89990d13e99/68747470733a2f2f706c61792e676f6f676c652e636f6d2f696e746c2f656e5f75732f6261646765732f696d616765732f67656e657269632f656e5f62616467655f7765625f67656e657269632e706e67" height="100" data-canonical-src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" style="max-width:100%;"></a></p>
<p>Or</p>
<p><a href="https://github.com/EXALAB/AnLinux-App/releases/latest"><img src="https://user-images.githubusercontent.com/663460/26973090-f8fdc986-4d14-11e7-995a-e7c5e79ed925.png" alt="Download APK from GitHub" height="100" style="max-width:100%;"></a></p>
<p>if you don't have access to Play Store.</p>
<h2><a id="user-content-how-it-works" class="anchor" aria-hidden="true" href="#how-it-works"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>How it works</h2>
<p>The bash script download image over internet, then decompress the image and then mount it using <a href="https://github.com/proot-me/PRoot">PRoot</a>.</p>
<h2><a id="user-content-desktop-environment" class="anchor" aria-hidden="true" href="#desktop-environment"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Desktop Environment</h2>
<p>We currently support 4 Desktop Environments, and only some distros are supported.</p>
<p>Supported Desktop Environments:</p>
<ol>
<li><a href="https://xfce.org" rel="nofollow">Xfce4</a></li>
<li><a href="https://mate-desktop.org" rel="nofollow">Mate</a></li>
<li><a href="https://lxqt.org" rel="nofollow">LXQt</a></li>
<li><a href="https://lxde.org" rel="nofollow">LXDE</a></li>
</ol>
<h2><a id="user-content-window-manager" class="anchor" aria-hidden="true" href="#window-manager"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Window Manager</h2>
<p>We currently support 2 Window Managers, and only <a href="https://www.ubuntu.com/" rel="nofollow">Ubuntu</a>, <a href="https://www.debian.org/" rel="nofollow">Debian</a>, <a href="https://www.kali.org/" rel="nofollow">Kali</a>, <a href="https://www.parrotsec.org/" rel="nofollow">Parrot Security OS</a>, <a href="https://getfedora.org/" rel="nofollow">Fedora</a> distros are supported.</p>
<p>Supported Window Managers:</p>
<ol>
<li><a href="https://awesomewm.org" rel="nofollow">Awesome</a></li>
<li><a href="https://ice-wm.org/" rel="nofollow">IceWM</a></li>
</ol>
<h2><a id="user-content-future-target-and-todo" class="anchor" aria-hidden="true" href="#future-target-and-todo"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Future target and todo:</h2>
<ol>
<li>KDE and GNOME DE support. (Might retest other blacklisted DE too)</li>
<li>Audio support.</li>
<li>Fix PRoot error.</li>
<li>Might consider support BSD distro.</li>
</ol>
<p>If you have any tweak, suggestion, recommendation, please open an issue on Github.</p>
<h2><a id="user-content-note" class="anchor" aria-hidden="true" href="#note"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Note</h2>
<ol>
<li>
<p>This app requires <a href="https://github.com/termux/termux-app">Termux</a> to work, it can be install from Play Store or from <a href="https://f-droid.org" rel="nofollow">F-Droid</a> if you don't have access to Play Store</p>
</li>
<li>
<p>About device requirements:</p>
<p>Android Version : At least Android Lollipop</p>
<p>Architecture : armv7, arm64, x86, x86_64</p>
</li>
<li>
<p>Currently supported distros:</p>
<p><a href="https://www.ubuntu.com/" rel="nofollow">Ubuntu</a>, <a href="https://www.debian.org/" rel="nofollow">Debian</a>, <a href="https://www.kali.org/" rel="nofollow">Kali</a>, <a href="https://www.kali.org/kali-linux-nethunter/" rel="nofollow">Kali Nethunter</a>, <a href="https://www.parrotsec.org/" rel="nofollow">Parrot Security OS</a>, <a href="https://www.backbox.org/" rel="nofollow">BackBox</a>, <a href="https://getfedora.org/" rel="nofollow">Fedora</a>, <a href="https://www.centos.org/" rel="nofollow">CentOS</a>, <a href="https://www.opensuse.org/" rel="nofollow">openSUSE Leap</a>, <a href="https://www.opensuse.org/" rel="nofollow">openSUSE Tumbleweed</a>, <a href="https://www.archlinux.org/" rel="nofollow">Arch Linux</a>, <a href="https://blackarch.org/" rel="nofollow">BlackArch</a>, <a href="https://alpinelinux.org/" rel="nofollow">Alpine</a></p>
</li>
<li>
<p>For any suggestion or issue, please open an issue on Github.</p>
</li>
<li>
<p>The support of Windows Manager has been removed, because of /dev/tty0 could not be found error.</p>
</li>
</ol>
<h2><a id="user-content-extra-license" class="anchor" aria-hidden="true" href="#extra-license"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Extra License</h2>
<p>The author of application icon is <a href="https://www.iconfinder.com/pocike" rel="nofollow">Alpár-Etele Méder</a></p>
<p>Source of Ashmem library used in this app: <a href="https://github.com/pelya/android-shmem">android-shmem</a></p>
<h2><a id="user-content-reference" class="anchor" aria-hidden="true" href="#reference"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Reference</h2>
<ol>
<li><a href="https://github.com/corbinlc/GNURootDebian">GNURootDebian</a></li>
<li><a href="https://github.com/pelya/debian-noroot">debian-noroot</a></li>
<li><a href="https://github.com/Neo-Oli/termux-ubuntu">termux-ubuntu</a></li>
</ol>
</article>
      </div>
  </div>



  </div>
</div>

