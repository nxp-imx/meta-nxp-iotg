# Copyright (C) 2016 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "NXP IoT Gateway BSP package group"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    bridge-utils \
    hostap-daemon \
    connman \
    connman-client \
    smcroute \
    radvd \
    crda \
    texinfo \
    libftdi \
    libnss-mdns \
    libudev \
    gst-rtsp \
    python-core \
    python-pip \
    python-pytun \
    txthings \
    ntp \
    nodejs \
    nodejs-npm \
    man \
    man-pages \
    dnsmasq \
    polkit-group-rule-network \
    polkit-group-rule-datetime \
    rpm \
    rsyslog \
    tcpdump \
    vim \
    xuser-account \
    opensc \
    tslib \
    tslib-calibrate \
    tslib-tests \
    apt \
    dpkg \
"
