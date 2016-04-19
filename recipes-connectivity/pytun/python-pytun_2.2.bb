SUMMARY = "Python TUN/TAP tunnul module"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=13f7629e8e4989b66b4a913ab05a91de"

SRC_URI = "https://pypi.python.org/packages/source/p/${PN}/${PN}-${PV}.tar.gz"

inherit setuptools

SRC_URI[md5sum] = "e2d080f236879e27fc1a43603aaabb5d"
SRC_URI[sha256sum] = "60de9001a568a5667a8b754461e165820e2256a3bf5907d1d474e22a4d94899a"
