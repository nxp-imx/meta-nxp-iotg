FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://NetworkManager.conf "

do_install_append () {
	if [ -e "${WORKDIR}/NetworkManager.conf" ]; then
		install -d ${D}${sysconfdir}/NetworkManager
		install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/
	fi
}

PACKAGECONFIG[systemd] = " \
	 --with-systemdsystemunitdir=${systemd_unitdir}/system --enable-polkit, \
	 polkit \
"
