# Remove connman dependency.
#
# Unfortunately to remove items, this must be kept up-to-date with
# poky/meta/recipes-core/packagegroups/packagegroup-core-tools-testapps.bb.

RDEPENDS_${PN} = "\
    blktool \
    tslib-calibrate \
    tslib-tests \
    lrzsz \
    ${KEXECTOOLS} \
    alsa-utils-amixer \
    alsa-utils-aplay \
    gst-meta-video \
    gst-meta-audio \
    ltp \
    ${@base_contains('DISTRO_FEATURES', 'x11', "${X11TOOLS}", "", d)} \
    ${@base_contains('DISTRO_FEATURES', 'x11 opengl', "${X11GLTOOLS}", "", d)} \
    ${@base_contains('DISTRO_FEATURES', '3g', "${3GTOOLS}", "", d)} \
    "
