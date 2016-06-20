HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://README;md5=00ca86bea3d1f904366f1b0b68edd14e"
DEPENDS = "libnl openssl"
DESCRIPTION = "User space daemon for extended IEEE 802.11 management"

inherit update-rc.d
INITSCRIPT_NAME = "hostapd"


DEFAULT_PREFERENCE = "-1"

SRC_URI = " \
    http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
    file://defconfig \
	file://hostapd.conf \
	file://access-point.service \
	file://usbreset \
	file://reset-usbwifi \
"

S = "${WORKDIR}/hostapd-${PV}/hostapd"


do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/.config
}

do_compile() {
    export CFLAGS="-MMD -O2 -Wall -g -I${STAGING_INCDIR}/libnl3"
    make
}

do_install() {
    install -d ${D}${sbindir} ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}
    install -m 0755 ${S}/hostapd ${D}${sbindir}
    install -m 0755 ${S}/hostapd_cli ${D}${sbindir}

	install -d -m 0755 ${D}/lib/systemd/system
	install -m 0644 ${WORKDIR}/access-point.service ${D}/lib/systemd/system

	install -m 0755 ${WORKDIR}/usbreset ${D}${sbindir}
	install -m 0755 ${WORKDIR}/reset-usbwifi ${D}${sbindir}
}

FILES_${PN} += "/lib/systemd/system/access-point.service"

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"

SRC_URI[md5sum] = "ba22e639bc57aa4035d2ea8ffa9bbbee"
SRC_URI[sha256sum] = "262ce394b930bccc3d65fb99ee380f28d36444978f524c845a98e8e29f4e9d35"
