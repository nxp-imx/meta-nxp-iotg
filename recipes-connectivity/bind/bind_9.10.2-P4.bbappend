# Append path for freescale layer to include bsp tslib fixes
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://bind9 "

do_install_append () {
	if [ -e "${WORKDIR}/bind9" ]; then
		install -d ${D}${sysconfdir}/default
		install -m 0644 ${WORKDIR}/bind9 ${D}${sysconfdir}/default/
	fi
}
