SUMMARY = "AllSeen Alliance Open Source Project"
DESCRIPTION = "Build the AllJoyn project and add a service to start the AllJoyn Daemon on system startup"
SECTION = "libs/network"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://core/alljoyn/SConstruct;beginline=1;endline=20;md5=30ef187e49afb790bdb36da8751037bb"

require alljoyn.inc

PR = "r0"

SRC_URI = " https://allseenalliance.org/releases/alljoyn/${PV}/alljoyn-services-${PV}.00-src.tar.gz;name=services \
            https://allseenalliance.org/releases/alljoyn/${PV}/alljoyn-${PV}.00b-src.tar.gz;name=core \
            https://allseenalliance.org/releases/alljoyn/${PV}/alljoyn-ddapi-v${PV}-src.tar.gz;name=ddapi \
            https://allseenalliance.org/releases/alljoyn/${PV}/ajtcl-${PV}.00-src.tar.gz;name=tcl \
            https://allseenalliance.org/releases/alljoyn/${PV}/AllJoynCodeGenSetup-${PV}.tar.gz;name=cg \
            https://git.allseenalliance.org/cgit/lighting/service_framework.git/snapshot/service_framework-14.12.zip;name=lsf \
            git://git.allseenalliance.org/gerrit/services/base_tcl.git;protocol=http;branch=RB14.12;name=basetcl \
            file://alljoyn.service \
            file://lsf-controller.service \
            file://add-lsf-to-build.patch;patchdir=${WORKDIR}/alljoyn-${PV}.00b-src \
            file://fix-cross-compile.patch;patchdir=${WORKDIR}/alljoyn-${PV}.00b-src \
            file://service-framework.patch;patchdir=${WORKDIR}/service_framework-14.12 \
            file://lighting-controller-client.patch;patchdir=${WORKDIR}/service_framework-14.12 \
           "

SRCREV_basetcl = "c0867c9bd03680197a811ee462247eaaed7011cb"

SRC_URI[core.md5sum] = "74ed192672f4330022f71c6f72095690"
SRC_URI[core.sha256sum] = "06f41f37e231556dc953d18fd511f808c163a1aa36aa159f83b6d2674bfebac4"
SRC_URI[services.md5sum] = "389c2623619281e6849da25cee7e1bb7"
SRC_URI[services.sha256sum] = "a2c51e468209f6b9708beb8bacd0e7405f0bc42f6c049a951fcc58b96ae98e70"
SRC_URI[ddapi.md5sum] = "d00dde013e00cdc953325f93dd1bf205"
SRC_URI[ddapi.sha256sum] = "dbd7d034609759d4e4c586fb0ed62fe0854fc7049fdaba199895fff9469d5c4f"
SRC_URI[tcl.md5sum] = "fbcfa3236852fc645ea5ec01d451cda8"
SRC_URI[tcl.sha256sum] = "02bd6c84149f07d5804acdbf2560bbb67f1ae11c38a1ee7522fedaf78b3cced8"
SRC_URI[cg.md5sum] = "2c4dbf2b0d8808c0d7635d304c67cbd0"
SRC_URI[cg.sha256sum] = "f7eaf03e3f214e1c0493ee299a8d19ca7b640c463ed4503a30768ee44f6ca57f"
SRC_URI[cg.sha256sum] = "f7eaf03e3f214e1c0493ee299a8d19ca7b640c463ed4503a30768ee44f6ca57f"
SRC_URI[lsf.md5sum] = "efae46a5579494dc5f5ed227965dced3"
SRC_URI[lsf.sha256sum] = "e02dcbf339ff76d6736593254aa5ab3b85036c77af41feec934cb3e0b21cf6fe"

