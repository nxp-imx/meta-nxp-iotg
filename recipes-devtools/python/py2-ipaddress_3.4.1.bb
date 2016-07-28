SUMMARY = "Python 2.6 backport of the Python 3.4 ipaddress module."
HOMEPAGE = "https://bitbucket.org/kwi/py2-ipaddress/"
LICENSE = "Python-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=b8f6b0182a64c5078ee13bbfb653b85e"

DEPENDS += "python-pip"

SRC_URI = "https://pypi.python.org/packages/06/f2/ff20f2d2fd4757be329c8ecb81e9e7fa3bec0b65445821e3a575410cf194/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "47734313c841068e3d5386d048d01c3d"
SRC_URI[sha256sum] = "6d7bf02ac2590764691bf50ac213e966bc885ed37c02606513dcac484190564b"

inherit pypi setuptools
