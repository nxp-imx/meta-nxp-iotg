SUMMARY = "Python CoAP library for Twisted framework"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "https://github.com/siskin/txThings/archive/master.zip \
           file://COPYING.MIT"
S = "${WORKDIR}/txThings-master"

inherit setuptools

RDEPENDS_${PN} = "\
    python-twisted \
"

SRC_URI[md5sum] = "d5db0193358c7cc48497cb6f6c1fc036"
SRC_URI[sha256sum] = "e2bd34d97d99a3e4b13f368941696beda3f362a1e4076bca9167562a54d9585c"
