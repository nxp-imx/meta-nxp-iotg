SUMMARY = "Static Multicast Routing Daemon"
DESCRIPTION = "SMCRoute is a daemon and command line tool to manipulate the multicast routing table in the UNIX kernel."
HOMEPAGE = "http://troglobit.github.io/smcroute.html"
SECTION = "net"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRCREV = "9324e3ed6d49e0d14a84de3957abec2d8835ad3d"
SRC_URI = "git://github.com/troglobit/smcroute.git;branch=master;protocol=git"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--enable-ipv6"
