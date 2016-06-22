SUMMARY = "Python CoAP library for Twisted framework"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "git://github.com/mwasilak/${PN}.git \
           file://COPYING.MIT"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit setuptools

RDEPENDS_${PN} = "\
    python-twisted \
    python-ipaddress \
"

