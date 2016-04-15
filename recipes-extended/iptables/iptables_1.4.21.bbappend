FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://iptables.ipv4.nat "


do_install_append() {
    install -m 0644 -D ${WORKDIR}/iptables.ipv4.nat ${D}${sysconfdir}/iptables.ipv4.nat
}
