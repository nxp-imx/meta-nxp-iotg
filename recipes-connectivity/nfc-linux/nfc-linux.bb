LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=c11edb8caa295e4fcd84f8520efc0d8a"

SRC_URI = "git://github.com/NXPNFCLinux/linux_libnfc-nci.git;protocol=https;branch=master"
SRCREV ="${AUTOREV}"

S = "${WORKDIR}/git"

do_configure_prepend() {
}

INSANE_SKIP_${PN} += "dev-deps"

inherit autotools