do_configure_prepend() {
	# Create the source folder from the various packages
    mkdir -p ${S}/core/alljoyn
    cp -a ${WORKDIR}/alljoyn-${PV}.00b-src/* ${S}/core/alljoyn
    cp -a ${WORKDIR}/alljoyn-services-${PV}.00-src/services ${S}
    mkdir -p ${S}/core/ajtcl
    cp -a ${WORKDIR}/ajtcl-${PV}.00-src/* ${S}/core/ajtcl
    mkdir -p ${S}/devtools/codegen
    cp -a  ${WORKDIR}/AllJoynCodeGenSetup-${PV}/* ${S}/devtools/codegen
    mkdir -p ${S}/data
    cp -a  ${WORKDIR}/alljoyn-ddapi-v${PV}-src/data/* ${S}/data
    mkdir -p ${S}/core/service_framework
    cp -a  ${WORKDIR}/service_framework-14.12/* ${S}/core/service_framework
    mkdir -p ${S}/services/base_tcl
    cp -a  ${WORKDIR}/git/* ${S}/services/base_tcl
}

do_compile_prepend() {
    cd ${S}/core/alljoyn
}

do_compile_append() {
    cd ${S}
}

ALLJOYN_DISTDIR = "${S}/core/alljoyn/build/${OS}/${CPU}/${VARIANT}/dist"

do_install() {

    # Install all libs
    install -d ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/c/lib liballjoyn_c ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/cpp/lib liballjoyn ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/cpp/lib liballjoyn_about ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/config/lib liballjoyn_config ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/controlpanel/lib liballjoyn_controlpanel ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/notification/lib liballjoyn_notification ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/onboarding/lib liballjoyn_onboarding ${D}${libdir}
    oe_libinstall -so -C ${ALLJOYN_DISTDIR}/services_common/lib liballjoyn_services_common ${D}${libdir}
    oe_libinstall -a -C ${S}/core/alljoyn/build/${OS}/standard_core_library/lighting_controller_client/lib liblighting_controller_client ${D}${libdir}

    # Install C tools and sample apps
    install -d ${D}${prefix_nativesdk}/alljoyn/c/bin
    cp -a ${ALLJOYN_DISTDIR}/c/bin/* ${D}${prefix_nativesdk}/alljoyn/c/bin

    install -d ${D}${prefix_nativesdk}/alljoyn/c/samples
    cp -a ${ALLJOYN_DISTDIR}/c/samples/* ${D}${prefix_nativesdk}/alljoyn/c/samples

    # Install core tools and sample apps
    install -d ${D}${prefix_nativesdk}/alljoyn/cpp/bin
    cp -a ${ALLJOYN_DISTDIR}/cpp/bin/* ${D}${prefix_nativesdk}/alljoyn/cpp/bin

    install -d ${D}${prefix_nativesdk}/alljoyn/cpp/samples
    cp -a ${ALLJOYN_DISTDIR}/cpp/samples/* ${D}${prefix_nativesdk}/alljoyn/cpp/samples

    # Install Config service framework
    install -d ${D}${prefix_nativesdk}/alljoyn/config/bin
    cp -a ${ALLJOYN_DISTDIR}/config/bin/* ${D}${prefix_nativesdk}/alljoyn/config/bin

    # Install Control Panel service framework
    install -d ${D}${prefix_nativesdk}/alljoyn/controlpanel/bin
    cp -a ${ALLJOYN_DISTDIR}/controlpanel/bin/* ${D}${prefix_nativesdk}/alljoyn/controlpanel/bin

    # Install Notification service framework
    install -d ${D}${prefix_nativesdk}/alljoyn/notification/bin
    cp -a ${ALLJOYN_DISTDIR}/notification/bin/* ${D}${prefix_nativesdk}/alljoyn/notification/bin

    # Install Onboarding service framework
    install -d ${D}${prefix_nativesdk}/alljoyn/onboarding/bin
    cp -a ${ALLJOYN_DISTDIR}/onboarding/bin/* ${D}${prefix_nativesdk}/alljoyn/onboarding/bin

    # Install Lighting service
    install -d ${D}${prefix_nativesdk}/alljoyn/lighting/standard_core_library/lighting_controller_client/samples
    install -v ${S}/core/alljoyn/build/${OS}/standard_core_library/lighting_controller_client/samples/* ${D}${prefix_nativesdk}/alljoyn/lighting/standard_core_library/lighting_controller_client/samples

    install -d ${D}${prefix_nativesdk}/alljoyn/lighting/standard_core_library/lighting_controller_service/bin
    install -v ${S}/core/alljoyn/build/${OS}/standard_core_library/lighting_controller_service/bin/lighting_controller_service ${D}${prefix_nativesdk}/alljoyn/lighting/standard_core_library/lighting_controller_service/bin

    install -d ${D}${prefix_nativesdk}/alljoyn/lighting/thin_core_library/lamp_service/bin
    install -v ${S}/core/alljoyn/build/${OS}/thin_core_library/lamp_service/bin/lamp_service ${D}${prefix_nativesdk}/alljoyn/lighting/thin_core_library/lamp_service/bin
    install -v ${S}/core/alljoyn/build/${OS}/thin_core_library/lamp_service/bin/LaunchLampServices.sh ${D}${prefix_nativesdk}/alljoyn/lighting/thin_core_library/lamp_service/bin

    # Install all include files
    install -d ${D}${includedir}/qcc/posix
    install -v ${ALLJOYN_DISTDIR}/cpp/inc/qcc/posix/*.h ${D}${includedir}/qcc/posix
    install -v ${ALLJOYN_DISTDIR}/cpp/inc/qcc/*.h ${D}${includedir}/qcc

    install -d ${D}${includedir}/alljoyn-c
    install -v ${ALLJOYN_DISTDIR}/c/inc/alljoyn_c/*.h ${D}${includedir}/alljoyn-c

    install -d ${D}${includedir}/alljoyn
    cp -a ${ALLJOYN_DISTDIR}/cpp/inc/alljoyn/* ${D}${includedir}/alljoyn
    cp -a ${ALLJOYN_DISTDIR}/notification/inc/alljoyn/* ${D}${includedir}/alljoyn
    cp -a ${ALLJOYN_DISTDIR}/onboarding/inc/alljoyn/* ${D}${includedir}/alljoyn
    cp -a ${ALLJOYN_DISTDIR}/services_common/inc/alljoyn/* ${D}${includedir}/alljoyn
    cp -a ${ALLJOYN_DISTDIR}/config/inc/alljoyn/* ${D}${includedir}/alljoyn
    cp -a ${ALLJOYN_DISTDIR}/controlpanel/inc/alljoyn/* ${D}${includedir}/alljoyn

    # Install Lighting service framework include files
    install -d ${D}${includedir}/alljoyn/lighting/common/inc
    cp -a ${S}/core/service_framework/common/inc/* ${D}${includedir}/alljoyn/lighting/common/inc

    install -d ${D}${includedir}/alljoyn/lighting/standard_core_library/lighting_controller_service/inc
    cp -a ${S}/core/alljoyn/build/${OS}/standard_core_library/lighting_controller_service/inc/* ${D}${includedir}/alljoyn/lighting/standard_core_library/lighting_controller_service/inc

    install -d ${D}${includedir}/alljoyn/lighting/standard_core_library/lighting_controller_client/inc
    cp -a ${S}/core/alljoyn/build/${OS}/standard_core_library/lighting_controller_client/inc/* ${D}${includedir}/alljoyn/lighting/standard_core_library/lighting_controller_client/inc

    install -d ${D}${includedir}/alljoyn/lighting/thin_core_library/lamp_service/inc
    cp -a ${S}/core/alljoyn/build/${OS}/thin_core_library/lamp_service/inc/* ${D}${includedir}/alljoyn/lighting/thin_core_library/lamp_service/inc

    # Install systemd service
    install -d -m 0755 ${D}/lib/systemd/system
    install -v ${WORKDIR}/alljoyn.service ${D}/lib/systemd/system
    install -v ${WORKDIR}/lsf-controller.service ${D}/lib/systemd/system
}

FILES_${PN} += " ${prefix_nativesdk} \
    /lib/systemd/system/* \
    ${includedir} \
    ${libdir} \
"

FILES_${PN}-dbg += " ${libdir}/.debug \
    ${prefix_nativesdk}/alljoyn/c/bin/.debug \
    ${prefix_nativesdk}/alljoyn/c/bin/samples/.debug \
    ${prefix_nativesdk}/alljoyn/cpp/bin/.debug \
    ${prefix_nativesdk}/alljoyn/cpp/bin/samples/.debug \
    ${prefix_nativesdk}/alljoyn/config/bin/.debug \
    ${prefix_nativesdk}/alljoyn/controlpanel/bin/.debug \
    ${prefix_nativesdk}/alljoyn/notification/bin/.debug \
    ${prefix_nativesdk}/alljoyn/onboarding/bin/.debug \
    ${prefix_nativesdk}/alljoyn/lighting/standard_core_library/lighting_controller_service/bin/.debug \
    ${prefix_nativesdk}/alljoyn/lighting/standard_core_library/lighting_controller_client/samples/.debug \
    ${prefix_nativesdk}/alljoyn/lighting/thin_core_library/lamp_service/bin/.debug \
"