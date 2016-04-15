FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = " file://80xrandr.sh "

do_install_append() {
	install -d ${D}${sysconfdir}/X11/Xsession.d
	install -m 0755 ${WORKDIR}/80xrandr.sh ${D}${sysconfdir}/X11/Xsession.d
}
