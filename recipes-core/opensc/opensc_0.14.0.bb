LICENSE = "LGPL2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "http://sourceforge.net/projects/opensc/files/OpenSC/opensc-${PV}/opensc-${PV}.tar.gz \
	   "
SRC_URI[md5sum] = "8e99885dbe28a9c71d5140f0105c56ff"
SRC_URI[sha256sum] = "facdca215f74d999b286ae246ada8d8fcb97ce58f0a6dd30d8b1c180101e9bf0"

inherit autotools pkgconfig

DEPENDS = "pcsc-lite openssl"

EXTRA_OECONF = "--enable-pcsc=yes"

FILES_${PN}-dev += " \
    ${libdir}/pkcs11-spy.so \
    ${libdir}/opensc-pkcs11.so \
    ${libdir}/onepin-opensc-pkcs11.so \
    ${libdir}/pkcs11/pkcs11-spy.so \
    ${libdir}/pkcs11/opensc-pkcs11.so \
    ${libdir}/pkcs11/onepin-opensc-pkcs11.so \
"

FILES_${PN}-dbg += "${libdir}/pkcs11/.debug"
