DESCRIPTION = "NXP IoT Gateway Image"
LICENSE = "MIT"

inherit core-image distro_features_check

IMAGE_FEATURES += " \
    tools-sdk \
    dbg-pkgs \
    dev-pkgs \
    tools-debug \
    eclipse-debug \
    tools-profile \
    debug-tweaks \
    ssh-server-openssh \
    "

WEB = "web-webkit"

IMAGE_INSTALL += " \
    ${CORE_IMAGE_BASE_INSTALL} \
    kernel-dev \
    packagegroup-core-full-cmdline \
    packagegroup-nxp-iotg \
    packagegroup-tools-bluetooth \
"

export IMAGE_BASENAME = "nxp-image-iotg"
