# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
  file://ftpget.cfg \
  file://udhcpd.conf \
  file://udhcpd.leases \
  file://udhcpd.service \
"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf
    install -d ${D}${localstatedir}/lib/misc
	install -m 0644 ${WORKDIR}/udhcpd.leases ${D}${localstatedir}/lib/misc/udhcpd.leases
	install -d -m 0755 ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/udhcpd.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}/system/udhcpd.service"
